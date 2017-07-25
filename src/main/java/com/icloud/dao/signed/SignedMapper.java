package com.icloud.dao.signed;

import java.util.List;

import com.icloud.common.QueryBuilder;
import com.icloud.model.signed.Signed;

public interface SignedMapper {
    int deleteByPrimaryKey(String id);

    int insert(Signed record);

    int insertSelective(Signed record);

    Signed selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Signed record);

    int updateByPrimaryKey(Signed record);
    
    long countByExample(QueryBuilder example);

    List<Signed> selectByExample(QueryBuilder example);

	int selectSignedCountToday(String openid);
}