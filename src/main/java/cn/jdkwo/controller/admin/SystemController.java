package cn.jdkwo.controller.admin;

import cn.jdkwo.entity.admin.User;
import cn.jdkwo.service.admin.UserService;
import cn.jdkwo.util.CpachaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 系统操作控制类
 */
@Controller
@RequestMapping("/system")
public class SystemController {

    @Autowired
    private UserService userService;

    /**
     * 后台登录主页
     * @param model
     * @return
     */
     @RequestMapping(value="/index",method= RequestMethod.GET)
      public ModelAndView index(ModelAndView model){
         model.setViewName("system/index");
         //model.addObject("name","玉");
         return model;
      }

    @RequestMapping(value="/welcome",method= RequestMethod.GET)
    public ModelAndView welcome(ModelAndView model){
        model.setViewName("system/welcome");
        return model;
    }


    /**
     * 登录控制方法
     * @param model
     * @return
     */
      @RequestMapping(value = "/login",method = RequestMethod.GET)
      public ModelAndView login(ModelAndView model){
          model.setViewName("/system/login");
          return model;
      }

    /**
     * 登录表单验证
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
     public Map<String,String> loginAct(User user,String cpacha,HttpServletRequest request){
        Map<String,String> ret = new HashMap<>();
        if(user == null){
            ret.put("type","error");
            ret.put("msg","请填写用户信息");
            return  ret;
        }
        if(StringUtils.isEmpty(cpacha)){
            ret.put("type","error");
            ret.put("msg","请填写验证码!");
            return ret;
        }
        if(StringUtils.isEmpty(user.getUsername())){
            ret.put("type","error");
            ret.put("msg","请填写用户名!");
            return ret;
        }
        if(StringUtils.isEmpty(user.getPassword())){
            ret.put("type","error");
            ret.put("msg","请填写密码!");
            return ret;
        }
        Object loginCpacha = request.getSession().getAttribute("loginCpacha");
        if(loginCpacha == null){
            ret.put("type","error");
            ret.put("msg","会话超时,请刷新页面!");
            return ret;
        }
        if(!cpacha.toUpperCase().equals(loginCpacha.toString().toUpperCase())){
            ret.put("type","error");
            ret.put("msg","验证码错误!");
            return ret;
        }
        User byUsername = userService.findByUsername(user.getUsername());
        if(byUsername == null){
            ret.put("type","error");
            ret.put("msg","该用户不存在!");
            return ret;
        }
        if(!user.getPassword().equals(byUsername.getPassword())){
            ret.put("type","error");
            ret.put("msg","密码错误!");
            return ret;
        }
        request.getSession().setAttribute("admin",byUsername);
        ret.put("type","success");
        ret.put("msg","登陆成功!");
        return ret;
    }

    /**
     * 验证码生成方法
     * @param vcodeLen
     * @param width
     * @param height
     * @param cpachaType
     * @param request
     * @param response
     */
      @RequestMapping(value = "/get_cpacha",method = RequestMethod.GET)
      public void generateCpacha(
              @RequestParam(name="vl",required = false,defaultValue = "4") Integer vcodeLen,
              @RequestParam(name="w",required = false,defaultValue = "100") Integer width,
              @RequestParam(name="h",required = false,defaultValue = "30") Integer height,
              @RequestParam(name="type",required = true,defaultValue = "loginCpacha") String cpachaType,
              HttpServletRequest request,
              HttpServletResponse response){
          CpachaUtil cpachaUtil = new CpachaUtil(vcodeLen,width,height);
           String generatorVcode = cpachaUtil.generatorVCode();
           request.getSession().setAttribute(cpachaType,generatorVcode);
          BufferedImage generatorRotateVCodeImage = cpachaUtil.generatorRotateVCodeImage(generatorVcode, true);
          try {
              ImageIO.write(generatorRotateVCodeImage,"gif",response.getOutputStream());
          } catch (IOException e) {
              e.printStackTrace();
          }
      }




}
