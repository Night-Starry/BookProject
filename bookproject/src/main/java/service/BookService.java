package service;

import bean.Book;
import bean.Page;
import dao.BookDao;

import java.util.List;

public interface BookService {

    Page<Book> page(Book book, int pageNo, int pageSize);

    /**
     * 通过ISBN号查询书籍
     * @param isbn 书籍的isbn号
     * @return 返回书籍
     */
    Book searchBookByISBN(String isbn);

    /**
     * 通过ISBN号删除书籍
     * @param isbn 书籍的isbn号
     */
    int deleteBook(String isbn);

    /**
     * 通过ISBN号更新书籍
     * @param book book对象
     */
    int updateBook(Book book);

    /**
     * 添加书籍
     * @param book 书籍
     */
    int addBook(Book book);

    /**
     * 查询书籍
     * @param book 查询用的book对象
     * @return 返回书籍列表
     */
    List<Book> searchBook(Book book);
}
