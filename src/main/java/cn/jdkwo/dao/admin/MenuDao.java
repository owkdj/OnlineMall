package cn.jdkwo.dao.admin;

import cn.jdkwo.entity.admin.Menu;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@ResponseBody
public interface MenuDao {

    int addMenu(Menu menu);
    List<Menu> findList(Map<String,Object> queryMap);
    List<Menu> findTopList();
    int getTotal(Map<String,Object> queryMap);
    int edit(Menu menu);
    int delete(Long id);
    List<Menu> findChildernList(Long parentId);
    List<Menu> findThridChildernList(Long parentId);
}
