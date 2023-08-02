package dao;

import bean.Cart;
import dao.impl.CartDaoImpl;
import junit.framework.TestCase;

public class CartDaoTest extends TestCase {
    private final CartDao cartDao = new CartDaoImpl();

    public void testSearchCartByName() {
        System.out.println(cartDao.searchCartByName("admin"));
    }

    public void testSearchCart() {
        Cart cart = new Cart("admin", "4444444444");
        System.out.println(cartDao.searchCart(cart));
    }

    public void testAddCart() {
        Cart cart = new Cart("admin", "4444444444");
        cartDao.addCart(cart);
    }

    public void testDelCart() {
        Cart cart = new Cart("admin", "4444444444");
        System.out.println(cartDao.delCart(cart));
    }
}