package com.spring.tobi.user.strategyPattern;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface StatementStrategy {

    PreparedStatement makePreparedStatement(Connection conn) throws SQLException;
}
