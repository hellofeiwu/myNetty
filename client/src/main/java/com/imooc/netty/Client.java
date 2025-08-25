package com.imooc.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

import java.io.File;
import java.io.FileInputStream;

public class Client {
    public static void main(String[] args) throws Exception {
        EventLoopGroup workGroup = new NioEventLoopGroup();

        Bootstrap b = new Bootstrap();
        b.group(workGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000)
                .option(ChannelOption.SO_RCVBUF, 1024*32)
                .option(ChannelOption.SO_SNDBUF, 1024*32)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingDecoder());
                        ch.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingEncoder());
                        ch.pipeline().addLast(new ClientHandler());
                    }
                });

        ChannelFuture cf = b.connect("127.0.0.1", 8765).syncUninterruptibly();

        for(int i =0; i<20; i++) {
            RequestData requestData = new RequestData();
            requestData.setId(i);
            requestData.setMessage("hello " + i);

//            String path = System.getProperty("user.dir")
//                    + File.separatorChar + "source" + File.separatorChar + "001.jpg";
//            File file = new File(path);
//            FileInputStream fis = new FileInputStream(file);
//            byte[] data = new byte[fis.available()];
//            fis.read(data);
//            fis.close();
//            rd.setAttachment(GzipUtils.gzip(data));
//            channel.writeAndFlush(rd);

            String path = "/Users/feiwu/projects/java_training/myNetty/client/src/main/resources/aaa.png";
            File file = new File(path);
            FileInputStream fis = new FileInputStream(file);
            byte[] attachmentData = new byte[fis.available()];
            fis.read(attachmentData);
            fis.close();
            requestData.setAttachment(attachmentData);
            cf.channel().writeAndFlush(requestData);
        }

        cf.channel().closeFuture().sync();
        workGroup.shutdownGracefully();
    }
}
