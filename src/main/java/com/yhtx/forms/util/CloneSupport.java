package com.yhtx.forms.util;

/**
 *克隆支持
 */
public class CloneSupport<T> implements Cloneable {

    @Override
    public T clone() {
        try {
            return (T) super.clone();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
