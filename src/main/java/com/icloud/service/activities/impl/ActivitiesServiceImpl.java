package com.icloud.service.activities.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.icloud.common.QueryBuilder;
import com.icloud.common.QueryBuilder.Criteria;
import com.icloud.common.util.StringUtil;
import com.icloud.dao.activities.ActivitiesMapper;
import com.icloud.model.activities.Activities;
import com.icloud.service.activities.ActivitiesService;

/**
 * @filename      : ActivitiesServiceImpl.java
 * @description   : 活动service
 * @author        : zdh
 * @create        : 2017年6月8日 下午6:47:13   
 * @copyright     : zhumeng.com@gdufs-project
 *
 * Modification History:
 * Date             Author       Version
 * --------------------------------------
 */
@Service
@Transactional
public class ActivitiesServiceImpl implements ActivitiesService{

	@Autowired
	private ActivitiesMapper activitiesMapper;
	@Override
	public void save(Activities t) throws Exception {
		activitiesMapper.insert(t);
	}

	@Override
	public void update(Activities t) throws Exception {
		// TODO Auto-generated method stub
		activitiesMapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public List<Activities> findList(Activities t) throws Exception {
		QueryBuilder example=new QueryBuilder();
		Criteria criteria = example.createCriteria();
		if(StringUtil.checkObj(t.getStatus())){
			criteria.andFieldEqualTo("status",t.getStatus());
		}
		if(StringUtil.checkObj(t.getStatus())){
			criteria.andFieldEqualTo("banner",t.getBanner());
		}
		return activitiesMapper.selectByExample(example);
	}

	@Override
	public Integer findCount(Activities t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String id) throws Exception {
		// TODO Auto-generated method stub
		activitiesMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Activities findByKey(String id) throws Exception {
		return activitiesMapper.selectByPrimaryKey(id);
	}

	@Override
	public PageInfo<Activities> findByPage(int pageNo, int pageSize,
			Activities t) throws Exception {
		PageHelper.startPage(pageNo, pageSize);
		QueryBuilder example=new QueryBuilder();
		Criteria criteria = example.createCriteria();
		if(StringUtil.checkObj(t.getStatus())){
			criteria.andFieldEqualTo("status",t.getStatus());
		}
		if(StringUtil.checkObj(t.getBanner())){
			criteria.andFieldEqualTo("banner", t.getBanner());
		}
		if(StringUtil.checkObj(t.getName())){
			criteria.andFieldLike("name", "%"+t.getName()+"%");
		}
		example.setOrderByClause("modify_time");
		return new PageInfo<Activities>(activitiesMapper.selectByExample(example));
	}

}
