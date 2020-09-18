package top.auntie.cms.service;

import top.auntie.cms.dto.LoginDto;
import top.auntie.cms.exception.CommonException;

import javax.servlet.http.HttpServletRequest;

public interface LoginService {

    void login(LoginDto loginDto, HttpServletRequest request) throws CommonException;

}
