//package top.kaluna.modbusTcp.interceptor;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * @author Yuery
// * @date 2022/2/24/0024 - 17:54
// */
//@Component
//public class LogInterceptor implements HandlerInterceptor {
//
//    private static final Logger LOG = LoggerFactory.getLogger(LogInterceptor.class);
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        //打印请求信息
//        LOG.info("------ LogInterceptor开始 ------");
//        LOG.info("请求地址： {} {}", request.getRequestURL().toString(), request.getMethod());
//        LOG.info("远程地址：{}",request.getRemoteAddr());
//
//        long startTime = System.currentTimeMillis();
//        request.setAttribute("requestStartTime", startTime);
//        //比如说我在这里增加一个登录校验，当我校验出你未登录或者登录超时的时候，
//        // 我就这样return false，我们后面的业务逻辑就不会再执行了
//        return true;
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        long startTime = (Long) request.getAttribute("requestStartTime");
//        LOG.info("------ LogInterceptor 结束 耗时：{}ms ------", System.currentTimeMillis() - startTime);
//    }
//}
