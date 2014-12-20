/*
 * $Id: ReflectUtil.java,v 1.1 2011/05/13 08:00:19 ganjp Exp $
 * 
 * Copyright 2011 the original author or authors.
 * This software is core of developing project and written by ganjianping. 
 */
package org.ganjp.core.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>反射方法处理工具</p>
 * 
 * @author ganjp
 * @since 1.0
 */
public class ReflectUtil {
	private static Logger log = LoggerFactory.getLogger(FileUtil.class);
	
	/**
	 * <p>从source对象复制值到object对象</p>
	 * 
	 * @param srcObject 源对象
	 * @param tgtObject 目标对象
	 * 
	 */
	public static void copyValue(Object srcObject, Object tgtObject) {
		if (srcObject == null || tgtObject == null)
			return;

		Field[] sourceFields = srcObject.getClass().getDeclaredFields();
		for (int i = 0; i < sourceFields.length; i++) {
			Field sourceField = sourceFields[i];
			Field objectField = null;
			try {
				objectField = tgtObject.getClass().getDeclaredField(sourceField.getName());
			} catch (Exception ex) {
				continue;
			}
			Object value = null;
			// 得到源对象字段的
			try {
				Method method = getGetMethod(srcObject.getClass(), sourceField.getName());
				value = method.invoke(srcObject, null);
			} catch (Exception e) {
				try {
					value = sourceField.get(srcObject);
				} catch (Exception ex) {
					log.error("获取方法" + sourceField.getName() + "的值失败" + ex.getMessage());
				}
			}
			// 设置目标对象的
			try {
				Method method = getSetMethod(tgtObject.getClass(), objectField.getName());
				Object[] values = new Object[1];
				values[0] = value;
				method.invoke(tgtObject, values);
			} catch (Exception e) {
				try {
					objectField.set(tgtObject, value);
				} catch (Exception ex) {
					log.error("设置方法" + sourceField.getName() + "的值失败" + ex.getMessage());
				}
			}
		}
	}

	/**
	 * <p>根据字段名得到get方法对象Method</p>
	 * 
	 * @param clazz		Class
	 * @param fieldName	字段名称
	 * @return Method
	 * 
	 * @throws NoSuchMethodException
	 */
	public static Method getGetMethod(Class clazz, String fieldName) throws NoSuchMethodException {
		String methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
		return clazz.getMethod(methodName, null);
	}

	/**
	 * <p>根据字段名得到set方法</p>
	 * 
	 * @param clazz
	 * @param fieldName	字段名称
	 * @return
	 * 
	 * @throws NoSuchFieldException
	 * @throws NoSuchMethodException
	 */
	public static Method getSetMethod(Class clazz, String fieldName) throws NoSuchMethodException, NoSuchFieldException {
		String methodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
		Class[] clazzz = new Class[1];
		clazzz[0] = clazz.getDeclaredField(fieldName).getType();
		return clazz.getMethod(methodName, clazzz);
	}
	
	/**
	 * 根据属性名称获取属性值
	 * 	1.只能获取定义了get方法的属性的属性值
	 *  2.支持多级获取，例如获取用户对象的所属组织的组织名称: getPropertityValue(user,"org.orgName)
	 * @param obj
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public static Object getPropertityValue(Object obj , String fieldName) throws Exception{
		if (obj == null || StringUtil.isBlank(fieldName)) {
			return null;
		}
		if (fieldName.indexOf("\\.")==-1) {
			Method method = ReflectUtil.getGetMethod(obj.getClass(), fieldName);
			return method.invoke(obj, null);
		} else {
			String[] fields = fieldName.split("\\.");
			if(fields.length == 0){
				return null;
			}
			Object result = obj;
			for (int i=0; i< fields.length;i++) {
				Method method = ReflectUtil.getGetMethod(result.getClass(), fields[i]);
				result = method.invoke(result, null);
			}
			return result;
		}
	}
	
	/**
	 * 编译器加载类要依靠classloader， 而classloader有3个级别，从高到低分别是BootClassLoader(jre/classes), ExtClassLoader(jre/lib/ext), AppClassLoader. 
     * Thread.currentThread().getContextClassLoader().loadClass(className) 是线程中的类加载器，直接调用起来效率最高，假设在这三个类加载器都找不到你的类
     * 直接用Class.forname()映射，这样就要多消耗资源了，一个线程调用资源开销不大，那要是几百个并发呢。
     * Perform resolution of a class name.
	 * <p/>
	 *
	 * @param name The class name
	 * @return The class reference.
	 * @throws ClassNotFoundException From {@link Class#forName(String)}.
	 */
	public static Class classForName(String name) throws ClassNotFoundException {
		try {
			ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
			if ( contextClassLoader != null ) {
				return contextClassLoader.loadClass(name);
			}
		} catch ( Throwable ignore ) {
			
		}
		return Class.forName( name );
	}
	
	/**
	 * Perform resolution of a class name.
	 * <p/>
	 * Here we first check the context classloader, if one, before delegating to
	 * {@link Class#forName(String, boolean, ClassLoader)} using the caller's classloader
	 *
	 * @param name The class name
	 * @param caller The class from which this call originated (in order to access that class's loader).
	 * @return The class reference.
	 * @throws ClassNotFoundException From {@link Class#forName(String, boolean, ClassLoader)}.
	 */
	public static Class classForName(String name, Class caller) throws ClassNotFoundException {
		try {
			ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
			if ( contextClassLoader != null ) {
				return contextClassLoader.loadClass( name );
			}
		}
		catch ( Throwable ignore ) {
		}
		return Class.forName( name, true, caller.getClassLoader() );
	}
	
	/**
	 * Invoke the specified {@link Method} against the supplied target object
	 * with the supplied arguments. The target object can be <code>null</code>
	 * when invoking a static {@link Method}.
	 * <p>Thrown exceptions are handled via a call to {@link #handleReflectionException}.
	 * @param method the method to invoke
	 * @param target the target object to invoke the method on
	 * @param args the invocation arguments (may be <code>null</code>)
	 * @return the invocation result, if any
	 */
	public static Object invokeMethod(Method method, Object target, Object[] args) {
		try {
			return method.invoke(target, args);
		}
		catch (Exception ex) {
			handleReflectionException(ex);
		}
		throw new IllegalStateException("Should never get here");
	}
	
	/**
	 * Handle the given reflection exception. Should only be called if
	 * no checked exception is expected to be thrown by the target method.
	 * <p>Throws the underlying RuntimeException or Error in case of an
	 * InvocationTargetException with such a root cause. Throws an
	 * IllegalStateException with an appropriate message else.
	 * @param ex the reflection exception to handle
	 */
	public static void handleReflectionException(Exception ex) {
		if (ex instanceof NoSuchMethodException) {
			throw new IllegalStateException("Method not found: " + ex.getMessage());
		}
		if (ex instanceof IllegalAccessException) {
			throw new IllegalStateException("Could not access method: " + ex.getMessage());
		}
		if (ex instanceof InvocationTargetException) {
			handleInvocationTargetException((InvocationTargetException) ex);
		}
		if (ex instanceof RuntimeException) {
			throw (RuntimeException) ex;
		}
		handleUnexpectedException(ex);
	}
	
	/**
	 * Handle the given invocation target exception. Should only be called if
	 * no checked exception is expected to be thrown by the target method.
	 * <p>Throws the underlying RuntimeException or Error in case of such
	 * a root cause. Throws an IllegalStateException else.
	 * @param ex the invocation target exception to handle
	 */
	public static void handleInvocationTargetException(InvocationTargetException ex) {
		rethrowRuntimeException(ex.getTargetException());
	}
	

	/**
	 * Rethrow the given {@link Throwable exception}, which is presumably the
	 * <em>target exception</em> of an {@link InvocationTargetException}.
	 * Should only be called if no checked exception is expected to be thrown by
	 * the target method.
	 * <p>Rethrows the underlying exception cast to an {@link RuntimeException}
	 * or {@link Error} if appropriate; otherwise, throws an
	 * {@link IllegalStateException}.
	 * @param ex the exception to rethrow
	 * @throws RuntimeException the rethrown exception
	 */
	public static void rethrowRuntimeException(Throwable ex) {
		if (ex instanceof RuntimeException) {
			throw (RuntimeException) ex;
		}
		if (ex instanceof Error) {
			throw (Error) ex;
		}
		handleUnexpectedException(ex);
	}
	
	/**
	 * Throws an IllegalStateException with the given exception as root cause.
	 * @param ex the unexpected exception
	 */
	private static void handleUnexpectedException(Throwable ex) {
		// Needs to avoid the chained constructor for JDK 1.4 compatibility.
		IllegalStateException isex = new IllegalStateException("Unexpected exception thrown");
		isex.initCause(ex);
		throw isex;
	}
}
