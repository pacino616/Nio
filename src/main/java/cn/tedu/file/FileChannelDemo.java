package cn.tedu.file;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelDemo {
    /**
     * 基于流创建文件通道
     */
    @Test
    public void write() throws Exception {
        //创建输出流
        FileOutputStream fos = new FileOutputStream("1.txt");
        //从输出流上获取fileChannel对象
        FileChannel fc = fos.getChannel();
        //设置写入的当前位置
        fc.position(4);
        //定义写入的内容
        ByteBuffer by = ByteBuffer.wrap("1234".getBytes());
        fc.write(by);
        fc.close();
    }

    @Test
    public void read() throws IOException {
        //创建字节输入流
        FileInputStream fis = new FileInputStream(new File("1.txt"));
        //从输入流上获取fileChannel对象
        FileChannel fc = fis.getChannel();
        //设置读取的位置
        //文件通道底层可以用到zero copy零拷贝技术
        fc.position(4);
        //定义缓冲区对象
        ByteBuffer by = ByteBuffer.allocate(4);
        //此方法运用到了零拷贝技术
//        fc.transferTo();
        fc.read(by);
        System.out.println(new String(by.array()));
        ByteBuffer pywh = ByteBuffer.wrap("pypy".getBytes());
        fc.write(pywh);
        fc.close();
    }
}
