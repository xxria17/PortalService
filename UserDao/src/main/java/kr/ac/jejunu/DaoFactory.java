package kr.ac.jejunu;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;


import javax.sql.DataSource;
import java.sql.Driver;

@Configuration
public class DaoFactory {

    @Value("${db.driver}")
    private String className;
    @Value("${db.username}")
    private String username;
    @Value("${db.url}")
    private String url;
    @Value("${db.password}")
    private String password;

//    @Bean
//    public UserDao userDao() throws ClassNotFoundException {
//        return new UserDao(jdbcTemplate());
//    }

    @Bean
    public JdbcTemplate jdbcTemplate() throws ClassNotFoundException {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public DataSource dataSource() throws ClassNotFoundException {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass((Class<? extends Driver>) Class.forName(className));
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setUrl(url);
        return dataSource;
    }
}
