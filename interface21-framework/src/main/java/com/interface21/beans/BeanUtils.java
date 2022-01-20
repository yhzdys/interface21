/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.interface21.beans;

import java.beans.PropertyDescriptor;
import java.util.Arrays;
import java.util.List;

/**
 * Static convenience methods for JavaBeans.
 * Provides e.g. methods for sorting lists of beans by any property.
 * JavaBeans的静态便捷方法。提供例如通过任何属性对bean列表进行排序的方法。
 *
 * @author Rod Johnson
 * @author Juergen Hoeller
 */
public abstract class BeanUtils {

    /**
     * Convenience method to instantiate a class using its
     * no arg constructor. As this method doesn't try to load
     * classes by name, it should avoid class-loading issues.
     * 使用无参构造函数实例化类的便捷方法。由于此方法不会尝试按名称加载类，因此应避免出现类加载问题
     *
     * @param clazz class to instantiate.
     */
    public static Object instantiateClass(Class clazz) throws BeansException {
        try {
            //Object bean = Beans.instantiate(null, className);
            return clazz.newInstance();
        } catch (InstantiationException ex) {
            throw new FatalBeanException("Cannot instantiate [" + clazz + "]; is it an interface or an abstract class?", ex);
        } catch (IllegalAccessException ex) {
            throw new FatalBeanException("Cannot instantiate [" + clazz + "]; has class definition changed? Is there a public constructor?", ex);
        }
    }

    /**
     * Copy the property values of the given source bean into the target bean.
     *
     * @param source source bean
     * @param target target bean
     * @throws IllegalArgumentException if the classes of source and target do not match
     */
    public static void copyProperties(Object source, Object target)
            throws IllegalArgumentException, BeansException {
        copyProperties(source, target, null);
    }

    /**
     * Copy the property values of the given source bean into the given target bean,
     * ignoring the given ignoreProperties.
     *
     * @param source           source bean
     * @param target           target bean
     * @param ignoreProperties array of property names to ignore
     * @throws IllegalArgumentException if the classes of source and target do not match
     */
    public static void copyProperties(Object source, Object target, String[] ignoreProperties)
            throws IllegalArgumentException, BeansException {
        if (source == null || target == null || !source.getClass().isInstance(target)) {
            throw new IllegalArgumentException("Target must an instance of source");
        }
        List ignoreList = (ignoreProperties != null) ? Arrays.asList(ignoreProperties) : null;
        BeanWrapper sourceBw = new BeanWrapperImpl(source);
        BeanWrapper targetBw = new BeanWrapperImpl(target);
        MutablePropertyValues values = new MutablePropertyValues();
        for (int i = 0; i < sourceBw.getPropertyDescriptors().length; i++) {
            PropertyDescriptor sourceDesc = sourceBw.getPropertyDescriptors()[i];
            String name = sourceDesc.getName();
            PropertyDescriptor targetDesc = targetBw.getPropertyDescriptor(name);
            if (targetDesc.getWriteMethod() != null &&
                    (ignoreProperties == null || (!ignoreList.contains(name)))) {
                values.addPropertyValue(new PropertyValue(name, sourceBw.getPropertyValue(name)));
            }
        }
        targetBw.setPropertyValues(values);
    }

}
