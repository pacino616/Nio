package cn.pypy.exchanger;

import java.util.concurrent.Exchanger;

/**
 * 交换机的使用场景：是两个线程之间做数据交换；
 */
public class ExchangeDemo {
    public static void main(String[] args) {
        Exchanger<String> ex = new Exchanger<String>();
        new Thread(new Spy1(ex)).start();
        new Thread(new Spy2(ex)).start();

    }
}
class Spy1 implements Runnable{
    private Exchanger<String> ex;

    public Spy1(Exchanger<String> ex) {
        this.ex = ex;
    }

    @Override
    public void run() {
        String info = "1回目哦一笑";
        try {
            //将数据传给对方线程
            //此方法的返回值是对方线程传过来的值
            String exchange = ex.exchange(info);
            System.out.println("1收到2的消息："+exchange);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class Spy2 implements Runnable{
    Exchanger<String> ex;

    public Spy2(Exchanger<String> ex) {
        this.ex = ex;
    }

    @Override
    public void run() {
        String info = "2寸草不生";
        try {
            String exchange = ex.exchange(info);
            System.out.println("2收到1的数据："+exchange);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
