package service.impl;

import bean.Book;
import bean.Cart;
import bean.User;
import dao.BookDao;
import dao.CartDao;
import dao.impl.BookDaoImpl;
import dao.impl.CartDaoImpl;
import service.CartService;

import java.util.ArrayList;
import java.util.List;

public class CartServiceImpl implements CartService {

    private final CartDao cartDao = new CartDaoImpl();
    private final BookDao bookDao = new BookDaoImpl();

    @Override
    public int addToCart(String isbn, String user_name) {
        return cartDao.addCart(new Cart(user_name, isbn));
    }

    @Override
    public void delFromCart(String isbn, String user_name) {
        cartDao.delCart(new Cart(user_name, isbn));
    }

    @Override
    public List<Book> getFromCart(User user) {
        List<Cart> carts = cartDao.searchCartByName(user.getName());
        List<Book> books = new ArrayList<>();
        for(Cart cart: carts){
            books.add(bookDao.searchBookByISBN(cart.getIsbn()));
        }
        return books;
    }

    @Override
    public boolean hasCart(String isbn, String userName) {
        if(cartDao.hasCart(isbn, userName)){
            return false;
        } else {
            return true;
        }
    }
}
