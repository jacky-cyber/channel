/**
 * Project Name : channel-manager File Name : FieldChecker.java Package Name :
 * com.zjht.channel.manager.common.helper Date : Sep 17, 20151:17:57 PM
 * 
 */

package com.zjht.channel.manager.common.helper;

import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import com.zjht.channel.helper.common.AssertionHelper;
import com.zjht.channel.helper.common.StringHelper;
import com.zjht.channel.manager.common.annotation.FieldSpecification;
import com.zjht.channel.manager.common.constant.ErrorMessage;
import com.zjht.channel.manager.exception.helper.ExceptionHelper;

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
                    String info = fieldSpec.name();
                    String regex = fieldSpec.regex();

                    if (required
                            && (Objects.isNull(value) || String.valueOf(value).length() == 0)) {
                        throw ExceptionHelper.newIllegalParameterException(ErrorMessage._A0001,
                                info);
                    }

                    if (minLength > 0 && minLength > String.valueOf(value).length()) {
                        throw ExceptionHelper.newIllegalParameterException(ErrorMessage._A0002,
                                info, minLength);
                    }

                    if (maxLength > 0 && maxLength < String.valueOf(value).length()) {
                        throw ExceptionHelper.newIllegalParameterException(ErrorMessage._A0003,
                                info, maxLength);
                    }

                    if (length > -1 && length != String.valueOf(value).length()) {
                        throw ExceptionHelper.newIllegalParameterException(ErrorMessage._A0004,
                                info, length);
                    }

                    if ((!StringHelper.isEmpty(regex)
                            && !Pattern.compile(regex).matcher(String.valueOf(value)).matches())) {
                        throw ExceptionHelper.newIllegalParameterException(ErrorMessage._A0005,
                                info, regex);
                    }
                });
    }
}
