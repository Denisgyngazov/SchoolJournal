package Repository;

import Model.ClassRoom;
import Model.Pupil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class Pupils extends BaseTable implements TableOperations<Pupil> {
    public Pupils() {
        super("pupils");
    }

    @Override
    public void createTable() throws SQLException {
        super.executeSQL(
                "CREATE TABLE IF NOT EXISTS pupils(" +
                "id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY," +
                "name NVARCHAR(255) NOT NULL," +
                "surname NVARCHAR(255) NOT NULL," +
                "CONSTRAINT uc UNIQUE (id,name,surname))",
                "Создана таблица " + tableName);
    }

    public void createForeignKeys() throws SQLException {
        super.executeSQL("ALTER TABLE pupils_in_class_rooms ADD FOREIGN KEY (idPupils) REFERENCES pupils (id)",
                "Создан вшений ключ pupils.id <- pupils_in_class_rooms.idPupils");
    }

    @Override
    public Pupil save(Pupil obj) throws SQLException {
        if(obj.getId() == 0) {
            try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO pupils (name,surname) VALUES (?,?)")) {
                preparedStatement.setString(1, obj.getName());
                preparedStatement.setString(2, obj.getSurname());
                preparedStatement.executeUpdate();
                System.out.println("Запись добавлена" + " " + obj.getName() + " " + obj.getSurname());
            }
        }
            else {
            try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE pupils SET name = ?, surname = ? WHERE id = ?")) {
                preparedStatement.setString(1, obj.getName());
                preparedStatement.setString(2, obj.getSurname());
                preparedStatement.setLong(3, obj.getId());
                preparedStatement.executeUpdate();
                System.out.println("Запись обновлена");
            }
        }
        return obj;
    }

    @Override
    public Pupil getById(Pupil obj) throws SQLException {
        try(PreparedStatement preparedStatement = connection.prepareStatement("SELECT p.id, p.name, p.surname FROM pupils AS p " +
                "WHERE p.id = ?")) {
            preparedStatement.setLong(1,obj.getId());
            preparedStatement.executeUpdate();

        }
        return obj;
    }

    public List<Pupil> selectValue() throws SQLException {
        ResultSet resultSet = super.executeSqlResultSet("SELECT p.id,p.name,p.surname FROM pupils AS p ");
        List<Pupil> pupils = new LinkedList<>();
        while (resultSet.next()) { ;
            Pupil pupil = new Pupil (resultSet.getInt(1), resultSet.getString("name"), resultSet.getString("surname"));
            pupils.add(pupil);
            System.out.println(pupil.getId() + " " + pupil.getName() + " " + pupil.getSurname() + " ");

        }
        return pupils;
    }

    public Pupil getPupilForClass(ClassRoom classRoom) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT p.id, p.name, p.surname FROM pupils AS p " +
                "INNER JOIN pupils_in_class_rooms AS in ON p.id = in.idPupils " +
                "INNER JOIN class_rooms AS c ON c.id = in.idClassRooms " +
                "WHERE c.name = ?")) {
            preparedStatement.setString(1,classRoom.getName());
            ResultSet resultSet = preparedStatement.executeQuery();
            Pupil pupil = null;
            while (resultSet.next()) {
                pupil = new Pupil(resultSet.getInt(1),
                        resultSet.getString("name"),
                        resultSet.getString("surname"));
                System.out.println(pupil.getName() + " " + pupil.getSurname() + " ");
            }
            return pupil;
        }
    }
}

