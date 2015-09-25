package com.zjht.channel.manager.zookeeper.helper;

import java.util.stream.Stream;

import com.zjht.channel.helper.common.StringHelper;
import com.zjht.channel.manager.common.constant.Symbol;

/**
 * zookeeper相关处理辅助类
 * 
 * @author jun
 * @version v0.1
 * @since JDK 1.8
 * @date Sep 21, 2015 4:30:23 PM
 */
public final class ZKHelper {


    /**
     * 创建zookeeper node路径
     * 
     * @author jun
     * @param paths
     * @return
     * @since JDK 1.8
     */
    public static String createZPath(String... paths) {
        StringBuilder strBuilder = new StringBuilder();
        Stream.of(paths).filter(p->!StringHelper.isEmpty(p)).forEach(p -> {
            if(p.indexOf(Symbol.SLASH.code())==-1){
                strBuilder.append(Symbol.SLASH.code());
            }
            strBuilder.append(p);
        });
        return strBuilder.toString();
    }

    /**
     * 将路径转换为最后一个节点名称<br/>
     * <b>示例：<b/><br/>
     * 
     * <pre>
     * convert("/home/jun") ==> jun
     * convert("/home/zookeeper/channel") ==> channel
     * </pre>
     * 
     * @author jun
     * @param zpath
     * @return
     * @since JDK 1.8
     */
    public static String getNodeName(String zpath) {
        if (StringHelper.isEmpty(zpath)) {
            return "";
        }

        if (zpath.indexOf(Symbol.SLASH.code()) == -1) {
            return zpath;
        }

        return zpath.substring(zpath.lastIndexOf(Symbol.SLASH.code()) + 1);
    }

    public static void main(String[] args) {
        System.out.println(ZKHelper.createZPath("channel", "23213"));
    }
}
