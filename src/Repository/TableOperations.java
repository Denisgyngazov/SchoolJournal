package Repository;

import java.sql.SQLException;

public interface TableOperations<T> {
    void createTable() throws SQLException;
    void createForeignKeys() throws SQLException;
    T save(T obj) throws SQLException;
    T getById(T obj) throws SQLException;
}
