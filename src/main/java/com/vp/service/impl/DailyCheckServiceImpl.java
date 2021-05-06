package com.vp.service.impl;

import com.github.pagehelper.Page;
import com.vp.dao.*;
import com.vp.entity.*;
import com.vp.service.DailyCheckService;
import com.vp.vo.DailyCheckInfoVo;
import com.vp.vo.DailyCheckMainInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * 盘点服务类实现类
 *
 * @author flybesttop
 * @date 2021-05-02
 */
@Slf4j
@Service
public class DailyCheckServiceImpl implements DailyCheckService {

    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
    private CarMapper carMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DailyCheckMapper dailyCheckMapper;
    @Autowired
    private DailyCheckInfoMapper dailyCheckInfoMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer createDailyCheck(String openId, LocalDate checkDate) {
        DailyCheck dailyCheck = dailyCheckMapper.selectByCheckDate(checkDate);
        if (Objects.nonNull(dailyCheck)) {
            return 3;
        }
        try {
            Company defaultCompany = companyMapper.getDefaultCompanyInfo(openId);
            User user = userMapper.selectByPrimaryKey(openId);
            dailyCheck = new DailyCheck();
            dailyCheck.setCheckDate(checkDate);
            dailyCheck.setCompanyId(defaultCompany.getId());
            dailyCheck.setOperatorId(openId);
            dailyCheck.setOperatorName(user.getUsername());
            dailyCheckMapper.insertSelective(dailyCheck);
            List<Car> cars = carMapper.getCarList(defaultCompany.getId(), "", 0);
            for (Car car : cars) {
                DailyCheckInfo dailyCheckInfo = new DailyCheckInfo();
                dailyCheckInfo.setCarId(car.getId());
                dailyCheckInfo.setCompanyId(defaultCompany.getId());
                dailyCheckInfo.setDailyCheckId(dailyCheck.getId());
                dailyCheckInfo.setOperatorId(openId);
                dailyCheckInfo.setOperatorName(user.getUsername());
                dailyCheckInfo.setStatus(0);
                dailyCheckInfoMapper.insertSelective(dailyCheckInfo);
            }
            return 1;
        } catch (Exception e) {
            log.error(e.getMessage());
            return 2;
        }
    }

    @Override
    public Page<DailyCheck> getDailyChecks(String searchKey, String openId, Integer pageNum, Integer pageSize) {
        Company defaultCompany = companyMapper.getDefaultCompanyInfo(openId);
        Page<DailyCheck> pageData = new Page<>(pageNum, pageSize);
        pageData = dailyCheckMapper.getDailyChecks(pageData, searchKey, defaultCompany.getId());
        return pageData;
    }

    @Override
    public DailyCheckMainInfoVo getDailyCheckMainInfo(Integer dailyCheckId) {
        Integer nowCars = dailyCheckInfoMapper.countNowCars(dailyCheckId);
        Integer rentCars = dailyCheckInfoMapper.countRentCars(dailyCheckId);
        Integer otherCars = dailyCheckInfoMapper.countOtherCars(dailyCheckId);
        DailyCheckMainInfoVo dailyCheckMainInfoVo = new DailyCheckMainInfoVo();
        dailyCheckMainInfoVo.setNowCars(nowCars);
        dailyCheckMainInfoVo.setRentCars(rentCars);
        dailyCheckMainInfoVo.setOtherCars(otherCars);
        return dailyCheckMainInfoVo;
    }

    @Override
    public List<DailyCheckInfoVo> getDailyCheckInfo(Integer dailyCheckId, Integer dailyCheckCarStatus) {
        return dailyCheckInfoMapper.getDailyCheckInfoVos(dailyCheckId, dailyCheckCarStatus);
    }

    @Override
    public Boolean passDailyCheckInfo(Integer dailyCheckInfoId) {
        return dailyCheckInfoMapper.passDailyCheckInfo(dailyCheckInfoId) == 1;
    }

    @Override
    public Boolean reportDailyCheckInfo(Integer dailyCheckInfoId, String remark) {
        return dailyCheckInfoMapper.reportDailyCheckInfo(dailyCheckInfoId, remark) == 1;
    }

    @Override
    public Integer finishDailyCheck(Integer dailyCheckId) {
        Integer countNotFinish = dailyCheckInfoMapper.checkFinishAllDailyCheck(dailyCheckId);
        if (countNotFinish > 0) {
            return 2;
        }
        try {
            dailyCheckMapper.finishDailyCheck(dailyCheckId);
            return 1;
        }catch (Exception e){
            log.error(e.getMessage());
            return 3;
        }
    }

    @Override
    public Integer reportDailyCheck(Integer dailyCheckId) {
        try {
            dailyCheckMapper.reportDailyCheck(dailyCheckId);
            return 1;
        }catch (Exception e){
            log.error(e.getMessage());
            return 2;
        }
    }

}
