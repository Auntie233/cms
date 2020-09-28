package top.auntie.cms.service;

import org.springframework.data.domain.Page;
import top.auntie.cms.dto.RoleDto;
import top.auntie.cms.dto.UserDto;
import top.auntie.cms.exception.CommonException;
import top.auntie.cms.pojo.Role;
import top.auntie.cms.pojo.User;

import java.util.List;

public interface RoleService {

    Page<Role> findWithPage(Integer page, Integer size);

    void save(RoleDto roleDto) throws CommonException;

    List<Role> findAll();
}
