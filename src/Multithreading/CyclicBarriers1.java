package Multithreading;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarriers1 {
    private static final int num_threads=3;
    private static final CyclicBarrier barrier=new CyclicBarrier(num_threads,new BarrierAction());
    public static void main(String[] args){
        Thread[] threads=new Thread[num_threads];
        for (int i=0;i<num_threads;i++){
            threads[i]=new Thread(new Worker2());
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
    static class Worker2 implements Runnable{
        @Override
        public void run() {
            try {
                System.out.println("Thread "+Thread.currentThread().getName()+"is waiting for barrier");
                barrier.await();
            } catch (BrokenBarrierException | InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Thread"+Thread.currentThread().getName()+"has crossed barrier");
        }
    }

    static class BarrierAction implements Runnable{

        @Override
        public void run() {
            System.out.println("Barrier reached!!!");
        }
    }

}
