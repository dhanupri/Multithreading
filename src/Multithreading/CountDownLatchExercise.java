package Multithreading;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchExercise {
    private  static final int num_thread=3;
    private static final CountDownLatch start1=new CountDownLatch(1);
    public static final CountDownLatch finish=new CountDownLatch(num_thread);
    public static void main(String[] args){
        Thread[] threads=new Thread[num_thread];
        for (int i=0;i<num_thread;i++){
            threads[i]=new Thread(new Worker3());
            threads[i].start();
        }
        start1.countDown();
        try {
            finish.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("all threads have finished their work");
    }
    static class Worker3 implements Runnable{

        @Override
        public void run() {
            try {
                start1.await();
                System.out.println("Thread "+Thread.currentThread().getName()+"has finished its work");

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            finally {
                finish.countDown();
            }

        }
    }

}
