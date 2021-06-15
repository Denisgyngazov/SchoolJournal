package Repository;

import Services.DataBase;

import java.sql.*;

abstract class BaseTable  {

    protected Connection connection;
    protected String tableName;

    BaseTable(String tableName) {
        this.tableName = tableName;
    }

    void reopenConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DataBase.getConnection();
        }
    }

    void executeSQL(String sql, String description) throws SQLException {
        reopenConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
            System.out.println(description);
        }
    }

    ResultSet executeSqlResultSet(String sql) throws SQLException {
        reopenConnection();
        Statement statement = connection.createStatement();
        return statement.executeQuery(sql);
    }
}
