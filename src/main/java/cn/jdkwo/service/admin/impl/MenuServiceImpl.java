package cn.jdkwo.service.admin.impl;

import cn.jdkwo.dao.admin.MenuDao;
import cn.jdkwo.entity.admin.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuServiceImpl {

    @Autowired
    private MenuDao menuDao;
    public int add(Menu menu){
          return menuDao.addMenu(menu);
    }

}
