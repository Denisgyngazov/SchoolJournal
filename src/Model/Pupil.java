package Model;

public class Pupil extends BaseModel {

    private long id;
    private final String name;
    private final String surname;

    public Pupil(int id,String name, String surname)  {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public Pupil(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public void setId(long id) {
        this.id = id;
    }
}
