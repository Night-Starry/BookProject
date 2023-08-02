package servlet.pages;

import util.PageUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet("/login")
public class LoginPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String file_path = "";
        //使用Cookie判断用户登录状态
        Cookie[] cookies = req.getCookies();
        if(cookies != null){
            for (Cookie cookie: cookies) {
                if("name".equals(cookie.getName())){
                    //将用户信息写入session
                    HttpSession session = req.getSession();
                    session.setAttribute("name", cookie.getValue());
                    //用户已经登录过，直接进入首页
                    resp.sendRedirect("/home");
                    break;
                } else {
                    //用户没有登录，进入登录界面
                    file_path = getServletContext().getRealPath("/WEB-INF/html/login.html");
                }
            }
        }else {
            file_path = getServletContext().getRealPath("/WEB-INF/html/login.html");
        }
        PageUtil.returnPage(file_path, req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
