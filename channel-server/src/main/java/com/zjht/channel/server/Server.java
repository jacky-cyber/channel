/** 
 * Project Name:channel-server 
 * File Name:Server.java 
 * Package Name:com.zjht.channel.server 
 * Date:2015年8月28日上午9:19:36 
 * 
 */

package com.zjht.channel.server;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;

import java.net.InetAddress;
import java.util.function.Consumer;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * ClassName: Server <br/>
 * Function: 创建http服务. <br/>
 * date: 2015年8月28日 上午9:19:36 <br/>
 * 
 * @author jun yangwenjun@chinaexpresscard.com
 * @version v0.0.1
 * @since JDK 1.8
 */
@Component
public class Server extends AbstractVerticle {
    private final static Logger logger = LoggerFactory.getLogger(Server.class);
    
    @Resource(name="ChannelHttpRequestHanlder")
    private HttpRequestHandler channelHandler;
    
	private final static String WEB_CONTEXT_PATH = "zjhtplatform";//web项目上下文路径
	private final static int WEB_PORT = 9060;//web端口

	@Override
	public void start() {
	    logger.info("正在启动http server...");
		HttpServer server = vertx.createHttpServer();
		Router router = Router.router(vertx);
		String path = "/" + WEB_CONTEXT_PATH + "/*";
		router.route(path).handler(routingContext -> {
			channelHandler.handle(routingContext.request(), routingContext.response());
		});
		server.requestHandler(router::accept).listen(WEB_PORT);
		
		logger.info("成功启动http server，监听http://127.0.0.1:{}{}的请求",WEB_PORT,path);
	}
	
	/**
	 * 启动http server. <br/> 
	 * 
	 * @author jun yangwenjun@chinaexpresscard.com 
	 * @since JDK 1.8
	 */
	@PostConstruct
	public void init(){
	    VertxOptions options = new VertxOptions().setClustered(false);
	    Consumer<Vertx> runner = vertx -> {
            try {
                vertx.deployVerticle(this);
            } catch (Throwable t) {
                t.printStackTrace();
            }
        };
        runner.accept(Vertx.vertx(options));
	}
}
