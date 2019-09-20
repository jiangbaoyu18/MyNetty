package com.jby.heartbeat4;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;


public class MyServerHandler extends ChannelInboundHandlerAdapter{
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        String eventType=null;
        if(evt instanceof IdleStateEvent){
            IdleStateEvent event=(IdleStateEvent)evt;
            switch(event.state()){
                case READER_IDLE:
                    eventType="read idel";
                    break;
                case WRITER_IDLE:
                    eventType="write idel";
                    break;
                case ALL_IDLE:
                    eventType="read or write idel";
                    break;
            }
            System.out.println(ctx.channel().remoteAddress()+"time out,type:"+eventType);
            ctx.channel().close();
        }
    }
}
