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
        jdbcContext.workWithStatementStrategy(new AddStatement(user));
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
        jdbcContext.workWithStatementStrategy(new DeleteAllStatement());
    }
}
