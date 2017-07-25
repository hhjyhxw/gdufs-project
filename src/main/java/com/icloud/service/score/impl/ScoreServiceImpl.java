package com.icloud.service.score.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.icloud.common.QueryBuilder;
import com.icloud.common.QueryBuilder.Criteria;
import com.icloud.common.util.StringUtil;
import com.icloud.dao.score.ScoreMapper;
import com.icloud.model.score.Score;
import com.icloud.service.score.ScoreService;

/**
 * @filename      : ScoreServiceImpl.java
 * @description   : 积分流水service
 * @author        : zdh
 * @create        : 2017年6月8日 下午7:18:29   
 * @copyright     : zhumeng.com@gdufs-project
 *
 * Modification History:
 * Date             Author       Version
 * --------------------------------------
 */
@Service
@Transactional
public class ScoreServiceImpl implements ScoreService{

	@Autowired
	private  ScoreMapper scoreMapper;
	
	@Override
	public void save(Score t) throws Exception {
		// TODO Auto-generated method stub
		scoreMapper.insert(t);
	}

	@Override
	public void update(Score t) throws Exception {
		// TODO Auto-generated method stub
		scoreMapper.updateByPrimaryKey(t);
	}

	@Override
	public List<Score> findList(Score t) throws Exception {
		QueryBuilder example=new QueryBuilder();
		Criteria criteria = example.createCriteria();
		if(StringUtil.checkObj(t.getOpenid())){
			criteria.andFieldEqualTo("openid",t.getOpenid());
		}
		return scoreMapper.selectByExample(example);
	}

	@Override
	public Integer findCount(Score t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String id) throws Exception {
		// TODO Auto-generated method stub
		scoreMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Score findByKey(String id) throws Exception {
		// TODO Auto-generated method stub
		return scoreMapper.selectByPrimaryKey(id);
	}

	@Override
	public PageInfo<Score> findByPage(int pageNo, int pageSize, Score t)
			throws Exception {
		PageHelper.startPage(pageNo, pageSize);
		QueryBuilder example=new QueryBuilder();
		Criteria criteria = example.createCriteria();
		if(StringUtil.checkObj(t.getOpenid())){
			criteria.andFieldEqualTo("openid",t.getOpenid());
		}
		return new PageInfo<Score>(scoreMapper.selectByExample(example));
	}

}
