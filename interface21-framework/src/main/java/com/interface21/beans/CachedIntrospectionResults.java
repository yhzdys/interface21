/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.interface21.beans;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * Class to cache PropertyDescriptor information for a Java class.
 * 用于缓存Java类的PropertyDescriptor信息的类。
 * Package-visible; not used by application code.
 * 包可见；应用程序代码未使用。
 * <p>
 * <p>Necessary as Introspector.getBeanInfo() in JDK 1.3 will return a new
 * deep copy of the BeanInfo every time we ask for it. We take the opportunity
 * to hash property descriptors by method name for fast lookup.
 * 每次我们要求时，JDK 1.3中的Introspector.getBeanInfo（）都将返回BeanInfo的新的深层副本。我们借此机会通过方法名称对属性描述符进行散列以进行快速查找。
 * <p>
 * <p>Information is cached statically, so we don't need to create new
 * objects of this class for every JavaBean we manipulate. Thus this class
 * implements the factory design pattern, using a private constructor
 * and a public static forClass() method to obtain instances.
 * 信息是静态缓存的，因此我们不需要为我们处理的每个JavaBean创建此类的新对象。因此，此类使用私有构造函数和公共静态forClass（）方法来获取实例，从而实现工厂设计模式。
 *
 * @author Rod Johnson
 * @version $Revision$
 * @since 05 May 2001
 */
final class CachedIntrospectionResults {

    private static final Log logger = LogFactory.getLog(CachedIntrospectionResults.class);

    /**
     * Map keyed by class containing CachedIntrospectionResults or ReflectionException
     * class -> CachedIntrospectionResults / ReflectionException
     */
    private static Map $cache = new HashMap();

    /**
     * We might use this from the EJB tier, so we don't want to use
     * synchronization. Object references are atomic, so we
     * can live with doing the occasional unnecessary lookup at startup only.
     * 我们可能会从EJB层使用它，因此我们不想使用同步。对象引用是原子的，因此我们可以只在启动时进行偶尔的不必要查找
     */
    protected static CachedIntrospectionResults forClass(Class clazz) throws BeansException {
        Object o = $cache.get(clazz);
        if (o == null) {
            try {
                o = new CachedIntrospectionResults(clazz);
            } catch (BeansException ex) {
                o = ex;
            }
            $cache.put(clazz, o);
        } else {
            logger.debug("Using cached introspection results for class " + clazz);
        }

        // o is now an exception or CachedIntrospectionResults

        // We already have data for this class in the cache
        if (o instanceof BeansException)
            throw (BeansException) o;
        return (CachedIntrospectionResults) o;
    }

    private BeanInfo beanInfo;

    /**
     * Property descriptors keyed by property name
     * propertyName -> Property descriptors
     */
    private Map propertyDescriptorMap;

    /**
     * method descriptors keyed by method name
     * methodName -> method descriptors
     */
    private Map methodDescriptorMap;

    /**
     * Create new CachedIntrospectionResults instance fot the given class.
     * 为给定的类创建新的CachedIntrospectionResults实例。
     */
    private CachedIntrospectionResults(Class clazz) throws BeansException {
        try {
            logger.debug("Getting BeanInfo for class '" + clazz.getName() + "'");
            beanInfo = Introspector.getBeanInfo(clazz);

            logger.debug("Caching PropertyDescriptors for class '" + clazz.getName() + "'");
            propertyDescriptorMap = new HashMap();
            // This call is slow so we do it once
            PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
            for (int i = 0; i < pds.length; i++) {
                logger.debug("Found property [" + pds[i].getName() + "] of type [" + pds[i].getPropertyType() + "]; editor=[" + pds[i].getPropertyEditorClass() + "]");
                propertyDescriptorMap.put(pds[i].getName(), pds[i]);
            }

            logger.debug("Caching MethodDescriptors for class '" + clazz.getName() + "'");
            methodDescriptorMap = new HashMap();
            // This call is slow so we do it once
            MethodDescriptor[] mds = beanInfo.getMethodDescriptors();
            for (int i = 0; i < mds.length; i++) {
                logger.debug("Found method [" + mds[i].getName() + "] of type [" + mds[i].getMethod().getReturnType() + "]");
                methodDescriptorMap.put(mds[i].getName(), mds[i]);
            }
        } catch (IntrospectionException ex) {
            throw new FatalBeanException("Cannot get BeanInfo for object of class '" + clazz.getName() + "'", ex);
        }
    }

    protected BeanInfo getBeanInfo() {
        return beanInfo;
    }

    protected Class getBeanClass() {
        return beanInfo.getBeanDescriptor().getBeanClass();
    }

    protected PropertyDescriptor getPropertyDescriptor(String propertyName) throws BeansException {
        PropertyDescriptor pd = (PropertyDescriptor) propertyDescriptorMap.get(propertyName);
        if (pd == null)
            throw new FatalBeanException("No property [" + propertyName + "] in class [" + getBeanClass() + "]", null);
        return pd;
    }

    protected MethodDescriptor getMethodDescriptor(String methodName) throws BeansException {
        MethodDescriptor md = (MethodDescriptor) methodDescriptorMap.get(methodName);
        if (md == null)
            throw new FatalBeanException("No method [" + methodName + "] in class [" + getBeanClass() + "]", null);
        return md;
    }

}
