/** 
 * Project Name:channel-server 
 * File Name:Application.java 
 * Package Name:com.zjht.channel.service.bean 
 * Date:Sep 11, 20151:52:59 PM 
 * 
 */

package com.zjht.channel.service.bean;

/** 
 * ClassName: Application <br/> 
 * Function: dubbo的application配置信息. <br/> 
 * date: Sep 11, 2015 1:52:59 PM <br/> 
 * 
 * @author jun yangwenjun@chinaexpresscard.com 
 * @version v0.1
 * @since JDK 1.8
 */
public class Application {
	private String name;//当前应用名称
	private String version;//当前应用版本
	private String owner;//应用负责人
	private String organization;//组织名称(部门)
	private String architecture;//服务分层对应的架构
	private String environment;//应用环境，如：develop/test/product，不同环境使用不同的缺省值，以及作为只用于开发测试功能的限制条件 
	private String compiler;//java字节码编译器
	private String logger;//日志输出方式，可选：slf4j,jcl,log4j,jdk
	/** 
	 * name. 
	 * 
	 * @return  the name 
	 * @since   JDK 1.8
	 */
	public String getName() {
		return name;
	}
	/** 
	 * name. 
	 * 
	 * @param   name    the name to set 
	 * @since   JDK 1.8
	 */
	public void setName(String name) {
		this.name = name;
	}
	/** 
	 * version. 
	 * 
	 * @return  the version 
	 * @since   JDK 1.8
	 */
	public String getVersion() {
		return version;
	}
	/** 
	 * version. 
	 * 
	 * @param   version    the version to set 
	 * @since   JDK 1.8
	 */
	public void setVersion(String version) {
		this.version = version;
	}
	/** 
	 * owner. 
	 * 
	 * @return  the owner 
	 * @since   JDK 1.8
	 */
	public String getOwner() {
		return owner;
	}
	/** 
	 * owner. 
	 * 
	 * @param   owner    the owner to set 
	 * @since   JDK 1.8
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}
	/** 
	 * organization. 
	 * 
	 * @return  the organization 
	 * @since   JDK 1.8
	 */
	public String getOrganization() {
		return organization;
	}
	/** 
	 * organization. 
	 * 
	 * @param   organization    the organization to set 
	 * @since   JDK 1.8
	 */
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	/** 
	 * architecture. 
	 * 
	 * @return  the architecture 
	 * @since   JDK 1.8
	 */
	public String getArchitecture() {
		return architecture;
	}
	/** 
	 * architecture. 
	 * 
	 * @param   architecture    the architecture to set 
	 * @since   JDK 1.8
	 */
	public void setArchitecture(String architecture) {
		this.architecture = architecture;
	}
	/** 
	 * environment. 
	 * 
	 * @return  the environment 
	 * @since   JDK 1.8
	 */
	public String getEnvironment() {
		return environment;
	}
	/** 
	 * environment. 
	 * 
	 * @param   environment    the environment to set 
	 * @since   JDK 1.8
	 */
	public void setEnvironment(String environment) {
		this.environment = environment;
	}
	/** 
	 * compiler. 
	 * 
	 * @return  the compiler 
	 * @since   JDK 1.8
	 */
	public String getCompiler() {
		return compiler;
	}
	/** 
	 * compiler. 
	 * 
	 * @param   compiler    the compiler to set 
	 * @since   JDK 1.8
	 */
	public void setCompiler(String compiler) {
		this.compiler = compiler;
	}
	/** 
	 * logger. 
	 * 
	 * @return  the logger 
	 * @since   JDK 1.8
	 */
	public String getLogger() {
		return logger;
	}
	/** 
	 * logger. 
	 * 
	 * @param   logger    the logger to set 
	 * @since   JDK 1.8
	 */
	public void setLogger(String logger) {
		this.logger = logger;
	}
	/** 
	 * @see java.lang.Object#toString() 
	 */
	@Override
	public String toString() {
		return "Application [name=" + name + ", version=" + version + ", owner=" + owner + ", organization="
				+ organization + ", architecture=" + architecture + ", environment=" + environment + ", compiler="
				+ compiler + ", logger=" + logger + "]";
	}
}
