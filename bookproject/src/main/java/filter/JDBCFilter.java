package filter;

import util.JdbcUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class JDBCFilter implements Filter {
    /**
     * 配置JDBC事物提交或回滚
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        try {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            //提交事物
            JdbcUtil.commitAndClose();
        }catch (Exception e){
            //回滚事物
            JdbcUtil.rollbackAndClose();
            e.printStackTrace();
            //将异常再抛给tomcat，以跳转错误页面
            throw new RuntimeException(e);
        }
    }
}
