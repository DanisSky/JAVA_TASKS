package ru.itis.repositories;

import ru.itis.models.User;

import java.sql.SQLException;
import java.util.List;

public interface UsersRepository extends CrudRepository<User>{
    @Override
    void save(User entity) throws SQLException;
    @Override
    List<User> findAll();
}
