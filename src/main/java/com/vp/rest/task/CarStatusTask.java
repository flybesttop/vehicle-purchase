package com.vp.rest.task;

import com.vp.dao.CarLogMapper;
import com.vp.dao.CarMapper;
import com.vp.dao.RentCarMapper;
import com.vp.entity.Car;
import com.vp.entity.CarLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 车辆状态定时
 *
 * @author flybesttop
 * @date 2021-04-25
 */
@Slf4j
@Component
public class CarStatusTask {

    @Autowired
    private CarMapper carMapper;

    @Autowired
    private CarLogMapper carLogMapper;

    @Scheduled(cron = "0 1 0 * * ?")
    public void checkLeaseExpiredCar(){
        List<Car> cars=carMapper.getAllLeaseExpiredCar();
        for (Car c:cars) {
            c.setStatus(3);
            carMapper.updateByPrimaryKeySelective(c);
            CarLog carLog=new CarLog();
            carLog.setLog("车辆租赁超期");
            carLog.setCarId(c.getId());
            carLog.setOperatorId("0");
            carLog.setOperatorName("系统定时修改");
            carLogMapper.insertSelective(carLog);
        }
    }

}
