package ru.itis.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.UUID;

public class CookiesRepository {
    private final Connection connection;
    //language=sql
    private static final String SQL_ADD_UUID_TO_UUID_DB = "INSERT INTO \"uuid\" (uuid, user_id) values(?,?)";

    public CookiesRepository(Connection connection) {
        this.connection = connection;
    }

    public void save(UUID uuid, Long user_id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_UUID_TO_UUID_DB);
        preparedStatement.setObject(1, uuid, Types.OTHER);
        preparedStatement.setLong(2, user_id);
        preparedStatement.executeUpdate();
    }

}
