package com.yc.RMI.test;

import com.yc.RMI.core.RMIServer;
import com.yc.RMI.model.IUserAction;
import com.yc.RMI.model.UserAction;

public class TestServer {
    public static void main(String[] args) {
        RMIServer server = new RMIServer();
        server.setPort(54199);
        server.registryMethod(IUserAction.class, UserAction.class);

        try {
            server.startRMIServer();
        } catch (Exception e) {
			e.printStackTrace();
		}
    }


}
