package dao;

import bean.Book;

import java.util.List;

public interface BookDao{

    /**
     * 通过ISBN号查询书籍
     * @param isbn 书籍的isbn号
     * @return 返回书籍
     */
    Book searchBookByISBN(String isbn);

    /**
     * 通过ISBN号删除书籍
     * @param isbn 书籍的isbn号
     * @return 返回删除数量
     */
    int deleteBook(String isbn);

    /**
     * 通过ISBN号更新书籍
     * @param book book对象
     * @return 返回删除数量
     */
    int updateBook(Book book);

    /**
     * 添加书籍
     * @param book 书籍
     * @return
     */
    int addBook(Book book);

    /**
     * 查询书籍
     * @param book 查询用的book对象
     * @return 返回书籍列表
     */
    List<Book> searchBook(Book book);

    /**
     * 获取总记录数量
     * @return
     */
    Integer queryTotalCount();

    /**
     * 获取当前页数据
     * @param begin
     * @param pageSize
     * @return
     */
    List<Book> queryPageItems(Book book, int begin, int pageSize);
}
