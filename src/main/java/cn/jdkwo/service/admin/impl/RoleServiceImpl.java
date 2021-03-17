package cn.jdkwo.service.admin.impl;

import cn.jdkwo.dao.admin.RoleDao;
import cn.jdkwo.entity.admin.Role;
import cn.jdkwo.service.admin.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public int add(Role role) {
        return roleDao.add(role);
    }

    @Override
    public int edit(Role role) {
        return roleDao.edit(role);
    }

    @Override
    public int delete(Long id) {
        return roleDao.delete(id);
    }

    @Override
    public List<Role> findList(Map<String, Object> queryMap) {
        return roleDao.findList(queryMap);
    }

    public int getTotal(Map<String, Object> queryMap) {
        return roleDao.getTotal(queryMap);
    }
}
