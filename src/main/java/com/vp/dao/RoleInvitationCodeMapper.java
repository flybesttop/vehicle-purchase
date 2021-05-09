package com.vp.dao;

import com.vp.entity.RoleInvitationCode;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleInvitationCodeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RoleInvitationCode record);

    int insertSelective(RoleInvitationCode record);

    RoleInvitationCode selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RoleInvitationCode record);

    int updateByPrimaryKey(RoleInvitationCode record);
}