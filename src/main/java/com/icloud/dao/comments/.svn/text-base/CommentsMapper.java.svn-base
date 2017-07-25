package com.icloud.dao.comments;

import java.util.List;

import com.icloud.common.QueryBuilder;
import com.icloud.model.comment.Comments;

public interface CommentsMapper {
    int deleteByPrimaryKey(String id);

    int insert(Comments record);

    int insertSelective(Comments record);

    Comments selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Comments record);

    int updateByPrimaryKey(Comments record);
    
    long countByExample(QueryBuilder example);

    List<Comments> selectByExample(QueryBuilder example);
}