package repositories;

import config.PostgresConnectionProvider;
import models.AnonymousComment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentRepository {
    public List<AnonymousComment> findAll() {
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "select * from comment_table"
            );

            ResultSet resultSet = statement.executeQuery();
            List<AnonymousComment> comments = new ArrayList<>();

            while (resultSet.next()) {
                AnonymousComment comment = AnonymousComment.builder()
                        .id(resultSet.getLong("id"))
                        .text(resultSet.getString("text"))
                        .productID(resultSet.getLong("product_id"))
                        .build();
                comments.add(comment);
            }

            return comments;

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void save(AnonymousComment comment) {
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "insert into comment_table (text, product_id) values (?, ?)"
            );

            statement.setString(1, comment.getText());
            statement.setLong(2,  comment.getProductID());

            statement.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void delete(Long id) {
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "delete from comment_table where id = ?"
            );

            statement.setLong(1, id);

            statement.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
