package dao;

import bean.Goods;
import bean.Order;
import dao.impl.OrderDaoImpl;
import junit.framework.TestCase;

import java.util.ArrayList;

public class OrderDaoTest extends TestCase {

    private final OrderDao orderDao = new OrderDaoImpl();

    public void testSearchOrderById() {
        System.out.println(orderDao.searchOrderById("1234567891"));
    }

    public void testAddOrder() {
        ArrayList<Goods> goods = new ArrayList<>();
        goods.add(new Goods("1111111111", 2, 10.98, "1234567891"));
        Order order = new Order("1234567890", "admin", "2023/7/31", 100);
        Order order2 = new Order("1234567891", "admin", "2023/7/31", 100, goods);
        System.out.println(orderDao.addOrder(order));
        System.out.println(orderDao.addOrder(order2));
    }

    public void testDelOrder() {
        Order order = new Order("1234567890", "admin", "2023/7/31", 100);
        System.out.println(orderDao.delOrder(order.getId()));
    }
}