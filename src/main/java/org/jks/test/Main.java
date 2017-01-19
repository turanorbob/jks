package org.jks.test;

import com.amazon.sqs.javamessaging.AmazonSQSMessagingClientWrapper;
import com.amazon.sqs.javamessaging.SQSConnection;
import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;

import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * @author liaojian
 * @version 09/19/2016
 */
public class Main {

    public static void main(String args[]) throws Exception{
        // Create the connection factory using the environment variable credential provider.
        SQSConnectionFactory connectionFactory =
                SQSConnectionFactory.builder()
                        .withRegion(Region.getRegion(Regions.CN_NORTH_1))
                        .withAWSCredentialsProvider(new EnvironmentVariableCredentialsProvider())
                        .build();

        SQSConnection connection = connectionFactory.createConnection();
        // Get the wrapped client
        AmazonSQSMessagingClientWrapper client = connection.getWrappedAmazonSQSClient();

        if (!client.queueExists("javatest")) {
            client.createQueue("javatest");
        }
        // Create the non-transacted session with AUTO_ACKNOWLEDGE mode
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // Create a queue identity with name 'TestQueue' in the session
        Queue queue = session.createQueue("javatest");

        // Create a producer for the 'TestQueue'.
        MessageProducer producer = session.createProducer(queue);

        // Create the text message.
        TextMessage message = session.createTextMessage("Hello World!");

        // Send the message.
        producer.send(message);
        System.out.println("JMS Message " + message.getJMSMessageID());
    }
}
