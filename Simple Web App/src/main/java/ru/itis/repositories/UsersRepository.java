package ru.itis.repositories;

import ru.itis.models.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UsersRepository extends CrudRepository<User>{
    @Override
    void save(User entity) throws SQLException;
    @Override
    List<User> findAll();

    @Override
    Optional<User> findByLogin(String login) throws SQLException;
}
