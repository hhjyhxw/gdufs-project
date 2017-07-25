package com.icloud.web.user.app;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.icloud.common.dto.vo.user.UserInfoVo;
import com.icloud.common.util.StringUtil;
import com.icloud.model.user.User;
import com.icloud.service.user.UserService;
import com.icloud.web.AppBaseController;

/**
 * @filename      : UserInfoController.java
 * @description   : 用户信息
 * @author        : zdh
 * @create        : 2017年6月9日 下午3:14:41   
 * @copyright     : zhumeng.com@gdufs-project
 *
 * Modification History:
 * Date             Author       Version
 * --------------------------------------
 */
@RestController
public class UserInfoController extends AppBaseController{

	@Autowired
	private UserService userService;
	
	public final static Logger log = LoggerFactory.getLogger(UserInfoController.class);
	
	
	/**个人中心**/
	@RequestMapping("/myCenter")
	public String  myCenter(HttpServletRequest request){
		JSONObject resultObj = new JSONObject();
		try{
			JSONObject  parameterObj = super.readToJSONObect(request);
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
			
			UserInfoVo vo = new UserInfoVo();
			vo.setDepartments(user.getUniversity());//院系
			vo.setProfessional(user.getMajor());//专业
			vo.setHeadImg(user.getHeardUrl());//头像
			log.equals("是否绑定 ：1.equals(user.getStatus()) && StringUtil.checkObj(user.getUid())===="
			+("1".equals(user.getStatus()) && StringUtil.checkObj(user.getUid())));
			vo.setIsBind("1".equals(user.getStatus()) && StringUtil.checkObj(user.getUid()));
			vo.setName(user.getNickName());
			vo.setUid(user.getUid());
			
			JSONObject resultData = new JSONObject();
			resultData.put("userInfo", vo);
			
			resultObj.put("errCode", "0000");
			resultObj.put("resultData", resultData);
			resultObj.put("resultMsg", "");
			return pakageJsonp(resultObj);
		}catch(Exception e){
			e.printStackTrace();
		}
		resultObj.put("errCode", "1001");
		resultObj.put("resultMsg", "系统内部出错");
		return pakageJsonp(resultObj);
	}
	
	
	/**解绑**/
	@RequestMapping("/unBind")
	public String  unBind(HttpServletRequest request){
		JSONObject resultObj = new JSONObject();
		try{
			JSONObject  parameterObj = super.readToJSONObect(request);
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
			//解绑
			int isSucess = userService.updateUnbind(user.getId());
			if(isSucess==1){
				resultObj.put("errCode", "0000");
				resultObj.put("resultMsg", "解绑成功");
				return pakageJsonp(resultObj);
			}else{
				resultObj.put("errCode", "0000");
				resultObj.put("resultMsg", "解绑失败");
				return pakageJsonp(resultObj);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		resultObj.put("errCode", "1001");
		resultObj.put("resultMsg", "系统内部出错");
		return pakageJsonp(resultObj);
	}
}
