package service;

import bean.Book;
import dao.BookDao;
import dao.impl.BookDaoImpl;
import junit.framework.TestCase;
import service.impl.BookServiceImpl;

public class BookServiceTest extends TestCase {

    private final BookService bookService = new BookServiceImpl();

    public void testSearchBookByISBN() {
        System.out.println(bookService.searchBookByISBN("1111111111"));
    }

    public void testDeleteBook() {
        bookService.deleteBook("1234567890");
    }

    public void testUpdateBook() {
        Book book = new Book("1234567890", "name1", "author1", "version1",
                10.98, 100, 100);
        bookService.updateBook(book);
    }

    public void testAddBook() {
        Book book = new Book("1234567890", "name", "author", "version",
                10.98, 100, 10);
        bookService.addBook(book);
    }

    public void testSearchBook() {
        Book book = new Book(null, null, "i");
        System.out.println(bookService.searchBook(book));
    }

    public void testPage(){
        Book book = new Book(null, null, "i");
        System.out.println(bookService.page(new Book(), 1, 3).getItems());
    }
}