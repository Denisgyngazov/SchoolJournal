package Repository;

import Model.Teacher;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Teachers extends BaseTable implements TableOperations<Teacher> {
    public Teachers() {
        super("teachers");
    }

    @Override
    public void createTable() throws SQLException {
        super.executeSQL(
                "CREATE TABLE IF NOT EXISTS teachers(" +
                        "id BIGINT PRIMARY KEY," +
                        "name NVARCHAR(255) NOT NULL," +
                        "surname NVARCHAR(255) NOT NULL," +
                        "discipline NVARCHAR(255) NOT NULL)",
                "Создана таблица " + tableName);
    }

    @Override
    public void createForeignKeys() throws SQLException {
        super.executeSQL("ALTER TABLE pupils_in_class_rooms ADD FOREIGN KEY (idTeachers) REFERENCES teachers (id)",
                "Создан вшений ключ teachers.id <- pupils_in_class_rooms.idTeachers");
    }

    @Override
    public Teacher save(Teacher obj) throws SQLException {
        try(PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO teachers VALUES(?,?,?,?)")) {
            preparedStatement.setLong(1,obj.getID());
            preparedStatement.setString(2,obj.getName());
            preparedStatement.setString(3,obj.getSurname());
            preparedStatement.setString(4,obj.getDiscipline());
            preparedStatement.executeUpdate();
            System.out.println("Запись добавлена" + " " + obj.getID() + " " + obj.getName() + " " + obj.getSurname() + " " + obj.getDiscipline() );
        }
            return obj;
    }

    @Override
    public Teacher getById(Teacher obj) throws SQLException {
        try(PreparedStatement preparedStatement = connection.prepareStatement("SELECT t.id, t.name, t.surname, t.discipline " +
                "WHERE t.id = ?")) {
            preparedStatement.setLong(1,obj.getID());
            preparedStatement.executeUpdate();

        }
        return obj;
    }

    public Teacher searchTeacher(long search) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT t.id,t.name,t.surname,t.discipline FROM teachers AS t " +
                "WHERE t.id = ?")) {
            Teacher teacher = null;
            preparedStatement.setLong(1,search);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                 teacher = new Teacher(resultSet.getInt(1),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getString("discipline"));
                System.out.println(teacher.getName() + " " + teacher.getSurname() + " " + teacher.getDiscipline());;
            }
            return teacher;
        }
    }

//    public List<Teacher> selectValue() throws SQLException {
//        ResultSet resultSet = super.executeSqlValue("SELECT t.id,t.name,t.surname,t.discipline FROM teachers AS t " +
//                "INNER JOIN pupils_in_class_rooms AS in ON t.id = in.idTeachers");
//        List<Teacher> teachers = new LinkedList<>();
//        while (resultSet.next()) {
//            Teacher teacher = new Teacher(resultSet.getInt(1),
//                    resultSet.getString("name"),
//                    resultSet.getString("surname"),
//                    resultSet.getString("discipline"));
//            teachers.add(teacher);
//            System.out.println(teacher.getName() + " " + teacher.getSurname() + " " + teacher.getDiscipline());
//        }
//        return teachers;
//    }
}
