package com.vp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 公告请求类
 *
 * @author flybesttop
 * @date 2021-05-09
 */
@Data
public class NoticeDto {

    private Integer noticeId;

    private String title;

    private String content;

    private String openId;

    private String indexImg;

    private String operatorName;

    private String operatorImg;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime operatorTime;

    private List<String> imgList;
}
