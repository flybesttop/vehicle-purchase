package com.vp.service;

import com.vp.entity.Car;
import com.vp.entity.CarLog;
import com.vp.entity.RentCar;
import com.vp.request.RentCarReq;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 车辆服务
 *
 * @author flybesttop
 * @date 2021-04-18
 */
@Service
public interface CarService {
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
     * 获取所有车辆
     *
     * @param userId
     * @param searchKey
     * @param carStatus
     * @return
     */
    List<Car> getCarList(String userId, String searchKey, Integer carStatus);

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
     * @return
     */
    Boolean changeCarStatus(String userOpenId, Integer carId, Integer carStatus);

    /**
     * 租赁车辆
     *
     * @param rentCar
     * @return
     */
    Boolean rentCar(RentCarReq rentCar);
}
