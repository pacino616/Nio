package cn.tedu.bio;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class BIODemo {

    @Test
    public void accept() throws IOException {
        ServerSocket ss = new ServerSocket();
        ss.bind(new InetSocketAddress(9999));
        //accept方法是阻塞方法
        Socket sc = ss.accept();
        System.out.println("有链接炼乳");
        InputStream is = sc.getInputStream();
        //read方法也是阻塞方法，知道读取到内容之后才会释放
//        is.read();
//        System.out.println("有数据读入");
        OutputStream os = sc.getOutputStream();
        for(int i=0;i<100000;i++){
            //write方法也会产生阻塞，会一直往外写数据，直到写出一定量，网卡设备的缓冲去中写数据
            os.write("helloworld".getBytes());
            System.out.println(i);
        }
        System.out.println("有数据输出");
    }

    @Test
    public void connect() throws IOException {
        Socket sc = new Socket();
//        sc.bind(new InetSocketAddress("127.0.0.1",9999));
//        connect方法也是阻塞方法，直到建立连接，阻塞才释放
        sc.connect(new InetSocketAddress("127.0.0.1",9999));
        System.out.println("客户端链接成功。。。");
        while(true);
    }
}
