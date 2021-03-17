package cn.jdkwo.interceptor.admin;

import net.sf.json.JSONObject;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 后台登录拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public void afterCompletion(HttpServletRequest arg0,
                                HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
                           Object arg2, ModelAndView arg3) throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object arg2) throws Exception {

        //String requestURI = request.getRequestURI();
        Object admin = request.getSession().getAttribute("admin");
        if(admin == null){
            String header = request.getHeader("X-Requested-With");
            if("XMLHttpRequest".equals(header)){
                Map<String,String> ret = new HashMap<String, String>();
                ret.put("type","error");
                ret.put("msg","登录会话超时或未登录，请重新登录!");
                response.getWriter().write(JSONObject.fromObject(ret).toString());
                return false;
            }
            response.sendRedirect(request.getContextPath()+"/system/login");
            return false;
        }
        return true;
    }

}
