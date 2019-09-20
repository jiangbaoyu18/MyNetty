package com.jby.nio8;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Test3 {
    public static void main(String[] args) throws Exception{
        FileOutputStream os = new FileOutputStream("test3.txt");
        FileChannel channel = os.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(512);
        byte[] bytes = "helo world!!".getBytes();
        for(int i=0;i<bytes.length;i++){
            buffer.put(bytes[i]);
        }
        buffer.flip();
        channel.write(buffer);
        os.close();
    }
}
