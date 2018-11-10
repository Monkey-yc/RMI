package com.yc.RMI.core;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class RMIClient {
    private RMIClientExecute rmiClientExecute;

    public RMIClient(String serverIp, int serverPort) {
        rmiClientExecute = new RMIClientExecute(serverIp, serverPort);
    }

    @SuppressWarnings("unchecked")
	public <T> T getProxy(Class<?> klass) throws Exception {
    	//如果是接口，就是用JDK产生代理，否则就使用CGLib
    	if(klass.isInterface()) {
    		System.out.println("使用JDK模式产生代理");
	        return (T) Proxy.newProxyInstance(
	        		klass.getClassLoader(),
	                new Class<?>[] { klass }, 
	                new InvocationHandler() {
	                    @Override
	                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
	                        String methodId = String.valueOf(method.toString().hashCode());
	                        Object result = rmiClientExecute.execute(methodId, args);
	                        
	                        return result;
	                    }
	                });
	    } else {
	    	System.out.println("使用CGLib模式产生代理");
	    	Enhancer enhancer = new Enhancer();
			enhancer.setSuperclass(klass);
			enhancer.setCallback(new MethodInterceptor() {

				@Override
				public Object intercept(Object object, Method method, Object[] args, MethodProxy methodProxy)
						throws Throwable {
					Object result = null;

					try {
						result = methodProxy.invokeSuper(object, args);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					return result;
				}
			});

			return(T)enhancer.create(); 
	    }
    }


}
