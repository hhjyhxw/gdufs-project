package com.icloud.dao.equipment;

import java.util.List;

import com.icloud.common.QueryBuilder;
import com.icloud.model.equipment.Equipment;

public interface EquipmentMapper {
    int deleteByPrimaryKey(String id);

    int insert(Equipment record);

    int insertSelective(Equipment record);

    Equipment selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Equipment record);

    int updateByPrimaryKey(Equipment record);
    
    long countByExample(QueryBuilder example);

    List<Equipment> selectByExample(QueryBuilder example);
}