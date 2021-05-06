package com.vp.dao;

import com.github.pagehelper.Page;
import com.vp.entity.DailyCheck;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DailyCheckMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DailyCheck record);

    int insertSelective(DailyCheck record);

    DailyCheck selectByPrimaryKey(Integer id);

    DailyCheck selectByCheckDate(LocalDate checkDate);

    Page<DailyCheck> getDailyChecks(Page<DailyCheck> page, @Param("searchKey") String searchKey, @Param("companyId") Integer companyId);

    int updateByPrimaryKeySelective(DailyCheck record);

    int updateByPrimaryKey(DailyCheck record);

    int finishDailyCheck(Integer dailyCheckId);

    int reportDailyCheck(Integer dailyCheckId);
}