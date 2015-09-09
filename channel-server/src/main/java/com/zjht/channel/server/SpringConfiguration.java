/** 
 * Project Name:channel-server 
 * File Name:SpringConfiguration.java 
 * Package Name:com.zjht.channel.server 
 * Date:2015年8月31日上午10:02:49 
 * 
 */

package com.zjht.channel.server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * ClassName: SpringConfiguration <br/>
 * Function: Spring注解配置类. <br/>
 * date: 2015年8月31日 上午10:02:49 <br/>
 * 
 * @author jun yangwenjun@chinaexpresscard.com
 * @version v0.0.1
 * @since JDK 1.8
 */
@Configuration
@ComponentScan(basePackages = "com.zjht.channel", includeFilters = {
        @Filter(type = FilterType.REGEX, pattern = ".configuration.*"),
        @Filter(type = FilterType.REGEX, pattern = ".inspector.*"),
        @Filter(type = FilterType.REGEX, pattern = ".server.*") })
@PropertySource("classpath:system.properties")
public class SpringConfiguration {

    /**
     * <b>propertySourcesPlaceholderConfigurer:</b><br/>
     * <p>
     * If you are trying to access the property values using @Value("")
     * annotation, you should declare PropertySourcesPlaceholderConfigurerBean.
     * </p>
     * <br/>
     * 
     * @author jun yangwenjun@chinaexpresscard.com
     * @return
     * @since JDK 1.8
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}