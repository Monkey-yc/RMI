package com.yc.RMI.core;

import java.lang.reflect.Method;

public class RMIMethodDefinition {
    private Class<?> klass;
    private Method method;
    private Object object;

    public RMIMethodDefinition() {
    }

    public RMIMethodDefinition(Class<?> klass, Method method, Object object) {
        this.klass = klass;
        this.method = method;
        this.object = object;
    }

    public Class<?> getKlass() {
        return klass;
    }

    public void setKlass(Class<?> klass) {
        this.klass = klass;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
