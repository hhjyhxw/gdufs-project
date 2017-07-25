package com.icloud.dao.activities;

import java.util.List;

import com.icloud.common.QueryBuilder;
import com.icloud.model.activities.Activities;

public interface ActivitiesMapper {
    int deleteByPrimaryKey(String id);

    int insert(Activities record);

    int insertSelective(Activities record);

    Activities selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Activities record);

    int updateByPrimaryKey(Activities record);
    
    long countByExample(QueryBuilder example);

    List<Activities> selectByExample(QueryBuilder example);
}