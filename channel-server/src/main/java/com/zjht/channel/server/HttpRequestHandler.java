/** 
 * Project Name:channel-server 
 * File Name:HttpRequestHandler.java 
 * Package Name:com.zjht.channel.server 
 * Date:2015年8月28日下午4:48:52 
 * 
 */
  
  
package com.zjht.channel.server;  

import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
 
/** 
 * ClassName: HttpRequestHandler <br/> 
 * Function: http请求处理接口定义. <br/> 
 * date: 2015年8月28日 下午4:48:52 <br/> 
 * 
 * @author jun yangwenjun@chinaexpresscard.com 
 * @version v0.0.1
 * @since JDK 1.8
 */
public interface HttpRequestHandler {
	
	/**
	 * 
	 * handle:处理vert.x的http请求方法定义. <br/> 
	 * 
	 * @author jun yangwenjun@chinaexpresscard.com
	 * @param request
	 * @param response 
	 * @since JDK 1.8
	 */
	void handle(HttpServerRequest request,HttpServerResponse response);
}
  