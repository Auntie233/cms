package top.auntie.cms.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import top.auntie.cms.dao.RoleRepository;
import top.auntie.cms.dto.RoleDto;
import top.auntie.cms.exception.CommonException;
import top.auntie.cms.pojo.Role;
import top.auntie.cms.pojo.User;
import top.auntie.cms.service.RoleService;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Page<Role> findWithPage(Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return roleRepository.findAll(pageRequest);
    }

    @Override
    public void save(RoleDto roleDto) throws CommonException {
        if (roleDto.getId() == null) {
            Role record = new Role();
            record.setRoleName(roleDto.getRoleName());
            Example<Role> example = Example.of(record);
            if (!roleRepository.findAll(example).isEmpty()) {
                throw new CommonException("角色名["+roleDto.getRoleName()+"]已存在");
            }
        }
        Role role = new Role();
        BeanUtils.copyProperties(roleDto, role);
        roleRepository.save(role);
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }
}
