package top.kaluna.modbusTcp.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author Yuery
 * @date 2022/2/24/0024 - 17:40
 */
@Component
public class LogFilter implements Filter {
    public static final Logger LOG = LoggerFactory.getLogger(LogFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //打印请求信息
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        LOG.info("------ LogFilter开始 ------");
        LOG.info("请求地址：{} {}", request.getRequestURL().toString(), request.getMethod());
        LOG.info("远程地址：{}",request.getRemoteAddr());

        long startTime = System.currentTimeMillis();
        filterChain.doFilter(servletRequest, servletResponse);
        LOG.info("------ LogFilter 结束耗时：{}ms ------", System.currentTimeMillis() - startTime);
    }

    @Override
    public void destroy() {

    }
}