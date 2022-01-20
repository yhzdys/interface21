/*
 * Copyright (c) 2011-2025 PiChen
 */

/*
 * The Spring Framework is published under the terms
 * of the Apache Software License.
 */

package com.interface21.util;

/**
 * 常量异常.
 *
 * @author Rod Johnson
 * @version $Id$
 * @see Constants
 * @since 28-Apr-2003
 */
public class ConstantException extends IllegalArgumentException {

    /**
     * Thrown when an invalid constant name is requested
     *
     * @param field   invalid constant name
     * @param clazz   class containing the constant definitions
     * @param message description of the problem
     */
    public ConstantException(String field, Class clazz, String message) {
        super("Field '" + field + "' " + message + " in " + clazz);
    }

}
