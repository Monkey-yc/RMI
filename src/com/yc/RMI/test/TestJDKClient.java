package com.yc.RMI.test;

import com.yc.RMI.core.RMIClient;
import com.yc.RMI.model.IUserAction;
import com.yc.RMI.model.Usermodel;

public class TestJDKClient {
    public static void main(String[] args) {
        RMIClient rmiClient = new RMIClient("192.168.1.168", 54199);

        try {
            IUserAction JDKProxy = rmiClient.getProxy(IUserAction.class);
        	Usermodel JDKuser = JDKProxy.getUserById("12138");
            System.out.println("JDKuser    " + JDKuser);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
