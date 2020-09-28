package top.auntie.cms.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import top.auntie.cms.dao.UserRepository;
import top.auntie.cms.dao.UserRoleRepository;
import top.auntie.cms.dto.UserDto;
import top.auntie.cms.dto.UserRoleDto;
import top.auntie.cms.exception.CommonException;
import top.auntie.cms.pojo.User;
import top.auntie.cms.pojo.UserRole;
import top.auntie.cms.service.UserService;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public Page<User> findUserWithPage(@RequestParam("page") Integer page,@RequestParam("size") Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return userRepository.findAll(pageRequest);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveUser(UserDto userDto) throws CommonException {
        if (userDto.getId() == null) {
            User record = new User();
            record.setUsername(userDto.getUsername());
            Example<User> example = Example.of(record);
            if (!userRepository.findAll(example).isEmpty()) {
                throw new CommonException("用户名["+userDto.getUsername()+"]已存在");
            }
        }
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        userRepository.save(user);
    }

    @Override
    public void saveUserRole(UserRoleDto userRoleDto) {
        UserRole record = new UserRole();
        record.setUserId(userRoleDto.getUserId());
        userRoleRepository.delete(record);
        final Date now = new Date();
        final Long userId = userRoleDto.getUserId();
        List<UserRole> userRoles = userRoleDto.getRoleIds().parallelStream().map(id->{
            UserRole userRole = new UserRole();
            userRole.setUserId(userId);
            userRole.setRoleId(id);
            userRole.setCreateTime(now);
            return userRole;
        }).collect(Collectors.toList());
        userRoleRepository.saveAll(userRoles);
    }

}
