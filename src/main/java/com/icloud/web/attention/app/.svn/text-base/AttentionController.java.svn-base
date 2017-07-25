package com.icloud.web.attention.app;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.icloud.common.dto.vo.attention.AttentionVo;
import com.icloud.common.dto.vo.attention.DataVo;
import com.icloud.common.util.StringUtil;
import com.icloud.model.attention.Attention;
import com.icloud.model.user.User;
import com.icloud.service.attention.AttentionService;
import com.icloud.service.thirdinterface.ThirdInterfaceServie;
import com.icloud.service.user.UserService;
import com.icloud.web.AppBaseController;

/**
 * @filename      : AttentionController.java
 * @description   : 图书关注
 * @author        : zdh
 * @create        : 2017年6月9日 下午4:19:28   
 * @copyright     : zhumeng.com@gdufs-project
 *
 * Modification History:
 * Date             Author       Version
 * --------------------------------------
 */
@RestController
public class AttentionController extends AppBaseController{


	public final static Logger log = LoggerFactory.getLogger(AttentionController.class);
	@Autowired
	private AttentionService attentionService;
	@Autowired
	private UserService userService;
	/**关注列表**/
	@RequestMapping("/myFollow")
	public String  myFollow(HttpServletRequest request){
		
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
			Attention attentions = new Attention();
			attentions.setOpenid(user.getOpenid());
			attentions.setStatus("1");
			PageInfo<Attention> page = attentionService.findByPage(pageNum,pageSize,attentions);
			List<Attention> list = page.getList();
			DataVo data = new DataVo();
			data.setHasMore(page.getPageNum()<page.getPages());
			data.setPageNum(pageNum);
			data.setPageSize(pageSize);
			data.setTotalNum(page.getTotal());
			if(list!=null && list.size()>0){
				List<AttentionVo> followList = new ArrayList<AttentionVo>();
				for (Attention attention : list) {
					AttentionVo vo = new AttentionVo();
					vo.setAuthor(attention.getAuthor());
					vo.setBookName(attention.getBookName());
					vo.setCallNo(attention.getCallno());
					vo.setPublishor(attention.getPublishor());
					vo.setTranslator(attention.getTranslator());
					vo.setBooknumber(attention.getBooknumber());
					followList.add(vo);
				}
				data.setFollowList(followList);
			}
			resultObj.put("errCode", "0000");
			resultObj.put("resultMsg", "查询成功");
			resultObj.put("resultData", data);
			return pakageJsonp(resultObj);
		}catch(Exception e){
			e.printStackTrace();
		}
		resultObj.put("errCode", "1001");
		resultObj.put("resultMsg", "系统内部出错");
		return pakageJsonp(resultObj);
	}
	
	
	/**关注**/
	@RequestMapping("/follow")
	public String  follow(HttpServletRequest request){
		
		JSONObject resultObj = new JSONObject();
		try{
			JSONObject parameterObj = super.readToJSONObect(request);
			/*1、检验用户参数*/
			if(!parameterObj.containsKey("sid") && !parameterObj.containsKey("booknumber")){
				log.error("sid=="+parameterObj.getString("sid")+"&booknumber=="+parameterObj.getString("booknumber"));
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
			if(!StringUtil.checkObj(user.getPwd()) || !StringUtil.checkObj(user.getUid())){
				resultObj.put("errCode", "2000");//用户不存在
				resultObj.put("resultMsg", "用户未绑定");
				return pakageJsonp(resultObj);
			}
			
			Attention attentions = new Attention();
			attentions.setBooknumber(parameterObj.getString("booknumber"));
			attentions.setOpenid(user.getOpenid());
			//是否已关注
			List<Attention> attentionList = attentionService.findList(attentions);
			if(attentionList!=null && attentionList.size()>0){
				attentions = attentionList.get(0);
				if(parameterObj.getString("status")!=null && parameterObj.getString("status").equals("0")){
					attentions.setStatus("0");
					attentionService.update(attentions);
					resultObj.put("errCode", "0000");
					resultObj.put("resultMsg", "取消关注成功");
					return pakageJsonp(resultObj);
				}else if(parameterObj.getString("status")!=null && parameterObj.getString("status").equals("1")){
					attentions.setStatus("1");
					attentionService.update(attentions);
					resultObj.put("errCode", "0000");
					resultObj.put("resultMsg", "关注成功");
					return pakageJsonp(resultObj);
				}else{
					resultObj.put("errCode", "1000");
					resultObj.put("resultMsg", "status="+parameterObj.getString("status"));
					return pakageJsonp(resultObj);
				}
			}else{
				/*2、封装接口参数*/
				JSONObject paramObj = new JSONObject();
				//操作 option = c 书本详细信息
				paramObj.put("option", "C");
				//类型0	所有字段 1	题名关键词2	题名(精确匹配）3	著者4	主题词
				paramObj.put("booknumber",parameterObj.getString("booknumber"));
				//关键字 
				paramObj.put("word", parameterObj.getString("word"));
				//所属库   chinese/english
				paramObj.put("library",parameterObj.getString("library")!=null? parameterObj.getInteger("library"):"chinese");
				
				resultObj = ThirdInterfaceServie.bookDetail(paramObj, resultObj);
				if(resultObj.containsKey("errCode") && "0000".equals(resultObj.getString("errCode"))){
					//图书信息
					JSONObject resultData = resultObj.getJSONObject("resultData");
					JSONObject bookInfo = resultData.getJSONObject("bookInfo");
					Attention attention = new Attention();
					attention.setBooknumber(parameterObj.getString("booknumber"));
					attention.setCreateTime(new Date());
					attention.setHeaderUrl(user.getHeardUrl());
					attention.setNickName(user.getNickName());
					attention.setOpenid(user.getOpenid());
					attention.setStatus("1");//状态(1表示关注)
					
					attention.setAuthor(bookInfo.getString("author"));
					attention.setBookName(bookInfo.getString("bookName"));
					attention.setBooknumber(parameterObj.getString("booknumber"));
					attention.setPublishor(bookInfo.getString("publishor"));
					JSONArray array = bookInfo.getJSONArray("libraryInfo");
					if(array!=null && array.size()>0){
						attention.setCallno(array.getJSONObject(0).getString("callNo"));
					}
					attentionService.save(attention);
					
					resultObj.put("errCode", "0000");
					resultObj.put("resultMsg", "关注成功");
					return pakageJsonp(resultObj);
				}else{
					resultObj.put("errCode", "5000");
					resultObj.put("resultMsg", "获取不到图书信息");
					return pakageJsonp(resultObj);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		resultObj.put("errCode", "1001");
		resultObj.put("resultMsg", "系统内部出错");
		return pakageJsonp(resultObj);
	}
	
	
	/**是否已关注 1已关注 0，未关注**/
	@RequestMapping("/isfollow")
	public String  isfollow(HttpServletRequest request){
		
		JSONObject resultObj = new JSONObject();
		try{
			JSONObject parameterObj = super.readToJSONObect(request);
			/*1、检验用户参数*/
			if(!parameterObj.containsKey("sid") && !parameterObj.containsKey("booknumber")){
				log.error("sid=="+parameterObj.getString("sid")+"&booknumber=="+parameterObj.getString("booknumber"));
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
			if(!StringUtil.checkObj(user.getPwd()) || !StringUtil.checkObj(user.getUid())){
				resultObj.put("errCode", "2000");//用户不存在
				resultObj.put("resultMsg", "用户未绑定");
				return pakageJsonp(resultObj);
			}
			
			Attention attentions = new Attention();
			attentions.setBooknumber(parameterObj.getString("booknumber"));
			attentions.setOpenid(user.getOpenid());
			//是否已关注
			List<Attention> attentionList = attentionService.findList(attentions);
			if(attentionList!=null && attentionList.size()>0){
				attentions = attentionList.get(0);
				resultObj.put("isfollow",attentions.getStatus());
				resultObj.put("errCode", "0000");
				resultObj.put("resultMsg", "查询成功");
				return pakageJsonp(resultObj);
			}else{
				resultObj.put("isfollow","0");
				resultObj.put("errCode", "0000");
				resultObj.put("resultMsg", "查询成功");
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
