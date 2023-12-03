import spyAdventure.view.GameFrame;
import spyAdventure.view.GamePanel;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        new GameFrame(new GamePanel());
        //TODO: Make doors individually openable
        //Make doors update from collision manager using the new function to update cell in tile manager
    }
}