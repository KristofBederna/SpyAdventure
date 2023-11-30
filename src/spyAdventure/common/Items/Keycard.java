package spyAdventure.common.Items;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Keycard extends Item {
    private String color;
    public Keycard(String color) {
        name = color+"_Keycard";
        this.color = color;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Assets/Items/Keycards/"+name+".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
