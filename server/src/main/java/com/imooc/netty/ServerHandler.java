package com.imooc.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.FileOutputStream;

public class ServerHandler extends ChannelInboundHandlerAdapter {
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.err.println("Server channel active ...");
    }

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        RequestData requestData = (RequestData) msg;
        System.out.println(requestData.toString());

        byte[] attachmentData = requestData.getAttachment();
        String path = "/Users/feiwu/projects/java_training/myNetty/server/src/main/resources/bbb.png";
        FileOutputStream fos = new FileOutputStream(path);
        fos.write(attachmentData);
        fos.close();

        ResponseData responseData = new ResponseData();
        responseData.setId(requestData.getId());
        responseData.setMessage("got it from server");

        ctx.writeAndFlush(responseData);
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.fireExceptionCaught(cause);
    }
}
