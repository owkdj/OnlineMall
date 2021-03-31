package admin;

import cn.jdkwo.entity.admin.Menu;
import cn.jdkwo.service.admin.MenuService;
import cn.jdkwo.service.admin.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class adminTest extends baseTest {

    @Autowired
    private MenuService menuService;

    /**
     * 菜单分页功能测试
     */
    @Test
    public void test1() {
        Map<String, Object> queryMap = new HashMap<>();
        List<Menu> list = new ArrayList<>();
        List<Menu> topList = menuService.findTopList();
        for (Menu topMenu : topList
                ) {
            list.add(topMenu);
            queryMap.put("parentId", topMenu.getId());
            List<Menu> list1 = menuService.findList(queryMap);
            for (Menu childernlist : list1
                    ) {
                list.add(childernlist);
                list.addAll(menuService.findChildernList(childernlist.getId()));
            }
        }

    }






}
