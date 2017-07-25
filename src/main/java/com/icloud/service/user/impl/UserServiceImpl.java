package com.icloud.service.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.icloud.common.dto.UserSession;
import com.icloud.dao.user.UserMapper;
import com.icloud.model.user.User;
import com.icloud.service.redis.RedisService;
import com.icloud.service.user.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private RedisService redisService;

	@Override
	public void save(User t) throws Exception {
		userMapper.save(t);
	}

	@Override
	public void update(User t) throws Exception {
		userMapper.update(t);
	}

	@Override
	public PageInfo<User> findByPage(int pageNo, int pageSize, User t) throws Exception {
		PageHelper.startPage(pageNo, pageSize);
		List<User> list = userMapper.findForList(t);
		PageInfo<User> page = new PageInfo<User>(list);
		return page;
	}

	@Override
	public User findByKey(String id) throws Exception {
		return userMapper.findForObject(id);
	}

	

	@Override
	public User findByOpenId(String openId) {
		return userMapper.findByopenId(openId);
	}

	@Override
	public User getUserFromSession(String sessionKey) {
		UserSession session = (UserSession) redisService.get(sessionKey);
		if(null==session){
			return null;
		}
		String openId = session.getOpenId();
		return userMapper.findByopenId(openId);
		
	}

	
	@Override
	public List<User> findList(User t) throws Exception {
		return userMapper.findForList(t);
	}


	@Override
	public void delete(String id) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Integer findCount(User t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateUnbind(String id) {
		// TODO Auto-generated method stub
		return userMapper.updateUnbind(id);
	}
}
