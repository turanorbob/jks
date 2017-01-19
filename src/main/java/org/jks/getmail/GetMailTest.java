package org.jks.getmail;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author liaojian
 * @version 10/21/2016
 */
public class GetMailTest {
    private static Logger logger = LoggerFactory.getLogger(GetMailTest.class);

    public static void main(String args[]){
        int val = 1;
        String getmailPath = "/usr/local/bin/getmail";
        String mailTmpStorage = "/tmp";
        try{
            String cmd = String.format("%s -d -g %s", getmailPath, mailTmpStorage);
            //Process process = Runtime.getRuntime().exec(new String[]{getmailPath, "-d", "-g", mailTmpStorage});
            Process process = Runtime.getRuntime().exec(cmd);
            logger.debug(IOUtils.toString(process.getInputStream()));
            //DataOutputStream os = new DataOutputStream(process.getOutputStream());
            //os.writeBytes("lj_123456");
            val = process.waitFor();
            val = process.exitValue();
            if (val > 0) {
                logger.error(IOUtils.toString(process.getErrorStream()));
            }
        }
        catch (Exception e){
            logger.error("error", e);
        }

    }
}
