package com.znczCxtcGkj.netty;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpContentDecompressor;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.codec.http.HttpResponseDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslHandler;

import javax.net.ssl.SSLEngine;

public class HttpClientInitializer extends ChannelInitializer<SocketChannel> {

    private final boolean ssl;
    private ChannelHandler channelHandler;
    public HttpClientInitializer(boolean ssl,ChannelHandler channelHandler) {
        this.ssl = ssl;
        this.channelHandler=channelHandler;
    }


    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline p = ch.pipeline();

        p.addLast("log", new LoggingHandler(LogLevel.INFO));
//        if (ssl) {
//            SSLEngine engine = SSLContextFactory.getClientContext().createSSLEngine();
//            engine.setUseClientMode(true);
//            p.addLast("ssl", new SslHandler(engine));
//        }
        p.addLast("request-encoder", new HttpRequestEncoder());

        p.addLast("response-decoder", new HttpResponseDecoder());

        // Remove the following line if you don't want automatic content decompression.
        p.addLast("inflater", new HttpContentDecompressor());

        //HttpObjectAggregator会把多个消息转换�? �?个单�?的FullHttpRequest或是FullHttpResponse
        p.addLast("aggregator", new HttpObjectAggregator(1048576));
//        p.addLast("aggregator",  new HttpChunkAggregator(65536));
       
        p.addLast("handler", channelHandler);
    }
}
