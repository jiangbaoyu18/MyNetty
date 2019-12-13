package com.jby.nio8;

import java.nio.IntBuffer;
import java.security.SecureRandom;

/**
 *
 * Nonblocking io
 * Nio的三个核心概念：Selector,Channel ,Buffer，nio是面向block编程的（而非stream）
 * Buffer 本身为一块内存，数据的读写都是通过buffer实现的,基本类型都有对应的Buffer类型
 *      position:下一次要读或写的索引的位置；limit: 第一个不能读或写的索引位置
 *
 * Channel 类似于Stream，可以向其write or  read 数据 （通过Buffer）,Channel是双向的而Stream是单向的
 */
public class Test1 {

    public static void main(String[] args) {
        IntBuffer buf=IntBuffer.allocate(10);
        for(int i=0;i<buf.capacity();i++){
            int randomnum=new SecureRandom().nextInt(20);
            buf.put(randomnum);
        }
        buf.flip(); // wirte to read
        while(buf.hasRemaining()){
            System.out.println(buf.get());
        }
    }
}
