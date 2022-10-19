/**
 * Author: komiloff_doniyor2505@gmail.com
 * Date:10/7/2022
 * Time:10:26 PM
 * Project Name:texnopark-information-bot
 */
package repository.impl;

import entity.User;
import enums.BotState;
import repository.UserRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static config.DbConfig.connection;

public class UserRepositoryImpl implements UserRepository {
    @Override
    public boolean exitsByChatId(Long chatId) throws SQLException {

        String SELECT_FROM_EXITS_BY_CHAT_ID = "SELECT * FROM users WHERE chat_id = '" + chatId + "'";
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_EXITS_BY_CHAT_ID);
        ResultSet resultSet = preparedStatement.executeQuery();
        try {
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void save(User user) throws SQLException {
        String INSERT_INTO_SAVE_USER =
                "INSERT INTO users(chat_id, firstname,lastname, username, phone_number, bot_state)" +
                        "VALUES (?,?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_SAVE_USER);
        preparedStatement.setLong(1, user.getChatId());
        preparedStatement.setString(2, user.getFirstname());
        preparedStatement.setString(3, user.getLastname());
        preparedStatement.setString(4, user.getUsername());
        preparedStatement.setString(5, user.getPhoneNumber());
        preparedStatement.setString(6, user.getBotState().name());
        preparedStatement.executeUpdate();
    }

    @Override
    public User findByChatId(Long chatId) throws SQLException {

        String SELECT_FROM_BY_CHAT_ID = "SELECT * FROM users where chat_id = '" + chatId + "'";
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_BY_CHAT_ID);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return new User(
                    resultSet.getLong("id"),
                    resultSet.getLong("chat_id"),
                    resultSet.getString("firstname"),
                    resultSet.getString("lastname"),
                    resultSet.getString("username"),
                    resultSet.getString("phone_number"),
                    BotState.fromString(resultSet.getString("bot_state")));
        }
        return null;
    }

    @Override
    public void update(User user) throws SQLException {
        String UPDATE_BY_ID_USER = "UPDATE users SET firstname = ?, lastname = ?, username = ?, bot_state = ? WHERE id = " + user.getId();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BY_ID_USER);
        preparedStatement.setString(1, user.getFirstname());
        preparedStatement.setString(2, user.getLastname());
        preparedStatement.setString(3, user.getUsername());
        preparedStatement.setString(4, user.getBotState().name());
        preparedStatement.executeUpdate();
    }
}
