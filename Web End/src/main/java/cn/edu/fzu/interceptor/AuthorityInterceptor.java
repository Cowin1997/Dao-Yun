package cn.edu.fzu.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Set;

@Component
public class AuthorityInterceptor implements HandlerInterceptor {
    private static final Set<String> NOT_INTERCEPT_URI = new HashSet<>();//不拦截的URI

    static {
        NOT_INTERCEPT_URI.add("/user/login");
        NOT_INTERCEPT_URI.add("/check/task");
    }


    // 进入Handler方法之前执行。可以用于身份认证、身份授权。
    // 比如如果认证没有通过表示用户没有登陆，需要此方法拦截不再往下执行（return false），
    // 否则就放行（return true）
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        System.out.println("AuthorityInterceptor：" + request.getRequestURI());
//        String uri = request.getRequestURI();
//        //System.out.println(request.getMethod());
//        if(request.getMethod().equals("OPTIONS")) return true;
//        if (NOT_INTERCEPT_URI.contains(uri)) {
//            System.out.println("不拦截" + uri);
//            return true;
//        }else {
//         //   System.out.println("拦截" + uri);
//            HttpSession session = request.getSession(false); //如果不存在则不会产生一个session,而是返回null
//           // System.out.println(session);
//            if(session==null) {
//                throw new RuntimeException("用户未登陆");
//            }
//            return true;
//        }
    return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
