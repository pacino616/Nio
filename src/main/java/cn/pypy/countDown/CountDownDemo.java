package cn.pypy.countDown;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/**
 * 闭锁也叫线程递减锁，可以通过闭锁让线程挂起或继续执行
 * 主要通过CountDown（）和await()方法来实现
 */
public class CountDownDemo {
    @Test
    public static void main(String[] args) throws Exception {
        CountDownLatch cdl = new CountDownLatch(2);
        new Thread(new BuyGuo(cdl)).start();
        new Thread(new BuyFlut(cdl)).start();
//        Thread.sleep(1000);
        //await()方法会产生阻塞，阻塞放开的条件时闭锁初始数量为0
        cdl.await();
        System.out.println("开始做饭");
    }
}
class BuyGuo implements Runnable{
    private CountDownLatch cdl;
    public BuyGuo(CountDownLatch cdl) {
        this.cdl = cdl;
    }

    @Override
    public void run() {
        System.out.println("锅买回来了");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //当此线程工作完之后，闭锁的初始数量-1
        cdl.countDown();
    }
}
class BuyFlut implements  Runnable{
    private CountDownLatch cdl;

    public BuyFlut(CountDownLatch cdl) {
        this.cdl = cdl;
    }

    @Override
    public void run() {
        System.out.println("菜买回来了");
        cdl.countDown();
    }
}