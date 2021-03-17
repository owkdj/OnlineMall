package cn.jdkwo.service.admin;

import cn.jdkwo.entity.admin.Role;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 角色service
 */
public interface RoleService {

    int add(Role role);
    int edit(Role role);
    int delete(Long id);
    List<Role> findList(Map<String,Object> queryMap);
    int getTotal(Map<String,Object> queryMap);
}
