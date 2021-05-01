package com.vp.dao;

import com.vp.entity.RentCar;
import org.springframework.stereotype.Repository;

@Repository
public interface RentCarMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RentCar record);

    int insertSelective(RentCar record);

    RentCar selectByPrimaryKey(Integer id);

    RentCar getNowRentCar(Integer carId);

    int updateByPrimaryKeySelective(RentCar record);

    int updateByPrimaryKey(RentCar record);
}