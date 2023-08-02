package servlet;

import bean.Book;
import bean.Cart;
import bean.User;
import com.alibaba.fastjson.JSON;
import service.CartService;
import service.impl.CartServiceImpl;
import util.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/cart")
public class CartServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    protected void get_cart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=UTF-8");
        CartService cartService = new CartServiceImpl();
        List<Book> books = cartService.getFromCart(WebUtil.copyParamsToBean(new User(), req.getParameterMap()));

        PrintWriter pw = resp.getWriter();
        pw.print(JSON.toJSON(books));
        pw.close();
    }

    protected void remove_from_cart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=UTF-8");
        String isbn = req.getParameter("isbn");
        String user_name = req.getParameter("user_name");
        CartService cartService = new CartServiceImpl();
        cartService.delFromCart(isbn, user_name);
    }

    protected void add_to_cart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=UTF-8");
        String isbn = (String) req.getSession().getAttribute("isbn");
        String user_name = (String) req.getSession().getAttribute("name");
        CartService cartService = new CartServiceImpl();
        if(cartService.hasCart(isbn, user_name)){
            resp.getWriter().print("already");
        } else {
            if(-1 == cartService.addToCart(isbn, user_name)){
                resp.getWriter().print("fail");
            }else {
                resp.getWriter().print("success");
            }
        }
    }
}
