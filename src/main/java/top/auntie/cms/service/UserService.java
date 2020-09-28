package top.auntie.cms.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import top.auntie.cms.dto.UserDto;
import top.auntie.cms.dto.UserRoleDto;
import top.auntie.cms.exception.CommonException;
import top.auntie.cms.pojo.User;

public interface UserService {

    Page<User> findUserWithPage(Integer page, Integer size);

    void saveUser(UserDto userDto) throws CommonException;

    void saveUserRole(UserRoleDto userRoleDto);
}
