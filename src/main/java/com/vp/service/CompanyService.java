package com.vp.service;

import com.vp.entity.Company;
import com.vp.dto.UserCompanyDto;
import com.vp.vo.CompanyMemberVo;
import com.vp.vo.UserCompanyVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author flybesttop
 * @date 2020/11/26
 */
@Service
public interface CompanyService {

    /**
     * 创建企业，组织
     *
     * @param company
     * @return 若成功返回插入公司的id，若失败，返回-1
     */
    Integer createCompany(Company company);

    /**
     * 修改企业，组织
     *
     * @param company
     * @return 若成功返回插入公司的id，若失败，返回-1
     */
    Boolean updateCompany(Company company);

    /**
     * 通过用户openId查询用户企业，组织
     *
     * @param userOpenId
     * @param searchKey
     * @return
     */
    List<UserCompanyDto> getUserCompanies(String userOpenId, String searchKey);

    /**
     * 通过邀请码获取公司
     *
     * @param invitationCode
     * @return
     */
    List<UserCompanyDto> selectCompanyByInvitationCode(String invitationCode);

    /**
     * 通过用户openId查询用户企业，组织 以及默认
     *
     * @param userOpenId
     * @param searchKey
     * @return
     */
    UserCompanyVo getUserCompaniesAndDefault(String userOpenId, String searchKey);

    /**
     * 获取默认公司
     *
     * @param openId
     * @return
     */
    UserCompanyDto getDefaultCompany(String openId);

    /**
     * 获取默认公司
     *
     * @param openId
     * @return
     */
    Company getDefaultCompanyInfo(String openId);

    /**
     * 加入公司
     *
     * @param openId
     * @param roleId
     * @return
     */
    Boolean joinCompany(String openId, Integer roleId);

    /**
     * 获取公司成员
     *
     * @param openId
     * @param searchKey
     * @return
     */
    List<CompanyMemberVo> getCompanyMembers(String openId, String searchKey);

}
