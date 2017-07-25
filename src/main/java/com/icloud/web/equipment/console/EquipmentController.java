package com.icloud.web.equipment.console;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Result;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.icloud.common.DateTools;
import com.icloud.common.ResponseUtils;
import com.icloud.model.equipment.Equipment;
import com.icloud.service.equipment.EquipmentService;
import com.icloud.web.BaseController;

@Controller
public class EquipmentController extends BaseController<Equipment> {
	
	@Autowired
	private EquipmentService equipmentService;

	@RequestMapping("/admin/equipment_list")
	@Override
	public String list(HttpServletRequest request, Equipment t)
			throws Exception {
		
		PageInfo<Equipment> page = equipmentService.findByPage(1, 10, t);
		request.setAttribute("page", page);
		return "equipment/equipment_list";
	}

	@RequestMapping("/admin/equipment_getList")
	@Override
	public String getList(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
        String status = request.getParameter("status");
        String pageNo = request.getParameter("pageNo");
        Equipment e = new Equipment();
        e.setStatus(status);
        PageInfo<Equipment> page =  equipmentService.findByPage(StringUtils.isNotBlank(pageNo)?Integer.parseInt(pageNo):1, 10, e);
		List<Equipment> list = page.getList();
		JSONObject resultObj = new JSONObject();
		if(null!=list&&list.size()>0){
			JSONArray array = new JSONArray();
			for(Equipment ee:list){
				JSONObject obj = new JSONObject();
				obj.put("id", ee.getId());
				obj.put("mark", ee.getMark());
				obj.put("status", ee.getStatus());
				obj.put("nick", ee.getNickName());
				obj.put("time", DateTools.convertDateToString(ee.getCreateTime(),"yyyy-MM-dd HH:mm"));
				array.add(obj);
			}
			resultObj.put("list", array);
			resultObj.put("errCode", "0000");
			ResponseUtils.renderJson(response, resultObj.toJSONString());
			return null;
		}
		resultObj.put("errCode", "0001");
		ResponseUtils.renderJson(response, resultObj.toJSONString());
		return null;
	}

	@RequestMapping("/admin/equipment_to_iput")
	@Override
	public String toinput(String id, HttpServletRequest request)
			throws Exception {
		Equipment e = equipmentService.findByKey(id);
		request.setAttribute("e", e);
		return "equipment/equipment_detail";
	}

	@RequestMapping("/admin/equipment_input")
	@Override
	public String input(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		Equipment e = equipmentService.findByKey(id);
		String type = request.getParameter("type");
		if("0".equals(type)){
			e.setStatus("1");
		}else{
			e.setStatus("2");
		}
		equipmentService.update(e);
		ResponseUtils.renderHtml(response, "0000");
		return null;
	}

	@Override
	public String del(String id, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
