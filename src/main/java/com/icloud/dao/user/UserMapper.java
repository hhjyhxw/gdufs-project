package com.icloud.dao.user;

import com.icloud.dao.DAO;
import com.icloud.model.user.User;

public interface UserMapper extends DAO<User>{
	
	User findByopenId(String openId);
	
	int updateUnbind(String id);
}