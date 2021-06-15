package Repository;

import Model.PupilInClassRoom;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PupilsInClassRooms extends BaseTable implements TableOperations<PupilInClassRoom> {
    public PupilsInClassRooms() {
        super("pupils_in_class_rooms");
    }

    @Override
    public void createTable() throws SQLException {
        super.executeSQL(
                "CREATE TABLE IF NOT EXISTS pupils_in_class_rooms (" +
                        "id BIGINT PRIMARY KEY," +
                        "idPupils BIGINT," +
                        "idClassRooms BIGINT," +
                        "idTeachers BIGINT)",
                "Создана таблица " + tableName);
    }

    @Override
    public void createForeignKeys() throws SQLException {

    }

    @Override
    public PupilInClassRoom save(PupilInClassRoom obj) throws SQLException {
        try(PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO pupils_in_class_rooms VALUES(?,?,?,?)")) {
            preparedStatement.setLong(1,obj.getId());
            preparedStatement.setLong(2,obj.getPupilID());
            preparedStatement.setLong(3,obj.getClassRoomID());
            preparedStatement.setLong(4,obj.getTeacherID());
            preparedStatement.executeUpdate();
            System.out.println("Запись добавлена" + " " + obj.getId() + " " + obj.getPupilID() + " " + obj.getClassRoomID() + " " + obj.getTeacherID());
        }
        return obj;
    }

    @Override
    public PupilInClassRoom getById(PupilInClassRoom obj) throws SQLException {
        try(PreparedStatement preparedStatement = connection.prepareStatement("SELECT in.id, in.idPupils, in.idClassRooms, in.idTeachers " +
                "WHERE in.id = ?")) {
            preparedStatement.setLong(1,obj.getId());
            preparedStatement.executeUpdate();
        }
        return obj;
    }

//    public void selectValue() throws SQLException {
//        ResultSet resultSet = super.executeSqlValue("SELECT p.name,p.surname,t.name,t.surname,t.discipline,c.name FROM pupils_in_class_rooms AS r" +
//                " INNER JOIN pupils AS p ON p.id = r.idPupils " +
//                "INNER JOIN teachers AS t ON t.id = r.idClassRooms " +
//                "INNER JOIN class_rooms AS c ON c.id = r.idTeachers");
//        while (resultSet.next()) {
//            PupilInClassRoom pupilInClassRoom = new PupilInClassRoom(
//                    resultSet.getInt(1),
//                    resultSet.getInt(2),
//                    resultSet.getInt(3)
//                    ,resultSet.getInt(4));
//            System.out.println(pupilInClassRoom.getID() + " " + pupilInClassRoom.getPupilID() + " " + pupilInClassRoom.getClassRoomID() + " " + pupilInClassRoom.getIdTeacher());
//       }
//    }
}
