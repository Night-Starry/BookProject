package service;

import bean.Book;
import bean.User;

import java.util.List;

public interface CartService {

    /**
     * 将书籍添加到用户的购物车
     *
     * @param isbn
     * @param user_name
     * @return
     */
    int addToCart(String isbn, String user_name);

    /**
     * 将书籍从用户的购物车移除
     *@param isbn
     * @param user_name
     */
    void delFromCart(String isbn, String user_name);

    /**
     * 获取用户的购物车中书籍信息
     * @param user
     * @return
     */
    List<Book> getFromCart(User user);

    /**
     * 判断用户购物车中是否存在该书籍
     * @param isbn
     * @param userName
     * @return
     */
    boolean hasCart(String isbn, String userName);
}
