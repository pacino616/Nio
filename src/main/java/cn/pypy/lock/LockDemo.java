package cn.pypy.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockDemo {
    public static String name = "李雷";
    public static String gender = "含春梅";

    public static void main(String[] args) {
        //--创建重入锁
        //--底层支持两种锁机制：公平锁和非公平锁
        //参数如果是false,表示非公平锁，默认此机制
        //同步代码块默认是非公平锁
        //需要在finally{lock.unlock},避免产生死锁
        //官方建议：如果使用非公平锁，推荐使用同步代码快，JVM处理了关锁
        //如果要使用公平锁，可以使用重入锁
        Lock lock = new ReentrantLock(false);
        new Thread(new WriteRunner(lock)).start();
        new Thread(new ReadRunner(lock)).start();
    }
}
class WriteRunner implements Runnable{
    private Lock lock;

    public WriteRunner(Lock lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        while (true){
            //--上锁
            lock.lock();
            if(LockDemo.name=="李雷"){
                LockDemo.name="含春梅";
                LockDemo.gender="女";
            }else{
                LockDemo.name="李雷";
                LockDemo.gender="男";
            }
            //--释放锁
            lock.unlock();
        }
    }
}
class ReadRunner implements Runnable{
    private Lock lock;

    public ReadRunner(Lock lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        while (true){
            lock.lock();
            System.out.println(LockDemo.name+":"+LockDemo.gender);
            lock.unlock();
        }
    }
}