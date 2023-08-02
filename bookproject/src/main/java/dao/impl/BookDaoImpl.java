package dao.impl;

import bean.Book;
import dao.BaseDao;
import dao.BookDao;

import java.util.List;

public class BookDaoImpl extends BaseDao implements BookDao {
    @Override
    public Book searchBookByISBN(String isbn) {
        String sql = "select * from books where isbn = ?";
        return queryOne(Book.class, sql, isbn);
    }

    @Override
    public int deleteBook(String isbn) {
        String sql = "delete from books where isbn = ?";
        return update(sql, isbn);
    }

    @Override
    public int updateBook(Book book) {
        String sql = "update books set name = ?, author = ?, version = ?, price = ?, stock = ?, sale = ?, img = ?" +
                "where isbn = ?";
        return update(sql, book.getName(), book.getAuthor(), book.getVersion(), book.getPrice(), book.getStock(),
                book.getSale(), book.getImg(), book.getIsbn());
    }

    @Override
    public int addBook(Book book) {
        String sql = "insert into books values(?, ?, ?, ?, ?, ?, ?, ?)";
        return update(sql, book.getIsbn(), book.getName(), book.getAuthor(), book.getVersion(), book.getPrice(),
                book.getStock(), book.getSale(), book.getImg());
    }

    @Override
    public List<Book> searchBook(Book book) {
        StringBuilder sb_sql = new StringBuilder("select * from books where ");
        boolean flag = false;
        if(book.getIsbn() != null && !book.getIsbn().equals("")){
            sb_sql.append("isbn like '%").append(book.getIsbn()).append("%'");
            flag = true;
        }
        if(book.getAuthor() != null && !book.getAuthor().equals("")){
            if(flag){
                sb_sql.append(" and");
            }
            sb_sql.append(" author like '%").append(book.getAuthor()).append("%'");
            flag = true;
        }
        if(book.getName() != null && !book.getName().equals("")){
            if(flag){
                sb_sql.append(" and");
            }
            sb_sql.append(" name like '%").append(book.getName()).append("%'");
        }
        if(-1 == sb_sql.indexOf("like")){
            sb_sql.delete(sb_sql.indexOf("where"), sb_sql.length());
        }


        return queryList(Book.class, sb_sql.toString());
    }

    @Override
    public Integer queryTotalCount() {
        String sql = "select count(*) from books";
        Number number = (Number) queryValue(sql);
        if (number != null) {
            return number.intValue();
        }
        return 0;
    }

    @Override
    public List<Book> queryPageItems(Book book, int begin, int pageSize) {
        StringBuilder sb_sql = new StringBuilder("select * from books where ");
        boolean flag = false;
        if(book.getIsbn() != null && !book.getIsbn().equals("")){
            sb_sql.append("isbn like '%").append(book.getIsbn()).append("%'");
            flag = true;
        }
        if(book.getAuthor() != null && !book.getAuthor().equals("")){
            if(flag){
                sb_sql.append(" and");
            }
            sb_sql.append(" author like '%").append(book.getAuthor()).append("%'");
            flag = true;
        }
        if(book.getName() != null && !book.getName().equals("")){
            if(flag){
                sb_sql.append(" and");
            }
            sb_sql.append(" name like '%").append(book.getName()).append("%'");
        }
        if(-1 == sb_sql.indexOf("like")){
            sb_sql.delete(sb_sql.indexOf("where"), sb_sql.length());
        }

        sb_sql.append(" limit ").append(begin).append(", ").append(pageSize);
        return queryList(Book.class, sb_sql.toString());
    }
}
