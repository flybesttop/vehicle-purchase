package com.vp.rest;

import com.vp.entity.*;
import com.vp.request.RentCarReq;
import com.vp.service.CarService;
import com.vp.util.BaseResponse;
import com.vp.vo.CarLogVo;
import com.vp.vo.CarVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * 车辆控制
 *
 * @author flybesttop
 * @date 2021-04-18
 */
@Slf4j
@RestController
@RequestMapping("car")
public class CarController {
    @Autowired
    private CarService carService;

    @RequestMapping(value = "createCar", method = POST)
    public BaseResponse<Boolean> createCar(@RequestBody Car car) {
        Boolean res = carService.createCar(car);
        return new BaseResponse<>(res);
    }

    @RequestMapping(value = "saveCar", method = POST)
    public BaseResponse<Boolean> saveCar(@RequestBody Car car) {
        Boolean res = carService.saveCar(car);
        return new BaseResponse<>(res);
    }

    @RequestMapping(value = "getCar", method = GET)
    public BaseResponse<CarVo<?>> getCar(Integer carId) {
        CarVo<?> car = carService.getCarVoById(carId);
        return new BaseResponse<>(car);
    }

    @RequestMapping(value = "getCarList", method = GET)
    public BaseResponse<List<CarVo<?>>> getCarList(String userOpenId, String searchKey, Integer carStatus) throws Exception{
        List<CarVo<?>> cars = carService.getCarList(userOpenId, searchKey, carStatus);
        return new BaseResponse<>(cars);
    }

    @RequestMapping(value = "changeCarStatus", method = POST)
    public BaseResponse<Boolean> changeCarStatus(String userOpenId, Integer carId, Integer carStatus) {
        Boolean res = carService.changeCarStatus(userOpenId, carId, carStatus, "");
        return new BaseResponse<>(res);
    }

    @RequestMapping(value = "getCarLogList", method = GET)
    public BaseResponse<List<CarLogVo>> getCarLogList(Integer carId) {
        List<CarLog> data = carService.getCarLogList(carId);
        Map<String, List<CarLog>> dataMap = data.stream().collect(Collectors.groupingBy(item -> item.getCreateTime().toLocalDate().toString()));
        List<CarLogVo> dataVo = new ArrayList<>();
        for (Map.Entry<String, List<CarLog>> entry : dataMap.entrySet()) {
            CarLogVo cvo = new CarLogVo();
            cvo.setDate(entry.getKey());
            cvo.setCarLogs(entry.getValue());
            dataVo.add(cvo);
        }
        dataVo = dataVo.stream().sorted(Comparator.comparing(CarLogVo::getDate).reversed()).collect(Collectors.toList());
        return new BaseResponse<>(dataVo);
    }

    @RequestMapping(value = "rentCar", method = POST)
    public BaseResponse<Boolean> rentCar(@RequestBody RentCarReq rentCar) {
        Boolean res = carService.rentCar(rentCar);
        return new BaseResponse<>(res);
    }

    @RequestMapping(value = "updateRentCar", method = POST)
    public BaseResponse<Boolean> updateRentCar(@RequestBody RentCar rentCar) {
        Boolean res = carService.updateRentCar(rentCar);
        return new BaseResponse<>(res);
    }

    @RequestMapping(value = "stopCar", method = POST)
    public BaseResponse<Boolean> stopCar(String remark, Integer carId, String openId, String username) {
        Boolean res = carService.stopCar(remark, carId, openId, username);
        return new BaseResponse<>(res);
    }

    @RequestMapping(value = "updateStopCar", method = POST)
    public BaseResponse<Boolean> updateStopCar(@RequestBody StopCar stopCar) {
        Boolean res = carService.updateStopCar(stopCar);
        return new BaseResponse<>(res);
    }

    @RequestMapping(value = "repairCar", method = POST)
    public BaseResponse<Boolean> repairCar(@RequestBody RepairCar repairCar) {
        Boolean res = carService.repairCar(repairCar);
        return new BaseResponse<>(res);
    }

    @RequestMapping(value = "updateRepairCar", method = POST)
    public BaseResponse<Boolean> updateRepairCar(@RequestBody RepairCar repairCar) {
        Boolean res = carService.updateRepairCar(repairCar);
        return new BaseResponse<>(res);
    }

    @RequestMapping(value = "sellCar", method = POST)
    public BaseResponse<Boolean> sellCar(@RequestBody SellCar sellCar) {
        Boolean res = carService.sellCar(sellCar);
        return new BaseResponse<>(res);
    }

    @RequestMapping(value = "updateSellCar", method = POST)
    public BaseResponse<Boolean> updateSellCar(@RequestBody SellCar sellCar) {
        Boolean res = carService.updateSellCar(sellCar);
        return new BaseResponse<>(res);
    }

}
