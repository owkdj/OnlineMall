package cn.jdkwo.dao.admin;

import cn.jdkwo.entity.admin.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

    public User findByUsername(String username);
}
