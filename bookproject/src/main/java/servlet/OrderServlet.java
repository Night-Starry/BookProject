package servlet;

import bean.Book;
import bean.Goods;
import bean.Order;
import bean.User;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import dao.GoodsDao;
import dao.impl.GoodsDaoImpl;
import service.BookService;
import service.OrderService;
import service.UserService;
import service.impl.BookServiceImpl;
import service.impl.OrderServiceImpl;
import service.impl.UserServiceImpl;
import util.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet("/order")
public class OrderServlet extends BaseServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }


    /**
     * 添加订单
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void add_order(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

//        // 获取请求参数映射
//        java.util.Map<String, String[]> parameterMap = req.getParameterMap();
//
//        // 使用循环遍历所有键值对
//        for (java.util.Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
//            String parameterName = entry.getKey();
//            String[] parameterValues = entry.getValue();
//
//            // 在此处处理每个参数
//            System.out.println("Parameter name: " + parameterName);
//            System.out.println("Parameter values:");
//            for (String value : parameterValues) {
//                System.out.println(value);
//            }
//        }

        OrderService orderService = new OrderServiceImpl();

        //获取订单对象
        String id = req.getParameter("id");
        String name = (String) req.getSession().getAttribute("name");
        String time = req.getParameter("time");
        double total = Double.parseDouble(req.getParameter("total"));
        Order order = new Order(id, name, time, total);

        //获取其中的货物信息
        ArrayList<Goods> all_goods = new ArrayList<>();
        String[] isbn_all = req.getParameterValues("isbn_all[]");
        String[] num_all = req.getParameterValues("num_all[]");
        String[] price_all = req.getParameterValues("price_all[]");

        for(int i = 0; i < isbn_all.length; i++){
            all_goods.add(new Goods(isbn_all[i], Integer.parseInt(num_all[i]),
                    Double.parseDouble(price_all[i]), order.getId()));
        }
        order.setAll_goods(all_goods);


        //添加到数据库
        int flag = orderService.addOrder(order);

        //响应数据
        PrintWriter pw = resp.getWriter();
        if(-1 != flag) {
            pw.print("success");
        } else {
            pw.print("fail");
        }
        pw.close();
    }

    /**
     * 通过指定id获取订单
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void get_order(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=UTF-8");

        OrderService orderService = new OrderServiceImpl();
        String id = req.getParameter("id");
        Order order = orderService.searchOrder(id);

        PrintWriter pw = resp.getWriter();
        pw.print(JSON.toJSON(order));
        pw.close();
    }

    /**
     * 通过用户名和id获取订单信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void search_order(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=UTF-8");

        OrderService orderService = new OrderServiceImpl();
        String id = req.getParameter("id");
        String name = (String) req.getSession().getAttribute("name");

        List<Order> orders = orderService.searchOrder(name, id);

        PrintWriter pw = resp.getWriter();
        pw.print(JSON.toJSON(orders));
        pw.close();
    }

    /**
     * 设置session
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void set_order_id(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=UTF-8");

        String id = req.getParameter("id");

        req.getSession().setAttribute("id", id);
    }

    /**
     * 从session中获取订单号，获取指定的订单信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void get_specific_order(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=UTF-8");

        OrderService orderService = new OrderServiceImpl();
        String id = (String) req.getSession().getAttribute("id");
        Order order = orderService.searchOrder(id);

        PrintWriter pw = resp.getWriter();
        pw.print(JSON.toJSON(order));
        pw.close();
    }


    /**
     * 查询所有满足条件的订单
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void search_order_admin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=UTF-8");

        OrderService orderService = new OrderServiceImpl();
        Order order = WebUtil.copyParamsToBean(new Order(), req.getParameterMap());

        List<Order> orders = orderService.searchOrder(order);

        PrintWriter pw = resp.getWriter();
        pw.print(JSON.toJSON(orders));
        pw.close();
    }

    protected void get_order_admin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=UTF-8");

        OrderService orderService = new OrderServiceImpl();

        List<Order> orders = orderService.searchOrder(new Order());

        PrintWriter pw = resp.getWriter();
        pw.print(JSON.toJSON(orders));
        pw.close();
    }

}
