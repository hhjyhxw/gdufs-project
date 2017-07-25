package com.icloud.common.dto.vo.activities;
/**
 * @filename      : ActivitiesVo.java
 * @description   : 
 * @author        : zdh
 * @create        : 2017年6月9日 上午11:49:55   
 * @copyright     : zhumeng.com@gdufs-project
 *
 * Modification History:
 * Date             Author       Version
 * --------------------------------------
 */
public class ActivitiesVo {
	
	private String id;
	private String picUrl;//题图Url
	private String title;//活动标题
	private String desc;//活动描述
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
