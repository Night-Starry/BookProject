package servlet;

import bean.Book;
import bean.Page;
import bean.User;
import com.alibaba.fastjson.JSON;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import service.BookService;
import service.UserService;
import service.impl.BookServiceImpl;
import service.impl.UserServiceImpl;
import util.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.*;
import java.util.List;

@WebServlet("/book")
public class BookServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    /**
     * 获取所有图书信息
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void getBookInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=UTF-8");

        BookService bookService = new BookServiceImpl();
        //获取信息并转化为json字符串
        String book_json = JSON.toJSONString(bookService.searchBook(new Book()));

        //传递数据
        PrintWriter pw = resp.getWriter();
        pw.print(book_json);
        pw.close();

    }

    /**
     * 根据要求筛选图书信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void selectBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=UTF-8");
        String isbn = req.getParameter("isbn");
        String name = req.getParameter("name");
        String author = req.getParameter("author");

        BookService bookService = new BookServiceImpl();
        //获取信息并转化为json字符串
        String book_json = JSON.toJSONString(bookService.searchBook(new Book(isbn, name, author)));
        //传递数据
        PrintWriter pw = resp.getWriter();
        pw.print(book_json);
        pw.close();
    }

    /**
     * 在用户跳转页面查看书籍详细信息时设置isbn到session会话中
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void set_isbn(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String isbn = req.getParameter("isbn");
        HttpSession session = req.getSession();
        session.setAttribute("isbn", isbn);
    }

    /**
     * 删除图书
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void del_book(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        String isbn = req.getParameter("isbn");
        BookService bookService = new BookServiceImpl();
        PrintWriter pw = resp.getWriter();

        if(-1 != bookService.deleteBook(isbn)){
            pw.print("success");
        }else {
            pw.print("fail");
        }
        pw.close();
    }

    /**
     * 获取指定书籍信息，用户查看书籍详细信息时访问
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void getSpecificBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        resp.setContentType("application/json; charset=UTF-8");
        String isbn = (String) session.getAttribute("isbn");
        BookService bookService = new BookServiceImpl();
        //获取信息并转化为json字符串
        String book_json = JSON.toJSONString(bookService.searchBookByISBN(isbn));
        //传递数据
        PrintWriter pw = resp.getWriter();
        pw.print(book_json);
        pw.close();
    }

    /**
     * 更新图书信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void updateBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        // 获取对象
        Book book = WebUtil.copyParamsToBean(new Book(), req.getParameterMap());
        String isbn = req.getParameter("isbn");
        book.setIsbn(isbn);

        //判断是否为多段数据
//        if(ServletFileUpload.isMultipartContent(req)){
//            //创建fileItemFactory工厂实现类
//            FileItemFactory fileItemFactory = new DiskFileItemFactory();
//            //创建用于解析上传数据的工具类servletFileUpLoad类
//            ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
//            //解析上传的数据，得到每一个表单项FiLeItem
//            try {
//                List<FileItem> fileItems = servletFileUpload.parseRequest(req);
//                //循环判断每一个表单项
//                for(FileItem fileItem: fileItems){
//                    if(fileItem.isFormField()){
//                        //普通表单项
//                        System.out.println(fileItem.getFieldName());
//                        System.out.println(fileItem.getString("UTF-8"));
//                    }else {
//                        fileItem.write(new File("d:\\" + fileItem.getFieldName()));
//                    }
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }

        BookService bookService = new BookServiceImpl();


        boolean flag = -1 != bookService.updateBook(book);
        //传递数据
        PrintWriter pw = resp.getWriter();
        if(flag){
            pw.print("success");
        }else {
            pw.print("fail");
        }
        pw.close();
    }


    public void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        int pageNo = Integer.parseInt(req.getParameter("pageNo"));
        int pageSize = Integer.parseInt(req.getParameter("pageSize"));

        Book book = WebUtil.copyParamsToBean(new Book(), req.getParameterMap());
        BookService bookService = new BookServiceImpl();
        Page<Book> bookPage = bookService.page(book, pageNo, pageSize);

        //传递数据
        PrintWriter pw = resp.getWriter();
        pw.print(JSON.toJSON(bookPage));
        pw.close();
    }


    /**
     * 获取文件名
     * @param part
     * @return
     */
    private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return null;
    }
}
