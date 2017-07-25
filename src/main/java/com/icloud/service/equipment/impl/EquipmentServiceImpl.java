package com.icloud.service.equipment.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.icloud.common.QueryBuilder;
import com.icloud.common.QueryBuilder.Criteria;
import com.icloud.common.dto.vo.equipment.DataVo;
import com.icloud.common.dto.vo.equipment.EquipmentVo;
import com.icloud.common.util.StringUtil;
import com.icloud.dao.equipment.EquipmentMapper;
import com.icloud.model.equipment.Equipment;
import com.icloud.model.user.User;
import com.icloud.service.equipment.EquipmentService;

/**
 * @filename      : EquipmentServiceImpl.java
 * @description   : 设备保障service
 * @author        : zdh
 * @create        : 2017年6月8日 下午7:08:13   
 * @copyright     : zhumeng.com@gdufs-project
 *
 * Modification History:
 * Date             Author       Version
 * --------------------------------------
 */
@Service
@Transactional
public class EquipmentServiceImpl implements EquipmentService{

	@Autowired
	private EquipmentMapper equipmentMapper;
	@Override
	public void save(Equipment t) throws Exception {
		// TODO Auto-generated method stub
		equipmentMapper.insert(t);
	}

	@Override
	public void update(Equipment t) throws Exception {
		// TODO Auto-generated method stub
		equipmentMapper.updateByPrimaryKey(t);
	}

	@Override
	public List<Equipment> findList(Equipment t) throws Exception {
		QueryBuilder example=new QueryBuilder();
		Criteria criteria = example.createCriteria();
		if(StringUtil.checkObj(t.getStatus())){
			criteria.andFieldEqualTo("status",t.getStatus());
		}
		return equipmentMapper.selectByExample(example);
	}

	@Override
	public Integer findCount(Equipment t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String id) throws Exception {
		// TODO Auto-generated method stub
		equipmentMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Equipment findByKey(String id) throws Exception {
		// TODO Auto-generated method stub
		return equipmentMapper.selectByPrimaryKey(id);
	}

	@Override
	public PageInfo<Equipment> findByPage(int pageNo, int pageSize, Equipment t)
			throws Exception {
		PageHelper.startPage(pageNo, pageSize);
		QueryBuilder example=new QueryBuilder();
		Criteria criteria = example.createCriteria();
		if(StringUtil.checkObj(t.getStatus())){
			criteria.andFieldEqualTo("status",t.getStatus());
		}
		return new PageInfo<Equipment>(equipmentMapper.selectByExample(example));
	}

	@Override
	public DataVo mySubmitList(int pageNo, int pageSize, User user) {
		PageHelper.startPage(pageNo, pageSize);
		QueryBuilder example=new QueryBuilder();
		example.setOrderByClause("CREATE_TIME  DESC");
		Criteria criteria = example.createCriteria();
		if(StringUtil.checkObj(user.getOpenid())){
			criteria.andFieldEqualTo("openid",user.getOpenid());
		}
		
		PageInfo<Equipment> page =new PageInfo<Equipment>( equipmentMapper.selectByExample(example) );
		
		if( null != page.getList() && 0!=page.getList().size() ){
			//为了显示,进行数据转换
			DataVo data = new DataVo();
			List<EquipmentVo> voList = new ArrayList<EquipmentVo>();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			for (Equipment e : page.getList()) {
				EquipmentVo evo= new EquipmentVo();
				
				evo.setStatus(e.getStatus());
				evo.setSubmitTime( formatter.format(e.getCreateTime()) );
				evo.setSubmitreason( e.getDescbic() );
				voList.add(evo);
			}
			data.setList(voList);
			
			data.setPageSize(pageSize);
			data.setPageNum(pageNo);
			int num = page.getTotal()/pageSize + page.getTotal()%pageSize==0?0:1;
			if(page.getPages() == num){
				data.setHasMore(false);//用于前端控制分页的参数，是否还有更多
			}else{
				data.setHasMore(true);//用于前端控制分页的参数，是否还有更多
			}			
			data.setTotalNum(page.getTotal());
			return data;
		}
		return null;
	}

	@Override
	public void submitDisabled(Equipment equipment) {
		// TODO Auto-generated method stub
		equipmentMapper.insert(equipment);
	}
}
