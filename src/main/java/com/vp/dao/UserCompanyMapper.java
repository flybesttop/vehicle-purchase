package com.vp.dao;

import com.vp.entity.UserCompany;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCompanyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserCompany record);

    int insertSelective(UserCompany record);

    UserCompany selectByPrimaryKey(Integer id);

    UserCompany selectUserDefaultCompany(String openId);

    int updateByPrimaryKeySelective(UserCompany record);

    int updateByPrimaryKey(UserCompany record);

    Integer getUserDefaultCompany(String openId);
}