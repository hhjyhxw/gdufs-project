package com.icloud.web.equipment.app;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.icloud.common.dto.vo.equipment.DataVo;
import com.icloud.model.equipment.Equipment;
import com.icloud.model.user.User;
import com.icloud.service.equipment.EquipmentService;
import com.icloud.service.user.UserService;
import com.icloud.web.AppBaseController;
import com.icloud.web.signed.app.SignedController;
/**
 * @filename      : EquipmentController.java
 * @description   : 签到
 * @create        : 2017年6月9日 下午4:19:28   
 * @copyright     : zhumeng.com@gdufs-project
 *
 * Modification History:
 * Date             Author       Version
 * --------------------------------------
 */
@RestController("equipmentApp")
public class EquipmentController extends AppBaseController{
	public final static Logger log = LoggerFactory.getLogger(SignedController.class);
	@Resource
	private EquipmentService equipmentService;
	
	@Resource
	private UserService userService;
	//测试代码
//	User user = new User();
//	{
//		user.setId("3");
//		user.setRealName("步惊云");
//		user.setNickName("火云邪神");
//		user.setEmail("934058456@qq.com");
//		user.setPhone("17736618875");
//		user.setUid("33");
//		user.setPwd("33");
//		user.setUnionid("33");
//		user.setOpenid("33");
//		user.setUniversity("清华");
//		user.setId("3");
//		user.setMajor("电");
//		user.setGrades("六");
//		user.setHobbies("足球");
//		user.setRemainScore(10000L);
//		user.setTotalScore(1000000L);
//		user.setStatus("1");
//	}
	
	@RequestMapping("/mySubmitList")
	public String  mySubmitList(HttpServletRequest request){
		JSONObject resultObj = new JSONObject();
		try {
			JSONObject parameterObj = super.readToJSONObect(request);
			/*1、检验用户参数--如果分页每页参数默认为第一页*/
			if( !parameterObj.containsKey("sid") ){
				
				resultObj.put("errCode", "1000");
				resultObj.put("resultMsg", "参数缺失");
				return pakageJsonp(resultObj);
			}
			String sessionKey = parameterObj.getString("sid");
			
			User user = userService.getUserFromSession(sessionKey);
			
			if(null==user){
				resultObj.put("errCode", "2000");//用户不存在
				resultObj.put("resultMsg", "用户不存在,请检查session_key");
				return pakageJsonp(resultObj);
			}
			
			Integer pageNo   = parameterObj.containsKey("pageNum")?parameterObj.getInteger("pageNum"):1;
			Integer pageSize = parameterObj.containsKey("pageSize")?parameterObj.getInteger("pageSize"):10;
			
			DataVo data =equipmentService.mySubmitList(pageNo,pageSize,user);
			
			if(null !=data){
				resultObj.put("errCode", "0000");
				resultObj.put("resultMsg", "查询成功");
				resultObj.put("resultData", data);				
			}else{
				resultObj.put("errCode", "1001");
				resultObj.put("resultMsg", "没有记录");				
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultObj.put("errCode", "1001");
			resultObj.put("resultMsg", "系统内部出错");
		}
		return pakageJsonp(resultObj);
	}
	///submitDisabled
	@RequestMapping("/submitDisabled")
	public String  submitDisabled(HttpServletRequest request){
		JSONObject resultObj = new JSONObject();
		try {
			JSONObject parameterObj = super.readToJSONObect(request);
			/*1、检验用户参数--如果分页每页参数默认为第一页*/
			if( !parameterObj.containsKey("sid") ){
				
				resultObj.put("errCode", "1000");
				resultObj.put("resultMsg", "参数缺失");
				return pakageJsonp(resultObj);
			}
			String sessionKey = parameterObj.getString("sid");
			
			User user = userService.getUserFromSession(sessionKey);
			if(null==user){
				resultObj.put("errCode", "2000");//用户不存在
				resultObj.put("resultMsg", "用户不存在,请检查session_key");
				return pakageJsonp(resultObj);
			}
			//创建"报障信息"
			Equipment   equipment = new Equipment();
			
						equipment.setMark(parameterObj.getString("type"));//申报原因标识
						equipment.setDescbic(parameterObj.getString("content"));//描述
						equipment.setOpenid(user.getOpenid());
						equipment.setUnionid(user.getUnionid());
						equipment.setNickName(user.getNickName());//昵称
						equipment.setHeaderUrl(user.getHeardUrl());//头像
						equipment.setCreateTime(new Date());
						equipment.setModifyDate(new Date());
						equipment.setStatus("0");
						
			//报障信息保存
			equipmentService.submitDisabled(equipment);			
			resultObj.put("errCode", "0000");
			resultObj.put("resultMsg", "添加成功");
		} catch (Exception e) {
			e.printStackTrace();
			resultObj.put("errCode", "1001");
			resultObj.put("resultMsg", "系统内部出错");
		}
		return pakageJsonp(resultObj);
	}	
	
	
	@RequestMapping("/getTypeList")
	public String  getTypeList(HttpServletRequest request){
		JSONObject resultObj = new JSONObject();
		try {
			JSONObject parameterObj = super.readToJSONObect(request);
			/*1、检验用户参数--如果分页每页参数默认为第一页*/
			if( !parameterObj.containsKey("sid") ){
				
				resultObj.put("errCode", "1000");
				resultObj.put("resultMsg", "参数缺失");
				return pakageJsonp(resultObj);
			}
			String sessionKey = parameterObj.getString("sid");
			
			User user = userService.getUserFromSession(sessionKey);
			if(null==user){
				resultObj.put("errCode", "2000");//用户不存在
				resultObj.put("resultMsg", "用户不存在,请检查session_key");
				return pakageJsonp(resultObj);
			}
		
			JSONObject resultData = new JSONObject();
		
				JSONArray array = new JSONArray();
				JSONObject obj = new JSONObject();
				obj.put("typeId", "1");
				obj.put("typeName","书架坏");
				array.add(obj);
				obj = new JSONObject();
				obj.put("typeId", "2");
				obj.put("typeName","书架摆放问题");
				array.add(obj);
				obj = new JSONObject();
				obj.put("typeId", "3");
				obj.put("typeName","书架栏目和书籍不对应");
				array.add(obj);
				obj = new JSONObject();
				obj.put("typeId", "4");
				obj.put("typeName","书籍发霉");
				array.add(obj);
				obj = new JSONObject();
				obj.put("typeId", "5");
				obj.put("typeName","其他");
				array.add(obj);
				
				resultData.put("list", array);
				resultObj.put("errCode", "0000");
				resultObj.put("resultMsg", "查询成功");
				resultObj.put("resultData", array);				
			
		} catch (Exception e) {
			resultObj.put("errCode", "1001");
			resultObj.put("resultMsg", "系统内部出错");
		}
		return pakageJsonp(resultObj);
	}
}
