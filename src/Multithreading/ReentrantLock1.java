package Multithreading;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLock1 {
    private static final int num_threads=3;
    private  static final int num_iterations=5;
    public static void main(String[] args){
        Lock lock=new ReentrantLock();
        ReentrantLock1 reentrantLock1=new ReentrantLock1();

        Thread[] threads=new Thread[num_threads];
        for (int i=0;i<num_threads;i++){
            threads[i]=new Thread(new Worker(lock,reentrantLock1));
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
    static class Worker implements Runnable{
        private Lock lock;
        private ReentrantLock1 reentrantLock1;
        public Worker(Lock lock,ReentrantLock1 reentrantLock1){
            this.lock=lock;
            this.reentrantLock1=reentrantLock1;
        }
        @Override
        public void run() {
            for (int i=0;i<num_iterations;i++){
                lock.lock();
                try {
                    work.doWork();
                } finally {
                    lock.unlock();
                }
            }

        }
    }
    static class work{
        public static void doWork(){
            String threadName=Thread.currentThread().getName();
            System.out.println("Thread  :"+threadName+"is performing work");

        }
    }
}
