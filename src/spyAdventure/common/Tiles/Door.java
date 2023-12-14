package spyAdventure.common.Tiles;

public class Door extends Tile{
    private final String color;

    //Constructor
    public Door(String color) {
        this.color = color;
        this.collision = true;
    }

    //Getters
    public String getColor() {
        return color;
    }
}
