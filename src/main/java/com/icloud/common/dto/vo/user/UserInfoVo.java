package com.icloud.common.dto.vo.user;
/**
 * @filename      : UserInfoVo.java
 * @description   : 
 * @author        : zdh
 * @create        : 2017年6月9日 下午3:22:06   
 * @copyright     : zhumeng.com@gdufs-project
 *
 * Modification History:
 * Date             Author       Version
 * --------------------------------------
 */
public class UserInfoVo {

	private Boolean isBind;//false时可跳转绑定，true直接进入个人资料页并显示解绑按钮，以下信息只有绑定
	private String uid;//学号工号会员号
	private String grade;//年级
	private String name;//昵称
	private String departments;//院校
	private String professional;//专业
	private String headImg;//头像
	

	public Boolean getIsBind() {
		return isBind;
	}
	public void setIsBind(Boolean isBind) {
		this.isBind = isBind;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDepartments() {
		return departments;
	}
	public void setDepartments(String departments) {
		this.departments = departments;
	}
	public String getProfessional() {
		return professional;
	}
	public void setProfessional(String professional) {
		this.professional = professional;
	}
	public String getHeadImg() {
		return headImg;
	}
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

}
