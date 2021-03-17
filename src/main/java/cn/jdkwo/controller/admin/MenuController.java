package cn.jdkwo.controller.admin;


import cn.jdkwo.entity.admin.Menu;
import cn.jdkwo.page.admin.Page;
import cn.jdkwo.service.admin.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 菜单管理控制器
 */
@RequestMapping("/admin/menu")
@Controller
public class MenuController {

            @Autowired
           private MenuService menuService;

            /**
             * 返回菜单管理list页
             * @param model
             * @return
             */
            @RequestMapping(value = "/list",method = RequestMethod.GET)
                       public ModelAndView list(ModelAndView model){
                       model.addObject("topList",menuService.findTopList());
                       model.setViewName("menu/list");
                       return model;
                   }

                /**
                 * 处理list传来的post请求
                 * @param
                 * @return
                 */
                @RequestMapping(value = "/list",method = RequestMethod.POST)
                @ResponseBody
                public Map<String,Object> getMenuList(Page page, @RequestParam(name="name",required = false,defaultValue = "") String name){
                    Map<String,Object> ret = new HashMap<>();
                    Map<String,Object> queryMap = new HashMap<>();
                    queryMap.put("offset",page.getOffset());
                    queryMap.put("pageSize",page.getRows());
                    queryMap.put("name",name);
                    List<Menu> list = menuService.findList(queryMap);
                    ret.put("rows",list);
                    ret.put("total",menuService.getTotal(queryMap));
                    return ret;
                   }

                    /**
                     * 选择菜单图标
                     * @param
                     * @return
                     */
                    @RequestMapping(value = "/get_icons",method = RequestMethod.POST)
                    @ResponseBody
                    public Map<String,Object> getIconList(HttpServletRequest request){
                        Map<String,Object> ret = new HashMap<>();
                        String path = request.getSession().getServletContext().getRealPath("/");
                        File file = new File(path +"\\resources\\admin\\easyui\\css\\icons");
                        List<String> icons = new ArrayList<>();
                        if(!file.exists()){
                            ret.put("type","error");
                            ret.put("total","文件目录不存在!");
                        }
                        File[] listFiles = file.listFiles();
                       for(File f:listFiles){
                           if(f != null && f.getName().contains("png")){
                               icons.add("icon-" + f.getName().substring(0,f.getName().indexOf(".")).replace("_","-"));
                           }
                       }
                        ret.put("type","success");
                        ret.put("content",icons);
                        return ret;
                    }

                /**
                 * 添加菜单
                  * @param menu
                 * @return
                 */
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
                           if(menu.getParentId() == null){
                               menu.setParentId(0l);
                           }
                           if(menuService.add(menu)<1){
                               ret.put("type","error");
                               ret.put("msg","添加失败,请联系管理员!!");
                               return  ret;
                           }
                           ret.put("type","success");
                           ret.put("msg","添加成功!");
                           return ret;
                       }

    /**
     * 菜单修改controller
      */
    @RequestMapping(value="/edit",method=RequestMethod.POST)
    @ResponseBody
    public Map<String, String> edit(Menu menu){
        Map<String, String> ret = new HashMap<String, String>();
        if(menu == null){
            ret.put("type", "error");
            ret.put("msg", "数据为空,请重新选择!");
            return ret;
        }
        if(StringUtils.isEmpty(menu.getName())){
            ret.put("type", "error");
            ret.put("msg", "菜单名为空,请重新选择!");
            return ret;
        }
        if(StringUtils.isEmpty(menu.getIcon())){
            ret.put("type", "error");
            ret.put("msg", "图标为空,请重新选择!");
            return ret;
        }
        if(menu.getParentId() == null){
            menu.setParentId(0l);
        }
        if(menuService.edit(menu) <= 0){
            ret.put("type", "error");
            ret.put("msg", "修改失败,请联系管理员!");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "修改成功!");
        return ret;
    }

    /**
     * 删除菜单
     */
    @RequestMapping(value="/delete",method=RequestMethod.POST)
    @ResponseBody
    public Map<String, String> delete(
            @RequestParam(name="id",required=true) Long id
    ){
        Map<String, String> ret = new HashMap<String, String>();
        if(id == null){
            ret.put("type", "error");
            ret.put("msg", "请选择菜单!");
            return ret;
        }
        List<Menu> findChildernList = menuService.findChildernList(id);
        if(findChildernList != null && findChildernList.size() > 0){
            ret.put("type", "error");
            ret.put("msg", "¸此菜单下存在子菜单!");
            return ret;
        }
        if(menuService.delete(id) <= 0){
            ret.put("type", "error");
            ret.put("msg", "删除失败,请联系管理员!");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "删除成功!");
        return ret;
    }


}
