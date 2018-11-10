package com.yc.RMI.core;

import java.lang.reflect.Method;

public class RMIMethodFactory {
    RMIMethodFactory() {
    }

    static void registryMethod(RMIMethodDepot rmiMethodDepot, Class<?> enterface, Class<?> klass) {
        try {
            doRegistry(rmiMethodDepot, enterface, klass.newInstance());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    static void registryMethod(RMIMethodDepot rmiMethodDepot, Class<?> enterface, Object object) {
        doRegistry(rmiMethodDepot, enterface, object);
    }

    static void doRegistry(RMIMethodDepot rmiMethodDepot, Class<?> enterface, Object object) {
        Method[] methods = enterface.getDeclaredMethods();
        for (Method method : methods) {
            String methodId = String.valueOf(method.toString().hashCode());
            RMIMethodDefinition rmd = new RMIMethodDefinition();
            rmd.setKlass(enterface);
            rmd.setMethod(method);
            rmd.setObject(object);
            rmiMethodDepot.addMethod(methodId, rmd);
        }
    }
}
