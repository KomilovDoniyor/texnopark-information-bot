/**
 * Author: komiloff_doniyor2505@gmail.com
 * Date:10/7/2022
 * Time:10:34 PM
 * Project Name:texnopark-information-bot
 */
package service.impl;

import entity.User;
import repository.UserRepository;
import repository.impl.UserRepositoryImpl;
import service.UserService;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {
    public static UserRepository userRepository = new UserRepositoryImpl();

    @Override
    public boolean exitsByChatId(Long chatId) throws SQLException {
        return userRepository.exitsByChatId(chatId);
    }

    @Override
    public void save(User user) throws SQLException {
        userRepository.save(user);
    }

    @Override
    public User findByChatId(Long chatId) throws SQLException {
        return userRepository.findByChatId(chatId);
    }

    @Override
    public void update(User user) throws SQLException {
        userRepository.update(user);
    }
}
