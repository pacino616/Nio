package cn.tedu.nio;

import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * 字节缓冲区，是特定的基本类型元素的有限序列
 * 包含：capacity容量（缓冲区能否保存的元素最大个数），
 * 限制limit（防止容量超过最大值），
 * 位置position:表示当前的位置
 */
public class ByteBufferDemo {
    @Test
    public void test(){
        //创建指定长度的缓冲区
        ByteBuffer bb = ByteBuffer.allocate(10);
        System.out.println(bb.get());

    }

    /**
     * 每执行一次put方法，除了将指定的内容保存到缓冲区中的当前位置，
     * 此后position++，limit和capacity并未发生变化
     * get()：获取当前position对应位置的元素内容
     */
    @Test
    public void put(){
        ByteBuffer bb = ByteBuffer.allocate(10);
        byte a = 1;
        byte b = 2;
        bb.put(a);
        bb.put(b);
        //position():获取缓冲区当前的position
        //position(int n):设置缓冲区当前的position的值为n
        bb.position(0);
        //limit():获取当前缓冲区的限制
        //limit(int n):设置当前缓冲区的限制，避免出现“脏数据”
        bb.limit(2);
        System.out.println(bb.get());
        System.out.println(bb.get());
    }

    @Test
    public void flip(){
        ByteBuffer bb = ByteBuffer.allocate(10);
        byte a = 1;
        byte b = 2;
        bb.put(a);
        bb.put(b);
        bb.flip();
        System.out.println(bb.get());
        System.out.println(bb.get());
    }

    /**
     * 判断position和limit指定位置之间还有没有元素
     * 换句话说，position是否小于limit
     */
    @Test
    public void hasRemain(){
        ByteBuffer bb = ByteBuffer.allocate(10);
        byte a = 1;
        byte b = 2;
        bb.put(a);
        bb.put(b);
        bb.flip();
        bb.put(b);
        while(bb.hasRemaining()){
            System.out.println(bb.get());
        }
    }

    @Test
    public void wrap(){
        ByteBuffer bb = ByteBuffer.wrap("helloZebra".getBytes());
        System.out.println();
    }
}
