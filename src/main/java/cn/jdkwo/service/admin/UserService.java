package cn.jdkwo.service.admin;

import cn.jdkwo.entity.admin.User;

public interface UserService {

     User findByUsername(String username);
}
