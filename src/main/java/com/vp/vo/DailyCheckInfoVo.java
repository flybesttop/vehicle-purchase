package com.vp.vo;

import lombok.Data;

/**
 * 每日盘点信息
 *
 * @author flybesttop
 * @date 2021-05-03
 */
@Data
public class DailyCheckInfoVo {

    private Integer carId;

    private String carNumber;

    private String carBrand;

    private String carImg;

    private String carColor;

    private String carModel;

    private String engineNumber;

    private String vim;

    private String carStatus;

    private Integer dailyCheckInfoId;

    private Integer dailyCheckInfoStatus;

    private String dailyCheckRemark;

}
