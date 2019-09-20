package com.jby.protobuf6;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MyServerChannelHandler extends SimpleChannelInboundHandler<Msg.MyMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Msg.MyMessage msg) throws Exception {
        Msg.MyMessage.DataType dataType = msg.getDataType();
        if(dataType == Msg.MyMessage.DataType.PersonType){
            Msg.Person person = msg.getPerson();
            System.out.println(person.getAddress());
            System.out.println(person.getName());
            System.out.println(person.getAge());
            System.out.println(person);

        }else if(dataType == Msg.MyMessage.DataType.DogType){
            Msg.Dog dog = msg.getDog();
            System.out.println(dog.getAge());
            System.out.println(dog.getName());
            System.out.println(dog);

        }else{
            Msg.Cat cat = msg.getCat();
            System.out.println(cat.getAge());
            System.out.println(cat.getName());
            System.out.println(cat);


        }

    }
}
