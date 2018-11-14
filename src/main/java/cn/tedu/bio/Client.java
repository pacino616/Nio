package cn.tedu.bio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket sc = new Socket();
        sc.connect(new InetSocketAddress("127.0.0.1",7777));
        while (true);
    }
}
