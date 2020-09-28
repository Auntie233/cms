package top.auntie.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import top.auntie.cms.annotation.RestControllerMapper;
import top.auntie.cms.dto.RoleDto;
import top.auntie.cms.dto.UserDto;
import top.auntie.cms.exception.CommonException;
import top.auntie.cms.model.ResultModel;
import top.auntie.cms.service.RoleService;
import top.auntie.cms.service.UserService;

@RestControllerMapper("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/find")
    public ResultModel findPage(Integer page, Integer size) {
        return ResultModel.success(roleService.findWithPage(page, size));
    }

    @PostMapping("/save")
    public ResultModel save(@RequestBody RoleDto roleDto) throws CommonException {
        roleService.save(roleDto);
        return ResultModel.success();
    }

    @GetMapping("/select")
    public ResultModel findRoleSelect() {
        return ResultModel.success(roleService.findAll());
    }

}
