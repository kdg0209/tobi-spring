package com.spring.tobi.user.dao;

import com.spring.tobi.user.domain.User;
import com.spring.tobi.user.strategyPattern.AddStatement;
import com.spring.tobi.user.strategyPattern.DeleteAllStatement;
import com.spring.tobi.user.strategyPattern.GetStatement;
import com.spring.tobi.user.strategyPattern.StatementStrategy;

import javax.sql.DataSource;
import java.sql.*;

public class UserDao {
    DataSource dataSource;

    public void add(User user) throws ClassNotFoundException, SQLException {
        jdbcContextWitStatementStrategy(new AddStatement(user));
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Connection conn = dataSource.getConnection();

        StatementStrategy statementStrategy = new GetStatement(id);
        PreparedStatement ps =  statementStrategy.makePreparedStatement(conn);
        ResultSet rs = ps.executeQuery();

        rs.next();
        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        rs.close();
        ps.close();
        conn.close();

        return user;
    }

    public void deleteAll() throws SQLException{
        jdbcContextWitStatementStrategy(new DeleteAllStatement());
    }

    public void jdbcContextWitStatementStrategy(StatementStrategy statementStrategy) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        try{
            conn = dataSource.getConnection();

            // 변하는 부분을 전략 패턴을 이용하여 독립시킴
            ps = statementStrategy.makePreparedStatement(conn);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {}
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {}
            }
        }
    }

    private PreparedStatement makeStatement(Connection conn) throws SQLException {
        PreparedStatement ps;
        ps = conn.prepareStatement("delete from users");
        return ps;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
