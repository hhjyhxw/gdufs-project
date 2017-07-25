package com.icloud.common.dto.vo.attention;

import java.util.List;

/**
 * @filename      : DataVo.java
 * @description   : 
 * @author        : zdh
 * @create        : 2017年6月9日 上午11:50:16   
 * @copyright     : zhumeng.com@gdufs-project
 *
 * Modification History:
 * Date             Author       Version
 * --------------------------------------
 */
public class DataVo {
	private List<AttentionVo> followList;
	
	private Long totalNum;
	private Boolean hasMore;
	private Integer pageNum;
	private Integer pageSize;
	

	public List<AttentionVo> getFollowList() {
		return followList;
	}
	public void setFollowList(List<AttentionVo> followList) {
		this.followList = followList;
	}
	public Long getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(Long totalNum) {
		this.totalNum = totalNum;
	}
	public Boolean getHasMore() {
		return hasMore;
	}
	public void setHasMore(Boolean hasMore) {
		this.hasMore = hasMore;
	}
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
}
