/*
 * ValueObject.java
 *
 * Created on August 24, 2002, 5:40 PM
 */

package com.apress.javaedge.common;
import  org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 *
 * @author  John Carnell
 */
public abstract class ValueObject {
    
    /** Creates a new instance of ValueObject */
    public ValueObject() {
    }

    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    } 
    
}
