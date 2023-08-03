package filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class PermissionFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        HttpSession session = httpServletRequest.getSession();
        String name = (String) session.getAttribute("name");
        //获取请求资源路径
        String requestURI = httpServletRequest.getRequestURI();

        if(requestURI.contains("login") || requestURI.contains("register") || requestURI.contains("user")){
            //访问登录或注册界面
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }else {
            if (name == null) {
                httpServletResponse.sendRedirect("/book_project/login");
            } else {
                //用户继续访问
                filterChain.doFilter(httpServletRequest, httpServletResponse);
            }
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
