package com.vp.dao;

import com.vp.entity.SellCar;
import org.springframework.stereotype.Repository;

@Repository
public interface SellCarMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SellCar record);

    int insertSelective(SellCar record);

    SellCar selectByPrimaryKey(Integer id);

    SellCar getNowSellCar(Integer carId);

    int updateByPrimaryKeySelective(SellCar record);

    int updateByPrimaryKey(SellCar record);
}