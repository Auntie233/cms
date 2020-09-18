package top.auntie.cms.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import top.auntie.cms.dao.UserRepository;
import top.auntie.cms.dto.LoginDto;
import top.auntie.cms.exception.CommonException;
import top.auntie.cms.model.GlobalSession;
import top.auntie.cms.model.SessionInfo;
import top.auntie.cms.pojo.User;
import top.auntie.cms.service.LoginService;
import top.auntie.cms.util.SessionUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void login(LoginDto loginDto, HttpServletRequest request) throws CommonException {
        User userRecord = new User();
        userRecord.setUsername(loginDto.getUserName());
        Example<User> userExample = Example.of(userRecord);
        Optional<User> userOptional = userRepository.findOne(userExample);
        if (!userOptional.isPresent()) {
            throw new CommonException("用户名不存在");
        }
        User user = userOptional.get();
        if (!user.getStatus()) {
            throw new CommonException("用户已被禁用");
        }
        if (!loginDto.getPassword().equals(user.getPassword())) {
            throw new CommonException("用户名或密码错误");
        }
        SessionInfo sessionInfo = new SessionInfo();
        BeanUtils.copyProperties(user, SessionInfo.class);
        SessionUtil.setAttribute(request, GlobalSession.SESSION_USER_KEY, sessionInfo);
    }


}
