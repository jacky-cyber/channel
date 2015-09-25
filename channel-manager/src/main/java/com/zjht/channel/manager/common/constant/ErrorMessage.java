package com.zjht.channel.manager.common.constant;

/** 
 * 错误提示代码及信息定义
 * 
 * @author jun
 * @version v0.1
 * @since JDK 1.8
 * @date Sep 19, 2015 10:31:29 PM
 */
public enum ErrorMessage {
	
	_00000("00000","系统处理成功"),
	_A0001("A0001","[{}]的值不能为空"),	
	_A0002("A0002","[{}]的长度必须大于[{}]"),
	_A0003("A0003","[{}]的长度必须小于[{}]"),
	_A0004("A0004","[{}]的长度必须等于[{}]"),
	_A0005("A0005","[{}]必须满足\"{}\"规则"),
	
	_00101("00101","请输入用户名!"),
	_00102("00102","请输入密码!"),
	_00103("00103","用户不存在"),
	_00104("00104","用户名或密码错误!"),
	_00201("00201","未登录或session失效"),
	_00301("00301","IP地址不能为空"),
	
	_00401("00401","zookeeper集群命名空间[{}]不存在路径[{}]"),
	_00402("00402","zookeeper集群命名空间[{}]已存在路径[{}]"),
	_00403("00403","不支持的ZNODE状态[{}]"),
	
	_00501("00501","已存在服务名称[{}]和服务版本[{}]的记录"),
	_00502("00502","已存在服务接口[{}]和服务版本[{}]的记录"),
	
	_99999("99999","系统处理失败，请稍后再试");
	
	private String code;
	private String message;
	
	private ErrorMessage(String code,String message){
		this.code = code;
		this.message = message;
	}
	
	public String code(){
		return this.code;
	}
	
	public String message(){
		return this.message;
		
	}
}
