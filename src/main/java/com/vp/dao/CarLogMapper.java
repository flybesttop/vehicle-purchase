package com.vp.dao;

import com.vp.entity.CarLog;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CarLog record);

    int insertSelective(CarLog record);

    CarLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CarLog record);

    int updateByPrimaryKey(CarLog record);

    List<CarLog> getCarLogList(Integer carId);
}