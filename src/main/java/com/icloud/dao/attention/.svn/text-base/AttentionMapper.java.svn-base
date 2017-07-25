package com.icloud.dao.attention;

import java.util.List;

import com.icloud.common.QueryBuilder;
import com.icloud.model.attention.Attention;

public interface AttentionMapper {
    int deleteByPrimaryKey(String id);

    int insert(Attention record);

    int insertSelective(Attention record);

    Attention selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Attention record);

    int updateByPrimaryKey(Attention record);
    
    long countByExample(QueryBuilder example);

    List<Attention> selectByExample(QueryBuilder example);
}