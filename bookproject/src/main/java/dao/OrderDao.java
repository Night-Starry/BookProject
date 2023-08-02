package dao;

import bean.Order;

import java.util.List;

public interface OrderDao {

    /**
     * 通过订单号查询订单
     * @param id
     * @return
     */
    Order searchOrderById(String id);


    /**
     * 添加订单
     * @return 如果添加失败，返回-1
     */
    int addOrder(Order order);

    /**
     * 通过订单号删除订单
     * @return 如果删除失败，返回-1
     */
    int delOrder(String id);

    /**
     * 查询用户的订单信息
     */
    List<Order> searchOrder(String name, String id);

    List<Order> searchOrder(Order order);
}
