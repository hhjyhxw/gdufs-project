package com.icloud.web.activities.console;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.icloud.common.ResponseUtils;
import com.icloud.model.activities.Activities;
import com.icloud.service.activities.ActivitiesService;
import com.icloud.web.BaseController;

@Controller
public class ActivityController extends BaseController<Activities> {
	
	@Autowired
	private ActivitiesService activitiesService;

	@RequestMapping("/admin/activities_list")
	@Override
	public String list(HttpServletRequest request, Activities t)
			throws Exception {
		PageInfo<Activities> page = activitiesService.findByPage(1, 10, t);
		request.setAttribute("page", page);
		return "activities/activities_list";
	}

	
	@RequestMapping("/admin/activities_getList")
	@Override
	public String getList(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String name = request.getParameter("name");
		String status = request.getParameter("status");
		String banner = request.getParameter("banner");
		String pageNo = request.getParameter("pageNo");
		Activities ac = new Activities();
		if(StringUtils.isNotBlank(name)){
			ac.setName(name);
		}
		if(StringUtils.isNotBlank(status)){
			ac.setStatus(status);
		}
		if(StringUtils.isNotBlank(banner)){
			ac.setBanner(banner);
		}
		PageInfo<Activities> page = activitiesService.findByPage(StringUtils.isNotBlank(pageNo)?Integer.parseInt(pageNo):1, 10, ac);
		List<Activities> list = page.getList();
		JSONObject resultObj = new JSONObject();
		if(null!=list&&list.size()>0){
			JSONArray array = new JSONArray();
			for(Activities a:list){
				JSONObject obj = new JSONObject();
				obj.put("name", a.getName());
				obj.put("status", a.getStatus());
				obj.put("icon", a.getIcon());
				obj.put("id", a.getId());
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

	@RequestMapping("/admin/activities_to_input")
	@Override
	public String toinput(String id, HttpServletRequest request)
			throws Exception {
		Activities ac= activitiesService.findByKey(id);
		request.setAttribute("ac", ac);
		return "activities/activities_input";
	}

	@RequestMapping("/admin/activities_input")
	@Override
	public String input(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String status = request.getParameter("status");
        String banner = request.getParameter("banner");
        String icon = request.getParameter("icon");
        String introduction = request.getParameter("introduction");
        String order = request.getParameter("order");
        Activities ac = null;
        if(StringUtils.isNotBlank(id)){
        	ac = activitiesService.findByKey(id);
        	ac.setBanner(banner==null?"0":banner);
            ac.setCreateTime(new Date());
            ac.setIcon(icon);
            ac.setStatus(status==null?"0":status);
            ac.setName(name);
            ac.setIntroduction(introduction);
            ac.setModifyTime(new Date());
            ac.setOrders(Integer.parseInt(order));
            activitiesService.update(ac);
            ResponseUtils.renderHtml(response, "0002");
        }else{
        	ac = new Activities();
        	ac.setBanner(banner==null?"0":banner);
            ac.setCreateTime(new Date());
            ac.setIcon(icon);
            ac.setStatus(status==null?"0":status);
            ac.setName(name);
            ac.setIntroduction(introduction);
            ac.setModifyTime(new Date());
            ac.setOrders(Integer.parseInt(order));
            activitiesService.save(ac);
            ResponseUtils.renderHtml(response, "0000");
        }
		return null;
	}

	@RequestMapping("/admin/activities_del")
	@Override
	public String del(String id, HttpServletResponse response)
			throws Exception {
		activitiesService.delete(id);
		ResponseUtils.renderHtml(response, "0000");
		return null;
	}

}
