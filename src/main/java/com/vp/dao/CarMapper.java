package com.vp.dao;

import com.vp.entity.Car;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Car record);

    int insertSelective(Car record);

    Car selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Car record);

    int updateByPrimaryKey(Car record);

    Car getCarByVim(String vim);

    Car getCarByVimOther(@Param("vim") String vim, @Param("carId") Integer carId);

    List<Car> getCarList(@Param("companyId") Integer companyId, @Param("searchKey") String searchKey, @Param("carStatus") Integer carStatus);
}