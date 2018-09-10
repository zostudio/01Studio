package com.zos.security.rbac.support;

import java.io.Serializable;

/**
 * 性别
 *
 * @author 01Studio
 */
public enum Gender implements Serializable {

    /**
     * 男性
     */
    MALE("Male"),

    /**
     * 女性
     */
    FEMALE("Female");

    private String name;

    private Gender(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }


}
