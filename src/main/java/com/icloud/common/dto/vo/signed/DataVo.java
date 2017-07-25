package com.icloud.common.dto.vo.signed;

import java.util.List;

import com.icloud.model.signed.Signed;

public class DataVo {
	private long   totalScore;//总积分
	private String name;   //姓名
	
	List<SignedVo>   list;     //签到列表
	
	private boolean isSignToday;//今天是否签到 true false
	private long totalNum;
	private boolean hasMore;    //用于前端控制分页的参数，是否还有更多
	private int pageNo;
	private int pageSize;
	public long getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(long totalScore) {
		this.totalScore = totalScore;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<SignedVo> getList() {
		return list;
	}
	public void setList(List<SignedVo> list) {
		this.list = list;
	}
	public boolean isSignToday() {
		return isSignToday;
	}
	public void setSignToday(boolean isSignToday) {
		this.isSignToday = isSignToday;
	}
	public long getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(long totalNum) {
		this.totalNum = totalNum;
	}
	public boolean isHasMore() {
		return hasMore;
	}
	public void setHasMore(boolean hasMore) {
		this.hasMore = hasMore;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
