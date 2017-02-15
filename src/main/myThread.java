package main;

/**
 * Created by ПК on 15.02.2017.
 */
public class myThread implements Runnable {
    int counter = 0;

    public int getCounter() {
        return counter;
    }

    @Override
    public void run() {
        counter++;
        System.out.println("Поток #" + counter + " запущен.");

    }
}
