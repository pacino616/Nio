package cn.tedu.map;

import org.junit.Test;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 学习并发map的创建和使用
 * HashMap线程不安全
 * HashTable线程安全：
 * 1.底层的所有方法都上锁
 * 2.存在锁整表
 * concurrentMap:并发安全，引入了分段锁机制
 */
public class ConcurrentMapDemo {
    /**
     * concurrentMap,引入分段锁机制，底层分了16段（segment），理论上并发性能比Hash高16倍
     * 每个segment可以看做是一个hashTbale,即某个线程操作某个k,v时，
     * 只会锁此<k,v>的segment,不会锁其他的segment
     * 负载因子的概念和hashMap保持一致
     * 此外，底层的链表结构也和HashMap一致
     * 注意：以上都是针对jdk1.8之前
     * jdk1.8之后：
     * 1.用到CAS算法（compare and swap）无锁算法：底层把链表换成红黑树
      */
    @Test
    public void create() {
        ConcurrentHashMap<Integer,Integer> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 1000; i++) {
            map.put(i,i);
        }
        System.out.println();
    }

}
