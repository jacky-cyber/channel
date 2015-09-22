///** 
// * Project Name:channel-manager 
// * File Name:SystemInitializer.java 
// * Package Name:com.zjht.channel.common 
// * Date:Sep 9, 20158:10:26 PM 
// * 
// *//*
//
//package com.zjht.channel.manager.server;
//
//import java.util.EnumSet;
//
//import javax.servlet.DispatcherType;
//import javax.servlet.FilterRegistration;
//import javax.servlet.ServletContext;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRegistration;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.WebApplicationInitializer;
//import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
//import org.springframework.web.filter.CharacterEncodingFilter;
//import org.springframework.web.servlet.DispatcherServlet;
//
//import com.opensymphony.sitemesh.webapp.SiteMeshFilter;
//
//*//**
// * ClassName: SystemInitializer <br/>
// * Function: servlet3支持的方式，通过编程方式注入servlet、filter以及listener等web.xml的配置项. <br/>
// * date: Sep 9, 2015 8:10:26 PM <br/>
// * 
// * @author jun yangwenjun@chinaexpresscard.com
// * @version v0.1
// * @since JDK 1.8
// *//*
//public class SystemInitializer implements WebApplicationInitializer {
//	 private final static Logger logger = LoggerFactory.getLogger(SystemInitializer.class);
//	 
//	*//**
//	 * @see org.springframework.web.WebApplicationInitializer#onStartup(javax.servlet.ServletContext)
//	 *//*
//	@Override
//	public void onStartup(ServletContext servletContext) throws ServletException {
//		initializeServlet(servletContext);
//		initializeFilter(servletContext);
//	}
//	
//	
//	*//** 
//	 * 初始化Filter. <br/> 
//	 * 
//	 * @author jun yangwenjun@chinaexpresscard.com
//	 * @param servletContext 
//	 * @since JDK 1.8
//	 *//*  
//	private void initializeFilter(ServletContext servletContext) {
//		logger.info("initializing sitmesh");
//		//sitemesh装饰器
//		FilterRegistration.Dynamic dynSiteMesh = servletContext.addFilter("sitemesh", SiteMeshFilter.class);
//		dynSiteMesh.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");
//		
//		logger.info("initializing Spring CharacterEncodingFilter");
//		//字符编码过滤器
//		FilterRegistration.Dynamic dynEncodingFilter = servletContext.addFilter("CharacterEncodingFilter", CharacterEncodingFilter.class);
//		dynEncodingFilter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
//		dynEncodingFilter.setInitParameter("encoding", "UTF-8");
//		dynEncodingFilter.setInitParameter("forceEncoding", "true");
//	}
//
//	*//** 
//	 * 初始化Servlet. <br/> 
//	 * 
//	 * @author jun yangwenjun@chinaexpresscard.com 
//	 * @since JDK 1.8
//	 *//*  
//	private void initializeServlet(ServletContext servletContext) {
//		
//		
//		logger.info("initializing Spring Container with AnnotationConfig");
//		AnnotationConfigWebApplicationContext webApplicationContext = new AnnotationConfigWebApplicationContext();
//		webApplicationContext.register(SpringConfiguration.class);
//		
//		logger.info("initializing SpringMVC DispatcherServlet");
//		DispatcherServlet dispatcherServlet = new DispatcherServlet(webApplicationContext);
//		ServletRegistration.Dynamic dynamic = servletContext.addServlet("dispatcherServlet", dispatcherServlet);
//		dynamic.setLoadOnStartup(1);
//        dynamic.addMapping("/");
//		
//	}
//}
//*/