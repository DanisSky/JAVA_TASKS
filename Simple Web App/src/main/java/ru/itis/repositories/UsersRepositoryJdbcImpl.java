package ru.itis.repositories;

import ru.itis.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UsersRepositoryJdbcImpl implements UsersRepository {

    private final Connection connection;
    //language=sql
    private static final String SQL_ADD_USER_TO_DB = "INSERT INTO \"users\"(first_name, last_name, login, pass) VALUES(?, ?, ?, ?)";
    private static final String SQL_SELECT_ALL_FROM_USERS = "SELECT id, first_name, last_name from \"users\"";

    public UsersRepositoryJdbcImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(User entity) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_USER_TO_DB);
        preparedStatement.setString(1, entity.getFirstName());
        preparedStatement.setString(2, entity.getLastName());
        preparedStatement.setString(3, entity.getLogin());
        preparedStatement.setString(4, entity.getPass());
        preparedStatement.executeUpdate();
    }

    @Override
    public List<User> findAll() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_FROM_USERS);
            List<User> result = new ArrayList<>();
            while (resultSet.next()) {
                User user = User.builder()
                        .id(resultSet.getLong("id"))
                        .firstName(resultSet.getString("first_name"))
                        .lastName(resultSet.getString("last_name"))
                        .build();
                result.add(user);
            }
            return result;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }

    }
}
