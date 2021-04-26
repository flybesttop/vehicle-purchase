package com.vp.dao;

import com.vp.entity.CarStatus;
import org.springframework.stereotype.Repository;

@Repository
public interface CarStatusMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CarStatus record);

    int insertSelective(CarStatus record);

    CarStatus selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CarStatus record);

    int updateByPrimaryKey(CarStatus record);

    CarStatus selectByStatusId(Integer statusId);
}