/*
 * Copyright (c) 2011-2025 PiChen
 */

/*
 * The Spring Framework is published under the terms
 * of the Apache Software License.
 */

package com.interface21.beans.factory.support;

import com.interface21.beans.PropertyValues;

/**
 * Internal BeanFactory implementation class. This abstract base
 * class defines the BeanFactory type.Use a FactoryBean to
 * customize behaviour when returning application beans.
 * 内部BeanFactory实现类。这个抽象基类定义BeanFactory类型。使用FactoryBean自定义返回应用程序bean时的行为。
 * <p>
 * <p> A BeanDefinition describes a bean instance,
 * which has property values and further information supplied
 * by concrete classes or subinterfaces.
 * BeanDefinition描述了一个bean实例，该实例具有属性值和由具体类或子接口提供的更多信息。
 * <p>
 * <p>Once configuration is complete, a BeanFactory will be able
 * to return direct references to objects defined by
 * BeanDefinitions.
 * 一旦配置完成，BeanFactory将能够直接返回由BeanDefinitions定义的对象的引用。
 *
 * @author Rod Johnson
 * @version $Id$
 */
public abstract class AbstractBeanDefinition {

    /**
     * Is this a singleton bean?
     */
    private boolean singleton;

    /**
     * Property map
     */
    private PropertyValues pvs;

    /**
     * Creates new BeanDefinition
     *
     * @param pvs properties of the bean
     */
    protected AbstractBeanDefinition(PropertyValues pvs, boolean singleton) {
        this.pvs = pvs;
        this.singleton = singleton;
    }

    /**
     * Is this a <b>Singleton</b>, with a single, shared
     * instance returned on all calls,
     * or should the BeanFactory apply the <b>Prototype</b> design pattern,
     * with each caller requesting an instance getting an independent
     * instance? How this is defined will depend on the BeanFactory.
     * "Singletons" are the commoner type.
     *
     * @return whether this is a Singleton
     */
    public final boolean isSingleton() {
        return singleton;
    }


    /**
     * Return the PropertyValues to be applied to a new instance
     * of this bean.
     *
     * @return the PropertyValues to be applied to a new instance
     * of this bean
     */
    public PropertyValues getPropertyValues() {
        return pvs;
    }

    public void setPropertyValues(PropertyValues pvs) {
        this.pvs = pvs;
    }

    /**
     * @see Object#equals(Object)
     */
    public boolean equals(Object other) {
        if (!(other instanceof AbstractBeanDefinition))
            return false;
        AbstractBeanDefinition obd = (AbstractBeanDefinition) other;
        return this.singleton = obd.singleton &&
                this.pvs.changesSince(obd.pvs).getPropertyValues().length == 0;
    }

}
