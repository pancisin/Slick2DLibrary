package System;

import Game.AbstractActor;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


/**
 *
 * @author Patrik
 */
public class Map extends AbstractActor {

    int width, height, tileWidth, tileHeight;
    private final Image  tileset = new Image("resources/levels/tileset00.png");

    public Map(String f) throws FileNotFoundException, IOException, SlickException {

        BufferedReader reader = new BufferedReader(new FileReader(f));
        String line;
        while ((line = reader.readLine()) != null) {
            switch (line) {
                case "[header]":
                    width = getValue(reader.readLine());
                    height = getValue(reader.readLine());
                    tileWidth = getValue(reader.readLine());
                    tileHeight = getValue(reader.readLine());
                    break;

                case "[layer]":
                    String[] part = reader.readLine().split("=");
                    if ("background".equals(part[1])) {
                        reader.readLine();

                        String[] i = reader.readLine().split(",");
                        for (int x = 0; x < width; x++) {
                            Image tile = tileset.getSubImage(x, 0, tileWidth, tileHeight);
                            tile.getGraphics().drawImage(tile, 20, 20);
                            System.out.println("vykreslene");
                        } 
                    }
                    break;

                default:
                    break;
            }
        }
    }

    private int getValue(String s) {
        String[] part = s.split("=");
        return Integer.parseInt(part[1]);
    }

    @Override
    public void act() {
    }
}
