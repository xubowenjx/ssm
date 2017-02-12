package com.xbw.spring.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FieldsCollector {
	private FieldsCollector(){
		//private 
	}
	/**
	 * @Title: getFileds
	 * @Description: 将Object转化为Map
	 * @Author: xubowen              
	 * @Create Date: 2016年4月1日 上午9:32:48
	 * @History: 2016年4月1日 上午9:32:48 xubowen Created.
	 *
	 * @param object
	 * @return
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 *
	 */
	public static Map<String, Object> getFileds(Object object)   {
		Class clazz = object.getClass();
		if(object instanceof Map){//参数是Map就直接返回
			return (Map<String, Object>) object;
		} 
		Field[] fields = clazz.getDeclaredFields();
		Map<String, Object> map = new HashMap<String, Object>();
		for (int i = 0,l = fields.length;i<l; i++) {
			Object resultObject = getPro(object, fields[i].getName()
					);
			map.put(fields[i].getName(), resultObject);
		}
		return map;
	}
	/**
	 * @Title: Map2Object
	 * @Description: 将map对象转化为需要的类实例
	 * @Author: xubowen              
	 * @Create Date: 2016年4月7日 下午6:12:51
	 * @History: 2016年4月7日 下午6:12:51 xubowen Created.
	 *
	 * @param map
	 * @param clazz
	 * @return
	 *
	 */
	public static  Object Map2Object( Map<String, Object> map,Class clazz)  {
		 Field[] fields = clazz.getDeclaredFields();
		 Object obj = null;
		try {
			obj = Class.forName(clazz.getName()).newInstance();
			Field f;
			for(int i=0,j=fields.length;i<j;i++){
					f=fields[i];
					Object t =map.get(fields[i].getName());
					if(t!=null){
						f.setAccessible(true);
						boolean b =  "true".equals(t)||"false".equals(t);
						f.set(obj,b?b:t);
					}
			 }
		} catch (Exception e) {
			CommUtils.log(e, null);
		} 
		
		return obj;
	}

	public static List<Map<String, Object>> getFileds(List<Object> objects) {
		List<Map<String,Object>> rs = new ArrayList<Map<String,Object>>();
		for(Object o:objects){
			 rs.add( getFileds(o));
		}
		return rs;
	}
	/**
	 * @Title: getPro
	 * @Description: 反射获取对象的属性值
	 * @Author: xubowen              
	 * @Create Date: 2016年4月7日 下午6:13:36
	 * @History: 2016年4月7日 下午6:13:36 xubowen Created.
	 *
	 * @param obj
	 * @param name
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 *
	 */
	public static  Object getPro(Object obj, String name)  {
			Field fields[] = obj.getClass().getDeclaredFields();// 获得对象所有属性
			Field field ;
			Object ret = null ;
			for (int i = 0; i < fields.length; i++) {
				field = fields[i];
				if (field.getName().equals(name)) {
					field.setAccessible(true);
					try {
						ret = field.get(obj);
					} catch (IllegalArgumentException e) {
						CommUtils.log(e, null);
					} catch (IllegalAccessException e) {
						CommUtils.log(e, null);
					}
					break;
				}
			}
			return ret;
		}
	 
}
