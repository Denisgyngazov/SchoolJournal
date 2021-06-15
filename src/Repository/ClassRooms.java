package Repository;

import Model.ClassRoom;
import Model.Teacher;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ClassRooms extends BaseTable implements TableOperations<ClassRoom> {
    public ClassRooms() {
        super("class_rooms");
    }

    @Override
    public void createTable() throws SQLException {
        super.executeSQL("CREATE TABLE IF NOT EXISTS class_rooms(" +
                "id BIGINT PRIMARY KEY," +
                "name NVARCHAR(255) NOT NULL)","Создана таблица" + tableName);
    }

    @Override
    public void createForeignKeys() throws SQLException {
        super.executeSQL("ALTER TABLE pupils_in_class_rooms ADD FOREIGN KEY (idClassRooms) REFERENCES class_rooms (id)",
                "Создан вшений ключ class_rooms.id <- pupils_in_class_rooms.idClassRooms");
    }

    @Override
    public ClassRoom save(ClassRoom obj) throws SQLException {
        try(PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO class_rooms VALUES (?,?)")) {
            preparedStatement.setLong(1, obj.getId());
            preparedStatement.setString(2, obj.getName());
            preparedStatement.executeUpdate();
            System.out.println("Запись добавлена" + " " + obj.getId() + " " + obj.getName());
        }
        return obj;
    }

    @Override
    public ClassRoom getById(ClassRoom obj) throws SQLException {
        try(PreparedStatement preparedStatement = connection.prepareStatement("SELECT c.id, c.name FROM class_rooms AS c " +
                "WHERE c.id = ?")) {
            preparedStatement.setLong(1,obj.getId());
            preparedStatement.executeUpdate();
        }
        return obj;
    }

    public ClassRoom getClassForTeacher(Teacher teacher) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT c.id, c.name FROM class_rooms AS c " +
                "INNER JOIN pupils_in_class_rooms AS in ON c.id = in.idClassRooms " +
                "INNER JOIN teachers AS t ON t.id = in.idTeachers " +
                "WHERE t.surname = ?")) {
            preparedStatement.setString(1,teacher.getSurname());
            ResultSet resultSet = preparedStatement.executeQuery();
            ClassRoom classRoom = null;
            if (resultSet.next()) {
                classRoom = new ClassRoom(resultSet.getInt(1),
                        resultSet.getString("name"));
                System.out.println(classRoom.getName());
            }
            return classRoom;
        }
    }

    public List<ClassRoom> selectValue() throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT c.id,c.name FROM class_rooms AS c " +
                "INNER JOIN pupils_in_class_rooms AS in ON c.id = in.idClassRooms ")) {
            //preparedStatement.setString(1, "2A");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<ClassRoom> classRooms = new LinkedList<>();
            while (resultSet.next()) {
                ClassRoom classRoom = new ClassRoom(resultSet.getInt(1), resultSet.getString("name"));
                classRooms.add(classRoom);
                System.out.println(classRoom.getName());
            }
            return classRooms;
        }
    }
}



