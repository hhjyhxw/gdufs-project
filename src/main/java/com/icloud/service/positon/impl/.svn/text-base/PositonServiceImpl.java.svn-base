package com.icloud.service.positon.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.icloud.common.QueryBuilder;
import com.icloud.common.QueryBuilder.Criteria;
import com.icloud.common.util.StringUtil;
import com.icloud.dao.positon.PositonMapper;
import com.icloud.model.positon.Positon;
import com.icloud.service.positon.PositonService;

/**
 * @filename      : PositonServiceImpl.java
 * @description   : 位置二维码管理
 * @author        : zdh
 * @create        : 2017年6月8日 下午7:14:56   
 * @copyright     : zhumeng.com@gdufs-project
 *
 * Modification History:
 * Date             Author       Version
 * --------------------------------------
 */
@Service
@Transactional
public class PositonServiceImpl implements PositonService{

	@Autowired
	private PositonMapper positonMapper;
	
	@Override
	public void save(Positon t) throws Exception {
		// TODO Auto-generated method stub
		positonMapper.insert(t);
	}

	@Override
	public void update(Positon t) throws Exception {
		// TODO Auto-generated method stub
		positonMapper.updateByPrimaryKey(t);
	}

	@Override
	public List<Positon> findList(Positon t) throws Exception {
		QueryBuilder example=new QueryBuilder();
		Criteria criteria = example.createCriteria();
		if(StringUtil.checkObj(t.getStatus())){
			criteria.andFieldEqualTo("status",t.getStatus());
		}
		return positonMapper.selectByExample(example);
	}

	@Override
	public Integer findCount(Positon t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String id) throws Exception {
		// TODO Auto-generated method stub
		positonMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Positon findByKey(String id) throws Exception {
		// TODO Auto-generated method stub
		return positonMapper.selectByPrimaryKey(id);
	}

	@Override
	public PageInfo<Positon> findByPage(int pageNo, int pageSize, Positon t)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
