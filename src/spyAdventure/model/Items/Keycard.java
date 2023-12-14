package spyAdventure.model.Items;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Keycard extends Item {
    private final String color;

    //Constructor
    public Keycard(String color) {
        name = color+"_Keycard";
        this.color = color;
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Assets/Items/Keycards/" + name + ".png")));
        } catch (IOException ignored) {

        }
    }

    //Getters
    public String getColor() {
        return color;
    }
}
