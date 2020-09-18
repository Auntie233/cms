package top.auntie.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.auntie.cms.annotation.RestControllerMapper;
import top.auntie.cms.dto.UserDto;
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
    public ResultModel saveUser(UserDto userDto) throws CommonException {
        userService.saveUser(userDto);
        return ResultModel.success();
    }

}
