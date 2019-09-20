package com.jby.heartbeat4;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

public class MyServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline=ch.pipeline();
        // 当server超过5s没有读取到client的请求，7s没有向client写入数据，4s没有读取到client的请求且没有向client写入数据，会触发相应的事件
        pipeline.addLast(new IdleStateHandler(5,7,4, TimeUnit.SECONDS));
        // IdleStateHandler触发idle event事件后，会向pipeline后面的handler传递事件，注意handler的顺序
        pipeline.addLast(new MyServerHandler());

    }
}
