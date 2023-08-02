package servlet.pages;

import bean.User;
import service.UserService;
import service.impl.UserServiceImpl;
import util.ImgUtil;
import util.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/img")
public class ImgServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        //获取图像文件
        String img_file = req.getParameter("img_file");
        String file_path = getServletContext().getRealPath(img_file);

        ImgUtil.returnPage(file_path, req, resp);
    }
}
