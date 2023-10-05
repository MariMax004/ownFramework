package com.example.config;

import net.sf.cglib.proxy.Enhancer;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public class DeprecatedHandlerProxyConfigurator implements ProxyConfigurator {
    @Override
    public Object replaceWithProxyIfNeeded(Object t, Class implClass) {
        List<Method> methods = getAnnotatedMethods(implClass, Deprecated.class);
        if (implClass.isAnnotationPresent(Deprecated.class)) {

            if (implClass.getInterfaces().length == 0) {
                return Enhancer.create(implClass, new net.sf.cglib.proxy.InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        return getInvocationHandlerLogic(method, args, t);
                    }
                });
            }

            return Proxy.newProxyInstance(implClass.getClassLoader(), implClass.getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    return getInvocationHandlerLogic(method, args, t);
                }
            });
        } else if (!methods.isEmpty()) {
            for (Method method : methods) {
                return Proxy.newProxyInstance(implClass.getClassLoader(), implClass.getInterfaces(), new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        return getInvocationHandlerLogic(method, args, t);
                    }
                });
            }
        }
        return t;
    }

    private Object getInvocationHandlerLogic(Method method, Object[] args, Object t) throws
            IllegalAccessException, InvocationTargetException {
        System.out.println("********** что ж ты делаешь урод!! ");
        return method.invoke(t, args);
    }

    private List<Method> getAnnotatedMethods(Class implClass, Class<? extends Annotation> annotationClass) {
        Method[] methods = implClass.getDeclaredMethods();
        List<Method> annotatedMethods = new ArrayList<>();

        for (Method method : methods) {
            Annotation annotation = method.getAnnotation(annotationClass);
            if (annotation != null) {
                annotatedMethods.add(method);
            }
        }
        return annotatedMethods;
    }
}
