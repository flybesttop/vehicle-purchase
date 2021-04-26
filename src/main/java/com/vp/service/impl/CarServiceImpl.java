package com.vp.service.impl;

import com.github.pagehelper.util.StringUtil;
import com.vp.dao.*;
import com.vp.entity.*;
import com.vp.request.RentCarReq;
import com.vp.service.CarService;
import com.vp.vo.CompanyMemberVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;


/**
 * 车辆服务实现
 *
 * @author flybesttop
 * @date 2021-04-18
 */
@Slf4j
@Service
public class CarServiceImpl implements CarService {
    @Autowired
    private CarMapper carMapper;
    @Autowired
    private CarLogMapper carLogMapper;
    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CarStatusMapper carStatusMapper;
    @Autowired
    private RentCarMapper rentCarMapper;

    @Override
    public Boolean createCar(Car car) {
        try {
            if (!StringUtil.isEmpty(car.getVim())) {
                Car oldCar = carMapper.getCarByVim(car.getVim());
                if (Objects.isNull(oldCar)) {
                    Company defaultCompany = companyMapper.getDefaultCompanyInfo(car.getPurchaseUserId());
                    car.setPurchaseCompanyId(defaultCompany.getId());
                    car.setPurchaseCompanyName(defaultCompany.getCompanyName());
                    carMapper.insertSelective(car);
                    CarLog carLog = new CarLog();
                    carLog.setCarId(car.getId());
                    carLog.setLog("创建车辆-" + car.getPurchaseUserName());
                    carLog.setOperatorId(car.getPurchaseUserId());
                    carLog.setOperatorName(car.getPurchaseUserName());
                    carLogMapper.insertSelective(carLog);
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean saveCar(Car car) {
        try {
            if (!StringUtil.isEmpty(car.getVim())) {
                Car oldCar = carMapper.getCarByVimOther(car.getVim(), car.getId());
                if (Objects.isNull(oldCar)) {
                    carMapper.updateByPrimaryKeySelective(car);
                    CarLog carLog = new CarLog();
                    carLog.setCarId(car.getId());
                    carLog.setLog("更新车辆-" + car.getPurchaseUserName());
                    carLog.setOperatorId(car.getPurchaseUserId());
                    carLog.setOperatorName(car.getPurchaseUserName());
                    carLogMapper.insertSelective(carLog);
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    @Override
    public Car getCarByVim(String vim) {
        return carMapper.getCarByVim(vim);
    }

    @Override
    public Car getCarById(Integer carId) {
        return carMapper.selectByPrimaryKey(carId);
    }

    @Override
    public List<Car> getCarList(String userId, String searchKey, Integer carStatus) {
        Company defaultCompany = companyMapper.getDefaultCompanyInfo(userId);
        List<Car> cars = carMapper.getCarList(defaultCompany.getId(), searchKey, carStatus);
        return cars;
    }

    @Override
    public List<CarLog> getCarLogList(Integer carId) {
        return carLogMapper.getCarLogList(carId);
    }

    @Override
    @Transactional
    public Boolean changeCarStatus(String userOpenId, Integer carId, Integer carStatus) {
        try {
            User user = userMapper.selectByPrimaryKey(userOpenId);
            Car car = carMapper.selectByPrimaryKey(carId);
            CarStatus newCarStatus = carStatusMapper.selectByStatusId(carStatus);
            CarStatus oldCarStatus = carStatusMapper.selectByStatusId(car.getStatus());
            car.setStatus(carStatus);
            carMapper.updateByPrimaryKeySelective(car);
            CarLog carLog = new CarLog();
            carLog.setOperatorId(userOpenId);
            carLog.setOperatorName(user.getUsername());
            carLog.setCarId(carId);
            carLog.setLog("状态修改：" + oldCarStatus.getStatusName() + " -> " + newCarStatus.getStatusName() + "-" + user.getUsername());
            carLogMapper.insertSelective(carLog);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean rentCar(RentCarReq rentCarReq) {
        try {
            RentCar rentCar = new RentCar();
            rentCar.setCarId(rentCarReq.getCarId());
            rentCar.setOperatorId(rentCarReq.getOperatorId());
            rentCar.setOperatorName(rentCarReq.getOperatorName());
            rentCar.setRentPeopleIdcard(rentCarReq.getRentPeopleIdcard());
            rentCar.setRentPeopleName(rentCarReq.getRentPeopleName());
            rentCar.setRentPeoplePhone(rentCarReq.getRentPeoplePhone());
            rentCar.setRentPrice(rentCarReq.getRentPrice());
            rentCar.setRentTimeEnd(rentCarReq.getRentTimeEnd());
            rentCar.setRentTimeStart(rentCarReq.getRentTimeStart());
            rentCar.setRemark(rentCarReq.getRemark());
            rentCarMapper.insertSelective(rentCar);
            changeCarStatus(rentCarReq.getOperatorId(), rentCarReq.getCarId(), rentCarReq.getNewStatus());
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }

    }
}
