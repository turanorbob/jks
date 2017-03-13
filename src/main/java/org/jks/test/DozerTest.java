package org.jks.test;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.jks.model.UserModel;
import org.joda.time.DateTime;

/**
 * @author liaojian
 * @version 02/04/2017
 */
public class DozerTest {

    public static void main(String args[]){
        UserModel userModel = new UserModel();
        userModel.setUpdatedBy("1");
        userModel.setCreatedBy("1");
        DateTime now = DateTime.now();

        userModel.setCreatedAt(now.minus(5));
        userModel.setUpdatedAt(now);

        Mapper mapper = new DozerBeanMapper();

        System.out.println(userModel);
        UserModel userModel1 = mapper.map(userModel, UserModel.class);
        System.out.println(userModel1);
    }
}
