package com.yc.RMI.test;

import com.yc.RMI.core.RMIClient;
import com.yc.RMI.model.UserActionSon;
import com.yc.RMI.model.Usermodel;

public class TestCGLibClient {
	public static void main(String[] args) {
		RMIClient rmiClient = new RMIClient("192.168.1.168", 54199);

		try {			      
			UserActionSon CGLibProxy = rmiClient.getProxy(UserActionSon.class);
			Usermodel CGLibuser = CGLibProxy.getUserById("12138");
	        System.out.println("CGLibuser    " + CGLibuser);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	

	}
}
