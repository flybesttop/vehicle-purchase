package com.vp.rest;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.vp.dto.UserCompanyDto;
import com.vp.entity.Company;
import com.vp.entity.UserCompany;
import com.vp.service.CompanyService;
import com.vp.service.RoleService;
import com.vp.service.UserCompanyService;
import com.vp.util.BaseResponse;
import com.vp.vo.CompanyMemberVo;
import com.vp.vo.UserCompanyVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author flybesttop
 * @date 2020/11/26
 */
@Slf4j
@RestController
@RequestMapping("company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserCompanyService userCompanyService;

    @RequestMapping(value = "selectCompanyByInvitationCode", method = POST)
    public BaseResponse<List<UserCompanyDto>> selectCompanyByInvitationCode(String invitationCode) {
        List<UserCompanyDto> userCompanyDtos = companyService.selectCompanyByInvitationCode(invitationCode);
        return new BaseResponse<>(userCompanyDtos);
    }

    @RequestMapping(value = "joinCompany", method = POST)
    public BaseResponse<Boolean> joinCompany(String openId, Integer roleId) {
        Boolean joinCompany = companyService.joinCompany(openId, roleId);
        return new BaseResponse<>(joinCompany);
    }

    @RequestMapping(value = "createCompany", method = POST)
    public BaseResponse<Boolean> createCompany(@RequestBody Company company) {
        Integer companyId = companyService.createCompany(company);
        //创建公司还需要默认生成超级管理员角色
        if (companyId != -1) {
            roleService.createDefaultRole(company.getCreatorOpenId(), companyId);
            return new BaseResponse<>(true);
        }
        return new BaseResponse<>(false);
    }

    @RequestMapping(value = "updateCompany", method = POST)
    public BaseResponse<Boolean> updateCompany(@RequestBody Company company) {
        Boolean result = companyService.updateCompany(company);
        return new BaseResponse<>(result);
    }

    @RequestMapping(value = "myCompanies", method = POST)
    public BaseResponse<UserCompanyVo> myCompanies(String userOpenId, String searchKey) {
        UserCompanyVo result = companyService.getUserCompaniesAndDefault(userOpenId, searchKey);
        return new BaseResponse<>(result);
    }

    @RequestMapping(value = "changeDefault", method = POST)
    public BaseResponse<Boolean> changeDefault(Integer defaultUserCompanyId, Integer oldDefaultUserCompanyId) {
        Boolean result = userCompanyService.changeDefaultCompany(defaultUserCompanyId, oldDefaultUserCompanyId);
        return new BaseResponse<>(result);
    }

    @RequestMapping(value = "getDefaultCompany", method = POST)
    public BaseResponse<UserCompanyDto> getDefaultCompany(String openId) {
        UserCompanyDto userCompany = companyService.getDefaultCompany(openId);
        return new BaseResponse<>(userCompany);
    }

    @RequestMapping(value = "getDefaultCompanyInfo", method = POST)
    public BaseResponse<Company> getDefaultCompanyInfo(String openId) {
        Company userCompany = companyService.getDefaultCompanyInfo(openId);
        return new BaseResponse<>(userCompany);
    }

    @RequestMapping(value = "getCompanyMembers", method = POST)
    public BaseResponse<List<CompanyMemberVo>> getCompanyMembers(String openId, String searchKey) {
        List<CompanyMemberVo> companyMemberVos = companyService.getCompanyMembers(openId, searchKey);
        return new BaseResponse<>(companyMemberVos);
    }

    @RequestMapping(value = "deleteMember", method = POST)
    public BaseResponse<Boolean> deleteMember(Integer userCompanyId, String openId) {
        Boolean res = userCompanyService.deleteMember(userCompanyId, openId);
        return new BaseResponse<>(res);
    }
}
