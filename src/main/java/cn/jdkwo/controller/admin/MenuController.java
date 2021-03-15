package cn.jdkwo.controller.admin;


import cn.jdkwo.entity.admin.Menu;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * 菜单管理控制器
 */
@RequestMapping("/admin/menu")
@Controller
public class MenuController {

           @RequestMapping(value = "/list",method = RequestMethod.GET)
           public ModelAndView list(ModelAndView model){
               model.setViewName("menu/list");
               return model;
           }

           @RequestMapping(value = "/add",method = RequestMethod.POST)
           @ResponseBody
           public Map<String,String> add(Menu menu){
               Map<String,String> ret = new HashMap<>();
               if(menu == null){
                   ret.put("type","error");
                   ret.put("msg","请填写正确的菜单信息");
                   return  ret;
               }
               if(StringUtils.isEmpty(menu.getName())){
                   ret.put("type","error");
                   ret.put("msg","请填写菜单名称!");
                   return  ret;
               }
               if(StringUtils.isEmpty(menu.getIcon())){
                   ret.put("type","error");
                   ret.put("msg","请填写菜单图标类!");
                   return  ret;
               }
               ret.put("type","success");
               ret.put("msg","添加成功!");
               return ret;
           }
}
