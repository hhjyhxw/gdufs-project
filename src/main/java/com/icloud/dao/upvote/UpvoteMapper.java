package com.icloud.dao.upvote;

import java.util.List;

import com.icloud.common.QueryBuilder;
import com.icloud.model.upvote.Upvote;

public interface UpvoteMapper {
    int deleteByPrimaryKey(String id);

    int insert(Upvote record);

    int insertSelective(Upvote record);

    Upvote selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Upvote record);

    int updateByPrimaryKey(Upvote record);
    
    long countByExample(QueryBuilder example);

    List<Upvote> selectByExample(QueryBuilder example);
}