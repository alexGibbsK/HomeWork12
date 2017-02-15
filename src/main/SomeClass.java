package main;

import annotations.Asynk;
import annotations.Init;
import annotations.Service;

/**
 * Created by ПК on 15.02.2017.
 */
@Service
public class SomeClass {
    @Init
    public void sayHello(){
        System.out.println("INIT method ");
    }

    @Asynk
    public void sayHelloInNewThread(){
        System.out.println("Asynk method");
    }

    @Asynk
    public void sayAsynkTest(){
        System.out.println("Asynk method 2");
    }

    @Init
    @Asynk
    public void sayAsynkTest2(){
        System.out.println("Asynk method 3");
    }
}
