package cn.jdkwo.controller.admin;

import cn.jdkwo.entity.admin.Authority;
import cn.jdkwo.entity.admin.Menu;
import cn.jdkwo.entity.admin.Role;
import cn.jdkwo.page.admin.Page;
import cn.jdkwo.service.admin.AuthorityService;
import cn.jdkwo.service.admin.MenuService;
import cn.jdkwo.service.admin.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色控制器
 */
@RequestMapping("/admin/role")
@Controller
public class RoleController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private AuthorityService authorityService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private Authority authority;

    /**
     * 角色列表
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(ModelAndView model) {
        model.setViewName("role/list");
        return model;
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getList(Page page,
                                       @RequestParam(name = "name", required = false, defaultValue = "") String name
    ) {
        Map<String, Object> ret = new HashMap<>();
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("name", name);
        queryMap.put("offset", page.getOffset());
        queryMap.put("pageSize", page.getRows());
        ret.put("rows", roleService.findList(queryMap));
        ret.put("total", roleService.getTotal(queryMap));
        return ret;
    }

    /**
     * 添加角色
     *
     * @param role
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> add(Role role) {
        Map<String, String> ret = new HashMap<String, String>();
        if (role == null) {
            ret.put("type", "error");
            ret.put("msg", "数据为空,请重新填写!");
            return ret;
        }
        if (StringUtils.isEmpty(role.getName())) {
            ret.put("type", "error");
            ret.put("msg", "角色名为空!");
            return ret;
        }
        if (roleService.add(role) <= 0) {
            ret.put("type", "error");
            ret.put("msg", "");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "添加成功!");
        return ret;
    }

    /**
     * 修改角色
     *
     * @param role
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> edit(Role role) {
        Map<String, String> ret = new HashMap<String, String>();
        if (role == null) {
            ret.put("type", "error");
            ret.put("msg", "数据为空,请重新填写!");
            return ret;
        }
        if (StringUtils.isEmpty(role.getName())) {
            ret.put("type", "error");
            ret.put("msg", "角色名为空!");
            return ret;
        }
        if (roleService.edit(role) <= 0) {
            ret.put("type", "error");
            ret.put("msg", "角色编辑失败，请联系管理员！");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "编辑成功!");
        return ret;
    }

    /**
     * 删除角色
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> delete(Long id) {
        Map<String, String> ret = new HashMap<String, String>();
        if (id == null) {
            ret.put("type", "error");
            ret.put("msg", "请选择角色");
            return ret;
        }
        try {
            if (roleService.delete(id) <= 0) {
                ret.put("type", "error");
                ret.put("msg", "删除失败,请联系管理员!");
                return ret;
            }
        } catch (Exception e) {
            // TODO: handle exception
            ret.put("type", "error");
            ret.put("msg", "该角色下存在权限或者用户信息，不能删除！");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "角色删除成功！");
        return ret;
    }

    /**
     * 获取所有菜单
     *
     * @return
     */
    @RequestMapping(value = "/get_all_menu", method = RequestMethod.POST)
    @ResponseBody
    public List<Menu> getAllMenu() {
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("offset", 0);
        queryMap.put("pageSize", 999);
        return menuService.findList(queryMap);
    }

    /**
     * 添加权限
     */
    @RequestMapping(value = "/add_authority", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> addAuthority(
            @RequestParam(name = "ids", required = true) String ids,
            @RequestParam(name = "roleId", required = true) Long roleId) {
        Map<String, String> ret = new HashMap<>();
        if (StringUtils.isEmpty(ids)) {
            ret.put("type", "error");
            ret.put("msg", "请选择相应权限!");
            return ret;
        }
        if (roleId == null) {
            ret.put("type", "error");
            ret.put("msg", "请选择相应角色!");
            return ret;
        }
        if (ids.contains(",")) {
            ids = ids.substring(0, ids.length() - 1);
        }
        String[] idArr = ids.split(",");
        if (idArr.length > 0) {
            authorityService.deleteByRoleId(roleId);
        }
        for (String id : idArr) {
            authority.setMenuId(Long.valueOf(id));
            authority.setRoleId(roleId);
            authorityService.add(authority);
        }
        ret.put("type", "success");
        ret.put("msg", "权限编辑成功!");
        return ret;
    }

    /**
     * 得到角色已有权限
     */
    @RequestMapping(value = "/get_role_authority", method = RequestMethod.POST)
    @ResponseBody
      public List<Authority> getAuthority(@RequestParam(name="roleId",required = true) Long roleId){
           return authorityService.findListByRoleId(roleId);
      }

}
