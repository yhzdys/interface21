/*
 * Copyright (c) 2011-2025 PiChen
 */

/**
 * Generic framework code included with
 * <a href="http://www.amazon.com/exec/obidos/tg/detail/-/1861007841/">Expert One-On-One J2EE Design and Development</a>
 * by Rod Johnson (Wrox, 2002).
 * This code is free to use and modify.
 * Please contact <a href="mailto:rod.johnson@interface21.com">rod.johnson@interface21.com</a>
 * for commercial support.
 */

package com.interface21.beans.factory;

import com.interface21.beans.BeansException;

/**
 * Interface to be implemented by objects that hold a number of bean definitions,
 * each uniquely identified by a String name. An independent instance of any of
 * these objects can be obtained (the Prototype design pattern), or a single
 * shared instance can be obtained (a superior alternative to the singleton
 * design pattern). Which type of instance will be returned depends on the bean
 * factory configuration - the API is the same. The Singleton approach is much
 * more useful and more common in practice.
 * 包含一组bean定义的对象实现的接口，每个bean定义都需要有一个唯一的名称。获取到的bean对象可能是单例的，
 * 也可能是独立的（原型）,这取决于具体的实现类,通常来说，单例的形式更常用.
 * <p>
 * <p>The point of this approach is that the BeanFactory is a central registry
 * of application components, and centralizes the configuring of application
 * components (no more do individual objects need to read properties files,
 * for example). See chapters 4 and 11 of "Expert One-on-One J2EE Design and
 * Development" for a discussion of the benefits of this approach.
 * 这种方法的重点是BeanFactory是应用程序组件的中央注册表，
 * 并集中了应用程序组件的配置（例如，不再需要单个对象读取属性文件）。
 * 有关此方法的好处的讨论，请参见“一对一J2EE专家设计和开发”的第4章和第11章。
 * <p>
 * <p>Normally a BeanFactory will load bean definitions stored in a configuration
 * source (such as an XML document), and use the com.interface21.beans package
 * to configure the beans. However, an implementation could simply return Java
 * objects it creates as necessary directly in Java code. There are no constraints
 * on how the definitions could be stored: LDAP, RDBMS, XML, properties file etc.
 * Implementations are encouraged to support references amongst beans, to either
 * Singletons or Prototypes.
 * 通常，BeanFactory将加载存储在配置源（例如XML文档）中的bean定义，并使用com.interface21.beans包来配置bean。
 * 但是，实现可以根据需要直接在Java代码中直接返回它创建的Java对象。
 * 定义的存储方式没有任何限制：LDAP，RDBMS，XML，属性文件等。
 * 鼓励实现以支持Bean之间对Singleton或Prototypes的引用。
 *
 * @author Rod Johnson
 * @version $Revision$
 * @since 13 April 2001
 */
public interface BeanFactory {

    /**
     * Return an instance (possibly shared or independent) of the given bean name.
     * This method allows a bean factory to be used as a replacement for
     * the Singleton or Prototype design pattern.
     * 返回给定bean名称的实例（可能是共享的或独立的）。
     * 这种方法允许将bean factory用作Singleton或Prototype设计模式的替代品。
     * <p>Note that callers should retain references to returned objects. There is
     * no guarantee that this method will be implemented to be efficient. For example,
     * it may be synchronized, or may need to run an RDBMS query.
     * 请注意，调用者应保留对返回对象的引用。 无法保证此方法将被有效实施。
     * 例如，它可能需要同步，或者可能需要运行RDBMS查询。
     *
     * @param name name of the bean to return  要返回的bean的名称
     * @return the instance of the bean  Bean的实例
     * @throws NoSuchBeanDefinitionException if there's no such bean definition
     */
    Object getBean(String name) throws BeansException;

    /**
     * Return an instance (possibly shared or independent) of the given bean name.
     * 返回给定bean名称的实例（可能是共享的或独立的）。
     * Provides a measure of type safety by throwing an exception if the bean is not
     * of the required type.
     * 如果bean不属于给定的类型，则通过引发异常来提供类型安全性的度量。
     * This method allows a bean factory to be used as a replacement for
     * the Singleton or Prototype design pattern.
     * 这种方法允许将bean factory用作Singleton或Prototype设计模式的替代品。
     * <p>Note that callers should retain references to returned objects. There is
     * no guarantee that this method will be implemented to be efficient. For example,
     * it may be synchronized, or may need to run an RDBMS query.
     * 请注意，调用者应保留对返回对象的引用。 无法保证此方法将被有效实施。
     * 例如，它可能需要同步，或者可能需要运行RDBMS查询。
     *
     * @param name         name of the bean to return  要返回的bean的名称
     * @param requiredType type the bean may match. Can be an interface or superclass
     *                     of the actual class. For example, if the value is Object.class, this method will
     *                     succeed whatever the class of the returned instance.
     *                     bean可能匹配的类型.可以是实际的类型的接口或者父类。
     *                     例如当值为Object.class时，不管bean的实际类型是什么，这个方法都会成功。
     * @return the instance of the bean  Bean的实例
     * @throws BeanNotOfRequiredTypeException if the bean is not of the required type
     * @throws NoSuchBeanDefinitionException  if there's no such bean definition
     */
    Object getBean(String name, Class requiredType) throws BeansException;

    /**
     * Is this bean a singleton? That is, will getBean() always return the same object?
     *
     * @param name name of the bean to query
     * @return is this bean a singleton
     * @throws NoSuchBeanDefinitionException if there is no bean with the given name
     */
    boolean isSingleton(String name) throws NoSuchBeanDefinitionException;

    /**
     * Return the aliases for the given bean name, if defined.
     * 返回给定bean的别名，如果有的话。
     *
     * @param name the bean name to check for aliases
     * @return the aliases, or an empty array if none
     * @throws NoSuchBeanDefinitionException if there's no such bean definition
     */
    String[] getAliases(String name) throws NoSuchBeanDefinitionException;

}
