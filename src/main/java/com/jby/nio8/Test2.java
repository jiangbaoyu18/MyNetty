package com.jby.nio8;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Test2 {
    public static void main(String[] args) throws Exception {
        FileInputStream fs = new FileInputStream("test2.txt");
        FileChannel channel = fs.getChannel();
        ByteBuffer buf=ByteBuffer.allocate(512);
        channel.read(buf);// 读取数据到channel关联的buffer
        buf.flip();
        while(buf.hasRemaining()){
            byte b = buf.get();
            System.out.println((char)b);
        }

    }
}
