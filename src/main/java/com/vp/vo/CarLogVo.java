package com.vp.vo;

import com.vp.entity.CarLog;
import lombok.Data;

import java.util.List;

/**
 * 车辆日志
 *
 * @author mtx
 * @date 2021-04-25
 */
@Data
public class CarLogVo {

    private String date;

    private List<CarLog> carLogs;

}
