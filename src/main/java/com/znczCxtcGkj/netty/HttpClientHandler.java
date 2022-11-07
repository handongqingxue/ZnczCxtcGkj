package com.znczCxtcGkj.netty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;


public class HttpClientHandler extends SimpleChannelInboundHandler<HttpObject> {
	
	Logger logger = LoggerFactory.getLogger(HttpClientHandler.class);
	
	private StringBuilder result=new StringBuilder();
	
	private ChannelPromise promise=null;

    public void messageReceived(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
       
        	// Thread.sleep(1000);
            if (msg instanceof HttpResponse) {
                HttpResponse response = (HttpResponse) msg;

                logger.debug("STATUS: " + response.status());
                logger.debug("VERSION: " + response.protocolVersion());

                if (!response.headers().isEmpty()) {
                    for (String name : response.headers().names()) {
                        for (String value : response.headers().getAll(name)) {
                            logger.debug("HEADER: " + name + " = " + value);
                        }
                    }
                    
                }

                if (HttpUtil.isTransferEncodingChunked(response)) {
                    logger.debug("CHUNKED CONTENT {");
                } else {
                    logger.debug("CONTENT {");
                }
            }
            if (msg instanceof HttpContent) {
                HttpContent content = (HttpContent) msg;
                result.append(content.content().toString(CharsetUtil.UTF_8));
                logger.debug(result.toString());

                if (content instanceof LastHttpContent) {
                    logger.debug("} END OF CONTENT");
                    if(promise!=null) {
                    	promise.setSuccess();
                    }
                    ctx.channel().close();
                }
            }
       
    }
    
    public ChannelPromise getPromise(Channel ch){
    	promise=ch.newPromise();
    	return promise;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        messageReceived(ctx, msg);
    }

	public String getResult() {
		return result.toString();
	}
    
    
}
