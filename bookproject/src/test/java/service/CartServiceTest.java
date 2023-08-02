package service;

import bean.User;
import junit.framework.TestCase;
import service.impl.CartServiceImpl;

public class CartServiceTest extends TestCase {

    private final CartService cartService = new CartServiceImpl();

    public void testAddToCart() {
        cartService.addToCart("5555555555", "admin");
    }

    public void testDelFromCart() {
        cartService.delFromCart("5555555555", "admin");
    }

    public void testGetFromCart() {
        System.out.println(cartService.getFromCart(new User("admin", null)));
    }
}