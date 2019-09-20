package com.jby.protobuf6;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Random;

public class MyClientChannelHandler extends SimpleChannelInboundHandler<Msg.MyMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Msg.MyMessage msg) throws Exception {


    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        int i = new Random().nextInt(3);
        Msg.MyMessage message=null;
        if(i==0){
            message= Msg.MyMessage.newBuilder().setDataType(Msg.MyMessage.DataType.PersonType)
                    .setPerson(Msg.Person.newBuilder().setAddress("bj").setAge(12).setName("jby")).build();
        }else if(i==2){
            message= Msg.MyMessage.newBuilder().setDataType(Msg.MyMessage.DataType.DogType)
                    .setDog(Msg.Dog.newBuilder().setAge(12).setName("miaomiao")).build();
        }else {
            message= Msg.MyMessage.newBuilder().setDataType(Msg.MyMessage.DataType.CatType)
                    .setCat(Msg.Cat.newBuilder().setAge(12).setName("wangwang")).build();
        }
        ctx.channel().writeAndFlush(message);
    }
}
