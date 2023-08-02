package dao;

import bean.Cart;

import java.util.List;

public interface CartDao {

    /**
     * 通过姓名查询用户的购物车信息
     * @param name
     * @return
     */
    List<Cart> searchCartByName(String name);

    /**
     * 查询满足要求的购物车信息
     * @param cart
     * @return
     */
    List<Cart> searchCart(Cart cart);

    /**
     * 添加信息
     * @param cart
     * @return
     */
    int addCart(Cart cart);

    /**
     * 删除信息
     * @param cart
     * @return
     */
    int delCart(Cart cart);

    /**
     * 判断用户购物车是否存在该书籍
     * @param isbn
     * @param userName
     * @return
     */
    boolean hasCart(String isbn, String userName);
}
