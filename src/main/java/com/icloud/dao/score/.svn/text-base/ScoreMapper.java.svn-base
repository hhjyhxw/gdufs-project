package com.icloud.dao.score;

import java.util.List;

import com.icloud.common.QueryBuilder;
import com.icloud.model.score.Score;

public interface ScoreMapper {
    int deleteByPrimaryKey(String id);

    int insert(Score record);

    int insertSelective(Score record);

    Score selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Score record);

    int updateByPrimaryKey(Score record);
    
    
    long countByExample(QueryBuilder example);

    List<Score> selectByExample(QueryBuilder example);
}