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
        System.out.println("INIT: Started ");
    }

    @Asynk
    public void sayHelloInNewThread(){
        System.out.println("Asynk: started");
    }

    @Asynk
    public void sayAsynkTest(){
        System.out.println("Asynk: test started");
    }

    @Asynk
    public void sayAsynkTest2(){
        System.out.println("Asynk: test2 started");
    }
}
