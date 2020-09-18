package top.auntie.cms.service.impl;

import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.auntie.cms.dao.UserRepository;
import top.auntie.cms.pojo.User;
import top.auntie.cms.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findUserWithPage() {
        return userRepository.findAll();
    }

}
