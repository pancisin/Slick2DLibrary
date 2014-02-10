package System;

import Gui.Game;
import org.newdawn.slick.*;

public class SlickTest {

    static String title = "FirstGame";
    static boolean fullscreen = false;
    static boolean showFPS = true;
    static int width = 1024;
    static int height = 768;
    static int fpslimit = 60;

    public static void main(String[] args) throws SlickException {

        AppGameContainer app = new AppGameContainer(new Game(title));
        app.setDisplayMode(width, height, fullscreen);
        app.setSmoothDeltas(true);
        app.setTargetFrameRate(fpslimit);
        app.setShowFPS(showFPS);
        app.start();
        
        
    }

}
