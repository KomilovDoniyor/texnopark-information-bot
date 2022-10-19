package repository;

import entity.User;

import java.sql.SQLException;

public interface UserRepository {
    boolean exitsByChatId(Long chatId) throws SQLException;

    void save(User user) throws SQLException;

    User findByChatId(Long chatId) throws SQLException;

    void update(User user) throws SQLException;
}
