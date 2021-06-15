package Model;

public class Teacher extends BaseModel {
    private final long id;
    private final String name;
    private final String surname;
    private final String discipline;

    public Teacher(long id, String name, String surname, String discipline) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.discipline = discipline;
    }

    public long getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getDiscipline() {
        return discipline;
    }

}
