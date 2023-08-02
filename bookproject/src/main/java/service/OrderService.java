package service;

import bean.Order;

import java.util.List;

public interface OrderService {
    /**
     * 添加订单信息
     */
    int addOrder(Order order);


    /**
     * 查询订单信息
     * @param id 订单号
     * @return
     */
    Order searchOrder(String id);

    /**
     * 查询订单信息
     * @param name 用户姓名
     * @param id 订单号
     * @return
     */
    List<Order> searchOrder(String name, String id);

    /**
     * 查询订单信息
     * @return
     */
    List<Order> searchOrder(Order order);
}
