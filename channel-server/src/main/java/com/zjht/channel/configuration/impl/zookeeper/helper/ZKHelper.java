package com.zjht.channel.configuration.impl.zookeeper.helper;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;
import com.zjht.channel.common.constant.RespCode;
import com.zjht.channel.common.constant.Symbol;
import com.zjht.channel.configuration.impl.zookeeper.ZKClient;
import com.zjht.channel.configuration.impl.zookeeper.annotation.ZKNode;
import com.zjht.channel.configuration.impl.zookeeper.annotation.ZKPath;
import com.zjht.channel.configuration.impl.zookeeper.annotation.ZKPathVariable;
import com.zjht.channel.exception.ExceptionHelper;
import com.zjht.channel.helper.common.AssertionHelper;
import com.zjht.channel.helper.common.ListHelper;
import com.zjht.channel.helper.common.MapHelper;
import com.zjht.channel.helper.common.StringHelper;

/**
 * zookeeper操作辅助类
 * 
 * @author jun
 * @since JDK 1.8
 * @date Sep 23, 2015 10:02:01 PM
 */
public class ZKHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(ZKHelper.class);

    /**
     * 根据注解从zookeeper获取节点实例化对象
     * 
     * @author jun
     * @param clazz
     * @param zkClient
     * @return
     */
    public static <T> T nodeOf(Class<T> clazz, ZKClient zkClient) {
        Preconditions.checkArgument(!Objects.isNull(clazz), "Class can not be null!");
        Preconditions.checkArgument(!Objects.isNull(zkClient), "ZKClient can not be null!");

        ZKPath zkPath = clazz.getDeclaredAnnotation(ZKPath.class);
        String basePath = zkPath.value();
        T instance = null;
        try {
            instance = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e1) {
            e1.printStackTrace();
        }
        Field[] fields = clazz.getDeclaredFields();

        if (fields == null || fields.length == 0) {
            LOGGER.warn("class [{}] have no fileds!", clazz.getCanonicalName());
            return instance;
        }

        for (Field field : fields) {
            if (field.getDeclaredAnnotation(ZKNode.class) == null) {
                LOGGER.debug("字段[{}]在没有ZKNode注解标注，不处理", field);
                continue;
            }

            Class<?> type = field.getType();

            // 目前只支持处理以下字段类型
            // 1. java.lang.String
            // 2. java.lang.Boolean
            // 3. java.lang.Long
            // 4. java.lang.Integer
            if (!type.isAssignableFrom(String.class) 
                    && (!type.isAssignableFrom(Boolean.class) && !type.isAssignableFrom(boolean.class))  
                    && (!type.isAssignableFrom(Long.class) && !type.isAssignableFrom(long.class))
                    && (!type.isAssignableFrom(Integer.class) && !type.isAssignableFrom(int.class))) {
                throw new RuntimeException("不支持的字段类型[" + type.getName() + "]");
            }

            ZKNode zkNode = field.getDeclaredAnnotation(ZKNode.class);
            boolean required = zkNode.required();
            String path = field.getName();// 节点路径
            String name = zkNode.name();// 节点名称

            String regex = zkNode.regex();// 正则
            int length = zkNode.length();// 长度
            int minLength = zkNode.minLength();// 最小长度
            int maxLength = zkNode.maxLength();// 最大长度
            String[] range = zkNode.range();// 范围

            String data = "";// zookeeper上节点的数据

            // 1. 从zookeeper的节点获取数据
            try {
                data = zkClient.getData(basePath.concat(Symbol.SLASH.code()).concat(path));
                LOGGER.debug("data=[{}]",data);
            } catch (Exception e) {
                // 如果字段是必须的，获取节点数据出现时，则抛出
                if (required) {
                    throw new RuntimeException(e.getMessage());
                }
            }

            if(StringHelper.isEmpty(data)){
                LOGGER.warn("字段[{}]在zookeeper上没有设置!",name);
                data = zkNode.value();
            }
            
            if(StringHelper.isEmpty(data)){
                LOGGER.warn("字段[{}]没有设置任何默认值!",name);
                continue;
            }
            
            // 2. 校验数据
            if (type.isAssignableFrom(String.class) && required ) {
                if ((Objects.isNull(data) || String.valueOf(data).length() == 0)) {
                    throw ExceptionHelper.newApplicationException(RespCode._A0001, name);
                }

                if (minLength > 0 && minLength > String.valueOf(data).length()) {
                    throw ExceptionHelper.newApplicationException(RespCode._A0002, name, minLength);
                }

                if (maxLength > 0 && maxLength < String.valueOf(data).length()) {
                    throw ExceptionHelper.newApplicationException(RespCode._A0003, name, maxLength);
                }

                if (length > 0 && length != String.valueOf(data).length()) {
                    throw ExceptionHelper.newApplicationException(RespCode._A0004, name, length);
                }

                if ((!StringHelper.isEmpty(regex)
                        && !Pattern.compile(regex).matcher(String.valueOf(data)).matches())) {
                    throw ExceptionHelper.newApplicationException(RespCode._A0005, name, regex);
                }

                if (range.length > 0) {
                    List<String> s = ListHelper.listOf(range);
                    if (!s.contains(data)) {
                        throw ExceptionHelper.newApplicationException(RespCode._A0006, name, range);
                    }
                }
            }
            
            // 3. 设置值
            try {
                Method setter = clazz.getDeclaredMethod("set".concat(path.substring(0, 1).toUpperCase()).concat(path.substring(1)), type);

                if (type.isAssignableFrom(String.class)) {
                    setter.invoke(instance, String.valueOf(data));
                }

                if (type.isAssignableFrom(Long.class)||type.isAssignableFrom(long.class)) {
                    setter.invoke(instance, Long.valueOf(data));
                }

                if (type.isAssignableFrom(Integer.class)||type.isAssignableFrom(int.class)) {
                    setter.invoke(instance, Integer.valueOf(data));
                }

                if (type.isAssignableFrom(Boolean.class)||type.isAssignableFrom(boolean.class)) {
                    setter.invoke(instance, Boolean.valueOf(data));
                }
            } catch (Exception e) {
                LOGGER.error("",e);
                throw new RuntimeException(e.getMessage());
            }
        }
        return instance;
    }


    /**
     * 根据注解从zookeeper获取节点实例化对象
     * 
     * @author jun
     * @param clazz
     * @param zkClient
     * @return
     */
    public static <T> List<T> nodesOf(Class<T> clazz, ZKClient zkClient) {
        Preconditions.checkArgument(!Objects.isNull(clazz), "Class can not be null!");
        Preconditions.checkArgument(!Objects.isNull(zkClient), "ZKClient can not be null!");

        ZKPath zkPath = clazz.getDeclaredAnnotation(ZKPath.class);
        Preconditions.checkArgument(!Objects.isNull(zkPath), "class has no ZKPath annotation!");

        String basePath = zkPath.value();// zookeeper路径
        boolean isPathVariable = isPathVariable(basePath);// 是否路径变量

        Preconditions.checkArgument(isPathVariable, "not path varibale!!!");

        // 获取路径变量名称
        String[] pathVariables =
                StringHelper.hold(basePath, Symbol.OPEN_BRACE.code(), Symbol.CLOSE_BRACE.code());
        Map<String, Map<String, String>> nodeMap = MapHelper.newHashMap();
        nodeMap.put(getRootPath(basePath), MapHelper.newHashMap());
        for (int i = 0; i < pathVariables.length; i++) {
            nodeMap = loopPath(zkClient, pathVariables[i], nodeMap);
        }

        LOGGER.debug("PathVariable={}",nodeMap);
        
        Field[] fields = clazz.getDeclaredFields();
        if (fields == null || fields.length == 0) {
            LOGGER.warn("class [{}] have no fileds!", clazz.getCanonicalName());
            return null;
        }

        List<T> instances = ListHelper.newArrayList();
        nodeMap.forEach((bPath, pathVariableMap) -> {
            T instance = null;
            // 3. 设置值
            try {
                instance = clazz.newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }

            for (Field field : fields) {
                if (field.getDeclaredAnnotation(ZKNode.class) == null) {
                    LOGGER.debug("字段[{}]在没有ZKNode注解标注，不处理", field);
                    continue;
                }

                Class<?> type = field.getType();
                // 目前只支持处理以下字段类型
                // 1. java.lang.String
                // 2. java.lang.Boolean
                // 3. java.lang.Long
                // 4. java.lang.Integer
                if (!type.isAssignableFrom(String.class) 
                        && !type.isAssignableFrom(Boolean.class) && !type.isAssignableFrom(boolean.class)
                        && !type.isAssignableFrom(Long.class) && !type.isAssignableFrom(long.class)
                        && !type.isAssignableFrom(Integer.class) && !type.isAssignableFrom(int.class)) {
                    throw new RuntimeException("不支持的字段类型[" + type.getName() + "]");
                }
                ZKNode zkNode = field.getDeclaredAnnotation(ZKNode.class);
                boolean required = zkNode.required();
                String path = field.getName();// 节点路径名称
                String name = zkNode.name();// 节点中文名称

                String regex = zkNode.regex();// 正则
                int length = zkNode.length();// 长度
                int minLength = zkNode.minLength();// 最小长度
                int maxLength = zkNode.maxLength();// 最大长度
                String[] range = zkNode.range();// 范围
                
                String data = "";
                
                ZKPathVariable zkPathVariable = field.getDeclaredAnnotation(ZKPathVariable.class);
                if(!Objects.isNull(zkPathVariable)){
                    data  = pathVariableMap.get(zkPathVariable.value());
                }else{
                    // 1. 从zookeeper的节点获取数据
                    try {
                            data = zkClient.getData(createZPath(bPath, path));
                            LOGGER.debug("zookeeper节点[{}] 值[{}]",createZPath(bPath, path),data);
                    } catch (Exception e) {
                        // 如果字段是必须的，获取节点数据出现时，则抛出
                        if (required) {
                            throw new RuntimeException(e.getMessage());
                        }else{
                            LOGGER.warn("忽略的异常信息：{}"+e.getMessage());
                        }
                    }
                }
                
                if(StringHelper.isEmpty(data)){
                    LOGGER.warn("字段[{}]在zookeeper上没有设置!",name);
                    data = zkNode.value();
                }
                
                if(StringHelper.isEmpty(data)){
                    LOGGER.warn("字段[{}]没有设置任何默认值!",name);
                    continue;
                }
                
                // 2. 校验数据
                if (required && type.isAssignableFrom(String.class)) {
                    if ((Objects.isNull(data) || String.valueOf(data).length() == 0)) {
                        throw ExceptionHelper.newApplicationException(RespCode._A0001, name);
                    }

                    if (minLength > 0 && minLength > String.valueOf(data).length()) {
                        throw ExceptionHelper.newApplicationException(RespCode._A0002, name,
                                minLength);
                    }

                    if (maxLength > 0 && maxLength < String.valueOf(data).length()) {
                        throw ExceptionHelper.newApplicationException(RespCode._A0003, name,
                                maxLength);
                    }

                    if (length > 0 && length != String.valueOf(data).length()) {
                        throw ExceptionHelper.newApplicationException(RespCode._A0004, name,
                                length);
                    }

                    if ((!StringHelper.isEmpty(regex)
                            && !Pattern.compile(regex).matcher(String.valueOf(data)).matches())) {
                        throw ExceptionHelper.newApplicationException(RespCode._A0005, name, regex);
                    }

                    if (range.length > 0) {
                        List<String> s = ListHelper.listOf(range);
                        if (!s.contains(data)) {
                            throw ExceptionHelper.newApplicationException(RespCode._A0006, name,
                                    Arrays.toString(range));
                        }
                    }
                }

                // 3. 设置值
                try {
                    Method setter = clazz.getDeclaredMethod("set".concat(path.substring(0, 1).toUpperCase()).concat(path.substring(1)), type);
                    LOGGER.debug("设置值：{}",data);
                    if (type.isAssignableFrom(String.class)) {
                        setter.invoke(instance, String.valueOf(data));
                    }

                    if (type.isAssignableFrom(Long.class) || type.isAssignableFrom(long.class)) {
                        setter.invoke(instance, Long.valueOf(data));
                    }

                    if (type.isAssignableFrom(Integer.class) || type.isAssignableFrom(int.class)) {
                        setter.invoke(instance, Integer.valueOf(data));
                    }

                    if (type.isAssignableFrom(Boolean.class) || type.isAssignableFrom(boolean.class)) {
                        setter.invoke(instance, Boolean.valueOf(data));
                    }
                } catch (Exception e) {
                    LOGGER.error("异常:",e);
                    throw new RuntimeException(e.getMessage());
                }
            }
            instances.add(instance);
        });
        return instances;

    }

    /**
     * TODO
     * 
     * @author jun
     * @param basePath
     * @return
     */
    private static String getRootPath(String path) {
        AssertionHelper.check(!StringHelper.isEmpty(path), "path can not be empty!!!");
        if (isPathVariable(path)) {
            return path.substring(0, path.indexOf(Symbol.OPEN_BRACE.code()) - 1);
        }
        return path;
    }


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
        Stream.of(paths).filter(p -> !StringHelper.isEmpty(p)).forEach(p -> {
            if (p.indexOf(Symbol.SLASH.code()) == -1) {
                strBuilder.append(Symbol.SLASH.code());
            }
            strBuilder.append(p);
        });
        return strBuilder.toString();
    }

    /**
     * 判断是否是路径变量的格式，如/channe/{name}/{value} <b>注意:</b><br/>
     * 路径变量格式必须为{path}<br/>
     * 且路径变量必须连续,如/{name}/{value}才合法<br/>
     * 如下的格式：/channel/{name}/other/{value}不合法
     * 
     * @author jun
     * @param path
     * @return
     */
    private static boolean isPathVariable(String path) {
        Preconditions.checkArgument(!StringHelper.isEmpty(path), "path can not be null!!!");
        int leftBraceTimes = StringHelper.times(path, "{");
        int rightBraceTimes = StringHelper.times(path, "}");
        Preconditions.checkArgument(leftBraceTimes == rightBraceTimes, "invalid path!!!");
        if (leftBraceTimes <= 0) {
            return false;
        }
        return Pattern.compile("(/[{]{1}[\\w]+[}]{1}){" + String.valueOf(leftBraceTimes) + "}")
                .matcher(path).find();
    }

    public static void main(String[] args) {
        /*
         * String basePath = "/channel/{path1}/{path2}"; String[] pathVariables =
         * StringHelper.hold(basePath, Symbol.OPEN_BRACE.code(), Symbol.CLOSE_BRACE.code());
         * Map<String,Map<String,String>> nodeMap = MapHelper.newHashMap(); Map<String,String>
         * pathVariableMap = MapHelper.newHashMap(); nodeMap.put("/channel", pathVariableMap);
         * String name = ""; for (int i = 0; i < pathVariables.length; i++) { name =
         * pathVariables[i]; nodeMap = loopPath(name,nodeMap); } System.out.println(nodeMap);
         */

        System.out.println(isPathVariable("channel"));
        System.out.println(isPathVariable("{}/channle/{123123}/{123123}"));
        System.out.println(getRootPath("/channle"));
        System.out.println(getRootPath("{}/channle/{123123}/{123123}"));
    }


    @SuppressWarnings("serial")
    private static Map<String, List<String>> map = new HashMap<String, List<String>>() {
        {
            put("/channel", ListHelper.listOf("test1", "test2", "test3"));
            put("/channel/test1", ListHelper.listOf("1.0", "2.0"));
            put("/channel/test2", ListHelper.listOf("2.1", "2.2"));
            put("/channel/test3", ListHelper.listOf("3.1", "3.2"));
        }
    };



    /**
     * TODO
     * 
     * @author jun
     * @param name
     * @param nodeMap
     */
    private static Map<String, Map<String, String>> loopPath(String name,
            Map<String, Map<String, String>> parentNodeMap) {
        Map<String, Map<String, String>> nodeMap = MapHelper.newHashMap();
        parentNodeMap.forEach((path, pathVariableMap) -> {
            List<String> children = map.get(path);
            if (ListHelper.isEmpty(children)) {
                throw ExceptionHelper.newZookeeperException(RespCode._00304, path);
            }
            children.forEach(p -> {
                // 这里要实现深拷贝
                Map<String, String> pathVar = MapHelper.newHashMap();
                pathVar.putAll(pathVariableMap);
                pathVar.put(name, p);
                nodeMap.put(createZPath(path, p), pathVar);
            });
            nodeMap.remove(path);
        });
        return nodeMap;
    }


    /**
     * 生成子节点路径，即路径变量集合
     * 
     * @author jun
     * @param name
     * @param nodeMap
     */
    private static Map<String, Map<String, String>> loopPath(ZKClient zkClient, String name,
            Map<String, Map<String, String>> parentNodeMap) {
        Map<String, Map<String, String>> nodeMap = MapHelper.newHashMap();
        parentNodeMap.forEach((path, pathVariableMap) -> {
            List<String> children = zkClient.getChildren(path);
            if (ListHelper.isEmpty(children)) {
                throw ExceptionHelper.newZookeeperException(RespCode._00304, path);
            }
            children.forEach(p -> {
                // 这里要实现深拷贝
                Map<String, String> pathVar = MapHelper.newHashMap();
                pathVar.putAll(pathVariableMap);
                pathVar.put(name, p);
                nodeMap.put(createZPath(path, p), pathVar);
            });
            nodeMap.remove(path);
        });
        return nodeMap;
    }
}
