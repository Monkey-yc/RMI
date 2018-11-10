package com.yc.RMI.model;

import java.io.Serializable;

public class UserAction implements IUserAction, Serializable{
	private static final long serialVersionUID = -5043786714102310236L;

	@Override
    public Usermodel getUserById(String Id) {
        Usermodel user = new Usermodel();
        user.setId("12138");
        user.setName("能成功");
        user.setSex(true);

        return user;
    }

}
