/** 
 * Project Name:channel-server 
 * File Name:ChannelHttpRequestHanlder.java 
 * Package Name:com.zjht.channel.server 
 * Date:2015年8月28日下午4:48:14 
 * 
 */

package com.zjht.channel.server.hanlder;

import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;
import com.zjht.channel.common.bean.Request;
import com.zjht.channel.common.bean.Response;
import com.zjht.channel.common.constant.RespCode;
import com.zjht.channel.exception.InspectionException;
import com.zjht.channel.exception.ServiceException;
import com.zjht.channel.exception.bean.ErrorMessage;
import com.zjht.channel.helper.common.JsonHelper;
import com.zjht.channel.inspector.InspectionEngine;
import com.zjht.channel.server.HttpRequestHandler;
import com.zjht.channel.server.helper.RRHelper;
import com.zjht.channel.service.ServiceManager;

import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;

/**
 * ClassName: ChannelHttpRequestHanlder <br/>
 * Function: channel的http请求处理类. <br/>
 * date: 2015年8月28日 下午4:48:14 <br/>
 * 
 * @author jun yangwenjun@chinaexpresscard.com
 * @version v0.0.1
 * @since JDK 1.8
 */
@Component("ChannelHttpRequestHanlder")
public class ChannelHttpRequestHanlder implements HttpRequestHandler {
    private final static Logger logger = LoggerFactory
            .getLogger(ChannelHttpRequestHanlder.class);

    @Resource(name = "InspectionEngine")
    private InspectionEngine inspectionEngine;

    @Resource(name = "ServiceManager")
    private ServiceManager serviceManager;

    /**
     * @see com.zjht.channel.server.HttpRequestHandler#handle(io.vertx.core.http.HttpServerRequest,
     *      io.vertx.core.http.HttpServerResponse)
     */
    @Override
    public void handle(HttpServerRequest httpRequest,
            HttpServerResponse httpResponse) {
        Request request    = null;
        Response<?> response  = null;
        String respContent = "";// 响应给调用的json字符串
        Object content   = "";// 调用后端服务返回的json字符串

        try {
            request = RRHelper.newRequest(httpRequest);
            
            logger.info("开始对请求进行规则校验");
            inspectionEngine.validate(httpRequest);
            logger.info("完成对请求进行规则校验");
            
            
            logger.info("请求信息：{}",request.getMsg());
            
            logger.info("开始调用服务");
            content = serviceManager.invoke(request.getSvrName(),request.getSvrVersion(), RRHelper.requestParam(request));
            logger.info("完成调用服务");
        } catch (InspectionException e) {
            logger.info("规则校验不通过，响应码[{}],响应信息[{}]",e.code(),e.message());
            content = new ErrorMessage(e.code(), e.message());
        } catch (ServiceException e) {
            logger.info("调用服务失败，响应码[{}],响应信息[{}]",e.code(),e.message());
            content = new ErrorMessage(e.code(), e.message());
        } catch (Exception e) {
            logger.error("处理失败", e);
            content = new ErrorMessage(RespCode._99999.code(), RespCode._99999.message());
        }
        response = RRHelper.newResponse(request,content,"","");
        respContent = JsonHelper.toJson(response);
        logger.debug("响应信息：{}",respContent);
        
        httpResponse.putHeader("Content-Type", "application/json; charset=utf-8");
        httpResponse.end(respContent);
    }
    
    public static void main(String[] args) {
        Object content = null;
        
        Map<String,Object> ss = Maps.newHashMap();
        ss.put("test", "testValue");
        ss.put("error",new ErrorMessage(RespCode._99999.code(), RespCode._99999.message()));
        content = ss;
        System.out.println(JsonHelper.toJson(RRHelper.newResponse(null,content,"","")));
    }
}