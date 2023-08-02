package dao;

import bean.Goods;

import java.util.List;

public interface GoodsDao {

    /**
     * 通过订单号获取所有货物信息
     * @param id
     * @return
     */
    List<Goods> searchById(String id);

    /**
     * 查询货物信息
     * @param goods
     * @return
     */
    List<Goods> searchGoods(Goods goods);

    /**
     * 添加货物信息
     * @param goods
     * @return
     */
    int addGoods(Goods goods);
}
