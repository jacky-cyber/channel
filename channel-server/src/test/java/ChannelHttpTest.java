/** 
 * File Name:HttpTest.java 
 * Package Name:com.junx.demo.jodd 
 * Date:2015年9月7日下午5:02:48 
 * 
 */

import java.security.MessageDigest;

import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import jodd.util.Base64;
 
/** 
 * ClassName: ChannelHttpTest <br/> 
 * Function: 渠道http接口测试. <br/> 
 * date: 2015年9月7日 下午5:02:48 <br/> 
 * 
 * @author jun yangwenjun@chinaexpresscard.com 
 * @version v0.0.1
 * @since JDK 1.8
 */
public class ChannelHttpTest {
    
    public static void main(String[] args) throws Exception {
        
        String service = "test1";
        String ver = "1.0";
//        String httpUrl = "http://172.16.104.147:9060/zjhtplatform/" + service + "/" + ver + "/?";
        String httpUrl = "http://192.168.23.1:9060/zjhtplatform/" + service + "/" + ver + "/?";
        
        String appNo = "ZJ150706";
        String key = "lA5ej/5Ykhz32p2UoSo9x8HHhgTEV6ek";
        // 得到body值
        String msg = "{" 
                + "\"service\":\""+service+"\","
                + "\"version\":\"1.0\"," + "\"type\":null,"
                + "\"content\":{},"
                + "\"state\":null" + "}";
        String sign = Base64.encodeToString(digest(key + msg + key));
        System.out.println(">>开始请求:");
        HttpResponse response = HttpRequest.get(httpUrl).query("sign", sign)
                .query("appNo", appNo).query("msg", msg).send();
        response.close();//此处要close();严重: java.io.IOException: 远程主机强迫关闭了一个现有的连接。
        System.out.println(">>"+service+ " Repsone:" + response.bodyText());
    }
    
    /**
     * 生成摘要
     * 
     * @param s
     * @return
     * @throws Exception
     */
    public final static byte[] digest(String s) throws Exception
    {

        byte[] btInput = s.getBytes("UTF-8");
        // 获得MD5摘要算法的 MessageDigest 对象
        MessageDigest mdInst = MessageDigest.getInstance("SHA-1");
        // 使用指定的字节更新摘要
        mdInst.update(btInput);
        return mdInst.digest();
    }
}
  