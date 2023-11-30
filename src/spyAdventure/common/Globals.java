package spyAdventure.common;

import java.awt.*;

public class Globals {
    final static private int TILE_SIZE = 16;
    final static private int SCALE = 3;
    final static public int SCALED_TILE_SIZE = TILE_SIZE*SCALE; // 48x48 tile
    final static public int MAX_TILES_WIDTH = 16;
    final static public int MAX_TILES_HEIGHT = 12;
    final static public int SCREEN_WIDTH = MAX_TILES_WIDTH*SCALED_TILE_SIZE; // 768px
    final static public int SCREEN_HEIGHT = MAX_TILES_HEIGHT*SCALED_TILE_SIZE; // 576px
    final static public int FPS = 60;
    final static public Font UI_FONT = new Font("Arial", Font.BOLD, 16);

}
