package util;

import org.apache.commons.beanutils.BeanUtils;
import java.util.Map;

public class WebUtil {

    /**
     * 将字典的值放入对象中
     * @param t 对象
     * @param map 储存信息的map
     * @return 返回对象
     * @param <T>
     */
    public static <T> T copyParamsToBean(T t, Map map){
        try {
            BeanUtils.populate(t, map);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }
}
