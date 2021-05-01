package com.vp.vo;

import com.vp.entity.Car;
import lombok.Data;

/**
 * 不同状态的车辆信息显示
 *
 * @author flybesttop
 * @date 2021-04-28
 */
@Data
public class CarVo<T> {

    /**
     * 车辆信息
     */
    private Car car;

    /**
     * 对于不同状态的车辆会有不同的专属信息
     */
    private T carMessage;
}
