package dao;

import bean.Goods;
import dao.impl.GoodsDaoImpl;
import junit.framework.TestCase;

public class GoodsDaoTest extends TestCase {

    private final GoodsDao goodsDao = new GoodsDaoImpl();

    public void testSearchById() {
        System.out.println(goodsDao.searchById("1234567891"));
    }

    public void testSearchGoods() {
        Goods goods = new Goods("1111111111", 2, 10.98, "1234567891");
        System.out.println(goodsDao.searchGoods(new Goods()));
        System.out.println(goodsDao.searchGoods(goods));
    }

    public void testAddGoods() {
        Goods goods = new Goods("2222222222", 2, 10.98, "1234567891");
        System.out.println(goodsDao.addGoods(goods));
    }
}