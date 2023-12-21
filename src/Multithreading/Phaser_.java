package Multithreading;

import java.util.Scanner;
import java.util.concurrent.Phaser;

public class Phaser_ {
    public static void main(String[] args){
        Phaser  phaser=new Phaser(4);
        Thread thread1=new Thread(new worker4(phaser,"Thread 1"));
        Thread thread2=new Thread(new worker4(phaser,"Thread 2"));
        Thread thread3=new Thread(new worker4(phaser,"Thread 3"));
        Thread thread4=new Thread(new worker4(phaser,"Thread 4"));
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }
    static class worker4 implements Runnable{
        private final Phaser phaser;
        private final String name;

        worker4(Phaser phaser, String name) {
            this.phaser = phaser;
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println(name+"Starting phase 1");
            phaser.arriveAndAwaitAdvance();//wait for all threads to reach the point
            //phase 1
            System.out.println(name+" phase 1 task");
            //wait for all threads to complete phase 1
            phaser.arriveAndAwaitAdvance();
            System.out.println(name + " phase 2");
            phaser.arriveAndAwaitAdvance(); // Wait for all threads to reach this point

            // Perform phase 2 tasks
            System.out.println(name + " performing phase 2 tasks");

            // Wait for all threads to complete phase 2
            phaser.arriveAndAwaitAdvance();

            System.out.println(name + " completed all phases");
            phaser.arriveAndDeregister(); // Deregister from the Phaser


        }
    }
}
