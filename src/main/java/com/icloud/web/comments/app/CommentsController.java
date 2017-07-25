package com.icloud.web.comments.app;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.icloud.common.DateTools;
import com.icloud.common.dto.vo.comments.CommentsVo;
import com.icloud.common.dto.vo.comments.DataVo;
import com.icloud.common.util.StringUtil;
import com.icloud.model.comment.Comments;
import com.icloud.model.user.User;
import com.icloud.service.comments.CommentsService;
import com.icloud.service.user.UserService;
import com.icloud.web.AppBaseController;

/**
 * @filename      : CommentsController.java
 * @description   : 图书评论
 * @author        : zdh
 * @create        : 2017年6月9日 下午2:35:38   
 * @copyright     : zhumeng.com@gdufs-project
 *
 * Modification History:
 * Date             Author       Version
 * --------------------------------------
 */
@RestController
public class CommentsController extends AppBaseController{


	public final static Logger log = LoggerFactory.getLogger(CommentsController.class);
	@Autowired
	private CommentsService commentsService;
	@Autowired
	private UserService userService;
	/**活动列表**/
	@RequestMapping("/bookComment")
	public String  bookComment(HttpServletRequest request){
		
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
			Comments commentss = new Comments();
//			commentss.setStatus("1");
			PageInfo<Comments> page = commentsService.findByPage(pageNum,pageSize,commentss);
			List<Comments> list = page.getList();
			DataVo data = new DataVo();
			data.setHasMore(page.getPageNum()<page.getPages());
			data.setPageNum(pageNum);
			data.setPageSize(pageSize);
			data.setTotalCount(page.getTotal());
			if(list!=null && list.size()>0){
				List<CommentsVo> datalist = new ArrayList<CommentsVo>();
				for (Comments comments : list) {
					CommentsVo vo = new CommentsVo();
					vo.setContent(comments.getContent());
					vo.setUserName(comments.getNickName());
					vo.setCommentTime(DateTools.convertDateToString(comments.getCreateTime(),"yyyy-MM-dd HH:mm:ss"));
					datalist.add(vo);
				}
				data.setDatalist(datalist);
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
	
	
	/**参与评论**/
	@RequestMapping("/addComment")
	public String  addComment(HttpServletRequest request){
		
		JSONObject resultObj = new JSONObject();
		try{
			JSONObject parameterObj = super.readToJSONObect(request);
			/*1、检验用户参数*/
			if(!parameterObj.containsKey("sid") && !parameterObj.containsKey("booknumber")&& !parameterObj.containsKey("content")){
					log.error("sid=="+parameterObj.getString("sid")+"&booknumber=="+parameterObj.getString("booknumber")
					+"&content=="+parameterObj.getString("content"));
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
			
			Comments comments = new Comments();
			comments.setBooknumber(parameterObj.getString("booknumber"));
			comments.setContent(parameterObj.getString("content"));
			comments.setCreateTime(new Date());
			comments.setHeaderUrl(user.getHeardUrl());
			comments.setNickName(user.getNickName());
			comments.setOpenid(user.getOpenid());
			comments.setStatus("0");
			commentsService.save(comments);
			
			resultObj.put("errCode", "0000");
			resultObj.put("resultMsg", "添加成功");
			return pakageJsonp(resultObj);
		}catch(Exception e){
			e.printStackTrace();
		}
		resultObj.put("errCode", "1001");
		resultObj.put("resultMsg", "系统内部出错");
		return pakageJsonp(resultObj);
	}
	
}
