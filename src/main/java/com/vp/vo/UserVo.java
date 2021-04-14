package com.vp.vo;

import com.vp.entity.Node;
import com.vp.entity.User;
import lombok.Data;

import java.util.List;

/**
 * 用户
 * @author flybesttop
 * @date 2021-04-13
 */
@Data
public class UserVo {

    private User user;

    private List<Node> nodes;
}
