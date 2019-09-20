package com.jby.netty_test1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * netty hello world
 * netty 用途： http , socket（自定义协议） , websocket(长连接)
 *
 */
public class TestServer {
    public static void main(String[] args) {
        EventLoopGroup bossGroup =new NioEventLoopGroup(); //接收用户请求
        EventLoopGroup workerGroup = new NioEventLoopGroup(); //实际处理用户请求
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();//服务端启动配置
            bootstrap.group(bossGroup,workerGroup).channel(NioServerSocketChannel.class);
            bootstrap.childHandler(new TestServerInitializer());//添加自定义请求处理器
            ChannelFuture channelFuture = bootstrap.bind(8899).sync(); //绑定监听端口
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
