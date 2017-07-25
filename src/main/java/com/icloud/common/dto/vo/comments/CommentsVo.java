package com.icloud.common.dto.vo.comments;
/**
 * @filename      : CommentsVo.java
 * @description   : 
 * @author        : zdh
 * @create        : 2017年6月9日 下午2:40:05   
 * @copyright     : zhumeng.com@gdufs-project
 *
 * Modification History:
 * Date             Author       Version
 * --------------------------------------
 */
public class CommentsVo {

	private String content;//评论内容
	private String userName;//评论人
	private String commentTime;//"2013-06-15 22:00:00",//评论时间
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCommentTime() {
		return commentTime;
	}
	public void setCommentTime(String commentTime) {
		this.commentTime = commentTime;
	}
}
