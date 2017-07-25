package com.icloud.web.activities.app;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.icloud.common.dto.vo.activities.ActivitiesVo;
import com.icloud.common.dto.vo.activities.DataVo;
import com.icloud.model.activities.Activities;
import com.icloud.model.user.User;
import com.icloud.service.activities.ActivitiesService;
import com.icloud.service.user.UserService;
import com.icloud.web.AppBaseController;

/**
 * @filename      : AppActivityController.java
 * @description   : 活动
 * @author        : zdh
 * @create        : 2017年6月9日 上午11:40:36   
 * @copyright     : zhumeng.com@gdufs-project
 *
 * Modification History:
 * Date             Author       Version
 * --------------------------------------
 */
@RestController
public class AppActivityController extends AppBaseController{

	public final static Logger log = LoggerFactory.getLogger(AppActivityController.class);
	@Autowired
	private ActivitiesService activitiesService;
	@Autowired
	private UserService userService;
	/**活动列表**/
	@RequestMapping("/activityList")
	public String  activityList(HttpServletRequest request){
		
		JSONObject resultObj = new JSONObject();
		try{
			JSONObject parameterObj = super.readToJSONObect(request);
			/*1、检验用户参数*/
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
			Integer pageNum = parameterObj.getInteger("pageNum")!=null?parameterObj.getInteger("pageNum"):0;
			Integer pageSize = parameterObj.getInteger("pageSize")!=null?parameterObj.getInteger("pageSize"):10;
			Activities activitiess = new Activities();
			activitiess.setStatus("1");
			PageInfo<Activities> page = activitiesService.findByPage(pageNum,pageSize,activitiess);
			List<Activities> list = page.getList();
			DataVo data = new DataVo();
			data.setHasMore(page.getPageNum()<page.getPages());
			data.setPageNum(pageNum);
			data.setPageSize(pageSize);
			data.setTotalNum(page.getTotal());
			if(list!=null && list.size()>0){
				List<ActivitiesVo> newList = new ArrayList<ActivitiesVo>();
				for (Activities activities : list) {
					ActivitiesVo vo = new ActivitiesVo();
					vo.setDesc(activities.getIntroduction());
					vo.setId(activities.getId());
					vo.setPicUrl(activities.getIcon());
					vo.setTitle(activities.getName());
					newList.add(vo);
				}
				data.setList(newList);
			}
			resultObj.put("errCode", "0000");
			resultObj.put("resultMsg", "查询成功");
			resultObj.put("resultData",data);
			return pakageJsonp(resultObj);
		}catch(Exception e){
			e.printStackTrace();
		}
		resultObj.put("errCode", "1001");
		resultObj.put("resultMsg", "系统内部出错");
		return pakageJsonp(resultObj);
	}
	
	/**banner列表**/
	@RequestMapping("/getBannerList")
	public String  getBannerList(HttpServletRequest request){
		
		JSONObject resultObj = new JSONObject();
		try{
			JSONObject parameterObj = super.readToJSONObect(request);
			/*1、检验用户参数*/
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
			Activities activitiess = new Activities();
			activitiess.setStatus("1");//可用的图片
			activitiess.setBanner("1");//作为首页的banner
			List<Activities> list = activitiesService.findList(activitiess);
			
			JSONObject resultData = new JSONObject();
			if(list!=null && list.size()>0){
				JSONArray array = new JSONArray();
				for (Activities activities : list) {
					JSONObject obj = new JSONObject();
					obj.put("picUrl", activities.getIcon());
					obj.put("activityId",activities.getId());
					array.add(obj);
				}
				resultData.put("list", array);
			}
			resultObj.put("errCode", "0000");
			resultObj.put("resultMsg", "查询成功");
			resultObj.put("resultData",resultData);
			return pakageJsonp(resultObj);
		}catch(Exception e){
			e.printStackTrace();
		}
		resultObj.put("errCode", "1001");
		resultObj.put("resultMsg", "系统内部出错");
		return pakageJsonp(resultObj);
	}
	
	/**活动详情**/
	@RequestMapping("/activityIdDetail")
	public String  activityIdDetail(HttpServletRequest request){
		
		JSONObject resultObj = new JSONObject();
		try{
			JSONObject parameterObj = super.readToJSONObect(request);
			/*1、检验用户参数*/
			if(!parameterObj.containsKey("sid") && !parameterObj.containsKey("activityId")){
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
			String activityId = parameterObj.getString("activityId");
			Activities activities = activitiesService.findByKey(activityId);
			
			JSONObject resultData = new JSONObject();
			if(activities!=null){
				JSONObject activityInfo = new JSONObject();
				//[url,url...]
				JSONArray array = new JSONArray();
				array.add(activities.getIcon());
				//活动对象
				activityInfo.put("picUrl", array);
				activityInfo.put("content", activities.getIntroduction());
				activityInfo.put("title", activities.getName());
				//resultData
				resultData.put("activityInfo", activityInfo);
			}
			resultObj.put("errCode", "0000");
			resultObj.put("resultMsg", "查询成功");
			resultObj.put("resultData",resultData);
			return pakageJsonp(resultObj);
		}catch(Exception e){
			e.printStackTrace();
		}
		resultObj.put("errCode", "1001");
		resultObj.put("resultMsg", "系统内部出错");
		return pakageJsonp(resultObj);
	}
}
