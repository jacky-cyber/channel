/** 
 * Project Name:channel-server 
 * File Name:ErrorMessage.java 
 * Package Name:com.zjht.channel.common.constant 
 * Date:2015年8月28日下午2:03:40 
 * 
 */

package com.zjht.channel.common.constant;


/**
 * ClassName: RespCode <br/>
 * Function: 返回码及返回说明枚举类. <br/>
 * date: 2015年8月28日 下午2:03:40 <br/>
 * 
 * @author jun yangwenjun@chinaexpresscard.com
 * @version v0.0.1
 * @since JDK 1.8
 */
public enum RespCode {

	_00000("00000", "处理成功"),

	_A0001("A0001","[{}]的值不能为空"),   
	_A0002("A0002","[{}]的长度必须大于[{}]"),
	_A0003("A0003","[{}]的长度必须小于[{}]"),
	_A0004("A0003","[{}]的长度必须等于[{}]"),
	_A0005("A0005","[{}]必须满足\"{}\"规则"),
	_A0006("A0006","[{}]取值范围必须为\"{}\""),
	
	// 规则校验响应码定义  start
	_00100("00100", "IP地址[{}]没有访问服务的权限"),
	_00101("00101", "请求路径[{}]不合法，格式[{}]"),
	_00102("00102", "请求服务名称[{}]及其版本[{}]不存在"),
	_00103("00103", "非法终端号[{}]"),
	_00104("00104", "终端号[{}]没有访问服务名称[{}]及其版本[{}]的权限"),
	_00105("00105", "请求参数{}不能为空"),
	_00106("00106", "请求参数{}格式有误"),
	_00107("00107", "请求验签不通过"),
	// 规则校验响应码定义  end
	
	//服务调用响应吗定义 start
	_00200("00200", "服务列表不存在"),
	_00201("00201", "未发现名称[{}]及其版本[{}]的服务"),
	_00202("00202", "暂时无法访问名称[{}]及版本[{}]的服务，请稍后再试"),//调用服务时出现内部错误
	_00203("00203", "暂时无法访问名称[{}]及版本[{}]的服务，请稍后再试"),//服务无法访问
	//服务调用响应吗定义 end
	
	
	_00301("00301","zookeeper集群命名空间[{}]不存在节点[{}]"),
	_00302("00302","zookeeper集群命名空间[{}]已存在节点[{}]"),
	_00303("00303","不支持的ZNODE状态[{}]"),
	_00304("00304","zookeeper集群未发现[{}]的子节点"),
	
	_00401("00304","zookeeper集群未发现[{}]的子节点"),
	
	_99999("99999", "处理失败，请稍后重试");

	private String code;
	private String message;

	private RespCode(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String code(){
		return this.code;
	}

	public String message() {
		return this.message;
	}

}
