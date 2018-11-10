package com.yc.RMI.core;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class RMIServer implements Runnable{
    private ServerSocket server;
    private int port;
    private boolean goon;
    private static long executeId;
    private RMIMethodDepot rmiMethodDepot;

    public RMIServer() {
        rmiMethodDepot = new RMIMethodDepot();
        goon = false;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public RMIMethodDepot getRmiMethodFactory() {
        return rmiMethodDepot;
    }
    
    //TODO 此处可用包扫描进行注册
    
    public void registryMethod(Class<?> enterface, Object object) {
        RMIMethodFactory.registryMethod(rmiMethodDepot, enterface, object);
    }

    public void registryMethod(Class<?> enterface, Class<?> klass) {
        RMIMethodFactory.registryMethod(rmiMethodDepot, enterface, klass);
    }

    public void startRMIServer() throws Exception {
        if (port == 0) {
            //抛出异常
        	throw new Exception("端口号错误");
        }
        server = new ServerSocket(port);
        goon = true;
        new Thread(this,"RMI_SERVER").start();
    }

    public void stopRMIServer() {
        if (server != null && !server.isClosed()) {
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                server = null;
            }
        }
    }

    @Override
    public void run() {
        while (goon) {
            try {
                Socket client = server.accept();
                new RMIServerExecute(this, client, ++executeId);
            } catch (IOException e) {
                goon = false;
                e.printStackTrace();
            }
        }
        stopRMIServer();
    }
}
