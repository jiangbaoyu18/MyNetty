package com.jby.netty_test1;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

/**
 * 方法回调流程：
 * handler added
 * channel registered
 * channel active
 * channel read0
 * channel inactive
 * channel unregistered
 *
 */
public class TestHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {//读取请求，构造响应，返回给客户端
        System.out.println(ctx.channel().remoteAddress());

        if(msg instanceof HttpRequest){
            HttpRequest req=(HttpRequest)msg;
            System.out.println("resquest name:"+req.method().name());//get or post
            URI uri = new URI(req.uri());
            if("/favicon.ico".equals(uri.getPath())){ //如果请求图标不作处理
                System.out.println("request icon ");
                return;
            }
            ByteBuf content= Unpooled.copiedBuffer("hello world\n", CharsetUtil.UTF_8);
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH,content.readableBytes());
            ctx.writeAndFlush(response);
            ctx.channel().close();// 此处可以根据client request 的类型执行相应的关闭逻辑
        }
    }

}
