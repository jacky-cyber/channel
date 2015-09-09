/** 
 * Project Name:channel-server 
 * File Name:RRHelper.java 
 * Package Name:com.zjht.channel.server.helper 
 * Date:2015年8月28日下午2:32:18 
 * 
 */

package com.zjht.channel.server.helper;

import io.vertx.core.http.HttpServerRequest;

import java.util.Map;
import java.util.Objects;

import com.zjht.channel.common.bean.ChannelMessage;
import com.zjht.channel.common.bean.Request;
import com.zjht.channel.common.bean.Response;
import com.zjht.channel.common.constant.Parameters;
import com.zjht.channel.common.constant.RespCode;
import com.zjht.channel.common.constant.Symbol;
import com.zjht.channel.common.helper.JsonHelper;
import com.zjht.channel.common.helper.StringHelper;
import com.zjht.channel.exception.InspectionException;

/**
 * ClassName: RRHelper <br/>
 * Function: 请求和响应相关处理辅助类. <br/>
 * date: 2015年8月28日 下午2:32:18 <br/>
 * 
 * @author jun yangwenjun@chinaexpresscard.com
 * @version v0.0.1
 * @since JDK 1.8
 */
public class RRHelper {

    /**
     * 将Vert.x的HttpServerRequest对象包装为自定义的Request对象. <br/>
     * 
     * @author jun yangwenjun@chinaexpresscard.com
     * @param httpRequest
     * @return
     * @since JDK 1.8
     */
    public static Request newRequest(HttpServerRequest httpRequest) {
        Request request = new Request();
        String ip         = "";// 请求者IP
        String msg        = "";// 请求具体信息
        String appno      = "";// 终端号
        String svrName    = "";// 服务名称
        String svrVersion = "";// 服务版本
        String[] svrInfo  = null;// 服务信息

        ip    = httpRequest.remoteAddress().host();
        appno = StringHelper.trim(httpRequest.getParam(Parameters.APPNO.getName()));
        msg   = StringHelper.trim(httpRequest.getParam(Parameters.MSG.getName()));
        svrInfo = StringHelper.split(httpRequest.path(), Symbol.SLASH.code());
        if(svrInfo.length==4){
            svrName = svrInfo[2];
            svrVersion = svrInfo[3];
        }
        request.setIdentifyId(SequenceHelper.next());
        request.setIp(ip);
        request.setAppno(appno);
        request.setMsg(msg);
        request.setSvrName(svrName);
        request.setSvrVersion(svrVersion);
        return request;
    }

    /**
     * 
     * 根据请求及响应结果创建相应对象.
     * 
     * @author jun yangwenjun@chinaexpresscard.com
     * @param request
     *            请求对象
     * @param content
     *            内容，具体响应信息
     * @param state
     *            状态，为了兼容旧版本接口
     * @param type
     *            类型，为了兼容旧版本接口
     * @return
     * @since JDK 1.8
     */
    public static <T>Response<T> newResponse(Request request, T content,
            String state, String type) {
        if(Objects.isNull(request)){
            request = new Request();
        }
        
        Response<T> resp = new Response<T>();
        resp.setIdentifyId(request.getIdentifyId());
        resp.setService(request.getSvrName());
        resp.setVersion(request.getSvrVersion());
        resp.setContent(content);
        resp.setState(state);
        resp.setType(type);
        return resp;
    }


    /**
     * 生成传给后端serice使用的Map集合. <br/>
     * 
     * @author jun yangwenjun@chinaexpresscard.com
     * @param request
     * @return
     * @since JDK 1.8
     */
    public static Map<String, String> requestParam(Request request) {
        ChannelMessage channelMsg  = null;
        Map<String, String> params = null;
        try{
            channelMsg = JsonHelper.fromJson(request.getMsg(), ChannelMessage.class);
            params = channelMsg.getContent();
        }catch(Exception e){
            throw new InspectionException(RespCode._00106.code(),
                    StringHelper.replace(RespCode._00106.message(), Parameters.MSG.getName()));
        }
        
        //设置额外参数
        params.put(Parameters.APPNO.getName(), request.getAppno());
        return params;
    }
}
