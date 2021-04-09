package kr.ac.jejunu;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.*;

public class UserDao {
    private final JdbcTemplate jdbcTemplate;

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User findById(Integer id) throws SQLException {
        //데이터 Mysql 연결
        Object[] params = new Object[] {id};
        String sql = "select * from userinfo where id = ?";
        return jdbcTemplate.query(sql, rs -> {
            User user = null;
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
            }
            return user;
        }, id);
    }

    public void insert(User user) throws SQLException {
        //데이터 Mysql 연결
        Object[] params = new Object[] {user.getName(), user.getPassword()};
        String sql = "insert into userinfo (name, password) values (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    sql
                    , Statement.RETURN_GENERATED_KEYS
            );
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i+1, params[i]);
            }
            return preparedStatement;
        }, keyHolder);
        user.setId(keyHolder.getKey().intValue());
    }

    public void update(User user) throws SQLException {
        //데이터 Mysql 연결
        String sql = "update userinfo set name = ?, password = ? where id = ?";
        Object[] params = new Object[] {user.getName(), user.getPassword(), user.getId()};

        jdbcTemplate.update(sql, params);
    }

    public void delete(Integer id) throws SQLException {
        //데이터 Mysql 연결
        Object[] params = new Object[] {id};
        String sql = "delete from userinfo where id = ?";

        jdbcTemplate.update(sql, params);
    }


}
