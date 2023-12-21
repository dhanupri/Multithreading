package Multithreading;

import java.util.Scanner;
//Write a Java program to create and start multiple threads
// that increment a shared counter variable concurrently.
public class Create_Threads {
    public static int counter=0;

    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int numThread=sc.nextInt();//number of threads
        for (int i=0;i<numThread;i++){
            Thread thread=new Thread(new CounterIncrement());
            thread.start();
        }
    }
}
class CounterIncrement implements Runnable{
    @Override
    public void run() {
        for (int i=0;i<1000;i++){
            incrementCounter();
        }
    }
    private void incrementCounter(){
        synchronized (Create_Threads.class){
           Create_Threads.counter++;
            System.out.println("Counter value"+Create_Threads.counter+"Thread:"+Thread.currentThread());
        }
    }

}