package com.vp.dao;

import com.vp.dto.UserCompanyDto;
import com.vp.entity.Company;
import com.vp.vo.CompanyMemberVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Company record);

    int insertSelective(Company record);

    Company selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Company record);

    int updateByPrimaryKey(Company record);

    int selectSameNameCompany(Company company);

    List<UserCompanyDto> getUserCompanies(@Param("openId") String openId, @Param("searchKey") String searchKey);

    List<UserCompanyDto> selectCompanyByInvitationCode(String invitationCode);

    UserCompanyDto getDefaultCompany(String openId);

    Company getDefaultCompanyInfo(String openId);

    List<CompanyMemberVo> getCompanyMembers(@Param("companyId") Integer companyId,@Param("searchKey") String searchKey);
}