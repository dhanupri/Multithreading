package Multithreading;

import java.util.LinkedList;
import java.util.Queue;

public class Producer_Customer {
    private static final int BUFFER_Size=10;
    private static final Queue<Integer> buffer=new LinkedList<>();
    public static void main(String[] args){
        Thread producerThread=new Thread(new Producer());
        Thread consumerThread=new Thread(new Consumer());
        producerThread.start();
        consumerThread.start();
    }
    static class Producer implements Runnable{

        @Override
        public void run() {
            int value=0;
            while (true){
                synchronized (buffer){
                    while (buffer.size()==BUFFER_Size){
                        try {
                            buffer.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    System.out.println("Producer produced "+value);
                    buffer.add(value++);
                    buffer.notify();

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }
            }

        }
    }

    static class Consumer implements Runnable{

        @Override
        public void run() {
            while (true){
                synchronized (buffer){
                    while (buffer.isEmpty()){
                        try {
                            buffer.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    int value=buffer.poll();
                    System.out.println("Consumer consumed:"+value);
                    buffer.notify();

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

        }
    }
}
