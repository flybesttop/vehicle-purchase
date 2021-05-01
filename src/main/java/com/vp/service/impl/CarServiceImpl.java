package com.vp.service.impl;

import com.github.pagehelper.util.StringUtil;
import com.vp.dao.*;
import com.vp.entity.*;
import com.vp.request.RentCarReq;
import com.vp.service.CarService;
import com.vp.vo.CarVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
public class CarServiceImpl<T> implements CarService {
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
    @Autowired
    private StopCarMapper stopCarMapper;
    @Autowired
    private RepairCarMapper repairCarMapper;
    @Autowired
    private SellCarMapper sellCarMapper;

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
                    carLog.setLog("更新车辆信息-" + car.getPurchaseUserName());
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
    public CarVo<?> getCarVoById(Integer carId) {
        Car c = carMapper.selectByPrimaryKey(carId);
        switch (c.getStatus()) {
            case 2:
            case 3:
                CarVo<RentCar> carVoRentCar = new CarVo<>();
                carVoRentCar.setCar(c);
                RentCar rentCar = rentCarMapper.getNowRentCar(c.getId());
                carVoRentCar.setCarMessage(rentCar);
                return carVoRentCar;
            case 4:
                CarVo<StopCar> carVoStopCar = new CarVo<>();
                carVoStopCar.setCar(c);
                StopCar stopCar = stopCarMapper.getNowStopCar(c.getId());
                carVoStopCar.setCarMessage(stopCar);
                return carVoStopCar;
            case 5:
                CarVo<RepairCar> carVoRepairCar = new CarVo<>();
                carVoRepairCar.setCar(c);
                RepairCar repairCar = repairCarMapper.getNowRepairCar(c.getId());
                carVoRepairCar.setCarMessage(repairCar);
                return carVoRepairCar;
            case 6:
                CarVo<SellCar> carVoSellCar = new CarVo<>();
                carVoSellCar.setCar(c);
                SellCar sellCar = sellCarMapper.getNowSellCar(c.getId());
                carVoSellCar.setCarMessage(sellCar);
                return carVoSellCar;
            default:
                CarVo<?> defaultCarVo = new CarVo<>();
                defaultCarVo.setCar(c);
                return defaultCarVo;
        }
    }

    @Override
    public List<CarVo<?>> getCarList(String userId, String searchKey, Integer carStatus) {
        Company defaultCompany = companyMapper.getDefaultCompanyInfo(userId);
        List<Car> cars = carMapper.getCarList(defaultCompany.getId(), searchKey, carStatus);
        List<CarVo<?>> carVos = new ArrayList<>();
        for (Car c : cars) {
            switch (c.getStatus()) {
                case 2:
                case 3:
                    CarVo<RentCar> carVoRentCar = new CarVo<>();
                    carVoRentCar.setCar(c);
                    RentCar rentCar = rentCarMapper.getNowRentCar(c.getId());
                    carVoRentCar.setCarMessage(rentCar);
                    carVos.add(carVoRentCar);
                    break;
                case 4:
                    CarVo<StopCar> carVoStopCar = new CarVo<>();
                    carVoStopCar.setCar(c);
                    StopCar stopCar = stopCarMapper.getNowStopCar(c.getId());
                    carVoStopCar.setCarMessage(stopCar);
                    carVos.add(carVoStopCar);
                    break;
                case 5:
                    CarVo<RepairCar> carVoRepairCar = new CarVo<>();
                    carVoRepairCar.setCar(c);
                    RepairCar repairCar = repairCarMapper.getNowRepairCar(c.getId());
                    carVoRepairCar.setCarMessage(repairCar);
                    carVos.add(carVoRepairCar);
                    break;
                case 6:
                    CarVo<SellCar> carVoSellCar = new CarVo<>();
                    carVoSellCar.setCar(c);
                    SellCar sellCar = sellCarMapper.getNowSellCar(c.getId());
                    carVoSellCar.setCarMessage(sellCar);
                    carVos.add(carVoSellCar);
                    break;
                default:
                    CarVo<?> defaultCarVo = new CarVo<>();
                    defaultCarVo.setCar(c);
                    carVos.add(defaultCarVo);
                    break;
            }
        }
        return carVos;
    }

    @Override
    public List<CarLog> getCarLogList(Integer carId) {
        return carLogMapper.getCarLogList(carId);
    }

    @Override
    @Transactional
    public Boolean changeCarStatus(String userOpenId, Integer carId, Integer carStatus, String message) {
        try {
            changeCarOldStatus(carId);
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
            carLog.setLog("状态修改：" + oldCarStatus.getStatusName() + " -> " + newCarStatus.getStatusName() + "-" + user.getUsername() + " " + message);
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
            RentCar rentCar = rentCarMapper.getNowRentCar(rentCarReq.getCarId());
            if (Objects.nonNull(rentCar)) {
                return false;
            }
            rentCar = new RentCar();
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
            changeCarStatus(rentCarReq.getOperatorId(), rentCarReq.getCarId(), rentCarReq.getNewStatus(), "车辆出租：" + rentCarReq.getRentPeoplePhone() + "-" + rentCarReq.getRentPeopleName() + ",时间：【" + rentCarReq.getRentTimeStart() + "】-【" + rentCarReq.getRentTimeEnd() + "】");
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean updateRentCar(RentCar rentCar) {
        try {
            rentCarMapper.updateByPrimaryKeySelective(rentCar);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean stopCar(String remark, Integer carId, String openId, String username) {
        try {
            StopCar stopCar = stopCarMapper.getNowStopCar(carId);
            if (Objects.nonNull(stopCar)) {
                return false;
            }
            stopCar = new StopCar();
            stopCar.setCarId(carId);
            stopCar.setOperatorId(openId);
            stopCar.setOperatorName(username);
            stopCar.setStopReason(remark);
            stopCarMapper.insertSelective(stopCar);
            changeCarStatus(openId, carId, 4, "原因：" + remark);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean updateStopCar(StopCar stopCar) {
        try {
            stopCarMapper.updateByPrimaryKeySelective(stopCar);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean repairCar(RepairCar repairCar) {
        try {
            RepairCar repairCarOld = repairCarMapper.getNowRepairCar(repairCar.getCarId());
            if (Objects.nonNull(repairCarOld)) {
                return false;
            }
            repairCarMapper.insertSelective(repairCar);
            changeCarStatus(repairCar.getOperatorId(), repairCar.getCarId(), 5, "原因：" + repairCar.getRemark());
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean updateRepairCar(RepairCar repairCar) {
        try {
            repairCarMapper.updateByPrimaryKeySelective(repairCar);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean sellCar(SellCar sellCar) {
        try {
            RepairCar repairCarOld = repairCarMapper.getNowRepairCar(sellCar.getCarId());
            if (Objects.nonNull(repairCarOld)) {
                return false;
            }
            sellCarMapper.insertSelective(sellCar);
            changeCarStatus(sellCar.getOperatorId(), sellCar.getCarId(), 6, "出售：" + sellCar.getSellPeoplePhone() + "-" + sellCar.getSellPeopleName() + "，出售价格(￥万)：" + sellCar.getSellPrice());
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean updateSellCar(SellCar sellCar) {
        try {
            sellCarMapper.updateByPrimaryKeySelective(sellCar);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    /**
     * 把老的状态取消掉
     *
     * @param carId
     */
    private void changeCarOldStatus(Integer carId) {
        Car car = carMapper.selectByPrimaryKey(carId);
        switch (car.getStatus()) {
            case 1:
                break;
            case 2:
                RentCar rentCar = rentCarMapper.getNowRentCar(carId);
                rentCar.setStatus(0);
                rentCarMapper.updateByPrimaryKeySelective(rentCar);
                break;
            case 4:
                StopCar stopCar = stopCarMapper.getNowStopCar(carId);
                stopCar.setStatus(0);
                stopCarMapper.updateByPrimaryKeySelective(stopCar);
                break;
            case 5:
                RepairCar repairCar = repairCarMapper.getNowRepairCar(carId);
                repairCar.setStatus(0);
                repairCarMapper.updateByPrimaryKeySelective(repairCar);
                break;
            default:
                break;
        }
    }
}
