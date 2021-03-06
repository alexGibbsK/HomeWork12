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
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, InvocationTargetException, InterruptedException {
        SomeClass someClass = new SomeClass();
        Class myClass = someClass.getClass();
        Method[] methods = myClass.getMethods();

        System.out.println("Number of threads on program start: " + ManagementFactory.getThreadMXBean().getThreadCount());

        if (myClass.isAnnotationPresent(Service.class)) {
            for (Method method :
                    methods) {
                checkAnnotation(myClass, method);
            }
        }

        System.out.println("Number of threads on program end: " + ManagementFactory.getThreadMXBean().getThreadCount());
    }

    private static void checkAnnotation(Class myClass, Method method) throws InterruptedException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Thread myThread;
        if (method.isAnnotationPresent(Init.class) && method.isAnnotationPresent(Asynk.class)) {
            System.out.println("Method must be Asynk OR Init, not both of them");
        } else if (method.isAnnotationPresent(Asynk.class)) {
            myThread = getNewThread(myClass, method);
            myThread.start();
            myThread.join();

        } else if (method.isAnnotationPresent(Init.class)) {
            method.invoke(myClass.newInstance(), null);
        }
    }

    private static Thread getNewThread(final Class myClass, final Method method) {
        return new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("\nNew Thread Started, thread id: " + Thread.currentThread().getId());
                //System.out.println("Number of threads while Asynk method runs: " + ManagementFactory.getThreadMXBean().getThreadCount());
                try {
                    method.invoke(myClass.newInstance(), null);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }finally {
                    System.out.println("Thread #" + Thread.currentThread().getId() + " finished\n");
                }
//                try {
//                    Thread.sleep(2000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println("Thread #" + Thread.currentThread().getId() + " finished\n");
            }
        });
    }
}
