package repositories;

import config.PostgresConnectionProvider;
import models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    // language=SQL
    private static final String SQL_SAVE_USER = "insert into users_table (login, password, balance) values (?, ?, ?)";

    // language=SQL
    private static final String SQL_FIND_ALL_USERS = "select * from users_table";

    // language=SQL
    private static final String SQL_UPDATE =
            "update users_table set balance = ? where id = ?";

    public void save(User user) {
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQL_SAVE_USER);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setInt(3, user.getBalance());

            statement.execute();

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public List<User> findAll() {
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_USERS);
            ResultSet resultSet = statement.executeQuery();
            List<User> list = new ArrayList<>();

            while (resultSet.next()) {
                User user = User.builder()
                        .id(resultSet.getLong("id"))
                        .login(resultSet.getString("login"))
                        .password(resultSet.getString("password"))
                        .role(resultSet.getString("role"))
                        .balance(resultSet.getObject("balance", Integer.class))
                        .build();

                list.add(user);
            }

            return list;

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void update(User user) {
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE);
            statement.setInt(1, user.getBalance());
            statement.setLong(2, user.getId());

            statement.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
