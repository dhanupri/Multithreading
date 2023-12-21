package Multithreading;

import java.util.concurrent.ConcurrentLinkedQueue;

public class Concurrent_Queue {
    public static void main(String[] args){
        ConcurrentLinkedQueue<String> queue=new ConcurrentLinkedQueue<>();
        Runnable producer=()->{
            for (int i=0;i<5;i++){
                String element ="Element"+i;
                queue.add(element);
                System.out.println("Produced:"+element);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        Runnable consumer=()->{
            for (int i=0;i<5;i++){
                String element="Element"+queue.poll();
                if (element!=null){
                    System.out.println("Consumed:"+element);
                }
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        Thread producerThread=new Thread(producer);
        Thread consumerThread=new Thread(consumer);
        producerThread.start();
        consumerThread.start();
    }
}
