package cn.jdkwo.service.admin;

import cn.jdkwo.entity.admin.Menu;

import java.util.List;
import java.util.Map;


public interface MenuService {

     int add(Menu menu);
     List<Menu> findList(Map<String,Object> queryMap);
     List<Menu> findTopList();
     int getTotal(Map<String,Object> queryMap);
     int edit(Menu menu);
     int delete(Long id);
     List<Menu> findChildernList(Long parentId);
     List<Menu> findThridChildernList(Long Id);
}
