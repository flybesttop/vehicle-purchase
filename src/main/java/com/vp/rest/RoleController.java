package com.vp.rest;

import com.vp.entity.Node;
import com.vp.entity.Role;
import com.vp.request.ChangeNodesRequest;
import com.vp.service.RoleService;
import com.vp.service.UserCompanyService;
import com.vp.util.BaseResponse;
import com.vp.vo.NodeVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * 角色控制
 *
 * @author flybesttop
 * @date 2021-04-08
 */
@Slf4j
@RestController
@RequestMapping("role")
public class RoleController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private UserCompanyService userCompanyService;

    @RequestMapping(value = "getDefaultCompanyRoles", method = POST)
    public BaseResponse<List<Role>> getDefaultCompanyRoles(String openId, String searchKey) {
        List<Role> roles = roleService.getDefaultCompanyRoles(openId, searchKey);
        return new BaseResponse<>(roles);
    }

    @RequestMapping(value = "getAllNodes", method = POST)
    public BaseResponse<NodeVo> getAllNodes() {
        NodeVo nodeVo = roleService.getAllNodes();
        return new BaseResponse<>(nodeVo);
    }

    @RequestMapping(value = "getAllUserNodes", method = POST)
    public BaseResponse<List<Node>> getAllUserNodes(Integer roleId) {
        List<Node> roles = roleService.getAllUserNodes(roleId);
        return new BaseResponse<>(roles);
    }

    @RequestMapping(value = "saveRoleNodes", method = POST)
    public BaseResponse<Boolean> saveRoleNodes(@RequestBody ChangeNodesRequest cmd) {
        Boolean data = roleService.saveRoleNodes(cmd.getNodeIds(), cmd.getChooseRoleId());
        return new BaseResponse<>(data);
    }

    @RequestMapping(value = "saveRole", method = POST)
    public BaseResponse<Boolean> saveRole(@RequestBody Role role) {
        Boolean data = roleService.saveRole(role);
        return new BaseResponse<>(data);
    }

    @RequestMapping(value = "changeRole", method = POST)
    public BaseResponse<Boolean> changeRole(Integer userCompanyId, Integer newRoleId) {
        Boolean res = userCompanyService.changeUserRole(userCompanyId, newRoleId);
        return new BaseResponse<>(res);
    }

}
