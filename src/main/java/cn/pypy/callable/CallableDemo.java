package cn.pypy.callable;

import java.util.concurrent.*;

/**
 * Callable是jdk1.5之后提供新的线程机制，比起Runnable的变化
 * 1.call()方法的返回值可以自定义
 * 2.call()方法的返回值可以接到
 * 3.异常可以抛出，run方法只能捕获
 * 4.callable线程类只能通过线程池启动，不能new Thread().start()启动
 */
public class CallableDemo {
    public static void main(String[] args) throws Exception {
        ExecutorService service = Executors.newCachedThreadPool();
        //通过此方法启动线程
        Future<String> submit = service.submit(new c1());
        //通过此方法获取call的返回值
        String s = submit.get();
        System.out.println(s);
        service.shutdown();
    }
}
class c1 implements Callable<String>{

    @Override
    public String call() throws Exception {
        System.out.println("线程启动");
        return "success";
    }
}