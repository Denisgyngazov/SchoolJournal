package Services;

import Repository.ClassRooms;
import Repository.Pupils;
import Repository.PupilsInClassRooms;
import Repository.Teachers;

import java.sql.*;

final public class DataBase {
    public static final  String DB_URL = "jdbc:h2:/C:/Users/GDU/Desktop/SchoolJournal/db/onedb";
    public static final  String DB_Driver = "org.h2.Driver";

    ClassRooms classRoomsTable;
    Pupils pupilsTable;
    Teachers teachersTable;
    PupilsInClassRooms pupilsInClassRoomsTable;

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public DataBase() throws SQLException, ClassNotFoundException {
        Class.forName(DB_Driver);
        initialTables();
        createTables();
        addForeignKeys();
    }

    private void initialTables() {
        classRoomsTable = new ClassRooms();
        pupilsTable = new Pupils();
        teachersTable = new Teachers();
        pupilsInClassRoomsTable = new PupilsInClassRooms();
    }

    private void createTables() throws SQLException {
        classRoomsTable.createTable();
        pupilsTable.createTable();
        teachersTable.createTable();
        pupilsInClassRoomsTable.createTable();
    }

    private void addForeignKeys() throws SQLException {
        classRoomsTable.createForeignKeys();
        pupilsTable.createForeignKeys();
        teachersTable.createForeignKeys();
    }
}
