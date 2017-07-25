package com.icloud.web.thirdinterface;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.icloud.common.util.StringUtil;
import com.icloud.model.user.User;
import com.icloud.service.thirdinterface.ThirdInterfaceServie;
import com.icloud.service.user.UserService;
import com.icloud.web.AppBaseController;

/**
 * 
 * @author z
 *
 */
@RestController
public class InterfaceController extends AppBaseController{
	@Autowired
	private UserService userService;
	
	public final static Logger log = LoggerFactory.getLogger(InterfaceController.class);
	/**我的当前借阅**/
	@RequestMapping("/borrowingHhistory")
	public String  borrowingHhistory(HttpServletRequest request){
		
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
			if(!StringUtil.checkObj(user.getPwd()) || !StringUtil.checkObj(user.getUid())){
				resultObj.put("errCode", "2001");//用户不存在
				resultObj.put("resultMsg", "用户未绑定");
				return pakageJsonp(resultObj);
			}
//			User user = userService.findByKey("2");
			/*2、封装接口参数*/
			JSONObject paramObj = new JSONObject();
			//操作, option = D  当前借阅
			paramObj.put("option", "D");
			//学工号
			paramObj.put("uid", user.getUid());
			//图书馆登录密码
			paramObj.put("pwd", user.getPwd());
			//第几页
			paramObj.put("pageNum", parameterObj.getInteger("pageNum")!=null?parameterObj.getInteger("pageNum"):0);
			//每页记录数
			paramObj.put("pageSize", parameterObj.getInteger("pageSize")!=null?parameterObj.getInteger("pageSize"):10);
			return pakageJsonp(ThirdInterfaceServie.borrowingHhistory(paramObj, resultObj));
		}catch(Exception e){
			e.printStackTrace();
		}
		resultObj.put("errCode", "1001");
		resultObj.put("resultMsg", "系统内部出错");
		return pakageJsonp(resultObj);
	}
	
	/**我的借阅历史**/
	@RequestMapping("/borrowingList")
	public String  borrowingList(HttpServletRequest request){
		
		JSONObject resultObj = new JSONObject();
		try{
			JSONObject parameterObj = super.readToJSONObect(request);
//			/*1、检验用户参数*/
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
			if(!StringUtil.checkObj(user.getPwd()) || !StringUtil.checkObj(user.getUid())){
				resultObj.put("errCode", "2001");//用户不存在
				resultObj.put("resultMsg", "用户未绑定");
				return pakageJsonp(resultObj);
			}
//			User user = userService.findByKey("2");
			/*2、封装接口参数*/
			JSONObject paramObj = new JSONObject();
			//操作, option = G 借阅历史
			paramObj.put("option", "G");
			//学工号
			paramObj.put("uid", user.getUid());
			//图书馆登录密码
			paramObj.put("pwd", user.getPwd());
			//第几页
			paramObj.put("pageNum", parameterObj.getInteger("pageNum")!=null?parameterObj.getInteger("pageNum"):0);
			//每页记录数
			paramObj.put("pageSize", parameterObj.getInteger("pageSize")!=null?parameterObj.getInteger("pageSize"):10);
			return pakageJsonp(ThirdInterfaceServie.borrowingHhistory(paramObj, resultObj));
		}catch(Exception e){
			e.printStackTrace();
		}
		resultObj.put("errCode", "1001");
		resultObj.put("resultMsg", "系统内部出错");
		return pakageJsonp(resultObj);
	}
	
	
	/**我的预约记录**/
	@RequestMapping("/bookingRecord")
	public String  bookingRecord(HttpServletRequest request){
		
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
			if(!StringUtil.checkObj(user.getPwd()) || !StringUtil.checkObj(user.getUid())){
				resultObj.put("errCode", "2001");//用户不存在
				resultObj.put("resultMsg", "用户未绑定");
				return pakageJsonp(resultObj);
			}
//			User user = userService.findByKey("2");
			/*2、封装接口参数*/
			JSONObject paramObj = new JSONObject();
			//操作 option = F 预约记录(E改为F)
			paramObj.put("option", "F");
			//学工号
			paramObj.put("uid", user.getUid());
			//图书馆登录密码
			paramObj.put("pwd", user.getPwd());
			//第几页
			paramObj.put("pageNum", parameterObj.getInteger("pageNum")!=null?parameterObj.getInteger("pageNum"):0);
			//每页记录数
			paramObj.put("pageSize", parameterObj.getInteger("pageSize")!=null?parameterObj.getInteger("pageSize"):10);
			return pakageJsonp(ThirdInterfaceServie.bookingRecord(paramObj, resultObj));
		}catch(Exception e){
			e.printStackTrace();
		}
		resultObj.put("errCode", "1001");
		resultObj.put("resultMsg", "系统内部出错");
		return pakageJsonp(resultObj);
	}
	/**参与预约**/
	@RequestMapping("/appointment")
	public String  appointment(HttpServletRequest request){
		
		JSONObject resultObj = new JSONObject();
		try{
			JSONObject parameterObj = super.readToJSONObect(request);
			/*1、检验用户参数*/
			if(!parameterObj.containsKey("sid") && !parameterObj.containsKey("barcode")
					 && !parameterObj.containsKey("appointmentTime")){
					log.error("sid=="+parameterObj.getString("sid")+"&barcode=="
					+parameterObj.getString("barcode")
					+"&appointmentTime=="+parameterObj.getString("appointmentTime"));
				resultObj.put("errCode", "1000");
				resultObj.put("resultMsg", "参数缺失");
				return pakageJsonp(resultObj);
			}
			String sessionKey = parameterObj.getString("sid");
			User user = userService.getUserFromSession(sessionKey);
//			User user = userService.findByKey("2");
			if(null==user){
				resultObj.put("errCode", "2000");//用户不存在
				resultObj.put("resultMsg", "用户不存在,请检查session_key");
				return pakageJsonp(resultObj);
			}
			if(!StringUtil.checkObj(user.getPwd()) || !StringUtil.checkObj(user.getUid())){
				resultObj.put("errCode", "2001");//用户不存在
				resultObj.put("resultMsg", "用户未绑定");
				return pakageJsonp(resultObj);
			}
			
			/*2、封装接口参数*/
			JSONObject paramObj = new JSONObject();
			//书在系统中的登记号码
			paramObj.put("barcode", parameterObj.getString("barcode"));
			//学工号
			paramObj.put("uid", user.getUid());
			//预约用的系统号  现在使用 barcode 替代  recKey
			paramObj.put("recKey",parameterObj.getString("recKey"));
			//预约时间
			paramObj.put("appointmentTime", parameterObj.getString("appointmentTime"));
			//图书馆
			paramObj.put("subLibrary", parameterObj.getString("subLibrary"));
		
			return pakageJsonp(ThirdInterfaceServie.appointment(paramObj, resultObj));
		}catch(Exception e){
			e.printStackTrace();
		}
		resultObj.put("errCode", "1001");
		resultObj.put("resultMsg", "系统内部出错");
		return pakageJsonp(resultObj);
	}
	
	/**取消预约**/
	@RequestMapping("/cancelAppointment")
	public String  cancelAppointment(HttpServletRequest request){
		
		JSONObject resultObj = new JSONObject();
		try{
			JSONObject parameterObj = super.readToJSONObect(request);
			/*1、检验用户参数*/
//			if(!parameterObj.containsKey("sid") && !parameterObj.containsKey("barcode")){
//					
//					log.error("sid=="+parameterObj.getString("sid")+"&barcode=="
//					+parameterObj.getString("barcode"));
//					
//				resultObj.put("errCode", "1000");
//				resultObj.put("resultMsg", "参数缺失");
//				return pakageJsonp(resultObj);
//			}
//			String sessionKey = parameterObj.getString("sid");
//			User user = userService.getUserFromSession(sessionKey);
			User user = userService.findByKey("2");
//			if(null==user){
//				resultObj.put("errCode", "2000");//用户不存在
//				resultObj.put("resultMsg", "用户不存在,请检查session_key");
//				return pakageJsonp(resultObj);
//			}
//			if(!StringUtil.checkObj(user.getPwd()) || !StringUtil.checkObj(user.getUid())){
//				resultObj.put("errCode", "2001");//用户不存在
//				resultObj.put("resultMsg", "用户未绑定");
//				return pakageJsonp(resultObj);
//			}
//			
			/*2、封装接口参数*/
			JSONObject paramObj = new JSONObject();
			//书在系统中的登记号码
			paramObj.put("barcode", parameterObj.getString("barcode"));
			//学工号
			paramObj.put("uid", user.getUid());
			//预约用的系统号  现在使用 barcode 替代  recKey
//			paramObj.put("recKey",parameterObj.getString("recKey"));
				
			return pakageJsonp(ThirdInterfaceServie.cancelAppointment(paramObj, resultObj));
		}catch(Exception e){
			e.printStackTrace();
		}
		resultObj.put("errCode", "1001");
		resultObj.put("resultMsg", "系统内部出错");
		return pakageJsonp(resultObj);
	}
	
	/**检索列表**/
	@RequestMapping("/listRetrieve")
	public String  listRetrieve(HttpServletRequest request){
		
		JSONObject resultObj = new JSONObject();
		try{
			
			JSONObject parameterObj = super.readToJSONObect(request);
			/*1、参数 检验*/
			if(!parameterObj.containsKey("word") || parameterObj.getString("word")==null || "".equals(parameterObj.getString("word"))){
				resultObj.put("errCode", "1000");
				resultObj.put("resultMsg", "缺失关键字word");
				return pakageJsonp(resultObj);
			}
			
			JSONObject paramObj = new JSONObject();
			//操作 option = B  ? //搜书   C 书本详细信息
			paramObj.put("option", "B");
			//关键字 
			paramObj.put("word", parameterObj.getString("word"));
			//搜索类型、对象（0所有字段）
			paramObj.put("type", parameterObj.getString("type")!=null? parameterObj.getString("type"):"0");
			//所属库   chinese/english
			paramObj.put("library",parameterObj.getString("library")!=null? parameterObj.getString("library"):"chinese");
			//第几页
			paramObj.put("pageNum", parameterObj.getInteger("pageNum")!=null?parameterObj.getInteger("pageNum"):1);
			//每页记录数
			paramObj.put("pageSize", parameterObj.getInteger("pageSize")!=null?parameterObj.getInteger("pageSize"):10);
			return pakageJsonp(ThirdInterfaceServie.listRetrieve(paramObj, resultObj));
		}catch(Exception e){
			e.printStackTrace();
		}
		resultObj.put("errCode", "1001");
		resultObj.put("resultMsg", "系统内部出错");
		return pakageJsonp(resultObj);
	}
	
	/**图书详情**/
	@RequestMapping("/bookDetail")
	public String  bookDetail(HttpServletRequest request){
		
		JSONObject resultObj = new JSONObject();
		try{
			JSONObject parameterObj = super.readToJSONObect(request);
			/*1、参数 检验*/
			if(!parameterObj.containsKey("booknumber")){
				resultObj.put("errCode", "1000");
				resultObj.put("resultMsg", "缺失参数booknumber");
				return pakageJsonp(resultObj);
			}
			/*2、封装接口参数*/
			JSONObject paramObj = new JSONObject();
			//操作 option = C 书本详细信息
			paramObj.put("option", "C");
			//类型0	所有字段 1	题名关键词2	题名(精确匹配）3	著者4	主题词
			paramObj.put("booknumber",parameterObj.getString("booknumber"));
			//所属库   chinese/english
			paramObj.put("library",parameterObj.getString("library")!=null? parameterObj.getInteger("library"):"chinese");
			
			return pakageJsonp(ThirdInterfaceServie.bookDetail(paramObj, resultObj));
		}catch(Exception e){
			e.printStackTrace();
		}
		resultObj.put("errCode", "1001");
		resultObj.put("resultMsg", "系统内部出错");
		return pakageJsonp(resultObj);
	}
	
	/**图书借阅排行榜**/
	@RequestMapping("/bookRank")
	public String  bookRank(HttpServletRequest request){
		JSONObject resultObj = new JSONObject();
		try{
			JSONObject parameterObj = super.readToJSONObect(request);
			//数目检索 不需要登录	
			/*1、封装接口参数*/
			JSONObject paramObj = new JSONObject();
			//y最近一年，s最近一季，m最近一月，w最近一周
			paramObj.put("time", parameterObj.getString("time")!=null?parameterObj.getString("time"):"y");
			//ALL全部、C中文、 E外文
			paramObj.put("language", parameterObj.getString("language")!=null?parameterObj.getString("language"):"ALL");
			//ALL全部、K历史
			paramObj.put("category",parameterObj.getString("category")!=null?parameterObj.getString("category"):"ALL");
			//不再分页，默认返回最多20条
//			//第几页
//			paramObj.put("pageNum", parameterObj.getInteger("pageNum")!=null?parameterObj.getInteger("pageNum"):0);
//			//每页记录数
//			paramObj.put("pageSize", parameterObj.getInteger("pageSize")!=null?parameterObj.getInteger("pageSize"):10);
			return pakageJsonp(ThirdInterfaceServie.bookRank(paramObj, resultObj));
		}catch(Exception e){
			e.printStackTrace();
		}
		resultObj.put("errCode", "1001");
		resultObj.put("resultMsg", "系统内部出错");
		return pakageJsonp(resultObj);
	}
	
	/**绑定**/
	@RequestMapping("/bind")
	public String  bind(HttpServletRequest request){
		JSONObject resultObj = new JSONObject();
		try{
			JSONObject  parameterObj = super.readToJSONObect(request);
//			/*1、检验用户参数*/
			if(!parameterObj.containsKey("sid") && !parameterObj.containsKey("uid") && !parameterObj.containsKey("pwd")){
				log.error("sid=="+parameterObj.getString("sid")+"&uid=="+parameterObj.getString("uid")+"&pwd=="+parameterObj.getString("pwd"));
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
			User use2 = new User();
			use2.setUid(parameterObj.getString("uid"));
			use2.setPwd(parameterObj.getString("pwd"));
			List<User> userList  = userService.findList(use2);
			if(userList!=null && userList.size()>0){
				if(!user.getId().equals(userList.get(0).getId())){
					resultObj.put("errCode", "1012");//用户不存在
					resultObj.put("resultMsg", "账号已绑定，不能重复绑定");
					return pakageJsonp(resultObj);
				}
			}
//			User user = userService.findByKey("2");
			/*2、封装接口参数*/
			JSONObject paramObj = new JSONObject();
			//option:"E",//个人信息——当前借阅及预约记录
			paramObj.put("option","E");
			paramObj.put("uid", parameterObj.getString("uid"));
			paramObj.put("pwd", parameterObj.getString("pwd"));
			resultObj = ThirdInterfaceServie.bind(paramObj, resultObj);
			if("0000".equals(resultObj.getString("errCode"))){
				user.setUid(parameterObj.getString("uid"));
				user.setPwd(parameterObj.getString("pwd"));
				user.setRealName(resultObj.getString("name"));
//				user.setMajor(resultObj.getString("Professional"));//专业 不存在了
				user.setGrades(resultObj.getString("grade"));//年级
				user.setUniversity(resultObj.getString("departments"));//院系
				user.setStatus("1");//0未绑定 ，1绑定，2 禁用
				userService.update(user);
			}
			return pakageJsonp(resultObj);
		}catch(Exception e){
			e.printStackTrace();
		}
		resultObj.put("errCode", "1001");
		resultObj.put("resultMsg", "系统内部出错");
		return pakageJsonp(resultObj);
	}
}
