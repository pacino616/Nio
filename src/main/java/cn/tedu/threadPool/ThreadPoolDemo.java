package cn.tedu.threadPool;

import org.junit.Test;

import java.util.concurrent.*;

public class ThreadPoolDemo {

    //--corePoolSize:核心线程数
    //当线程池创建时，是没有任何线程的，当有请求发起时，县城会创建核心线程
    //在请求过程中，无论核心线程是否闲置，线程池都会创建核心线程，直到达到线程数量为止
    //当核心线程数达到要求时，会将请求放入队列中，等待空闲线程
    //--maximimPoolSize:最大线程数=核心线程+临时线程数
    //--keepAliveTime:临时线程存活时间
    //--TimeUnit:时间单位，一般用毫秒表示
    //--workQueue:等待队列，用来存放还未处理的请求
    @Test
    public void create(){
        ExecutorService service = new ThreadPoolExecutor(
                5,10,3000,
                TimeUnit.MILLISECONDS,new ArrayBlockingQueue<Runnable>(10));
        //通过此方法启动线程
        for (int i = 0; i < 20; i++) {
            service.execute(new ClientRunner());
        }

        //关闭线程池,此方法调用时，不会接受外部请求了
        //但内部并不会马上销毁，而是等线程工作完之后才会销毁
        service.shutdown();
    }

    /**
     * 使用工具类创建线程池
     * newCachedThreadPool：
     * 1.没有核心线程
     * 2.都是临时线程
     * 3.队列是同步队列
     * 4.最大线程数Integer.MaxValue
     * 总结：大池子小队列
     * 可以很好的响应客户端请求，因为不需要等待
     * 但是注意：这种线程池适用短请求，如果都是长请求，线程就只创建而不销毁，最后内存泄漏
     */
    public void testCreate(){
        ExecutorService service = Executors.newCachedThreadPool();
    }

    /**
     * newFixedThreadPool:
     * 1.都是核心线程，没有临时线程
     * 2.无界队列
     * 总结：小池子大队列
     * 这种线程池的作用，削峰限流。局限性是不能及时响应客户请求；
     * 具体使用哪种线程池，根据公司的业务量
     * 线程池大小的设置要根据需求量大小和服务器内存大小
     */
    public void testCreate1(){
        //一般公司服务器64GB内存，大公司/国企，内存1TB
        ExecutorService service = Executors.newFixedThreadPool(10);
    }
}

class ClientRunner implements Runnable{

    @Override
    public void run() {
        System.out.println("处理客户端请求");
        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}