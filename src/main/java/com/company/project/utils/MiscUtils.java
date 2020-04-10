/**
 * Project Name:ezplatform
 * File Name:PluckUtils.java
 * Package Name:com.enmore.utils
 * Date:2016年8月3日下午2:28:50
 * Copyright (c) 2016, 上海易贸供应链管理有限公司版权所有.
 *
*/

package com.company.project.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * ClassName:PluckUtils <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Date: 2016年8月3日 下午2:28:50 <br/>
 * 
 * @author michaelgu
 * @version
 * @since JDK 1.6
 * @see
 */
public class MiscUtils {

	/**
	 * pluckListToArray:抓取List<T>中的指定属性列的数据. <br/>
	 *
	 * @author michaelgu
	 * @param list
	 * @param propertyName
	 * @param a
	 * @return
	 * @since JDK 1.6
	 */
	@SuppressWarnings("unchecked")
	public static <T> T[] pluckListToArray(List<?> list, String propertyName, T[] a) {

		List<Object> listPluck = new ArrayList<Object>();

		try {

			int iSize = list.size();

			for (int i = 0; i < iSize; i++) {

				Object o = list.get(i);

				Class<?> clazz = o.getClass();

				Field field = MiscUtils.getDeclaredField(clazz, propertyName);

				field.setAccessible(true);

				listPluck.add(field.get(o));
			}

			Object[] items = listPluck.toArray(new Object[0]);

			if (items != null) {

				if (a.length < items.length)
				    // Make a new array of a's runtime type, but my contents:
				    return (T[]) Arrays.copyOf(items, items.length, a.getClass());
				System.arraycopy(items, 0, a, 0, items.length);
				if (a.length > items.length)
					a[items.length] = null;
				return a;

			} else {

				return a;
			}

		} catch (NoSuchFieldException e) {

			return a;

		} catch (SecurityException e) {

			return a;

		} catch (IllegalArgumentException e) {

			return a;

		} catch (IllegalAccessException e) {

			return a;
		}
	}

	private static Field getDeclaredField(Class<?> clazz, String propertyName) throws NoSuchFieldException {

		try {

			return clazz.getDeclaredField(propertyName);

		} catch (NoSuchFieldException | SecurityException e) {

			if (Object.class.getName().equals(clazz.getSuperclass().getName())) {
				
				throw new NoSuchFieldException(e.getMessage());
				
			}else{
				
				return MiscUtils.getDeclaredField(clazz.getSuperclass(), propertyName);
			}
		}
	}

	/**
	 * pluckListToArray:抓取List<T>中的指定属性列的数据. <br/>
	 *
	 * @author michaelgu
	 * @param list
	 * @param propertyName
	 * @param a
	 * @return
	 * @since JDK 1.6
	 */
	public static <T> List<T> pluckList(List<?> list, String propertyName, T[] a) {

		return (List<T>) Arrays.asList(MiscUtils.pluckListToArray(list, propertyName, a));
	}

	/**
	 * copyList:复制一个List<T>到指定T的List<T>. <br/>
	 *
	 * @author michaelgu
	 * @param orig
	 * @param descClazz
	 * @param origClazz
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @since JDK 1.6
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> copyList(List<?> orig, Class<?> descClazz, Class<?> origClazz)
	        throws InstantiationException, IllegalAccessException, InvocationTargetException {
		
		if(orig == null) {
			
			return null;
			
		}else{

			@SuppressWarnings("rawtypes")
			List listDesc = new ArrayList<>();

			int iSize = orig.size();

			for (int i = 0; i < iSize; i++) {

				Object descInstance = descClazz.newInstance();
				
				Object o = orig.get(i);
				
				if(o != null){

	        BeanUtils.copyProperties(o, descInstance);
				}

        listDesc.add(descInstance);
			}

			return listDesc;
		}
	}


	/**
	 * copyBean:复制一个Bean的属性到另一个Bean <br/>
	 *
	 * @author michaelgu
	 * @return
	 * @throws BeansException
	 * @since JDK 1.6
	 */
	public static void copyBean(Object source, Object target) throws BeansException {
		BeanUtils.copyProperties(source, target);
	}

	/**
	 * POJO To Map <br/>
	 *
	 * @author michaelgu
	 * @return
	 * @throws BeansException
	 * @since JDK 1.6
	 */
	public static Map<String, String> beanToMap(Object object){
		Map<String, String> map = new HashMap<>();
		if(object == null){
			return map;
		}
		JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(object));
		if(jsonObject == null){
			return map;
		}
		Set<String> keySet = jsonObject.keySet();
		for(String key : keySet){
			map.put(key, jsonObject.getString(key));
		}
		return map;
	}
}
