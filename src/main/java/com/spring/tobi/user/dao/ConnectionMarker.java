package com.spring.tobi.user.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionMarker {

    public Connection makeConnection() throws ClassNotFoundException, SQLException;
}
