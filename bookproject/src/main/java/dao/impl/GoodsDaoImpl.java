package dao.impl;

import bean.Cart;
import bean.Goods;
import dao.BaseDao;
import dao.GoodsDao;

import java.util.List;

public class GoodsDaoImpl extends BaseDao implements GoodsDao {
    @Override
    public List<Goods> searchById(String id) {
        String sql = "select * from goods where id = ?";
        return queryList(Goods.class, sql, id);
    }

    @Override
    public List<Goods> searchGoods(Goods goods) {
        StringBuilder s_sql = new StringBuilder("select * from goods");
        String id = goods.getId();
        String isbn = goods.getIsbn();
        boolean flag = false;
        if(id != null && !id.equals("")){
            s_sql.append(" where id like '%").append(id).append("%'");
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

        return queryList(Goods.class, s_sql.toString());
    }

    @Override
    public int addGoods(Goods goods) {
        String sql = "insert into goods values(?, ?, ?, ?)";
        return update(sql, goods.getIsbn(), goods.getNum(), goods.getPrice(), goods.getId());
    }
}
