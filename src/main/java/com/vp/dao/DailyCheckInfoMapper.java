package com.vp.dao;

import com.vp.entity.DailyCheckInfo;
import com.vp.vo.DailyCheckInfoVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DailyCheckInfoMapper {

    int countNowCars(Integer dailyCheckId);

    int countRentCars(Integer dailyCheckId);

    int countOtherCars(Integer dailyCheckId);

    int deleteByPrimaryKey(Integer id);

    int insert(DailyCheckInfo record);

    int insertSelective(DailyCheckInfo record);

    DailyCheckInfo selectByPrimaryKey(Integer id);

    List<DailyCheckInfoVo> getDailyCheckInfoVos(@Param("dailyCheckId") Integer dailyCheckId, @Param("dailyCheckCarStatus") Integer dailyCheckCarStatus);

    int passDailyCheckInfo(Integer dailyCheckInfoId);

    int checkFinishAllDailyCheck(Integer dailyCheckId);

    int reportDailyCheckInfo(@Param("dailyCheckInfoId") Integer dailyCheckInfoId,@Param("remark") String remark);

    int updateByPrimaryKeySelective(DailyCheckInfo record);

    int updateByPrimaryKey(DailyCheckInfo record);
}