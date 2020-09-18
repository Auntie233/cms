package top.auntie.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.auntie.cms.annotation.ServiceLog;
import top.auntie.cms.dto.LoginDto;
import top.auntie.cms.exception.CommonException;
import top.auntie.cms.model.ResultModel;
import top.auntie.cms.service.LoginService;

import javax.servlet.http.HttpServletRequest;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    @ServiceLog(value = "登录", isOutter = true)
    public ResultModel login(@RequestBody LoginDto loginDto , HttpServletRequest request) throws CommonException {
        loginService.login(loginDto, request);
        return ResultModel.success();
    }

}
