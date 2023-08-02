package util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class JsUtil {
    /**
     * 给浏览器返回js
     * @param file_path js文件路径
     * @param req 请求
     * @param resp 响应
     * @throws ServletException
     * @throws IOException
     */
    public static void returnPage(String file_path, HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/javascript;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        OutputStream os = resp.getOutputStream();
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file_path));
        byte[] ch = new byte[1024];
        int length = 0;
        while(-1 != (length = bis.read(ch, 0, 1024))){
            os.write(ch, 0, length);
        }
        bis.close();
        os.close();
    }
}
