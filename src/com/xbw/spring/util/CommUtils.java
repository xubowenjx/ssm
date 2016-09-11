package com.xbw.spring.util;
/**
 * @ClassName: CommUtils   
 * @Description： 工具类
 * @author: xubowen
 * @date: 2015年9月6日 下午3:14:58   
 *
 */
public class CommUtils {
	/**
	 * @Title: isNull
	 * @Description:判断空字符串
	 * @Author: xubowen              
	 * @Create Date: 2015年9月6日 下午3:15:58
	 * @History: 2015年9月6日 下午3:15:58 xubowen Created.
	 *
	 * @param dest
	 * @return
	 *
	 */
	public static boolean null0blank(String dest){
		if(dest==null||"".equals(dest.trim())){
			return true;
		}
		return false;
	}
	/**
	 * @Title: ifNull
	 * @Description: 模仿Mysql ifnull函数
	 * @Author: xubowen              
	 * @Create Date: 2015年9月6日 下午3:16:34
	 * @History: 2015年9月6日 下午3:16:34 xubowen Created.
	 */
	public static  Object ifNull(Object obj ,Object repleaceObj){
		return obj==null?repleaceObj:obj;
	}
}
