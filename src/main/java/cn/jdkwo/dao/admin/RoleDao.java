package cn.jdkwo.dao.admin;

import cn.jdkwo.entity.admin.Role;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 角色dao
 */
@Repository
public interface RoleDao {

    int add(Role role);
    int edit(Role role);
    int delete(Long id);
    List<Role> findList(Map<String,Object> queryMap);
    int getTotal(Map<String,Object> queryMap);
}
