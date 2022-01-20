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

package com.interface21.context;

import java.util.Locale;

/**
 * Interface to be implemented by objects that can resolve messages.
 * 一个代表message解析能力的接口.
 * This enables parameterization and internationalization of messages.
 * 它赋予了对消息进行参数化和国际化的能力.
 *
 * @author Rod Johnson
 */
public interface MessageSource {

    /**
     * Try to resolve the message.Return default message if no message
     * was found.
     * 尝试解析消息。如果找不到消息，则返回默认消息.
     *
     * @param code           code to lookup up, such as 'calculator.noRateSet'.
     *                       用以查询的编码,例如'calculator.noRateSet'.
     *                       Users of this class are encouraged to base message names
     *                       on the relevant fully qualified class name, thus avoiding
     *                       conflict and ensuring maximum clarity.
     *                       推荐这个类的使用者构造与全限定类名相关的消息名称，从而避免冲突并在最大程度上保持清晰.
     * @param args           Array of arguments that will be filled in for params within
     *                       the message (params look like "{0}", "{1,date}", "{2,time}" within a message),
     *                       or null if none.
     *                       参数数组，用于填充消息体中的占位符，格式类似于"{0}", "{1,date}", "{2,time}" .
     * @param locale         Locale in which to do lookup.待查询的语言环境.
     * @param defaultMessage String to return if the lookup fails.查找失败时返回的字符串.
     * @return a resolved message if the lookup is successful;存在编码对应的消息时，返回解析后的消息体.
     * otherwise return the default message passed as a parameter.否则返回参数中传入的默认消息.
     * @see <a href="http://java.sun.com/j2se/1.3/docs/api/java/text/MessageFormat.html">java.text.MessageFormat</a>
     */
    String getMessage(String code, Object args[], String defaultMessage, Locale locale);

    /**
     * Try to resolve the message. Treat as an error if the message can't
     * be found.尝试解析消息。如果找不到消息，则抛出 NoSuchMessageException 异常.
     *
     * @param code   code to lookup up, such as 'calculator.noRateSet'.
     *               用以查询的编码,例如'calculator.noRateSet'.
     * @param args   Array of arguments that will be filled in for params within
     *               the message (params look like "{0}", "{1,date}", "{2,time}" within a message),
     *               or null if none.
     *               参数数组，用于填充消息体中的占位符，格式类似于"{0}", "{1,date}", "{2,time}" .
     * @param locale Locale in which to do lookup 待查询的语言环境.
     * @return message 存在编码对应的消息时，返回解析后的消息体.
     * @throws NoSuchMessageException not found in any locale. 在指定的语言环境中，查询不到编码对应的消息时，抛出异常.
     * @see <a href="http://java.sun.com/j2se/1.3/docs/api/java/text/MessageFormat.html">java.text.MessageFormat</a>
     */
    String getMessage(String code, Object args[], Locale locale) throws NoSuchMessageException;

    /**
     * <b>Using all the attributes contained within the <code>MessageSourceResolvable</code>
     * arg that was passed in (except for the <code>locale</code> attribute)</b>,
     * try to resolve the message from the <code>MessageSource</code> contained within the <code>Context</code>.<p>
     * NOTE: We must throw a <code>NoSuchMessageException</code> on this method since
     * at the time of calling this method we aren't able to determine if the <code>defaultMessage</code>
     * attribute is null or not.
     *
     * @param resolvable Value object storing 4 attributes required to properly resolve a message.
     * @param locale     Locale to be used as the "driver" to figuring out what message to return.
     * @return message Resolved message.
     * @throws NoSuchMessageException not found in any locale
     * @see <a href="http://java.sun.com/j2se/1.3/docs/api/java/text/MessageFormat.html">java.text.MessageFormat</a>
     */
    String getMessage(MessageSourceResolvable resolvable, Locale locale) throws NoSuchMessageException;
}

