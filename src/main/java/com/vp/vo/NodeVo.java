package com.vp.vo;

import com.vp.dto.NodeDto;
import lombok.Data;

import java.util.List;

/**
 * 权限节点
 * @author flybesttop
 * @date 2021-04-09
 */
@Data
public class NodeVo {

    /**
     * 顶层节点
     */
    List<NodeDto> topNodeList;
}
