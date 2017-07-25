package com.icloud.model.score;

import java.util.Date;
/**
 * 积分流水
 * @author z
 *
 */
public class Score {
    private String id;

    private String openid;

    private String unionid;

    private String nickName;//昵称

    private String headerUrl;//头像url

    private String fromMark;//来源标识 0 签到

    private String descbic;//来源ID （对应0 写入签到记录id）

    private Integer score;//积分值

    private Date createTime;//创建时间

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid == null ? null : unionid.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getHeaderUrl() {
        return headerUrl;
    }

    public void setHeaderUrl(String headerUrl) {
        this.headerUrl = headerUrl == null ? null : headerUrl.trim();
    }

    public String getFromMark() {
        return fromMark;
    }

    public void setFromMark(String fromMark) {
        this.fromMark = fromMark == null ? null : fromMark.trim();
    }

    public String getDescbic() {
        return descbic;
    }

    public void setDescbic(String descbic) {
        this.descbic = descbic == null ? null : descbic.trim();
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}