package com.jby.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;

public class Test {
    public static void main(String[] args) throws InvalidProtocolBufferException {
        DataInfo.Student student = DataInfo.Student.newBuilder().setName("姜宝钰").setAge(22).setAddress("bj").build();
        byte[] bytes=student.toByteArray();
        DataInfo.Student student1 = DataInfo.Student.parseFrom(bytes);
        System.out.println(student1.getAddress());
        System.out.println(student1.getAge());
        System.out.println(student1.getName());
    }
}
