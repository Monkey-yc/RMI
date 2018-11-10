package com.yc.RMI.core;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

public class RMIServerExecute implements Runnable {
    private Socket socket;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private RMIServer rmiServer;

    RMIServerExecute() {
    }

    RMIServerExecute(RMIServer rmiServer, Socket socket, long executeId) throws IOException {
        this.socket = socket;
        this.rmiServer = rmiServer;
        oos = new ObjectOutputStream(socket.getOutputStream());
        ois = new ObjectInputStream(socket.getInputStream());
        new Thread(this, "RMI_EXECUTE" + executeId).start();;
    }

    void stopExecute() {
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

    @Override
    public void run() {
        try {
            //接受RMI客户端传递过来的ID和参数
            String  methodId= ois.readUTF();
            Object[] paras = (Object[]) ois.readObject();
            //定位相关类，方法，对象
            RMIMethodDefinition rmd =
                    rmiServer.getRmiMethodFactory().getMethod(methodId);
            //执行客户端要求执行的方法
            Method method = rmd.getMethod();
            Object object = rmd.getObject();
            Object result = method.invoke(object, paras);
            //向客户端发送执行的结果
            oos.writeObject(result);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
			stopExecute();
		}
    }
}
