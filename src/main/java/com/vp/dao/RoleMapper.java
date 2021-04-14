package com.vp.dao;

import com.vp.entity.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> getCompanyRoles(@Param("companyId") Integer companyId,@Param("searchKey") String searchKey);

    Integer getTopRoleLevel(Integer companyId);
}