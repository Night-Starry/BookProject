package service.impl;

import bean.Book;
import bean.Page;
import dao.BookDao;
import dao.impl.BookDaoImpl;
import service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {

    private final BookDao bookDao = new BookDaoImpl();

    @Override
    public Book searchBookByISBN(String isbn) {
        return bookDao.searchBookByISBN(isbn);
    }

    @Override
    public int deleteBook(String isbn) {
        return bookDao.deleteBook(isbn);
    }

    @Override
    public int updateBook(Book book) {
        return bookDao.updateBook(book);
    }

    @Override
    public int addBook(Book book) {
        return bookDao.addBook(book);
    }

    @Override
    public List<Book> searchBook(Book book) {
        return bookDao.searchBook(book);
    }

    @Override
    public Page<Book> page(Book book, int pageNo, int pageSize) {
        Page<Book> page = new Page<>();
        //设置当前页码
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);

        //设置总记录数
        Integer pageTotalCount = bookDao.queryTotalCount();
        page.setPageTotalCount(pageTotalCount);

        //设置总页码
        int pageTotal = pageTotalCount / pageSize;
        if(pageTotalCount % pageSize > 0){
            pageTotal += 1;
        }
        page.setPageTotal(pageTotal);

        int begin = (pageNo - 1) * pageSize;
        //求当前页数据
        List<Book> items= bookDao.queryPageItems(book, begin, pageSize);
        //设置当前页数据
        page.setItems(items);

        return page;
    }
}
