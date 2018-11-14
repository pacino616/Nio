package cn.tedu.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NioClient {
    public static void main(String[] args) throws IOException {
        SocketChannel sc = SocketChannel.open();
        sc.configureBlocking(false);
        sc.connect(new InetSocketAddress("127.0.0.1",6666));
        while(!sc.isConnected()){
            sc.finishConnect();
        }
        ByteBuffer buf = ByteBuffer.wrap("hello Ser".getBytes());
        sc.write(buf);
        ByteBuffer bb = ByteBuffer.allocate(9);
        sc.read(bb);
        System.out.println("客户端读取到的服务端发来的消息="+new String(bb.array()));
        while (true);
    }
}
