package Services;

import Model.ClassRoom;
import Model.Pupil;
import Model.PupilInClassRoom;
import Model.Teacher;

import java.sql.SQLException;

 final public class TableObject {
    DataBase dataBase;

    public TableObject(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    Pupil onePupil = new Pupil(1,"Oleg22345", "Dawvidow");
    Pupil twoPupil = new Pupil(2,"Olesia", "Kisel");
    Pupil threePupil = new Pupil(3,"Danila", "Bik");
    ClassRoom oneClassRoom = new ClassRoom(1, "2A");
    PupilInClassRoom onePupilInClassRoom = new PupilInClassRoom(1,1,1,1);
    PupilInClassRoom twoPupilInClassRoom = new PupilInClassRoom(2,2,1,1);
    PupilInClassRoom threePupilInClassRoom = new PupilInClassRoom(3,3,1,1);
    Teacher oneTeacher = new Teacher(1,"Alla","Aranova","Russian");

    public void run() throws SQLException {
        insertValues();
        printValues();
    }

    private void insertValues() throws SQLException {
      // dataBase.classRoomsTable.save(oneClassRoom);
       dataBase.pupilsTable.save(onePupil);
       dataBase.pupilsTable.save(twoPupil);
//       dataBase. pupilsTable.save(twoPupil);
//       dataBase.pupilsTable.save(threePupil);
      // dataBase.teachersTable.save(oneTeacher);
      // dataBase.pupilsInClassRoomsTable.save(onePupilInClassRoom);
//       dataBase.pupilsInClassRoomsTable.save(twoPupilInClassRoom);
//       dataBase.pupilsInClassRoomsTable.save(threePupilInClassRoom);
    }

    private void printValues() throws SQLException {
        dataBase.pupilsTable.selectValue();
//        classRoomsTable.selectValue();
//        teachersTable.selectValue();
//        teachersTable.selectTeacher();
//        pupilsTable.selectPupil();
      //  dataBase.pupilsTable.getPupilForClass(dataBase.classRoomsTable.getClassForTeacher(dataBase.teachersTable.searchTeacher(1)));
    }
}
