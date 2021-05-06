package com.vp.rest;

import com.github.pagehelper.Page;
import com.vp.entity.DailyCheck;
import com.vp.service.DailyCheckService;
import com.vp.util.BaseResponse;
import com.vp.util.ResultCode;
import com.vp.vo.DailyCheckInfoVo;
import com.vp.vo.DailyCheckMainInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

/**
 * 盘点
 *
 * @author flybesttop
 * @date 2021-05-02
 */
@Slf4j
@RestController
@RequestMapping(value = "dailyCheck")
public class DailyCheckController {

    @Autowired
    private DailyCheckService dailyCheckService;

    /**
     * 创建盘点
     *
     * @param openId
     * @param checkDate
     * @return
     */
    @RequestMapping("createDailyCheck")
    public BaseResponse<Boolean> createDailyCheck(String openId, String checkDate) {
        Integer flag = dailyCheckService.createDailyCheck(openId, LocalDate.parse(checkDate));
        if (flag == 1) {
            return new BaseResponse<>(true, ResultCode.SUCCESS, "创建成功");
        } else if (flag == 3) {
            return new BaseResponse<>(false, ResultCode.FAILURE, "存在同日期盘点");
        }
        return new BaseResponse<>(false, ResultCode.FAILURE, "创建失败");
    }

    /**
     * 获取盘点
     *
     * @param openId
     * @return
     */
    @RequestMapping("getDailyChecks")
    public BaseResponse<Page<DailyCheck>> getDailyChecks(String searchKey, String openId, Integer pageNum, Integer pageSize) {
        Page<DailyCheck> dailyChecks = dailyCheckService.getDailyChecks(searchKey, openId, pageNum, pageSize);
        return new BaseResponse<>(dailyChecks);
    }

    /**
     * 获取每日盘点主信息
     *
     * @param dailyCheckId
     * @return
     */
    @RequestMapping("getDailyCheckMainInfo")
    public BaseResponse<DailyCheckMainInfoVo> getDailyCheckMainInfo(Integer dailyCheckId) {
        DailyCheckMainInfoVo dailyCheckMainInfoVo = dailyCheckService.getDailyCheckMainInfo(dailyCheckId);
        return new BaseResponse<>(dailyCheckMainInfoVo);
    }

    /**
     * 获取每日盘点信息
     *
     * @param dailyCheckId
     * @return
     */
    @RequestMapping("getDailyCheckInfo")
    public BaseResponse<List<DailyCheckInfoVo>> getDailyCheckInfo(Integer dailyCheckId, Integer dailyCheckCarStatus) {
        List<DailyCheckInfoVo> dailyCheckMainInfoVo = dailyCheckService.getDailyCheckInfo(dailyCheckId, dailyCheckCarStatus);
        return new BaseResponse<>(dailyCheckMainInfoVo);
    }

    /**
     * 通过盘点
     *
     * @param dailyCheckInfoId
     * @return
     */
    @RequestMapping("passDailyCheckInfo")
    public BaseResponse<Boolean> passDailyCheckInfo(Integer dailyCheckInfoId) {
        Boolean flag = dailyCheckService.passDailyCheckInfo(dailyCheckInfoId);
        return new BaseResponse<>(flag);
    }

    /**
     * 提交盘点问题
     *
     * @param dailyCheckInfoId
     * @return
     */
    @RequestMapping("reportDailyCheckInfo")
    public BaseResponse<Boolean> reportDailyCheckInfo(Integer dailyCheckInfoId, String remark) {
        Boolean flag = dailyCheckService.reportDailyCheckInfo(dailyCheckInfoId, remark);
        return new BaseResponse<>(flag);
    }

    /**
     * 结束盘点
     *
     * @param dailyCheckId
     * @return
     */
    @RequestMapping("finishDailyCheck")
    public BaseResponse<Boolean> finishDailyCheck(Integer dailyCheckId){
        Integer flag = dailyCheckService.finishDailyCheck(dailyCheckId);
        if (flag == 1) {
            return new BaseResponse<>(true, ResultCode.SUCCESS, "成功盘点");
        } else if (flag == 2) {
            return new BaseResponse<>(false, ResultCode.FAILURE, "仍存在问题盘点");
        }
        return new BaseResponse<>(false, ResultCode.FAILURE, "失败");
    }

    /**
     * 提交问题发起复盘
     *
     * @param dailyCheckId
     * @return
     */
    @RequestMapping("reportDailyCheck")
    public BaseResponse<Boolean> reportDailyCheck(Integer dailyCheckId){
        Integer flag = dailyCheckService.reportDailyCheck(dailyCheckId);
        if (flag == 1) {
            return new BaseResponse<>(true, ResultCode.SUCCESS, "成功发起复盘");
        }
        return new BaseResponse<>(false, ResultCode.FAILURE, "失败");
    }

}
