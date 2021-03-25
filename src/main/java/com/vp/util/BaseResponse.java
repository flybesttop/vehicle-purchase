package com.vp.util;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * 通用返回对象
 *
 * @author flybesttop
 * @date 2021-03-24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BaseResponse<T> implements Serializable {

    /**
     * 每次返回对应唯一一笔id
     */
    @Builder.Default
    private String id = UUID.randomUUID().toString().replace("-","");
    /**
     * 系统返回时间
     */
    @Builder.Default
    @JsonFormat(pattern = DateFormatterStr.NORMAL)
    private LocalDateTime resTime = LocalDateTime.now();
    @Builder.Default
    private ResultCode code = ResultCode.SUCCESS;
    /**
     * 对当前code的消息描述
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    public boolean isSuccess() {
        return code == ResultCode.SUCCESS;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public BaseResponse(T data) {
        this.data = data;
        this.id = UUID.randomUUID().toString().replace("-","");
        this.resTime = LocalDateTime.now();
        this.code = ResultCode.SUCCESS;
    }

    public BaseResponse(T data , ResultCode code, String message) {
        this.data = data;
        this.id = UUID.randomUUID().toString().replace("-","");
        this.resTime = LocalDateTime.now();
        this.code = code;
        this.message = message;
    }
}
