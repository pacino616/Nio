package cn.tedu.nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class NIODemo {
    @Test
    public void accept() throws Exception {
        //默认情况下是阻塞的，但是可调成非阻塞
        ServerSocketChannel ss = ServerSocketChannel.open();
        //可以使用此方法设置成为关闭阻塞模式
        ss.configureBlocking(false);
        ss.bind(new InetSocketAddress(8888));
        SocketChannel sc = null;
        while( null == sc){
            sc = ss.accept();
        }
        System.out.println("有客户端连接成功");
        sc.configureBlocking(false);
        ByteBuffer buf = ByteBuffer.allocate(10);
        int read = sc.read(buf);
        System.out.println("读取到数据"+new String(buf.array())+read);
    }

    @Test
    public void connect() throws IOException {
        //客户端默认情况下也是阻塞的
        SocketChannel sc = SocketChannel.open();
        //设置链接为非阻塞模式
        sc.configureBlocking(false);
        sc.connect(new InetSocketAddress("127.0.0.1",8888));
        System.out.println("链接成功");
        while(!sc.isConnected()){
            sc.finishConnect();
        }
        ByteBuffer bb = ByteBuffer.wrap("hellohadoop".getBytes());
        sc.write(bb);
        while (true);
    }
}
