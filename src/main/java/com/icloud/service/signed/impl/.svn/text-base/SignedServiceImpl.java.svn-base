package com.icloud.service.signed.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.icloud.common.QueryBuilder;
import com.icloud.common.QueryBuilder.Criteria;
import com.icloud.common.dto.vo.signed.DataVo;
import com.icloud.common.dto.vo.signed.SignedVo;
import com.icloud.common.util.ConfigUtil;
import com.icloud.common.util.StringUtil;
import com.icloud.dao.score.ScoreMapper;
import com.icloud.dao.signed.SignedMapper;
import com.icloud.model.score.Score;
import com.icloud.model.signed.Signed;
import com.icloud.model.user.User;
import com.icloud.service.signed.SignedService;
import com.icloud.service.user.UserService;

/**
 * @filename      : SignedServiceImpl.java
 * @description   : 签到service
 * @author        : zdh
 * @create        : 2017年6月8日 下午7:26:09   
 * @copyright     : zhumeng.com@gdufs-project
 *
 * Modification History:
 * Date             Author       Version
 * --------------------------------------
 */
@Service
@Transactional
public class SignedServiceImpl implements SignedService{

	@Autowired
	private SignedMapper signedMapper;
	
	@Resource
	private ScoreMapper scoreMapper;
	@Resource
	private UserService userService;
	
	@Override
	public void save(Signed t) throws Exception {
		// TODO Auto-generated method stub
		signedMapper.insert(t);
	}

	@Override
	public void update(Signed t) throws Exception {
		// TODO Auto-generated method stub
		signedMapper.updateByPrimaryKey(t);
	}

	@Override
	public List<Signed> findList(Signed t) throws Exception {
		QueryBuilder example=new QueryBuilder();
		Criteria criteria = example.createCriteria();
		if(StringUtil.checkObj(t.getOpenid())){
			criteria.andFieldEqualTo("openid",t.getOpenid());
		}
		if(StringUtil.checkObj(t.getCreateTime())){
			
		}
		return signedMapper.selectByExample(example);
	}

	@Override
	public Integer findCount(Signed t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String id) throws Exception {
		// TODO Auto-generated method stub
		signedMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Signed findByKey(String id) throws Exception {
		// TODO Auto-generated method stub
		return signedMapper.selectByPrimaryKey(id);
	}

	@Override
	public PageInfo<Signed> findByPage(int pageNo, int pageSize, Signed t)
			throws Exception {
		PageHelper.startPage(pageNo, pageSize);
		QueryBuilder example=new QueryBuilder();
		Criteria criteria = example.createCriteria();
		if(StringUtil.checkObj(t.getOpenid())){
			criteria.andFieldEqualTo("openid",t.getOpenid());
		}
		return new PageInfo<Signed>(signedMapper.selectByExample(example));
	}

	@Override
	public DataVo signInRecord(int pageNo, int pageSize, User user) {
		
		PageHelper.startPage(pageNo, pageSize);
		QueryBuilder example=new QueryBuilder();
		example.setOrderByClause("CREATE_TIME DESC");
		Criteria criteria = example.createCriteria();
		
		if(StringUtil.checkObj(user.getOpenid())){
			criteria.andFieldEqualTo("openid",user.getOpenid());
		}
		PageInfo<Signed> page =new PageInfo<Signed>( signedMapper.selectByExample(example) );
		if(null !=page.getList() && 0 !=page.getList().size() ){
			
			DataVo data= new  DataVo();
				data.setTotalScore(user.getTotalScore()!=null?user.getTotalScore():0);//积分流水表t_score
				data.setName(user.getRealName());
			List<SignedVo> voList = new ArrayList<SignedVo>();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			
			for (Signed signed : page.getList()) {
				SignedVo  signedVo = new SignedVo();
				
				signedVo.setSigninTime(formatter.format(signed.getCreateTime()));
				signedVo.setScore(Integer.parseInt(ConfigUtil.get("signed_score")));
				
				voList.add(signedVo);
			}
			data.setList(voList);
			data.setTotalNum(page.getTotal());
			
			int num = page.getTotal()/pageSize + page.getTotal()%pageSize==0?0:1;
			if(page.getPages() == num){
				data.setHasMore(false);//用于前端控制分页的参数，是否还有更多
			}else{
				data.setHasMore(true);//用于前端控制分页的参数，是否还有更多
			}
			data.setPageNo(pageNo);
			data.setPageSize(pageSize);	
			//今天是否签到 true false--没有冗余,需要查询数据库
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			if( format.format(new Date()).equals(format.format(page.getList().get(0).getCreateTime())) ){
				data.setSignToday(true);
			}else{
				data.setSignToday(false);
			}	
			return data;
		}
		return null;
	}

	@Override
	public void signUp(Signed sign,User user) {
		//添加签到记录
		signedMapper.insert(sign);
		
		//添加积分流水记录ConfigUtil.get获取数据
		Score score = new Score();
		score.setOpenid(user.getOpenid());
		score.setUnionid(user.getUnionid());
		score.setNickName(user.getNickName());
		score.setHeaderUrl(user.getHeardUrl());
		score.setFromMark("0");//来源标识 0 签到
		score.setDescbic( sign.getId() );//来源Id
		score.setScore(Integer.parseInt(ConfigUtil.get("signed_score")));
		score.setCreateTime(new Date());
		user.setTotalScore(user.getTotalScore()!=null?user.getTotalScore()+score.getScore():score.getScore());
		//添加积分流水记录
		scoreMapper.insert(score);
		try {
			userService.update(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int selectSignedCountToday(String openid) {
		// TODO Auto-generated method stub
		return signedMapper.selectSignedCountToday(openid);
	}
}
