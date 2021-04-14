package com.vp.dao;

import com.vp.entity.Node;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NodeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Node record);

    int insertSelective(Node record);

    Node selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Node record);

    int updateByPrimaryKey(Node record);

    List<Node> getNodeList(Integer pid);

    List<Node> getRoleNodeList(Integer roleId);

    List<Node> getUserDefaultCompanyRoleNodes(String openId);
}