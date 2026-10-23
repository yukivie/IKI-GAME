
public abstract class GameCharacter {
    protected String name;

    public GameCharacter(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract String attack();

    public abstract String specialMove();
}