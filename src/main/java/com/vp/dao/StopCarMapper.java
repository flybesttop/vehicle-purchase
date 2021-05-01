package com.vp.dao;

import com.vp.entity.StopCar;
import org.springframework.stereotype.Repository;

@Repository
public interface StopCarMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StopCar record);

    int insertSelective(StopCar record);

    StopCar selectByPrimaryKey(Integer id);

    StopCar getNowStopCar(Integer carId);

    int updateByPrimaryKeySelective(StopCar record);

    int updateByPrimaryKey(StopCar record);
}