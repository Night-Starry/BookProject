package dao;

import bean.Book;
import dao.impl.BookDaoImpl;
import junit.framework.TestCase;

public class BookDaoTest extends TestCase {

    private final BookDao bookDao = new BookDaoImpl();

    public void testSearchBookByISBN() {
        System.out.println(bookDao.searchBookByISBN("1111111111"));
    }

    public void testDeleteBook() {
        System.out.println(bookDao.deleteBook("1234567890"));
    }

    public void testUpdateBook() {
        Book book = new Book("1234567890", "name1", "author1", "version1",
                10.98, 100, 100);
        System.out.println(bookDao.updateBook(book));
    }

    public void testAddBook() {
        Book book = new Book("1234567890", "name", "author", "version",
                10.98, 100, 10);
        System.out.println(bookDao.addBook(book));
    }

    public void testSearchBook() {
        Book book = new Book(null, null, "i");
        System.out.println(bookDao.searchBook(book));
    }

    public void testPage(){
        Book book = new Book(null, null, "i");
        System.out.println(bookDao.queryPageItems(book, 0, 5));
        System.out.println(bookDao.queryPageItems(new Book(), 0, 5));
    }

        public void testQueryTotal(){
            System.out.println(bookDao.queryTotalCount());
    }
}