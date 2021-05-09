package com.vp.service;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.vp.entity.Node;
import com.vp.entity.Role;
import com.vp.entity.UserCompany;
import com.vp.vo.NodeVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色服务类
 *
 * @author flybesttop
 * @date 2021-03-29
 */
@Service
public interface RoleService {
    /**
     * 创建默认的角色类型
     *
     * @param openId
     * @param companyId
     * @return
     */
    Boolean createDefaultRole(String openId, Integer companyId);

    /**
     * 保存客户的用户企业关联
     * 保存的时候首先需要判断这个是否需要设为默认 如果需要就要修改当前已经存在的默认关系
     *
     * @param userCompany
     * @return
     */
    Boolean saveUserCompanyRole(UserCompany userCompany);

    /**
     * 查询公司角色
     *
     * @param companyId
     * @param searchKey
     * @return
     */
    List<Role> getCompanyRoles(Integer companyId, String searchKey);

    /**
     * 查询默认公司角色
     *
     * @param openId
     * @param searchKey
     * @return
     */
    List<Role> getDefaultCompanyRoles(String openId, String searchKey);

    /**
     * 获取邀请码
     *
     * @param roleId
     * @return
     */
    String getInvitationCode(Integer roleId, String openId);

    /**
     * 获取所有节点
     *
     * @return
     */
    NodeVo getAllNodes();

    /**
     * 获取所有用户节点
     *
     * @param roleId
     * @return
     */
    List<Node> getAllUserNodes(Integer roleId);

    /**
     * 保存角色节点权限
     *
     * @param nodeIds
     * @param chooseRoleId
     * @return
     */
    Boolean saveRoleNodes(List<String> nodeIds, Integer chooseRoleId);

    /**
     * 保存角色
     * @param role
     * @return
     */
    Boolean saveRole(Role role);

    /**
     * 删除角色
     * @param roleId
     * @return
     */
    Boolean deleteRole(Integer roleId);
}
