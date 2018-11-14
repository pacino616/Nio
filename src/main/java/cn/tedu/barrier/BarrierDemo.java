package cn.tedu.barrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 赛马场景
 * 有两匹赛马（两个线程）
 * 要求必须等到所有赛马到达栅栏前，才能一起跑
 */
public class BarrierDemo {
    public static void main(String[] args) {
        //创建栅栏，并分配一个初始的计数器
        CyclicBarrier barrier = new CyclicBarrier(2);
        new Thread(new Horsel(barrier)).start();
        new Thread(new Horse2(barrier)).start();
    }

}
class Horsel implements Runnable{
    private CyclicBarrier barrier;

    public Horsel(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    @Override
    public void run() {
        System.out.println("赛马1来到栅栏前");
        try {
            //此方法会产生阻塞，阻塞放开的条件是栅栏的初始计数器=0
            //并且实现计数器的递减
            barrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println("赛马1开始跑");
    }
}
class Horse2 implements Runnable{
    private CyclicBarrier barrier;

    public Horse2(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    @Override
    public void run() {
        System.out.println("赛马2正在拉肚子");
        try {
            Thread.sleep(5000);
            System.out.println("赛马2到达栅栏前");
            barrier.await();
            System.out.println("赛马2起跑");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}