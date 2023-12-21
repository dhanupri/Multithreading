package Multithreading;

import java.util.concurrent.Semaphore;

public class SemaphoreUsage {
    private static final int Num_threads=5;
    private static final int num_permits=2;
    public static void main(String[] args){
        Semaphore semaphore=new Semaphore(num_permits);
        Thread[] threads=new Thread[Num_threads];
        for(int i=0;i<Num_threads;i++){
            threads[i]=new Thread(new Worker1(semaphore));
            threads[i].start();
        }
        try {
            for (Thread thread:threads){
                thread.join();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    static class Worker1 implements Runnable{
        private final Semaphore semaphore;
        public Worker1(Semaphore semaphore) {
            this.semaphore=semaphore;
        }
        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println("Thread "+Thread.currentThread().getName()+" acquired the semaphore");
                Thread.sleep(10000);
                System.out.println("Thread "+Thread.currentThread().getName()+" acquired the semaphore");

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
