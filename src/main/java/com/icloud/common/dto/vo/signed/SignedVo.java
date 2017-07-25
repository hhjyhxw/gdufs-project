package com.icloud.common.dto.vo.signed;

import java.util.List;

import com.icloud.model.signed.Signed;

public class SignedVo {
	private String signinTime ;//签到时间
	private int    score;
	
	public String getSigninTime() {
		return signinTime;
	}
	public void setSigninTime(String signinTime) {
		this.signinTime = signinTime;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
}
