package com.zjht.channel.common.constant;

import java.util.List;

import com.zjht.channel.common.bean.FieldSpecification;
import com.zjht.channel.helper.common.ListHelper;

/**
 * TODO
 * 
 * @author jun
 * @version v0.1
 * @since JDK 1.8
 * @date Sep 23, 2015 2:12:42 PM
 */
public class ZKConfig {
    
    public static class INSPECTION {
        public final static String WHITELISTS_ROOT = "/inspection/whitelists";
        public final static String SECURITY_ROOT = "/inspection/security";
        public final static String PERMISSION_ROOT = "/inspection/permission";

    }

    public static class DUBBO {
        public final static String APPLICATION_ROOT = "/service/dubbo/application";
        public final static String REGISTRY_ROOT = "/service/dubbo/registry";
        public final static String CONSUMER_ROOT = "/service/dubbo/comsumer";
        public final static String REFERENCE_ROOT = "/service/dubbo/reference";

        public final static List<FieldSpecification> APPLICATION = ListHelper.listOf(
                new FieldSpecification().setName("name").setInfo("应用名称").setType(String.class).setRequired(true).setRange(ListHelper.listOf()),
                new FieldSpecification().setName("version").setInfo("应用版本").setType(String.class).setRequired(false).setRange(ListHelper.listOf()),
                new FieldSpecification().setName("owner").setInfo("应用负责人").setType(String.class).setRequired(false).setRange(ListHelper.listOf()),
                new FieldSpecification().setName("organization").setInfo("组织名称(部门)").setType(String.class).setRequired(false).setRange(ListHelper.listOf()),
                new FieldSpecification().setName("environment").setInfo("应用环境").setType(String.class).setRequired(false).setRange(ListHelper.listOf()),
                new FieldSpecification().setName("compiler").setInfo("Java字节码编译器").setType(String.class).setRequired(false).setRange(ListHelper.listOf("jdk","javassist")),
                new FieldSpecification().setName("logger").setInfo("日志输出方式").setType(String.class).setRequired(true).setRange(ListHelper.listOf("slf4j","jcl","log4j","jdk")));
    }

}
