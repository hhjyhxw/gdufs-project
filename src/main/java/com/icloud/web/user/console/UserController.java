package com.icloud.web.user.console;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.icloud.common.ResponseUtils;
import com.icloud.model.user.User;
import com.icloud.service.user.UserService;
import com.icloud.web.BaseController;

@Controller
public class UserController extends BaseController<User> {
    
	@Autowired
	private UserService userService;
	
	@RequestMapping("/admin/user_list")
	@Override
	public String list(HttpServletRequest request, User t) throws Exception {
		PageInfo<User> page = userService.findByPage(1, 10, t);
		request.setAttribute("page", page);
		return "user/user_list";
	}

	@RequestMapping("/admin/user_getlist")
	@Override
	public String getList(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
			String uid = request.getParameter("uid");
			String realName = request.getParameter("realName");
			String status = request.getParameter("status");
			String pageNo = request.getParameter("pageNo");
			User u = new User();
			if(StringUtils.isNotBlank(realName))
			u.setRealName(realName);
			if(StringUtils.isNotBlank(uid))
			u.setUid(uid);
			if(StringUtils.isNotBlank(status))
			u.setStatus(status);
			PageInfo<User> page = userService.findByPage(StringUtils.isBlank(pageNo)?1:Integer.parseInt(pageNo), 10,u);
			List<User> list = page.getList();
			JSONObject resultObj = new JSONObject();
			if(null!=list&&list.size()>0){
				JSONArray array = new JSONArray();
				for(User uu:list){
					JSONObject obj = new JSONObject();
					obj.put("id", uu.getId());
					obj.put("realName", uu.getRealName());
					obj.put("headUrl", uu.getHeardUrl());
					obj.put("grade", uu.getGrades());
					obj.put("university", uu.getUniversity());
					obj.put("status",uu.getStatus());
					array.add(obj);
				}
				resultObj.put("errCode", "0000");
				resultObj.put("list", array);
			}else{
				resultObj.put("errCode", "0001");
			}
			ResponseUtils.renderJson(response, resultObj.toJSONString());
		     return null;
	}

	@RequestMapping("/admin/user_input")
	@Override
	public String toinput(String id, HttpServletRequest request)
			throws Exception {
		User u = userService.findByKey(id);
		request.setAttribute("user", u);
		return "user/user_detail";
	}

	@Override
	public String input(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String del(String id, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
