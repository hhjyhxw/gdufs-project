package com.icloud.web.signed.app;


import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.icloud.common.dto.vo.signed.DataVo;
import com.icloud.model.signed.Signed;
import com.icloud.model.user.User;
import com.icloud.service.signed.SignedService;
import com.icloud.service.user.UserService;
import com.icloud.web.AppBaseController;


/**
 * @filename      : SignedController.java
 * @description   : 签到
 * Modification History:
 * Date             Author       Version
 * --------------------------------------
 */

//
@RestController("signedApp")
public class SignedController extends AppBaseController{
	
	public final static Logger log = LoggerFactory.getLogger(SignedController.class);
	@Resource
	private SignedService signedService;
	@Resource
	private UserService userService;
	
/*	User user = new User();
	{
		user.setId("3");
		user.setRealName("刘德斐");
		user.setNickName("火云邪神");
		user.setEmail("934058456@qq.com");
		user.setPhone("17736618875");
		user.setUid("33");
		user.setPwd("33");
		user.setUnionid("33");
		user.setOpenid("33");
		user.setUniversity("清华");
		user.setId("3");
		user.setMajor("电");
		user.setGrades("六");
		user.setHobbies("足球");
		user.setRemainScore(10000L);
		user.setTotalScore(1000000L);
		user.setStatus("1");
	}*/
	/**
	 *我的签到记录
	 **/
	@RequestMapping("/signInRecord")
	public String  signInRecord(HttpServletRequest request){
		JSONObject resultObj = new JSONObject();
		try{
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
			
			DataVo data = signedService.signInRecord(pageNo,pageSize,user);
			
			if(null != data){
				resultObj.put("errCode", "0000");
				resultObj.put("resultMsg", "查询成功");
				resultObj.put("resultData", data);				
			}else{
				resultObj.put("errCode", "1001");
				resultObj.put("resultMsg", "没有记录");				
			}	
		}catch(Exception e){
			e.printStackTrace();
			resultObj.put("errCode", "1001");
			resultObj.put("resultMsg", "系统内部出错");
		}
		return pakageJsonp(resultObj);
	}
	/**
	 * 签到
	 **/
	@RequestMapping("/signUp")
	public String  signUp(HttpServletRequest request){
		JSONObject resultObj = new JSONObject();
		try {
			JSONObject parameterObj = super.readToJSONObect(request);
			//1、检验用户参数
			if(!parameterObj.containsKey("sid")){
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
			int count = signedService.selectSignedCountToday(user.getOpenid());
			if(count>0){
				resultObj.put("errCode", "30000");
				resultObj.put("resultMsg", "已签到");
				return pakageJsonp(resultObj);
			}
//			List<Signed> signedList = signedService.findList(t);
			Signed sign =new Signed();
			sign.setOpenid( user.getOpenid() );
			sign.setUnionid( user.getUnionid() );
			sign.setNickName(user.getNickName());
			sign.setHeaderUrl(user.getHeardUrl());
			sign.setCreateTime(new Date());
			
			signedService.signUp(sign,user);
			
			resultObj.put("errCode", "0000");
			resultObj.put("resultMsg", "添加成功");
			
		} catch (Exception e) {
			e.printStackTrace();
			resultObj.put("errCode", "1001");
			resultObj.put("resultMsg", "系统内部出错");
		}
		return pakageJsonp(resultObj);
	}	
}
