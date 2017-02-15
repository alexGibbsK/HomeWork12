package main;

import annotations.Asynk;
import annotations.Init;
import annotations.Service;

import java.lang.management.ManagementFactory;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by ПК on 15.02.2017.
 */

public class Main {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        SomeClass someClass = new SomeClass();
        Class myClass = someClass.getClass();
        Method[] methods = myClass.getMethods();
        System.out.println("Start of program threads count:" + ManagementFactory.getThreadMXBean().getThreadCount());

        if (myClass.isAnnotationPresent(Service.class)) {
            for (Method method :
                    methods) {
                if (method.isAnnotationPresent(Init.class)) {
                    method.invoke(myClass.newInstance(), null);
                } else if (method.isAnnotationPresent(Asynk.class)) {
                    Thread myThread = getNewThread(myClass, method);
                    myThread.run();
                }
            }
        }

        System.out.println("End of program Threads count: " + ManagementFactory.getThreadMXBean().getThreadCount());
    }

    private static Thread getNewThread(final Class myClass, final Method method) {
        return new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("\nNew Thread Started");
                try {
                    method.invoke(myClass.newInstance(), null);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
                System.out.println("New Thread Closed\n");
            }

        });
    }
}
