///** 
// * Project Name:channel-manager 
// * File Name:SpringConfiguration.java 
// * Package Name:com.zjht.channel.server 
// * Date:Sep 9, 20158:14:27 PM 
// * 
// */
//
//package com.zjht.channel.manager.server;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Properties;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.FilterType;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
//import org.springframework.web.servlet.view.InternalResourceViewResolver;
//import org.springframework.web.servlet.view.JstlView;
//
//import com.zjht.channel.interceptor.SecurityInterceptor;
//import com.zjht.channel.manager.common.constant.Symbol;
//import com.zjht.channel.manager.common.helper.StringHelper;
//
///**
// * ClassName: SpringConfiguration <br/>
// * Function: Spring注解配置类. <br/>
// * date: Sep 9, 2015 8:14:27 PM <br/>
// * 
// * @author jun yangwenjun@chinaexpresscard.com
// * @version v0.1
// * @since JDK 1.8
// */
//@Configuration
//@EnableWebMvc
//@ComponentScan(basePackages = "com.zjht.channel.manager",useDefaultFilters=false, 
//includeFilters = {
//		@Filter(type = FilterType.REGEX, pattern = ".controller.*"),
//		@Filter(type = FilterType.REGEX, pattern = ".service.*") 
//		@ComponentScan.Filter(type = FilterType.ANNOTATION, value = {Controller.class})
//		})
//@PropertySource("classpath:channel-manager.properties")
//public class SpringConfiguration extends WebMvcConfigurerAdapter {
//	private final static Logger logger = LoggerFactory.getLogger(SpringConfiguration.class);
//	
//	@Value("${excludesURIs}")
//	private String excludesURIs;
//	
//	/**
//	 * 
//	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter#addResourceHandlers(org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry)
//	 */
//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
//	}
//	
//    /** 
//	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter#addInterceptors(org.springframework.web.servlet.config.annotation.InterceptorRegistry) 
//	 */
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		SecurityInterceptor interceptor = new SecurityInterceptor();
//		logger.debug("开始初始化安全检查排除URL");
//		List<String> excludesURIList = Arrays.asList(StringHelper.split(excludesURIs, Symbol.COMMA.code()));
//		logger.debug("完成初始化安全检查排除URL");
//		logger.debug("排除URI列表:{}",excludesURIList);
////		interceptor.setExcludesURIList(excludesURIList);
//		registry.addInterceptor(interceptor);
//	}
//
//
//	/**
//     * <b>propertySourcesPlaceholderConfigurer:</b><br/>
//     * <p>
//     * If you are trying to access the property values using @Value("")
//     * annotation, you should declare PropertySourcesPlaceholderConfigurerBean.
//     * </p>
//     * <br/>
//     * 
//     * @author jun yangwenjun@chinaexpresscard.com
//     * @return
//     * @since JDK 1.8
//     */
//    @Bean
//    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
//        return new PropertySourcesPlaceholderConfigurer();
//    }
//
//    
//    @Bean
//    public  InternalResourceViewResolver internalResourceViewResolver(){
//    	InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
//    	internalResourceViewResolver.setViewClass(JstlView.class);
//    	internalResourceViewResolver.setPrefix("/WEB-INF/views/");
//    	internalResourceViewResolver.setSuffix(".jsp");
//    	return internalResourceViewResolver;
//    }
//    
//    
//    /**
//     * 
//     * 异常处理器：系统运行时遇到指定的异常将会跳转到指定的页面. <br/> 
//     * TODO
//     * @author jun yangwenjun@chinaexpresscard.com
//     * @return 
//     * @since JDK 1.8
//     */
//    @Bean(name="exceptionResolver")  
//    public SimpleMappingExceptionResolver simpleMappingExceptionResolver(){  
//        SimpleMappingExceptionResolver simpleMappingExceptionResolver= new SimpleMappingExceptionResolver();  
//        simpleMappingExceptionResolver.setDefaultErrorView("common_error");  
//        simpleMappingExceptionResolver.setExceptionAttribute("exception");  
//        Properties properties = new Properties();  
//        properties.setProperty("java.lang.RuntimeException", "common_error");  
//        simpleMappingExceptionResolver.setExceptionMappings(properties);  
//        return simpleMappingExceptionResolver;  
//    }  
//
//}
