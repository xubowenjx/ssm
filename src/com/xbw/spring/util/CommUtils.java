package com.xbw.spring.util;

import org.apache.log4j.Logger;

/**
 * @ClassName: CommUtils   
 * @Description： 工具类
 * @author: xubowen
 * @date: 2015年9月6日 下午3:14:58   
 *
 */
public class CommUtils {
	private CommUtils(){
		//private 
	}
	
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
	/**
	  * @Title: log 
	  * @Description: 记录异常日志
	  * @param e
	  * @param contex 
	  * @return void 
	  * @throws
	 */
	public static void log(Exception e,String contex){
		Logger log = Logger.getLogger("异常日志");
		if(!null0blank(contex)&&e!=null){
			log.error(contex, e);
		}else if(e!=null){
			log.error(e);
		}else{
			log.info(contex);
		}
	}
}
