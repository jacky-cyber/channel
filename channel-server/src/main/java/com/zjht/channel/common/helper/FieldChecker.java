/**
 * Project Name : channel-manager File Name : FieldChecker.java Package Name :
 * com.zjht.channel.manager.common.helper Date : Sep 17, 20151:17:57 PM
 * 
 */

package com.zjht.channel.common.helper;

import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import com.zjht.channel.common.annotation.FieldSpecification;
import com.zjht.channel.common.constant.RespCode;
import com.zjht.channel.exception.ExceptionHelper;
import com.zjht.channel.helper.common.AssertionHelper;
import com.zjht.channel.helper.common.ListHelper;
import com.zjht.channel.helper.common.StringHelper;

/**
 * ClassName: FieldChecker <br/>
 * Function: 字段检查类. <br/>
 * date: Sep 17, 2015 1:17:57 PM <br/>
 * 
 * @author jun yangwenjun@chinaexpresscard.com
 * @version v0.1
 * @since JDK 1.8
 */
public class FieldChecker {

    /**
     * 检查对象中有#FieldSpecification注解的字段是否合法
     * 
     * @author jun
     * @param t
     * @since JDK 1.8
     */
    public static <T> void check(T t) {
        AssertionHelper.check(!Objects.isNull(t), "检查对象不能为null");
        Stream.of(t.getClass().getDeclaredFields())
                .filter(field -> (field.getAnnotation(FieldSpecification.class) != null)
                        && field.getType().isAssignableFrom(String.class))
                .forEach(field -> {
                    FieldSpecification fieldSpec = field.getAnnotation(FieldSpecification.class);
                    field.setAccessible(true);
                    Object value = null;
                    try {
                        value = field.get(t);
                    } catch (Exception e) {
                        throw new RuntimeException(e.getMessage());
                    }

                    boolean required = fieldSpec.required();
                    int minLength = fieldSpec.minLength();
                    int maxLength = fieldSpec.maxLength();
                    int length = fieldSpec.length();
                    String name = fieldSpec.name();
                    String regex = fieldSpec.regex();
                    String[] range = fieldSpec.range();

                    if (required
                            && (Objects.isNull(value) || String.valueOf(value).length() == 0)) {
                        throw ExceptionHelper.newApplicationException(RespCode._A0001, name);
                    }

                    if (minLength > 0 && minLength > String.valueOf(value).length()) {
                        throw ExceptionHelper.newApplicationException(RespCode._A0002, name,
                                minLength);
                    }

                    if (maxLength > 0 && maxLength < String.valueOf(value).length()) {
                        throw ExceptionHelper.newApplicationException(RespCode._A0003, name,
                                maxLength);
                    }

                    if (length > -1 && length != String.valueOf(value).length()) {
                        throw ExceptionHelper.newApplicationException(RespCode._A0004, name,
                                length);
                    }

                    if ((!StringHelper.isEmpty(regex)
                            && !Pattern.compile(regex).matcher(String.valueOf(value)).matches())) {
                        throw ExceptionHelper.newApplicationException(RespCode._A0005, name, regex);
                    }

                    if (range.length > 0) {
                        List<String> s = ListHelper.listOf(range);
                        if (!s.contains(value)) {
                            throw ExceptionHelper.newApplicationException(RespCode._A0006, name,
                                    range);
                        }
                    }

                });
    }
}
