package spyAdventure.model;

import spyAdventure.common.Globals;
import spyAdventure.common.Tiles.Door;
import spyAdventure.common.Tiles.Floor;
import spyAdventure.common.Tiles.Tile;
import spyAdventure.common.Tiles.Wall;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Objects;

public class TileManager {
    private final Tile[] tiles;
    private final int[][] intMap;
    private int currentMap = 10;

    //Constructor
    public TileManager() {
        tiles = new Tile[31];
        setTiles();
        intMap = new int[Globals.MAX_TILES_WIDTH][Globals.MAX_TILES_HEIGHT];
        loadMap(currentMap);
    }

    //Setup
    private void setTiles() {
        try {

            //Floors
            tiles[0] = new Floor();
            tiles[0].setImage("/Assets/Tiles/Floor/basicTile.png");
            tiles[1] = new Floor();
            tiles[1].setImage("/Assets/Tiles/Floor/cautionTile.png");
            tiles[2] = new Floor();
            tiles[2].setImage("/Assets/Tiles/Floor/labTile.png");
            tiles[3] = new Floor();
            tiles[3].setImage("/Assets/Tiles/Floor/smallLabTile.png");
            tiles[4] = new Floor();
            tiles[4].setImage("/Assets/Tiles/Floor/woodTile.png");
            tiles[5] = new Floor();
            tiles[5].setImage("/Assets/Tiles/Floor/carpetTile.png");

            //Walls
            tiles[6] = new Wall();
            tiles[6].setImage("/Assets/Tiles/Wall/bottomWallTile.png");
            tiles[7] = new Wall();
            tiles[7].setImage("/Assets/Tiles/Wall/leftBottomWallTile.png");
            tiles[8] = new Wall();
            tiles[8].setImage("/Assets/Tiles/Wall/leftTopWallTile.png");
            tiles[9] = new Wall();
            tiles[9].setImage("/Assets/Tiles/Wall/leftWallTile.png");
            tiles[10] = new Wall();
            tiles[10].setImage("/Assets/Tiles/Wall/RightBottomWallTile.png");
            tiles[11] = new Wall();
            tiles[11].setImage("/Assets/Tiles/Wall/RightTopWallTile.png");
            tiles[12] = new Wall();
            tiles[12].setImage("/Assets/Tiles/Wall/RightWallTile.png");
            tiles[13] = new Wall();
            tiles[13].setImage("/Assets/Tiles/Wall/topWallTile.png");
            tiles[28] = new Wall();
            tiles[28].setImage("/Assets/Tiles/Wall/glassWallTile.png");

            //Doors
            tiles[14] = new Door("blue");
            tiles[14].setImage("/Assets/Tiles/Door/doorClosedBlueKeycardFront.png");
            tiles[15] = new Door("blue");
            tiles[15].setImage("/Assets/Tiles/Door/doorClosedBlueKeycardLeft.png");
            tiles[16] = new Door("blue");
            tiles[16].setImage("/Assets/Tiles/Door/doorClosedBlueKeycardRight.png");
            tiles[17] = new Door("green");
            tiles[17].setImage("/Assets/Tiles/Door/doorClosedGreenKeycardFront.png");
            tiles[18] = new Door("green");
            tiles[18].setImage("/Assets/Tiles/Door/doorClosedGreenKeycardLeft.png");
            tiles[19] = new Door("green");
            tiles[19].setImage("/Assets/Tiles/Door/doorClosedGreenKeycardRight.png");
            tiles[20] = new Door("");
            tiles[20].setImage("/Assets/Tiles/Door/doorClosedKeypadFront.png");
            tiles[21] = new Door("");
            tiles[21].setImage("/Assets/Tiles/Door/doorClosedKeypadLeft.png");
            tiles[22] = new Door("");
            tiles[22].setImage("/Assets/Tiles/Door/doorClosedKeypadRight.png");
            tiles[23] = new Door("red");
            tiles[23].setImage("/Assets/Tiles/Door/doorClosedRedKeycardFront.png");
            tiles[24] = new Door("red");
            tiles[24].setImage("/Assets/Tiles/Door/doorClosedRedKeycardLeft.png");
            tiles[25] = new Door("red");
            tiles[25].setImage("/Assets/Tiles/Door/doorClosedRedKeycardRight.png");
            tiles[26] = new Door("open");
            tiles[26].setCollision(false);
            tiles[26].setImage("/Assets/Tiles/Door/doorOpenFront.png");
            tiles[27] = new Door("open");
            tiles[27].setCollision(false);
            tiles[27].setImage("/Assets/Tiles/Door/doorOpenSide.png");
            tiles[29] = new Door("open");
            tiles[29].setImage("/Assets/Tiles/Door/doorOpenSide.png");
            tiles[29].setCollision(true);
            tiles[30] = new Door("open");
            tiles[30].setImage("/Assets/Tiles/Door/doorOpenFront.png");
            tiles[30].setCollision(true);
        } catch (IOException ignored) { }
    }
    public void draw(Graphics2D Graphics2D) {
        for (int i = 0; i < Globals.MAX_TILES_WIDTH; i++) {
            for (int j = 0; j < Globals.MAX_TILES_HEIGHT; j++) {
                Graphics2D.drawImage(tiles[intMap[i][j]].getImage(), i*48, j*48, Globals.SCALED_TILE_SIZE, Globals.SCALED_TILE_SIZE, null);
            }
        }
    }
    public void loadMap(int index) {
        try {
            InputStream IS = getClass().getResourceAsStream("/Assets/IntMaps/Map"+index+".txt");
            BufferedReader BR = new BufferedReader(new InputStreamReader(IS));

            for (int i = 0; i < Globals.MAX_TILES_HEIGHT; i++) {
                String line = BR.readLine();
                String[] numbers = line.split("\t");
                for (int j = 0; j < Globals.MAX_TILES_WIDTH; j++) {
                    int num = Integer.parseInt(numbers[j]);
                    intMap[j][i] = num;
                 }
            }
            BR.close();
        } catch (Exception ignored) { }
    }

    //Getters
    public int[][] getIntMap() {
        return intMap;
    }
    public Tile[] getTiles() {
        return tiles;
    }

    public int getCurrentMap() {
        return currentMap;
    }

    //Setters
    public void setCurrentMap(int currentMap) {
        this.currentMap = currentMap;
    }

    public void updateCell(int row, int column, int changeTo) {
        intMap[row][column] = changeTo;
    }
}
