package controllers.listeners;

import config.PostgresConnectionProvider;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebListener
public class ContextInitListener implements ServletContextListener {
    // language=SQL
    private static final String SQL_CREATE_USER_TABLE =
            "create table if not exists users_table (" +
                    "id bigserial primary key," +
                    "login varchar(100)," +
                    "password varchar(200)," +
                    "role varchar(20) default 'user'," +
                    "balance integer)";

    // language=SQL
    private static final String SQL_CREATE_PRODUCT_TABLE =
            "create table if not exists product_table (" +
                    "id bigserial primary key," +
                    "name varchar(500)," +
                    "description varchar(2000)," +
                    "image bytea," +
                    "imgName varchar(500)," +
                    "price integer)";

    // language=SQL
    private static final String SQL_CREATE_ORDER_TABLE =
            "create table if not exists order_table (" +
                    "id bigserial primary key," +
                    "user_id bigint references users_table(id)," +
                    "product_id bigint references product_table(id)," +
                    "is_executed boolean default false)";

    // language=SQL
    private static final String SQL_CREATE_COMMENT_TABLE =
            "create table if not exists comment_table (" +
                    "id bigserial primary key," +
                    "text varchar(2000)," +
                    "product_id bigint references product_table(id))";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            String[] tables = new String[] {
                    SQL_CREATE_USER_TABLE,
                    SQL_CREATE_PRODUCT_TABLE,
                    SQL_CREATE_ORDER_TABLE,
                    SQL_CREATE_COMMENT_TABLE
            };

            PreparedStatement statement;
            for (String table : tables) {
                statement = connection.prepareStatement(table);
                statement.execute();
            }

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
