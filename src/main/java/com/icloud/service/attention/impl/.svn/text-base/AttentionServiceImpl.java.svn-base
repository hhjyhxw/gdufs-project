package com.icloud.service.attention.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.icloud.common.QueryBuilder;
import com.icloud.common.QueryBuilder.Criteria;
import com.icloud.common.util.StringUtil;
import com.icloud.dao.attention.AttentionMapper;
import com.icloud.model.attention.Attention;
import com.icloud.service.attention.AttentionService;

/**
 * @filename      : AttentionServiceImpl.java
 * @description   : 关注service
 * @author        : zdh
 * @create        : 2017年6月8日 下午6:55:11   
 * @copyright     : zhumeng.com@gdufs-project
 *
 * Modification History:
 * Date             Author       Version
 * --------------------------------------
 */
@Service
@Transactional
public class AttentionServiceImpl implements AttentionService{

	@Autowired
	private AttentionMapper attentionMapper;

	@Override
	public void save(Attention t) throws Exception {
		// TODO Auto-generated method stub
		attentionMapper.insert(t);
	}

	@Override
	public void update(Attention t) throws Exception {
		// TODO Auto-generated method stub
		attentionMapper.updateByPrimaryKey(t);
	}

	@Override
	public List<Attention> findList(Attention t) throws Exception {
		QueryBuilder example=new QueryBuilder();
		Criteria criteria = example.createCriteria();
		if(StringUtil.checkObj(t.getStatus())){
			criteria.andFieldEqualTo("status",t.getStatus());
		}
		if(StringUtil.checkObj(t.getBooknumber())){
			criteria.andFieldEqualTo("booknumber",t.getBooknumber());
		}
		if(StringUtil.checkObj(t.getOpenid())){
			criteria.andFieldEqualTo("openid",t.getOpenid());
		}
		return attentionMapper.selectByExample(example);
	}

	@Override
	public Integer findCount(Attention t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String id) throws Exception {
		// TODO Auto-generated method stub
		attentionMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Attention findByKey(String id) throws Exception {
		// TODO Auto-generated method stub
		return attentionMapper.selectByPrimaryKey(id);
	}

	@Override
	public PageInfo<Attention> findByPage(int pageNo, int pageSize, Attention t)
			throws Exception {
		PageHelper.startPage(pageNo, pageSize);
		QueryBuilder example=new QueryBuilder();
		Criteria criteria = example.createCriteria();
		if(StringUtil.checkObj(t.getStatus())){
			criteria.andFieldEqualTo("status",t.getStatus());
		}
		if(StringUtil.checkObj(t.getOpenid())){
			criteria.andFieldEqualTo("openid",t.getOpenid());
		}
		return new PageInfo<Attention>(attentionMapper.selectByExample(example));
	}
	

}
