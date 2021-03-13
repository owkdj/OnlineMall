package cn.jdkwo.controller;

import cn.jdkwo.util.CpachaUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 系统操作控制类
 */
@Controller
@RequestMapping("/system")
public class SystemController {

     @RequestMapping(value="/index",method= RequestMethod.GET)
      public ModelAndView index(ModelAndView model){
         model.setViewName("system/index");
         model.addObject("name","玉");
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
