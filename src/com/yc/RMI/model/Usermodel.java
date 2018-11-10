package com.yc.RMI.model;

import java.io.Serializable;

public class Usermodel implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -9081150096553953427L;
	private String Id;
    private String name;
    private boolean sex;

    public Usermodel() {
    }

    public Usermodel(String id, String name, Boolean sex) {
        Id = id;
        this.name = name;
        this.sex = sex;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Id='" + Id + '\'' +
                ", name='" + name + '\'' +
                ", sex=" + (sex ? "男娃" : "女娃");
    }
}
