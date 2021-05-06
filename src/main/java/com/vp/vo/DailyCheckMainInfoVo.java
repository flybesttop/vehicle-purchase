package com.vp.vo;

import com.vp.entity.DailyCheckInfo;
import lombok.Data;

import java.util.List;

/**
 * 盘点类
 *
 * @author flybesttop
 * @date 2021-05-02
 */
@Data
public class DailyCheckMainInfoVo {

    /**
     * 当前车辆
     */
    private Integer nowCars;

    /**
     * 租出车辆
     */
    private Integer rentCars;

    /**
     * 因故转移车辆
     */
    private Integer otherCars;

}
