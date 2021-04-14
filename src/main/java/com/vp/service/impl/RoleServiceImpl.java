package com.vp.service.impl;

import com.vp.dao.*;
import com.vp.dto.NodeDto;
import com.vp.entity.*;
import com.vp.service.RoleService;
import com.vp.vo.NodeVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * 角色服务实现类
 *
 * @author flybesttop
 * @date 2021-03-29
 */
@Slf4j
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    public RoleMapper roleMapper;
    @Autowired
    public UserCompanyMapper userCompanyMapper;
    @Autowired
    public NodeMapper nodeMapper;
    @Autowired
    public RoleNodeMappingMapper roleNodeMappingMapper;
    @Autowired
    public CompanyMapper companyMapper;

    @Override
    public Boolean createDefaultRole(String openId, Integer companyId) {
        try {
            Role role = Role.createDefaultRole(openId, companyId);
            roleMapper.insertSelective(role);
            UserCompany userCompany = new UserCompany();
            userCompany.setCompanyId(companyId);
            userCompany.setIsDefault(1);
            userCompany.setOpenId(openId);
            userCompany.setRoleId(role.getId());
            userCompany.setRoleName(role.getRoleName());
            saveUserCompanyRole(userCompany);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean saveUserCompanyRole(UserCompany userCompany) {
        try {
            UserCompany defaultUserCompany;
            if (userCompany.getIsDefault() == 1) {
                defaultUserCompany = userCompanyMapper.selectUserDefaultCompany(userCompany.getOpenId());
                if (Objects.nonNull(defaultUserCompany)) {
                    defaultUserCompany.setIsDefault(0);
                    userCompanyMapper.updateByPrimaryKeySelective(defaultUserCompany);
                }
            }

            if (Objects.nonNull(userCompany.getId())) {
                userCompanyMapper.updateByPrimaryKeySelective(userCompany);
            } else {
                userCompanyMapper.insertSelective(userCompany);
            }
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    @Override
    public List<Role> getCompanyRoles(Integer companyId, String searchKey) {
        return null;
    }

    @Override
    public List<Role> getDefaultCompanyRoles(String openId, String searchKey) {
        UserCompany userCompany = userCompanyMapper.selectUserDefaultCompany(openId);
        List<Role> roles = roleMapper.getCompanyRoles(userCompany.getCompanyId(), searchKey);
        return roles;
    }

    @Override
    public NodeVo getAllNodes() {
        List<Node> topNodes = nodeMapper.getNodeList(0);
        NodeVo nodeVo = new NodeVo();
        List<NodeDto> topNodeDtos = new ArrayList<>();
        topNodes.forEach(node -> {
            NodeDto nodeDto = new NodeDto();
            nodeDto.setTopNode(node);
            List<Node> lowerNodes = nodeMapper.getNodeList(node.getId());
            nodeDto.setChildNodeList(lowerNodes);
            topNodeDtos.add(nodeDto);
        });
        nodeVo.setTopNodeList(topNodeDtos);
        return nodeVo;
    }

    @Override
    public List<Node> getAllUserNodes(Integer roleId) {
        List<Node> roleNodeList = nodeMapper.getRoleNodeList(roleId);
        return roleNodeList;
    }

    @Override
    public Boolean saveRoleNodes(List<String> nodeIds, Integer chooseRoleId) {
        try {
            List<Node> roleNodeList = nodeMapper.getRoleNodeList(chooseRoleId);
            List<String> newNodes = new ArrayList<>();
            List<String> deleteNodes = new ArrayList<>();

            flag:
            for (Node n : roleNodeList) {
                for (String nodeId : nodeIds) {
                    if (nodeId.equals(n.getId() + "")) {
                        continue flag;
                    }
                }
                deleteNodes.add(n.getId() + "");
            }

            flag2:
            for (String nodeId : nodeIds) {
                for (Node n : roleNodeList) {
                    if (nodeId.equals(n.getId() + "")) {
                        continue flag2;
                    }
                }
                newNodes.add(nodeId);
            }

            for (String newNodeId : newNodes) {
                RoleNodeMapping roleNodeMapping = new RoleNodeMapping();
                roleNodeMapping.setNodeId(Integer.parseInt(newNodeId));
                roleNodeMapping.setRoleId(chooseRoleId);
                roleNodeMappingMapper.insertSelective(roleNodeMapping);
            }

            for (String deleteNodeId : deleteNodes) {
                roleNodeMappingMapper.deleteRoleNode(chooseRoleId, Integer.parseInt(deleteNodeId));
            }
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean saveRole(Role role) {
        try {
            if (Objects.isNull(role.getId())) {
                Company defaultCompany = companyMapper.getDefaultCompanyInfo(role.getCreatorId());
                role.setCompanyId(defaultCompany.getId());
                Integer topLevel = roleMapper.getTopRoleLevel(defaultCompany.getId());
                role.setLevel(topLevel + 1);
                role.setDescribe(UUID.randomUUID().toString().replace("-", ""));
                roleMapper.insertSelective(role);
            } else {
                roleMapper.updateByPrimaryKeySelective(role);
            }
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }
}
