package com.imooc.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ServerHandler extends ChannelInboundHandlerAdapter {
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.err.println("Server channel active ...");
    }

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        byte[] requestBytes = new byte[buf.readableBytes()];
        buf.readBytes(requestBytes);
        String request = new String(requestBytes, "utf-8");
        System.err.println("Server: " + request);
//        String request = (String)msg;
//        System.out.println("Server: " +request);

        //String responseBody = "response from server," + requestBody;
        ctx.writeAndFlush(Unpooled.copiedBuffer(request.getBytes()));
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.fireExceptionCaught(cause);
    }
}
