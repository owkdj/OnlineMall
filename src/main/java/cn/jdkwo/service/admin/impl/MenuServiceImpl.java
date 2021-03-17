package cn.jdkwo.service.admin.impl;

import cn.jdkwo.dao.admin.MenuDao;
import cn.jdkwo.entity.admin.Menu;
import cn.jdkwo.service.admin.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("menuService")
public class MenuServiceImpl implements MenuService{

    @Autowired
    private MenuDao menuDao;
    public int add(Menu menu){
          return menuDao.addMenu(menu);
    }
    public List<Menu> findList(Map<String,Object> queryMap){
        return menuDao.findList(queryMap);
    }

    @Override
    public List<Menu> findTopList() {
        return menuDao.findTopList();
    }

    @Override
    public int getTotal(Map<String, Object> queryMap) {
        return menuDao.getTotal(queryMap);
    }

    @Override
    public int edit(Menu menu) {
        return menuDao.edit(menu);
    }

    @Override
    public int delete(Long id) {
        return menuDao.delete(id);
    }

    @Override
    public List<Menu> findChildernList(Long parentId) {
        return menuDao.findChildernList(parentId);
    }

}
