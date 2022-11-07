package com.znczCxtcGkj.netty;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPromise;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.QueryStringEncoder;
import io.netty.handler.codec.http.cookie.ClientCookieEncoder;
import io.netty.handler.codec.http.cookie.DefaultCookie;
import io.netty.handler.codec.http.multipart.DefaultHttpDataFactory;
import io.netty.handler.codec.http.multipart.HttpDataFactory;
import io.netty.handler.codec.http.multipart.HttpPostRequestEncoder;

public class HttpClient {

	private Channel ch;
	private EventLoopGroup group = new NioEventLoopGroup();

	// method : get、post
	public static HttpRequest getRequestMethod(Map<String, String> header, Map<String, String> parameter, String url,
			String method) throws HttpPostRequestEncoder.ErrorDataEncoderException {
		URI uri;
		try {
			uri = new URI(url);
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return null;
		}

		String path = uri.getRawPath();
		String host = uri.getHost();

		HttpRequest request = null;

		if ("post".equalsIgnoreCase(method)) {
			request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_0, HttpMethod.POST, path);

			HttpDataFactory factory = new DefaultHttpDataFactory();
			// This encoder will help to encode Request for a FORM as POST.
			HttpPostRequestEncoder bodyRequestEncoder = new HttpPostRequestEncoder(factory, request, false);
			// add Form attribute
			if (parameter != null) {
				Set<Map.Entry<String, String>> entrySet = parameter.entrySet();
				for (Map.Entry<String, String> e : entrySet) {
					String key = e.getKey();
					String value = e.getValue();
					bodyRequestEncoder.addBodyAttribute(key, value);
				}
				try {
					request = bodyRequestEncoder.finalizeRequest();
				} catch (HttpPostRequestEncoder.ErrorDataEncoderException e) {
					// if an encoding error occurs
					e.printStackTrace();
				}
			}

//            request.headers().set(HttpHeaderNames.HOST, host);
//            request.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.CLOSE);
//            request.headers().set(HttpHeaderNames.ACCEPT_ENCODING, HttpHeaderValues.GZIP);
//            request.headers().set(HttpHeaderNames.COOKIE, ClientCookieEncoder.encode(
//                    new DefaultCookie("my-cookie", "foo"),
//                    new DefaultCookie("another-cookie", "bar")));
			Set<Map.Entry<String, String>> headerEntrySet = header.entrySet();
			for (Map.Entry<String, String> e : headerEntrySet) {
				String key = e.getKey();
				String value = e.getValue();
				request.headers().set(key, value);
			}
		} else if ("get".equalsIgnoreCase(method)) {
			// uri.toString()没有查询参数的uri
			QueryStringEncoder encoder = new QueryStringEncoder(uri.toString());
			if (parameter != null) {
				Set<Map.Entry<String, String>> entrySet = parameter.entrySet();
				for (Map.Entry<String, String> e : entrySet) {
					String key = e.getKey();
					String value = e.getValue();
					encoder.addParam(key, value);
				}
			}
			// encoder.toString()有查询参数的uri
			request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_0, HttpMethod.GET, encoder.toString());
			HttpHeaders headers = request.headers();
			headers.set(HttpHeaderNames.HOST, host);
			headers.set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
			headers.set(HttpHeaderNames.ACCEPT_ENCODING, HttpHeaderValues.GZIP.toString() + ','
					+ HttpHeaderValues.DEFLATE.toString() + ',' + HttpHeaderValues.BINARY.toString());
//
//            headers.set(HttpHeaderNames.ACCEPT_CHARSET, "ISO-8859-1,utf-8;q=0.7,*;q=0.7");
//            headers.set(HttpHeaderNames.ACCEPT_LANGUAGE, "fr");
			headers.set(HttpHeaderNames.USER_AGENT, "Netty Simple Http Client side");
//            headers.set(HttpHeaderNames.ACCEPT, "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");

			Set<Map.Entry<String, String>> headerEntrySet = header.entrySet();
			for (Map.Entry<String, String> e : headerEntrySet) {
				String key = e.getKey();
				String value = e.getValue();
				headers.set(key, value);
			}
//            headers.set(HttpHeaderNames.COOKIE, ClientCookieEncoder.encode(
//                    new DefaultCookie("my-cookie", "foo"),
//                    new DefaultCookie("another-cookie", "bar"))
//            );
		} else {
			System.err.println("this method is not support!");
		}
		return request;
	}

	// 更新轻服务的数据�? �?要发送的json格式
//	{
//		
//		"咨询楼盘": [
//			{
//				"唯一编码": "92528315975213058",
//				"关系名称": "咨询楼盘"
//			}
//		],
//		"基本属�?�组": {
//			"姓名": "客户名称",
//			"电话": "17130043795",
//			"客户四种类型": "30696"	
//		}
//
//	}

	/**
	 * 调用轻服�?
	 * 
	 * @param header
	 * @param parameter
	 * @param url
	 * @param method    get/post
	 * @return
	 * @throws HttpPostRequestEncoder.ErrorDataEncoderException
	 */
	public static HttpRequest getDefualtServiceRequestMethod(Map<String, String> header, Map<String, String> parameter,
			String url, String method) throws HttpPostRequestEncoder.ErrorDataEncoderException {
		header.put("userName", "admin");
		header.put("hydrocarbon-token", "b6664308b9b64534881c4387c51e653a");
		header.put("tokenTime", "2020-08-30");
		return getRequestMethod(header, parameter, url, method);
	}

	/**
	 * 
	 * @param url
	 * @param request
	 * @param channelHandler
	 * @throws HttpPostRequestEncoder.ErrorDataEncoderException
	 * @throws InterruptedException
	 */
	public void run(String url, HttpRequest request, ChannelHandler channelHandler)
			throws HttpPostRequestEncoder.ErrorDataEncoderException, InterruptedException {
		URI uri;
		try {
			uri = new URI(url);
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return;
		}

		String scheme = uri.getScheme() == null ? "http" : uri.getScheme();
		String host = uri.getHost() == null ? "localhost" : uri.getHost();
		int port = uri.getPort();
		if (port == -1) {
			if ("http".equalsIgnoreCase(scheme)) {
				port = 80;
			} else if ("https".equalsIgnoreCase(scheme)) {
				port = 443;
			}
		}

		if (!"http".equalsIgnoreCase(scheme) && !"https".equalsIgnoreCase(scheme)) {
			System.err.println("Only HTTP(S) is supported.");
		}

		boolean ssl = "https".equalsIgnoreCase(scheme);

		try {
			
			
			// Configure the client.
			Bootstrap b = new Bootstrap();
			b.group(group)
			.channel(NioSocketChannel.class)
					.option(ChannelOption.SO_KEEPALIVE, false)
					.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 15000)
					.handler(new HttpClientInitializer(ssl, channelHandler));

			// Make the connection attempt.
			ch = b.connect(host, port).sync().channel();
			ch.config().setConnectTimeoutMillis(15000);
			// send request
//			ChannelFuture future = ch.writeAndFlush(request).sync();
//			ch.closeFuture().sync();
			ChannelPromise promise = ((HttpClientHandler)channelHandler).getPromise(ch.writeAndFlush(request).channel());
			promise.await();
			ch.closeFuture().sync();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			group.shutdownGracefully();
		}
	}
	
	

	// 暂时无用
	public void stop() throws HttpPostRequestEncoder.ErrorDataEncoderException, InterruptedException {
		try {

			ch.close().sync();

		} finally {
			group.shutdownGracefully();
		}
	}

	// 暂时无用
	public void runSync(String url, HttpRequest request, ChannelHandler channelHandler)
			throws HttpPostRequestEncoder.ErrorDataEncoderException, InterruptedException {
		URI uri;
		try {
			uri = new URI(url);
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return;
		}

		String scheme = uri.getScheme() == null ? "http" : uri.getScheme();
		String host = uri.getHost() == null ? "localhost" : uri.getHost();
		int port = uri.getPort();
		if (port == -1) {
			if ("http".equalsIgnoreCase(scheme)) {
				port = 80;
			} else if ("https".equalsIgnoreCase(scheme)) {
				port = 443;
			}
		}

		if (!"http".equalsIgnoreCase(scheme) && !"https".equalsIgnoreCase(scheme)) {
			System.err.println("Only HTTP(S) is supported.");
		}

		boolean ssl = "https".equalsIgnoreCase(scheme);

		// Configure the client.
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(group).channel(NioSocketChannel.class).handler(new HttpClientInitializer(ssl, channelHandler));

			// Make the connection attempt.
			Channel ch = b.connect(host, port).sync().channel();
			// send request
			ch.writeAndFlush(request).sync();
			ch.closeFuture().sync();
		} finally {
			group.shutdownGracefully();
		}
	}

	public static void main(String args[])
			throws HttpPostRequestEncoder.ErrorDataEncoderException, InterruptedException {
		String url = "http://localhost:90/hydrocarbonboot/api2/entity/1/list/tmpl";
		Map<String, String> getData = new HashMap<String, String>();
//        getData.put("tags", "806:938356;");
//        getData.put("sort", "_p");

		Map<String, String> header = new HashMap<String, String>();
		header.put("userName", "admin");
		header.put("hydrocarbon-token", "b6664308b9b64534881c4387c51e653a");
		header.put("tokenTime", "2020-08-30");

		HttpRequest get = getRequestMethod(header, getData, url, "get");
		new HttpClient().run(url, get, new HttpClientHandler());
	}
}
