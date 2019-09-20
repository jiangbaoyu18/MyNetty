package com.jby.chat3;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MyClient {
    public static void main(String[] args) {
        EventLoopGroup eventLoopGroup=new NioEventLoopGroup();
        try {
            Bootstrap bootstrap=new Bootstrap();
            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class)
                    .handler(new MyChatClientInitializer());
            ChannelFuture channelFuture = bootstrap.connect("localhost",8899).sync(); // server bind 端口；client connect 端口

            Channel channel = channelFuture.channel();
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));// 获取console 输入
            for(;;){
                String content=reader.readLine();
                if ("exit".equals(content)){ // 退出系统
                    return;
                }
                channel.writeAndFlush(content+"\r\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            eventLoopGroup.shutdownGracefully();

        }
    }
}
