package com.spring.tobi.user.dao;

import lombok.RequiredArgsConstructor;

import java.sql.Connection;
import java.sql.SQLException;

@RequiredArgsConstructor
public class CountingConnectionMarker implements ConnectionMarker {
    int count = 0;
    private final ConnectionMarker connectionMarker;

    @Override
    public Connection makeConnection() throws ClassNotFoundException, SQLException {
        this.count++;
        return connectionMarker.makeConnection();
    }

    public int getCount() {
        return count;
    }
}
