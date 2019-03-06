import java.io.IOException;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Input;

/**
 * Main class for the game.
 * Handles initialisation, input and rendering.
 */
public class App extends BasicGame {
    /** screen width, in pixels */
    public static final int SCREEN_WIDTH = 1024;
    /** screen height, in pixels */
    public static final int SCREEN_HEIGHT = 768;
    public static final int INITLEVEL = 0;
    private static final String LEVEL0 = "assets/levels/0.lvl";
    private static final String LEVEL1 = "assets/levels/1.lvl";
    private World worldLevel0;
    private World worldLevel1;

    /**
     * Constructor method for App class.
     */
    public App() {
        super("Shadow Leap");
    }

    @Override
    /**
     * Handle initialization logic, new the game world.
     * @param gc The Slick game container object.
     */
    public void init(GameContainer gc)throws SlickException {
    	try {
			worldLevel0 = new World(LEVEL0);
			worldLevel1 = new World(LEVEL1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
    }

    @Override
    public void update(GameContainer gc, int delta)
            throws SlickException {
        // Get data about the current input (keyboard state).
        Input input = gc.getInput();
        
        //if get 5 scores then enter into the Level2
        if(worldLevel0.getCountScores() == 5) {
        	worldLevel1.update(input, delta);
        }else {
        	worldLevel0.update(input, delta);
        }
        //if get 5 scores in Level2 then end game
        if(worldLevel1.getCountScores() == 5) {
        	System.out.println("Congratulations! You Win!!");
        	System.exit(0);
        }
        
    }

    @Override
    public void render(GameContainer gc, Graphics g)
            throws SlickException {
    	
    	if(worldLevel0.getCountScores() == 5) {
    		worldLevel1.render(g);
    	}else {
    		worldLevel0.render(g);
    	}
    }

    /** Start-up method. Creates the game and runs it.
     * @param args Command-line arguments (ignored).
     */
    public static void main(String[] args)
            throws SlickException {
        AppGameContainer app = new AppGameContainer(new App());
        app.setShowFPS(false);
        app.setDisplayMode(SCREEN_WIDTH, SCREEN_HEIGHT, false);
        app.start();
    }

}