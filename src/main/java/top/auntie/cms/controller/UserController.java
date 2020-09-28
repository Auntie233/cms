package top.auntie.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import top.auntie.cms.annotation.RestControllerMapper;
import top.auntie.cms.dto.UserDto;
import top.auntie.cms.dto.UserRoleDto;
import top.auntie.cms.exception.CommonException;
import top.auntie.cms.model.ResultModel;
import top.auntie.cms.pojo.User;
import top.auntie.cms.service.UserService;

import java.util.List;

@RestControllerMapper("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/find")
    public ResultModel findUserPage(Integer page, Integer size) {
        return ResultModel.success(userService.findUserWithPage(page, size));
    }

    @PostMapping("/save")
    public ResultModel saveUser(@RequestBody UserDto userDto) throws CommonException {
        userService.saveUser(userDto);
        return ResultModel.success();
    }

    @PostMapping("/user/add/roles")
    public ResultModel saveUserRole(@RequestBody UserRoleDto userRoleDto) throws CommonException {
        userService.saveUserRole(userRoleDto);
        return ResultModel.success();
    }

}
