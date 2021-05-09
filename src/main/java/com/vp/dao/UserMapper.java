package com.vp.dao;

import com.vp.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    int deleteByPrimaryKey(String openId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String openId);

    int updateByPrimaryKeySelective(User record);

    List<String> getDailyCheckUserOpenId(Integer companyId);

    int updateByPrimaryKeySelectiveForLogin(User record);

    int updateByPrimaryKey(User record);
}