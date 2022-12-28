package repositories;

import config.PostgresConnectionProvider;
import models.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductRepository {

    // language=SQL
    private static final String SQL_SAVE_PRODUCT =
            "insert into product_table (name, description, image, imgname, price) VALUES (?, ?, ?, ?, ?)";

    // language=SQL
    private static final String SQL_FIND_ALL_PRODUCTS =
            "select * from product_table";

    // language=SQL
    private static final String SQL_FIND_BY_ID =
            "select * from product_table where id = ?";

    // language=SQL
    private static final String SQL_UPDATE_PRODUCT =
            "update product_table set name = ?, description = ?, image = ?, imgname = ?, price = ? where id = ?";

    public void update(Product product) {
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_PRODUCT);

            statement.setString(1, product.getName());
            statement.setString(2, product.getDescription());
            statement.setBytes(3, product.getImage());
            statement.setString(4, product.getImageName());
            statement.setInt(5, product.getPrice());
            statement.setLong(6, product.getId());

            statement.execute();

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void save(Product product) {
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQL_SAVE_PRODUCT);
            statement.setString(1, product.getName());
            statement.setString(2, product.getDescription());
            statement.setBytes(3, product.getImage());
            statement.setString(4, product.getImageName());
            statement.setInt(5, product.getPrice());

            statement.execute();

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public List<Product> findAll() {
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_PRODUCTS);
            ResultSet resultSet = statement.executeQuery();
            List<Product> list = new ArrayList<>();

            while (resultSet.next()) {
                Product product = Product.builder()
                        .id(resultSet.getLong("id"))
                        .name(resultSet.getString("name"))
                        .description(resultSet.getString("description"))
                        .image(resultSet.getBytes("image"))
                        .imageName(resultSet.getString("imgname"))
                        .price(resultSet.getObject("price", Integer.class))
                        .build();

                list.add(product);
            }

            return list;

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public Optional<Product> findById(Long id) {
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Product product = Product.builder()
                        .id(resultSet.getLong("id"))
                        .name(resultSet.getString("name"))
                        .description(resultSet.getString("description"))
                        .image(resultSet.getBytes("image"))
                        .imageName(resultSet.getString("imgname"))
                        .price(resultSet.getObject("price", Integer.class))
                        .build();

                return Optional.of(product);
            }

            return Optional.empty();

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void delete(Long id) {
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "delete from product_table where id = ?"
            );
            statement.setLong(1, id);

            statement.execute();

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
