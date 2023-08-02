package service.impl;

import bean.Order;
import dao.OrderDao;
import dao.impl.OrderDaoImpl;
import service.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    private final OrderDao orderDao = new OrderDaoImpl();
    @Override
    public int addOrder(Order order) {
        return orderDao.addOrder(order);
    }

    @Override
    public Order searchOrder(String id) {
        return orderDao.searchOrderById(id);
    }

    @Override
    public List<Order> searchOrder(String name, String id) {
        return orderDao.searchOrder(name, id);
    }

    @Override
    public List<Order> searchOrder(Order order) {
        return orderDao.searchOrder(order);
    }
}
