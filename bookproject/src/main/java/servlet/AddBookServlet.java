package servlet;

import bean.Book;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import service.BookService;
import service.impl.BookServiceImpl;
import util.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/addBook")
public class AddBookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //判断是否为多段数据
        if(ServletFileUpload.isMultipartContent(req)){
            //创建fileItemFactory工厂实现类
            DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
            fileItemFactory.setSizeThreshold(1024000);
            //创建用于解析上传数据的工具类servletFileUpLoad类
            ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
            //解析上传的数据，得到每一个表单项FiLeItem
            try {
                List<FileItem> fileItems = servletFileUpload.parseRequest(req);
                Map<String, String> book_map = new HashMap<>();
                FileItem img_file = null;
                //循环判断每一个表单项
                for(FileItem fileItem: fileItems){
                    if(fileItem.isFormField()){
                        //普通表单项
                        book_map.put(fileItem.getFieldName(), fileItem.getString("UTF-8"));
                    }else {
                        img_file = fileItem;
                    }
                }
                //获取book对象，执行更新操作
                Book book = WebUtil.copyParamsToBean(new Book(), book_map);

                BookService bookService = new BookServiceImpl();
                boolean flag = -1 != bookService.addBook(book);
                //传递数据
                PrintWriter pw = resp.getWriter();
                if(flag){
                    //更新图片
                    if (img_file != null) {
                        img_file.write(new File(getServletContext().getRealPath(book.getImg())));
                    }
                    pw.print("success");
                }else {
                    pw.print("fail");
                }
                pw.close();


            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e); // 把异常抛给filter过滤器
            }
        }
    }
}
