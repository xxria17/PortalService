package kr.ac.jejunu;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JejuUserDao extends UserDao {
    public JejuUserDao(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost/db?characterEncoding=utf-8&serverTimezone=UTC"
                , "root", "*");
    }
}
