package com.icloud.dao.positon;

import java.util.List;

import com.icloud.common.QueryBuilder;
import com.icloud.model.positon.Positon;

public interface PositonMapper {
    int deleteByPrimaryKey(String id);

    int insert(Positon record);

    int insertSelective(Positon record);

    Positon selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Positon record);

    int updateByPrimaryKey(Positon record);
    
    long countByExample(QueryBuilder example);

    List<Positon> selectByExample(QueryBuilder example);
}