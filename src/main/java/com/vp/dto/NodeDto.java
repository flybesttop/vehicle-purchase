package com.vp.dto;

import com.vp.entity.Node;
import lombok.Data;

import java.util.List;

/**
 * 节点数据类
 * @author flybesttop
 * @date 2021-04-09
 */
@Data
public class NodeDto {

    /**
     * 顶层节点
     */
    private Node topNode;

    /**
     * 子节点
     */
    private List<Node> childNodeList;


}
