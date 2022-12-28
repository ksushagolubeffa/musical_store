package repositories;

import config.PostgresConnectionProvider;
import models.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository {

    // language=SQL
    private static final String SQL_FIND_ADD_ORDERS =
            "select * from order_table";

    // language=SQL
    private static final String SQL_SAVE_ORDER =
            "insert into order_table (user_id, product_id, is_executed) values (?, ?, ?)";

    // language=SQL
    private static final String SQL_DELETE_ORDER =
            "delete from order_table where id = ?";
    public void save(Order order) {
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQL_SAVE_ORDER);
            statement.setLong(1, order.getUserID());
            statement.setLong(2, order.getProductID());
            statement.setBoolean(3, order.isExecuted());

            statement.execute();

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void delete(Long id) {
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE_ORDER);
            statement.setLong(1, id);

            statement.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
    public List<Order> findAll() {
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_ADD_ORDERS);
            ResultSet resultSet = statement.executeQuery();

            List<Order> list = new ArrayList<>();

            while (resultSet.next()) {
                Order order = Order.builder()
                        .id(resultSet.getLong("id"))
                        .productID(resultSet.getLong("product_id"))
                        .userID(resultSet.getLong("user_id"))
                        .build();
                list.add(order);
            }

            return list;

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
