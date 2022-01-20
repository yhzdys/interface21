/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.interface21.beans;

/**
 * Class to hold information and value for an individual property.
 * Using an object here, rather than just storing all properties in a
 * map keyed by property name, allows for more flexibility, and the
 * ability to handle indexed properties etc. if necessary.
 * 用于保存单个属性的信息和值的类。在这里使用对象，而不只是将所有属性存储在以属性名称为键的map中，
 * 可以提供更大的灵活性，并在必要时能够处理索引属性等。
 * <p>
 * <p>Note that the value doesn't need to be the final required type:
 * a BeanWrapper implementation should handle any necessary conversion,
 * as this object doesn't know anything about the objects it will be
 * applied to.
 * 请注意，value不必是最终所需的类型：BeanWrapper实现应处理任何必要的转换，因为此对象对将应用于的对象一无所知。
 *
 * @author Rod Johnson
 * @version $Id$
 * @since 13 May 2001
 */
public class PropertyValue {

    /**
     * Property name
     */
    private String name;

    /**
     * Value of the property
     */
    private Object value;

    /**
     * Creates new PropertyValue.
     *
     * @param name  name of the property
     * @param value value of the property (posibly before type conversion)
     */
    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    /**
     * Return the name of the property.
     *
     * @return the name of the property
     */
    public String getName() {
        return name;
    }

    /**
     * Return the value of the property.
     *
     * @return the value of the property. Type conversion
     * will probably not have occurred. It is the responsibility
     * of BeanWrapper implementations to perform type conversion.
     */
    public Object getValue() {
        return value;
    }

    public String toString() {
        return "PropertyValue: name='" + name + "'; value=[" + value + "]";
    }

    public boolean equals(Object other) {
        if (!(other instanceof PropertyValue))
            return false;
        PropertyValue pvOther = (PropertyValue) other;
        return this == other ||
                (this.name == pvOther.name && this.value == pvOther.value);
    }

}
