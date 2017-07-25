package com.icloud.service.comments.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.icloud.common.QueryBuilder;
import com.icloud.common.QueryBuilder.Criteria;
import com.icloud.common.util.StringUtil;
import com.icloud.dao.comments.CommentsMapper;
import com.icloud.model.comment.Comments;
import com.icloud.service.comments.CommentsService;

/**
 * @filename      : CommentsServiceImpl.java
 * @description   : 评论service
 * @author        : zdh
 * @create        : 2017年6月8日 下午7:01:03   
 * @copyright     : zhumeng.com@gdufs-project
 *
 * Modification History:
 * Date             Author       Version
 * --------------------------------------
 */
@Service
@Transactional
public class CommentsServiceImpl implements CommentsService{

	
	@Autowired
	private CommentsMapper commentsMapper;
	
	@Override
	public void save(Comments t) throws Exception {
		commentsMapper.insert(t);
	}

	@Override
	public void update(Comments t) throws Exception {
		commentsMapper.updateByPrimaryKey(t);
	}

	@Override
	public List<Comments> findList(Comments t) throws Exception {
		QueryBuilder example=new QueryBuilder();
		Criteria criteria = example.createCriteria();
		if(StringUtil.checkObj(t.getStatus())){
			criteria.andFieldEqualTo("status",t.getStatus());
		}
		return commentsMapper.selectByExample(example);
	}

	@Override
	public Integer findCount(Comments t) throws Exception {
		return null;
	}

	@Override
	public void delete(String id) throws Exception {
		commentsMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Comments findByKey(String id) throws Exception {
		return commentsMapper.selectByPrimaryKey(id);
	}

	@Override
	public PageInfo<Comments> findByPage(int pageNo, int pageSize, Comments t)
			throws Exception {
		PageHelper.startPage(pageNo, pageSize);
		QueryBuilder example=new QueryBuilder();
		Criteria criteria = example.createCriteria();
		if(StringUtil.checkObj(t.getStatus())){
			criteria.andFieldEqualTo("status",t.getStatus());
		}
		return new PageInfo<Comments>(commentsMapper.selectByExample(example));
	}

}
