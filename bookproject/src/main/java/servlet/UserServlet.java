package servlet;

import bean.User;
import com.alibaba.fastjson.JSON;
import service.UserService;
import service.impl.UserServiceImpl;
import util.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

@WebServlet("/user")
public class UserServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }



    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        req.setCharacterEncoding("UTF-8");

        // 获取对象
        User user = WebUtil.copyParamsToBean(new User(), req.getParameterMap());
        UserService userService = new UserServiceImpl();

        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter pw = resp.getWriter();
        if(userService.login(user) == null){
            pw.print("用户名或密码错误");
        } else {
            //登录成功，设置cookie
            Cookie cookie = new Cookie("name", user.getName());
            resp.addCookie(cookie);
            //设置cookie有效期为一周
            cookie.setMaxAge(60*60*24*7);
            //将用户信息写入session
            HttpSession session = req.getSession();
            session.setAttribute("name", user.getName());
            pw.print("success");
        }
        pw.close();
    }

    public void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        // 获取对象
        User user = WebUtil.copyParamsToBean(new User(), req.getParameterMap());
        UserService userService = new UserServiceImpl();

        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter pw = resp.getWriter();
        if(userService.existUser(user.getName())){
            pw.print("用户名已存在");
        } else {
            userService.registerUser(user);
            //注册成功，设置cookie
            Cookie cookie = new Cookie("name", user.getName());
            resp.addCookie(cookie);
            //设置cookie有效期为一周
            cookie.setMaxAge(60*60*24*7);
            //将用户信息写入session
            HttpSession session = req.getSession();
            session.setAttribute("name", user.getName());

            pw.print("success");
        }
        pw.close();
    }

    /**
     * 登出时删除cookie和session信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        // 获取对象
        String user_name = req.getParameter("name");

        //删除cookie和session信息
        Cookie cookie = new Cookie("name", user_name);
        cookie.setMaxAge(0);
        resp.addCookie(cookie);

        HttpSession session = req.getSession();
        if (session != null) {
            session.invalidate();
        }

    }

    /**
     * 注销时删除用户信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void signOut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        // 获取对象
        String user_name = req.getParameter("name");

        //删除cookie和session信息
        Cookie cookie = new Cookie("name", user_name);
        cookie.setMaxAge(0);
        resp.addCookie(cookie);

        HttpSession session = req.getSession();
        if (session != null) {
            session.invalidate();
        }
        //删除用户信息
        UserService userService = new UserServiceImpl();
        userService.delUser(user_name);
    }

    public void get_user_name(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter pw = resp.getWriter();
        HttpSession httpSession = req.getSession();
        String name = (String) httpSession.getAttribute("name");
        pw.print(name);
        pw.close();
    }

    public void search_user(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=UTF-8");

        String name = req.getParameter("name");
        PrintWriter pw = resp.getWriter();

        UserService userService = new UserServiceImpl();

        pw.print(JSON.toJSON(userService.searchUser(name)));
        pw.close();
    }

    /**
     * 管理员删除用户信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void del_user(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        // 获取对象
        String user_name = req.getParameter("name");

        //删除用户信息
        UserService userService = new UserServiceImpl();
        userService.delUser(user_name);
    }
}
