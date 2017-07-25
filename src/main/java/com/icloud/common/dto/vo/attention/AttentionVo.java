package com.icloud.common.dto.vo.attention;
/**
 * @filename      : AttentionVo.java
 * @description   : 
 * @author        : zdh
 * @create        : 2017年6月9日 下午4:47:36   
 * @copyright     : zhumeng.com@gdufs-project
 *
 * Modification History:
 * Date             Author       Version
 * --------------------------------------
 */
public class AttentionVo {
	
	private String bookName;
	private String author;//题图Url
	private String translator;//活动标题
	private String publishor;//活动描述
	private String callNo;//活动描述
	private String booknumber;//
	
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTranslator() {
		return translator;
	}
	public void setTranslator(String translator) {
		this.translator = translator;
	}
	public String getPublishor() {
		return publishor;
	}
	public void setPublishor(String publishor) {
		this.publishor = publishor;
	}
	public String getCallNo() {
		return callNo;
	}
	public void setCallNo(String callNo) {
		this.callNo = callNo;
	}
	public String getBooknumber() {
		return booknumber;
	}
	public void setBooknumber(String booknumber) {
		this.booknumber = booknumber;
	}
	
}
