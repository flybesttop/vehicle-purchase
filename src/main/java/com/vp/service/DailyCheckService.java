package com.vp.service;

import com.github.pagehelper.Page;
import com.sun.org.apache.xpath.internal.operations.Bool;
import com.vp.entity.DailyCheck;
import com.vp.vo.DailyCheckInfoVo;
import com.vp.vo.DailyCheckMainInfoVo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * 盘点服务类
 *
 * @author flybesttop
 * @date 2021-05-02
 */
@Service
public interface DailyCheckService {

    /**
     * 创建每日盘点
     *
     * @param openId
     * @param checkDate
     * @return
     */
    Integer createDailyCheck(String openId, LocalDate checkDate);

    /**
     * 获取每日盘点信息
     *
     * @param searchKey
     * @param openId
     * @param pageNum
     * @param pageSize
     * @return
     */
    Page<DailyCheck> getDailyChecks(String searchKey, String openId, Integer pageNum, Integer pageSize);

    /**
     * 获取盘点信息
     *
     * @param dailyCheckId
     * @return
     */
    DailyCheckMainInfoVo getDailyCheckMainInfo(Integer dailyCheckId);

    /**
     * 获取日常盘点信息
     *
     * @param dailyCheckId
     * @param dailyCheckStatus
     * @return
     */
    List<DailyCheckInfoVo> getDailyCheckInfo(Integer dailyCheckId, Integer dailyCheckStatus);

    /**
     * 通过盘点
     *
     * @param dailyCheckInfoId
     * @return
     */
    Boolean passDailyCheckInfo(Integer dailyCheckInfoId);

    /**
     * 提交盘点问题
     *
     * @param dailyCheckInfoId
     * @param remark
     * @return
     */
    Boolean reportDailyCheckInfo(Integer dailyCheckInfoId, String remark);

    /**
     * 提交盘点问题
     *
     * @param dailyCheckId
     * @return
     */
    Integer finishDailyCheck(Integer dailyCheckId);

    /**
     * 提交盘点问题
     *
     * @param dailyCheckId
     * @return
     */
    Integer reportDailyCheck(Integer dailyCheckId);
}
