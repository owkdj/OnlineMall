package cn.jdkwo.dao.admin;

import cn.jdkwo.entity.admin.User;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

@Repository
public interface UserDao {

    User findByUsername(String username);
    int add(User user);
    int edit(User user);
    int delete(String ids);//批量删除
    List<User> findList(Map<String,Object> queryMap);
    int getTotal(Map<String,Object> queryMap);
}
