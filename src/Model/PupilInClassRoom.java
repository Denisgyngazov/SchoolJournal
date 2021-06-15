package Model;

public class PupilInClassRoom extends BaseModel {
    private final long id;
    private final long pupilID;
    private final long classRoomID;
    private final long teacherID;

    public PupilInClassRoom(long id, long pupilID, long classRoomID, long teacherID) {
        this.id = id;
        this.pupilID = pupilID;
        this.classRoomID = classRoomID;
        this.teacherID = teacherID;
    }

    public long getId() {
        return id;
    }

    public long getPupilID() { return pupilID; }

    public long getClassRoomID() { return classRoomID; }

    public long getTeacherID() { return teacherID; }
}
