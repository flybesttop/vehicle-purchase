package com.vp.service;

import com.vp.entity.*;
import com.vp.request.RentCarReq;
import com.vp.vo.CarVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 车辆服务
 *
 * @author flybesttop
 * @date 2021-04-18
 */
@Service
public interface CarService<T> {
    /**
     * 创建车辆
     *
     * @param car
     * @return
     */
    Boolean createCar(Car car);

    /**
     * 创建车辆
     *
     * @param car
     * @return
     */
    Boolean saveCar(Car car);

    /**
     * 通过车架号查询车辆
     *
     * @param vim
     * @return
     */
    Car getCarByVim(String vim);

    /**
     * 通过id查询车辆
     *
     * @param carId
     * @return
     */
    Car getCarById(Integer carId);

    /**
     * 获取车辆信息
     *
     * @param carId
     * @return
     */
    CarVo<?> getCarVoById(Integer carId);

    /**
     * 获取所有车辆
     *
     * @param userId
     * @param searchKey
     * @param carStatus
     * @return
     */
    List<CarVo<?>> getCarList(String userId, String searchKey, Integer carStatus);

    /**
     * 或者车辆日志
     *
     * @param carId
     * @return
     */
    List<CarLog> getCarLogList(Integer carId);

    /**
     * 修改车辆状态
     *
     * @param userOpenId
     * @param carId
     * @param carStatus
     * @param message
     * @return
     */
    Boolean changeCarStatus(String userOpenId, Integer carId, Integer carStatus, String message);

    /**
     * 租赁车辆
     *
     * @param rentCar
     * @return
     */
    Boolean rentCar(RentCarReq rentCar);

    /**
     * 更新租赁车辆信息
     *
     * @param rentCar
     * @return
     */
    Boolean updateRentCar(RentCar rentCar);

    /**
     * 停用车辆
     *
     * @param remark
     * @param carId
     * @param openId
     * @return
     */
    Boolean stopCar(String remark, Integer carId, String openId, String username);

    /**
     * 更新停用车辆信息
     *
     * @param stopCar
     * @return
     */
    Boolean updateStopCar(StopCar stopCar);

    /**
     * 维修车辆
     *
     * @param repairCar
     * @return
     */
    Boolean repairCar(RepairCar repairCar);

    /**
     * 更新维修车辆信息
     *
     * @param repairCar
     * @return
     */
    Boolean updateRepairCar(RepairCar repairCar);

    /**
     * 出售车辆
     *
     * @param sellCar
     * @return
     */
    Boolean sellCar(SellCar sellCar);

    /**
     * 更新出售车辆信息
     *
     * @param sellCar
     * @return
     */
    Boolean updateSellCar(SellCar sellCar);
}
