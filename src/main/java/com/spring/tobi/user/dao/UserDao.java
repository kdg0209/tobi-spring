package com.spring.tobi.user.dao;

import com.spring.tobi.user.context.JdbcContext;
import com.spring.tobi.user.domain.User;
import com.spring.tobi.user.strategyPattern.AddStatement;
import com.spring.tobi.user.strategyPattern.DeleteAllStatement;
import com.spring.tobi.user.strategyPattern.GetStatement;
import com.spring.tobi.user.strategyPattern.StatementStrategy;

import javax.sql.DataSource;
import java.sql.*;

public class UserDao {

    private JdbcContext jdbcContext;
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcContext = new JdbcContext(this.dataSource);
    }

    public void add(User user) throws ClassNotFoundException, SQLException {
        jdbcContext.workWithStatementStrategy(new StatementStrategy() {
            @Override
            public PreparedStatement makePreparedStatement(Connection conn) throws SQLException {
                PreparedStatement ps = conn.prepareStatement("INSERT INTO users(id, name, password) VALUES (?, ?, ?)");
                ps.setString(1, user.getId());
                ps.setString(2, user.getName());
                ps.setString(3, user.getPassword());
                return ps;
            }
        });
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        StatementStrategy statementStrategy = new GetStatement(id);
        PreparedStatement ps = jdbcContext.getPreparedStatement(statementStrategy);
        ResultSet rs = ps.executeQuery();

        rs.next();
        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        rs.close();
        ps.close();
        return user;
    }

    public void deleteAll() throws SQLException{
        jdbcContext.executeSql("delete from users");
    }

}
