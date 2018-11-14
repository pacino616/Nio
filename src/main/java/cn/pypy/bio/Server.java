package cn.pypy.bio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket();
        ss.bind(new InetSocketAddress(7777));
        while (true){
            Socket sc = ss.accept();
            new Thread(new ClientService(sc)).start();
        }
    }
}

class ClientService implements Runnable{
    private Socket sc;
    public ClientService(Socket sc) {
        this.sc = sc;
    }

    @Override
    public void run() {
        System.out.println("提供服务的线程id是:"+Thread.currentThread().getId());
    }
}
