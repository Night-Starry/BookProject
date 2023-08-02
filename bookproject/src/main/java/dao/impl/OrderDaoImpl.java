package dao.impl;

import bean.Book;
import bean.Cart;
import bean.Goods;
import bean.Order;
import dao.BaseDao;
import dao.GoodsDao;
import dao.OrderDao;

import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl extends BaseDao implements OrderDao {

    private final GoodsDao goodsDao = new GoodsDaoImpl();
    @Override
    public Order searchOrderById(String id) {
        String sql = "select * from `order` where id = ?";
        Order order = queryOne(Order.class, sql, id);
        if (order != null) {
            List<Goods> goods = goodsDao.searchById(id);
            order.setAll_goods((ArrayList<Goods>) goods);
        }else {
            return null;
        }
        return order;
    }

    @Override
    public int addOrder(Order order) {
        String sql = "insert into `order` values(?, ?, ?, ?)";
        int out = update(sql, order.getId(), order.getName(), order.getTime(), order.getTotal());
        if(out == -1){
            return -1;
        }else {
            ArrayList<Goods> allGoods = order.getAll_goods();
            for (Goods goods:allGoods) {
                out = goodsDao.addGoods(goods);
                if(out == -1){
                    return -1;
                }
            }
        }
        return 1;
    }

    @Override
    public int delOrder(String id) {
        String sql = "delete from `order` where id = ?";
        return update(sql, id);
    }

    @Override
    public List<Order> searchOrder(String name, String id) {
        StringBuilder s_sql = new StringBuilder("select * from `order`");
        boolean flag = false;
        if(name != null && !name.equals("")){
            s_sql.append(" where name like '%").append(name).append("%'");
            flag = true;
        }
        if(id != null && !id.equals("")){
            if(!flag){
                s_sql.append(" where ");
            } else {
                s_sql.append(" and ");
            }
            s_sql.append("id like '%").append(id).append("%'");
        }

        return queryList(Order.class, s_sql.toString());
    }

    @Override
    public List<Order> searchOrder(Order order) {
        StringBuilder sb_sql = new StringBuilder("select * from `order` where ");
        boolean flag = false;
        if(order.getId() != null && !order.getId().equals("")){
            sb_sql.append("id like '%").append(order.getId()).append("%'");
            flag = true;
        }
        if(order.getTime() != null && !order.getTime().equals("")){
            if(flag){
                sb_sql.append(" and");
            }
            sb_sql.append(" time like '%").append(order.getTime()).append("%'");
            flag = true;
        }
        if(order.getName() != null && !order.getName().equals("")){
            if(flag){
                sb_sql.append(" and");
            }
            sb_sql.append(" name like '%").append(order.getName()).append("%'");
        }
        if(-1 == sb_sql.indexOf("like")){
            sb_sql.delete(sb_sql.indexOf("where"), sb_sql.length());
        }

        return queryList(Order.class, sb_sql.toString());
    }
}
