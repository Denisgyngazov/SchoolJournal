package Model;

public class ClassRoom extends BaseModel {
    private final long id;
    private final String name;

    public ClassRoom(long id ,String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
