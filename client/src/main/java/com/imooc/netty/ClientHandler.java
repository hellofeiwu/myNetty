package com.imooc.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

public class ClientHandler extends ChannelInboundHandlerAdapter {
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.err.println("client channel active ...");
    }

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try{
//            ByteBuf buf = (ByteBuf) msg;
//            byte[] requestBytes = new byte[buf.readableBytes()];
//            buf.readBytes(requestBytes);
//
//            String response = new String(requestBytes, "utf-8");

            String response = (String)msg;

            System.out.println("Client: "+ response);
        }finally {
            ReferenceCountUtil.release(msg);
        }
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.fireExceptionCaught(cause);
    }
}
