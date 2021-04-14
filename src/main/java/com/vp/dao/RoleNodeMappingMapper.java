package com.vp.dao;

import com.vp.entity.RoleNodeMapping;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleNodeMappingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RoleNodeMapping record);

    int insertSelective(RoleNodeMapping record);

    RoleNodeMapping selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RoleNodeMapping record);

    int updateByPrimaryKey(RoleNodeMapping record);

    int deleteRoleNode(@Param("roleId") Integer roleId, @Param("nodeId") Integer nodeId);
}