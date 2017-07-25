package com.icloud.service.thirdinterface;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.icloud.common.Contants;
import com.icloud.common.util.HttpClientUtils;

/**
 * @filename      : ThirdInterfaceServie.java
 * @description   : 调用广州图书馆所用接口
 * @author        : zdh
 * @create        : 2017年6月9日 上午9:44:49   
 * @copyright     : zhumeng.com@gdufs-project
 *
 * Modification History:
 * Date             Author       Version
 * --------------------------------------
 */
@Service
@Transactional
public class ThirdInterfaceServie {

	public final static Logger log = LoggerFactory.getLogger(ThirdInterfaceServie.class);
		
	
	public static JSONObject getNewJSONObject(JSONObject originObject,JSONObject resultObj){
		if(originObject==null){
			log.error("返回对象jsonObject为空");
			resultObj.put("errCode", "4000");
			resultObj.put("resultMsg", "获取数据失败");
			return resultObj;
		}
		if(originObject.containsKey("errCode")){
			log.error("errCode=="+originObject.getString("errCode")+";resultMsg="+originObject.getString("resultMsg"));
			return originObject;
		}else{
			log.error("返回码errCode为空");
			resultObj.put("errCode", "4000");
			resultObj.put("resultMsg", "获取数据失败");
			return resultObj;
		}
	}
	/**
	 * 我的借阅历史
	 * @author   : zdh
	 * @date     :
	 * @version  : 1.0  
	 * @param paramObj
	 * @param resultObj
	 * @return   :
	 */
	public static JSONObject borrowingHhistory(JSONObject paramObj,JSONObject resultObj){
		JSONObject jsonObject = HttpClientUtils.HttpRequest(Contants.BORROW_LIST_URL, "POST", JSON.toJSON(paramObj).toString());
		return getNewJSONObject(jsonObject,resultObj);
	}
	
	
	/**
	 * 我的预约记录
	 * @author   : zdh
	 * @date     :
	 * @version  : 1.0  
	 * @param paramObj
	 * @param resultObj
	 * @return   :
	 */
	public static JSONObject bookingRecord(JSONObject paramObj,JSONObject resultObj){
		JSONObject jsonObject = HttpClientUtils.HttpRequest(Contants.APPOINTMENT_LIST_URL, "POST", JSON.toJSON(paramObj).toString());
		return getNewJSONObject(jsonObject,resultObj);
	}
	
	/**
	 *  参与预约
	 * @author   : zdh
	 * @date     :
	 * @version  : 1.0  
	 * @param paramObj
	 * @param resultObj
	 * @return   :
	 */
	public static JSONObject appointment(JSONObject paramObj,JSONObject resultObj){
		JSONObject jsonObject = HttpClientUtils.HttpRequest(Contants.APPOINTENT_BOOK_URL, "POST", JSON.toJSON(paramObj).toString());
		return getNewJSONObject(jsonObject,resultObj);
	}
	
	/**
	 * 搜书
	 * @author   : zdh
	 * @date     :
	 * @version  : 1.0  
	 * @param paramObj
	 * @param resultObj
	 * @return   :
	 */
	public static JSONObject listRetrieve(JSONObject paramObj,JSONObject resultObj){
		JSONObject jsonObject = HttpClientUtils.HttpRequest(Contants.SEARCH_BOOK_LIST_URL, "POST", JSON.toJSON(paramObj).toString());
		return getNewJSONObject(jsonObject,resultObj);
	}
	

	/**
	 * 图书详情
	 * @author   : zdh
	 * @date     :
	 * @version  : 1.0  
	 * @param paramObj
	 * @param resultObj
	 * @return   :
	 */
	public static JSONObject bookDetail(JSONObject paramObj,JSONObject resultObj){
		JSONObject jsonObject = HttpClientUtils.HttpRequest(Contants.BOOK_DETAIL_URL, "POST", JSON.toJSON(paramObj).toString());
		return getNewJSONObject(jsonObject,resultObj);
	}
	
	/**
	 * 图书借阅排行榜
	 * @author   : zdh
	 * @date     :
	 * @version  : 1.0  
	 * @param paramObj
	 * @param resultObj
	 * @return   :
	 */
	public static JSONObject bookRank(JSONObject paramObj,JSONObject resultObj){
		JSONObject jsonObject = HttpClientUtils.HttpRequest(Contants.BOOK_RAMK_URL, "POST", JSON.toJSON(paramObj).toString());
		return getNewJSONObject(jsonObject,resultObj);
	}
	
	/**
	 * 登录获取用户数据
	 * @author   : zdh
	 * @date     :
	 * @version  : 1.0  
	 * @param paramObj
	 * @param resultObj
	 * @return   :
	 */
	public static JSONObject bind(JSONObject paramObj,JSONObject resultObj){
		JSONObject jsonObject = HttpClientUtils.HttpRequest(Contants.LOGIN_URL, "POST", JSON.toJSON(paramObj).toString());
		return getNewJSONObject(jsonObject,resultObj);
	}
	/**
	 * 取消预约
	 * @author   : zdh
	 * @date     :
	 * @version  : 1.0  
	 * @param paramObj
	 * @param resultObj
	 * @return   :
	 */
	public static Object cancelAppointment(JSONObject paramObj,
			JSONObject resultObj) {
		JSONObject jsonObject = HttpClientUtils.HttpRequest(Contants.CANCEL_BOOK, "POST", JSON.toJSON(paramObj).toString());
		return getNewJSONObject(jsonObject,resultObj);
	}
	
}
