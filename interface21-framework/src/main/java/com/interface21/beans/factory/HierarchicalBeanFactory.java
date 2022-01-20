/*
 * Copyright (c) 2011-2025 PiChen
 */

/*
 * The Spring Framework is published under the terms
 * of the Apache Software License.
 */

package com.interface21.beans.factory;

/**
 * Sub-interface implemented by bean factories that can be part
 * of a hierarchy.
 * 给那些有继承结构的bean工厂类实现的接口，表示有分层能力。
 *
 * @author Rod Johnson
 * @version $Id$
 * @since 07-Jul-2003
 */
public interface HierarchicalBeanFactory extends BeanFactory {

    /**
     * Returns the parent bean factory, or null if there is none.
     * 返回当前工厂类的父工厂，如果没有则返回null。
     *
     * @return the parent bean factory, or null if there is no parent
     */
    BeanFactory getParentBeanFactory();

}
