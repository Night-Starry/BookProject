package util;

import junit.framework.TestCase;

public class JdbcUtilTest extends TestCase {

    public void testGetConnection() {
        System.out.println(JdbcUtil.getConnection());
    }

    public void testClose() {
    }
}