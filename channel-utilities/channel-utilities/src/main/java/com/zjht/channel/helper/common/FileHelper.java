package com.zjht.channel.helper.common;


/**
 * 
 * @ClassName: FileHelper
 * @Description: 文件处理相关辅助类
 * @author jun/yangwenjun@chinaexpresscard.com
 * @date 2015年8月26日
 */
public final class FileHelper {
	
	/**
	 * 
	 * @Title: getClasspath
	 * @Description: 获取类路径
	 * @return String
	 * @throws
	 */
	public static String getClasspath(){
		return FileHelper.class.getResource("/").getPath();
	}
	
	public static void main(String[] args) {
		System.out.println(getClasspath());
	}
}	
