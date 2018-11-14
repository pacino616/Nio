package cn.tedu.blockQueue;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * 队列是FIFO：先进先出原则
 */
public class BlockQueueDemo {
    /**
     * add方法
     */
    @Test
    public void product() throws Exception {
        BlockingQueue queue=new ArrayBlockingQueue<>(10);
        for(int i=0;i<10;i++){
            queue.put(i);
        }
//        queue.add(11);
        System.out.println(queue.offer(11));
        queue.offer(11,3, TimeUnit.SECONDS);
        System.out.println("hello 1804");
    }

    /**
     * remove方法对队列为空时，会抛出异常NoSuchElement
     */
    @Test
    public void get() throws InterruptedException {
        BlockingQueue queue = new ArrayBlockingQueue<>(10);
        queue.add(1);
        queue.remove();
        queue.take();//阻塞方法
        System.out.println("hello");
        System.out.println();
    }

    /**
     * ArrayBlockingQueue：数组阻塞队列的特点：
     * 1.有界队列，容量在创建是指定
     * 2.底层是通过数组的数据结构实现的，所以查询快，增删慢
     * LinkedBlockingQueue:链阻塞队列特点
     * 1.是无界队列，默认的大小是Integer.MaxValue
     * 2.底层是链数据结构，增删快，因为队列的使用一般是增删，所以比Array常用
     *
     * 阻塞队列之所以能保证并发安全，底层是提供锁机制实现的（重入锁）
     */
    @Test
    public void createQueue(){
        BlockingQueue q1 = new ArrayBlockingQueue<>(10);
        BlockingQueue q2 = new LinkedBlockingQueue<>(10);
    }

    /**
     * 优先级队列，要求插入队里的元素必须实现Compareable接口
     * 队列会按CompareTo中的比较规则进行排序，实现元素的排序
     * 然后取出元素时，会按照排序后的顺序来取值
     */
    @Test
    public void createCompareQueue() throws Exception {
        BlockingQueue<Student> queue = new PriorityBlockingQueue<>();
        Student s1 = new Student("tom",100);
        Student s2 = new Student("jack",120);
        Student s3 = new Student("rose",80);
        queue.add(s1);
        queue.add(s2);
        queue.add(s3);
        for(int i=0;i<3;i++){
            System.out.println(queue.take());
        }
    }
}
