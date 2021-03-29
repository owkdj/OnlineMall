package cn.jdkwo.service.admin;

import cn.jdkwo.entity.admin.User;

import java.util.List;
import java.util.Map;

public interface UserService {

     User findByUsername(String username);
     int add(User user);
     int edit(User user);
     int delete(String ids);//批量删除
     List<User> findList(Map<String,Object> queryMap);
     int getTotal(Map<String,Object> queryMap);
}
