package util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;


public class PageUtil {
    /**
     * 给浏览器返回界面
     * @param file_path html文件路径
     * @param req 请求
     * @param resp 响应
     * @throws ServletException
     * @throws IOException
     */
    public static void returnPage(String file_path, HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        PrintWriter pw = resp.getWriter();
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file_path), StandardCharsets.UTF_8));
        char[] ch = new char[1024];
        int length = 0;
        while(-1 != (length = br.read(ch, 0, 1024))){
            pw.write(ch, 0, length);
        }
        br.close();
        pw.close();
    }
}
