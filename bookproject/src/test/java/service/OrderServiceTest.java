package service;

import bean.Order;
import junit.framework.TestCase;
import service.impl.OrderServiceImpl;

public class OrderServiceTest extends TestCase {

    private final OrderService orderService = new OrderServiceImpl();

    public void testAddOrder() {
        System.out.println(orderService.addOrder(new Order("1234567890", "admin", "2023/7/31", 100)));
    }

    public void testSearchOrder() {
        System.out.println(orderService.searchOrder("1234567891"));
    }
}