package com.yc.RMI.core;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class RMIClientExecute {
    private String serverIp;
    private int serverPort;

    RMIClientExecute() {
    }

    public RMIClientExecute(String serverIp, int serverPort) {
        this.serverIp = serverIp;
        this.serverPort = serverPort;
    }

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    int getServerPort() {
        return serverPort;
    }

    void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }
    void stopExecute(Socket socket, ObjectInputStream ois, ObjectOutputStream oos) {
        try {
            if (ois != null) {
                ois.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            ois = null;
        }
        try {
            if (oos != null) {
                oos.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            oos = null;
        }
        try {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }catch(IOException e) {
            e.printStackTrace();
        } finally {
            socket = null;
        }
    }

    @SuppressWarnings("unchecked")
	<T> T execute(String methodId, Object[] paras) throws IOException, ClassNotFoundException {
        Socket socket = new Socket(serverIp, serverPort);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        
        oos.writeUTF(methodId);
        oos.writeObject(paras);
        Object result = ois.readObject();
        stopExecute(socket, ois, oos);

        return (T) result;
    }
}
