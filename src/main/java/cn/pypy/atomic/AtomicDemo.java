package cn.pypy.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 原子性类型底层用到的CAS算法，既可以保证线程并发安全，而且性能很高
 */
public class AtomicDemo {
    //原子性整形
    public static AtomicInteger i = new AtomicInteger(0);

    public static void main(String[] args) throws Exception {
        CountDownLatch cdl = new CountDownLatch(2);
        new Thread(new AddRunner1(cdl)).start();
        new Thread(new AddRunner2(cdl)).start();
        cdl.await();
        System.out.println(i);
    }
}
class AddRunner1 implements Runnable{
    private CountDownLatch cdl;

    public AddRunner1(CountDownLatch cdl) {
        this.cdl = cdl;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000000; i++) {
            AtomicDemo.i.incrementAndGet();
        }
        cdl.countDown();
    }
}
class AddRunner2 implements Runnable{
    private CountDownLatch cdl;

    public AddRunner2(CountDownLatch cdl) {
        this.cdl = cdl;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000000; i++) {
            AtomicDemo.i.incrementAndGet();
        }
        cdl.countDown();
    }
}
