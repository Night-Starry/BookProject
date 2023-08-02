package dao.impl;

import bean.Cart;
import dao.BaseDao;
import dao.CartDao;

import java.util.List;

public class CartDaoImpl extends BaseDao implements CartDao {

    @Override
    public List<Cart> searchCartByName(String name) {
        String sql = "select * from cart where name = ?";
        return queryList(Cart.class, sql, name);
    }

    @Override
    public List<Cart> searchCart(Cart cart) {
        StringBuilder s_sql = new StringBuilder("select * from cart");
        String name = cart.getName();
        String isbn = cart.getIsbn();
        boolean flag = false;
        if(name != null && !name.equals("")){
            s_sql.append(" where name like '%").append(name).append("%'");
            flag = true;
        }
        if(isbn != null && !isbn.equals("")){
            if(!flag){
                s_sql.append(" where ");
            } else {
                s_sql.append(" and ");
            }
            s_sql.append("isbn like '%").append(isbn).append("%'");
        }

        return queryList(Cart.class, s_sql.toString());
    }

    @Override
    public int addCart(Cart cart) {
        String sql = "insert into cart values(?, ?)";
        return update(sql, cart.getName(), cart.getIsbn());
    }

    @Override
    public int delCart(Cart cart) {
        String sql = "delete from cart where name = ? and isbn = ?";
        return update(sql, cart.getName(), cart.getIsbn());
    }

    @Override
    public boolean hasCart(String isbn, String userName) {
        String sql = "select * from cart where name = ? and isbn = ?";
        return null != queryOne(Cart.class, sql, userName, isbn);
    }
}
