package cn.jdkwo.service.admin.impl;

import cn.jdkwo.dao.admin.UserDao;
import cn.jdkwo.entity.admin.User;
import cn.jdkwo.service.admin.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }
}
