package com.spring.tobi.user.dao;

import com.spring.tobi.user.domain.User;
import java.sql.*;

public class UserDao {

    private ConnectionMarker connectionMarker;

    public UserDao(ConnectionMarker connectionMarker) {
        this.connectionMarker = connectionMarker;
    }

    public void add(User user) throws ClassNotFoundException, SQLException {
        Connection conn = connectionMarker.makeConnection();
        PreparedStatement ps =  conn.prepareStatement("INSERT INTO users(id, name, password) VALUES (?, ?, ?)");
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());
        ps.executeUpdate();

        ps.close();
        conn.close();
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Connection conn = connectionMarker.makeConnection();
        PreparedStatement ps =  conn.prepareStatement("SELECT * FROM users WHERE id = ?");
        ps.setString(1, id);
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
}
