package cn.pypy.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NioServer {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel ss = ServerSocketChannel.open();
        ss.configureBlocking(false);
        ss.bind(new InetSocketAddress(6666));
        //创建多路复用选择器
        Selector selector = Selector.open();
        //要想让selector提供监听，需要先注册对应的事件，首先需要注册accept事件
        ss.register(selector, SelectionKey.OP_ACCEPT);
        while (true){
            //调用该方法select()，会产生阻塞,直到有监听的事件被触发
            selector.select();
            //获取事件的集合
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            //获取事件集合的迭代器
            Iterator<SelectionKey> iter = selectionKeys.iterator();
            while (iter.hasNext()){
                SelectionKey sk = iter.next();
                //证明有客户端请求建立会话链接
                if(sk.isAcceptable()){
                    //服务通道需要从sk中获取
                    ServerSocketChannel ssc = (ServerSocketChannel) sk.channel();
                    //创建会话对象
                    SocketChannel sc = null;
                    while (null == sc){
                        sc = ssc.accept();

                    }
                    //设置为非阻塞模式
                    sc.configureBlocking(false);
                    //输出为客户端提供服务的线程id
                    System.out.println("有客户端连入，提供服务的线程id="+Thread.currentThread().getId());
                    //为sc注册read和write事件
                    sc.register(selector,SelectionKey.OP_READ|SelectionKey.OP_WRITE);
                }
                if(sk.isReadable()){
                    //获取SocketChannel
                    SocketChannel sc = (SocketChannel) sk.channel();
                    ByteBuffer buf = ByteBuffer.allocate(10);
                    sc.read(buf);
                    System.out.println("服务端读到的内容："+new String(buf.array()));
                    System.out.println("负责处理读请求的线程id="+Thread.currentThread().getId());
                    //去点读事件监听
                    sc.register(selector,sk.interestOps()&~SelectionKey.OP_READ);
                }
                if(sk.isWritable()){
                    SocketChannel sc = (SocketChannel) sk.channel();
                    ByteBuffer buf = ByteBuffer.wrap("goodstudy".getBytes());
                    sc.write(buf);
                    System.out.println("负责处理写请求的线程id="+Thread.currentThread().getId());
                    //去掉写事故监听
                    sc.register(selector,sk.interestOps()&~SelectionKey.OP_WRITE);
                }
                //删除事件，目的：为了防止同一事件被处理多次
                iter.remove();
            }
        }
    }
}
