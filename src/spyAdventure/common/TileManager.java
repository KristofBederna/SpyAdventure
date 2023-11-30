package spyAdventure.common;

import spyAdventure.view.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    Tile[] tiles;
    int[][] mapNums;

    public TileManager() {
        tiles = new Tile[30];
        getTileImage();
        mapNums = new int[16][12];
        loadMap("/Assets/Map1.txt");
    }

    public void getTileImage() {
        try {

            //Floors
            tiles[0] = new Floor();
            tiles[0].setImage(ImageIO.read(getClass().getResourceAsStream("/Assets/Tiles/Floor/basicTile.png")));
            tiles[1] = new Floor();
            tiles[1].setImage(ImageIO.read(getClass().getResourceAsStream("/Assets/Tiles/Floor/cautionTile.png")));
            tiles[2] = new Floor();
            tiles[2].setImage(ImageIO.read(getClass().getResourceAsStream("/Assets/Tiles/Floor/labTile.png")));
            tiles[3] = new Floor();
            tiles[3].setImage(ImageIO.read(getClass().getResourceAsStream("/Assets/Tiles/Floor/smallLabTile.png")));
            tiles[4] = new Floor();
            tiles[4].setImage(ImageIO.read(getClass().getResourceAsStream("/Assets/Tiles/Floor/woodTile.png")));
            tiles[5] = new Floor();
            tiles[5].setImage(ImageIO.read(getClass().getResourceAsStream("/Assets/Tiles/Floor/carpetTile.png")));

            //Walls
            tiles[6] = new Wall();
            tiles[6].setImage(ImageIO.read(getClass().getResourceAsStream("/Assets/Tiles/Wall/bottomWallTile.png")));
            tiles[7] = new Wall();
            tiles[7].setImage(ImageIO.read(getClass().getResourceAsStream("/Assets/Tiles/Wall/leftBottomWallTile.png")));
            tiles[8] = new Wall();
            tiles[8].setImage(ImageIO.read(getClass().getResourceAsStream("/Assets/Tiles/Wall/leftTopWallTile.png")));
            tiles[9] = new Wall();
            tiles[9].setImage(ImageIO.read(getClass().getResourceAsStream("/Assets/Tiles/Wall/leftWallTile.png")));
            tiles[10] = new Wall();
            tiles[10].setImage(ImageIO.read(getClass().getResourceAsStream("/Assets/Tiles/Wall/RightBottomWallTile.png")));
            tiles[11] = new Wall();
            tiles[11].setImage(ImageIO.read(getClass().getResourceAsStream("/Assets/Tiles/Wall/RightTopWallTile.png")));
            tiles[12] = new Wall();
            tiles[12].setImage(ImageIO.read(getClass().getResourceAsStream("/Assets/Tiles/Wall/RightWallTile.png")));
            tiles[13] = new Wall();
            tiles[13].setImage(ImageIO.read(getClass().getResourceAsStream("/Assets/Tiles/Wall/topWallTile.png")));

            //Doors
            tiles[14] = new Door();
            tiles[14].setImage(ImageIO.read(getClass().getResourceAsStream("/Assets/Tiles/Door/doorClosedBlueKeycardFront.png")));
            tiles[15] = new Door();
            tiles[15].setImage(ImageIO.read(getClass().getResourceAsStream("/Assets/Tiles/Door/doorClosedBlueKeycardLeft.png")));
            tiles[16] = new Door();
            tiles[16].setImage(ImageIO.read(getClass().getResourceAsStream("/Assets/Tiles/Door/doorClosedBlueKeycardRight.png")));
            tiles[17] = new Door();
            tiles[17].setImage(ImageIO.read(getClass().getResourceAsStream("/Assets/Tiles/Door/doorClosedGreenKeycardFront.png")));
            tiles[18] = new Door();
            tiles[18].setImage(ImageIO.read(getClass().getResourceAsStream("/Assets/Tiles/Door/doorClosedGreenKeycardLeft.png")));
            tiles[19] = new Door();
            tiles[19].setImage(ImageIO.read(getClass().getResourceAsStream("/Assets/Tiles/Door/doorClosedGreenKeycardRight.png")));
            tiles[20] = new Door();
            tiles[20].setImage(ImageIO.read(getClass().getResourceAsStream("/Assets/Tiles/Door/doorClosedKeypadFront.png")));
            tiles[21] = new Door();
            tiles[21].setImage(ImageIO.read(getClass().getResourceAsStream("/Assets/Tiles/Door/doorClosedKeypadLeft.png")));
            tiles[22] = new Door();
            tiles[22].setImage(ImageIO.read(getClass().getResourceAsStream("/Assets/Tiles/Door/doorClosedKeypadRight.png")));
            tiles[23] = new Door();
            tiles[23].setImage(ImageIO.read(getClass().getResourceAsStream("/Assets/Tiles/Door/doorClosedRedKeycardFront.png")));
            tiles[24] = new Door();
            tiles[24].setImage(ImageIO.read(getClass().getResourceAsStream("/Assets/Tiles/Door/doorClosedRedKeycardLeft.png")));
            tiles[25] = new Door();
            tiles[25].setImage(ImageIO.read(getClass().getResourceAsStream("/Assets/Tiles/Door/doorClosedRedKeycardRight.png")));
            tiles[26] = new Door();
            tiles[26].setCollision(false);
            tiles[26].setImage(ImageIO.read(getClass().getResourceAsStream("/Assets/Tiles/Door/doorOpenFront.png")));
            tiles[27] = new Door();
            tiles[27].setCollision(false);
            tiles[27].setImage(ImageIO.read(getClass().getResourceAsStream("/Assets/Tiles/Door/doorOpenSide.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D Graphics2D) {
        for (int i = 0; i < Globals.MAX_TILES_WIDTH; i++) {
            for (int j = 0; j < Globals.MAX_TILES_HEIGHT; j++) {
                Graphics2D.drawImage(tiles[mapNums[i][j]].getImage(), i*48, j*48, Globals.SCALED_TILE_SIZE, Globals.SCALED_TILE_SIZE, null);
            }
        }
    }
    public void loadMap(String path) {
        try {
            InputStream IS = getClass().getResourceAsStream(path);
            BufferedReader BR = new BufferedReader(new InputStreamReader(IS));

            for (int i = 0; i < Globals.MAX_TILES_HEIGHT; i++) {
                String line = BR.readLine();
                String numbers[] = line.split(" ");
                for (int j = 0; j < Globals.MAX_TILES_WIDTH; j++) {
                    int num = Integer.parseInt(numbers[j]);
                    mapNums[j][i] = num;
                 }
            }
            BR.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
