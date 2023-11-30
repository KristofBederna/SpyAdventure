package spyAdventure.common.Tiles;

public class Door extends Tile{
    private String color;
    public Door(String color) {
        this.color = color;
        this.collision = true;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
}
