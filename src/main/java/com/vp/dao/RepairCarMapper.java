package com.vp.dao;

import com.vp.entity.RepairCar;
import com.vp.entity.StopCar;
import org.springframework.stereotype.Repository;

@Repository
public interface RepairCarMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RepairCar record);

    int insertSelective(RepairCar record);

    RepairCar selectByPrimaryKey(Integer id);

    RepairCar getNowRepairCar(Integer carId);

    int updateByPrimaryKeySelective(RepairCar record);

    int updateByPrimaryKey(RepairCar record);
}