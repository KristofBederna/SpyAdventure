import spyAdventure.view.GameFrame;
import spyAdventure.view.GamePanel;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        new GameFrame(new GamePanel());
        //TODO: When a door is opened it is always the 27 orientation that it changes to, make sure that orientation is passed in the method
    }
}