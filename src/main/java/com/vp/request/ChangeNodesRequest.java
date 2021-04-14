package com.vp.request;

import lombok.Data;

import java.util.List;

/**
 * 修改节点请求
 * @author flybesttop
 * @date 2021-04-13
 */
@Data
public class ChangeNodesRequest {

    List<String> nodeIds;

    Integer chooseRoleId;

}
