import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * This class represents and handle all the single elements in the game world.
 */
public class World {
	// some constants for the game
	/** The width of a tile. */
	public static final float PIXEL = 48;
	/** The init x-coordinate of frog. */
	public static final float X_FROG = 512;
	/** The init y-coordinate of frog. */
	public static final float Y_FROG = 720;
	/** The width from the screen edge. */
	public static final float EDGE = X_FROG % PIXEL;
	private static final float BUS_SPEED = (float) 0.15;
	private static final float CAR_SPEED = (float) 0.5;
	private static final float BIKE_SPEED = (float) 0.2;
	private static final float BULLDOZER_SPEED = (float) 0.05;
	private static final float LOG_SPEED = (float) 0.1;
	private static final float LONGLOG_SPEED = (float) 0.07;
	private static final float TURTLES_SPEED = (float) 0.085;
	private static final float X_LIVES = 24;
	private static final float Y_LIVES = 744;
	private static final float OFFSET_LIVES = 32;
	private static final int OFFESET_TREEHOLES = 120;
	private static final int INITLIVES = 3;
	private static final int TREEHOLES = 5;
	private static final int TIME1 = 25;
	private static final int TIME2 = 35;
	private static final int EXTRALIFE_LASTTIME = 14;
	private static final int TURTLE_LASTTIME = 7;
	private static final int TURTLE_INTERVAL = 2;

	private String text;
	private static String[] parseText;
	private int[] countHoles = new int[TREEHOLES];
	private ArrayList<Sprite> element = new ArrayList<Sprite>();
	private ArrayList<Lives> lives = new ArrayList<Lives>();
	private ArrayList<Score> score = new ArrayList<Score>();
	private ArrayList<ExtraLife> extraLife = new ArrayList<ExtraLife>();
	private ArrayList<Integer> logIndex = new ArrayList<Integer>();
	private Frog frog;
	private int countScores = 0;
	private Random random = new Random();
	private int randomTime = 0;
	private int randomLog = 0;
	private int extraLifeAppearTime = 0;
	private int extraLifeDisappearTime = 0;
	private int turtleDisappearTime = 0;
	private boolean isTurtleVisible = true;

	/**
	 * Constructor: handle file parsing, time randomizing and all the initialize.
	 * @param filePath The .csv file path.
	 * @throws IOException
	 * @throws NumberFormatException
	 * @throws SlickException
	 */
	public World(String filePath) throws IOException, NumberFormatException, SlickException {

		// random a time interval at the beginning
		randomTime = setTimeInterval(TIME1, TIME2);

		// open and read and parse file.CSV
		BufferedReader reader = new BufferedReader(new FileReader(filePath));

		while ((text = reader.readLine()) != null) {
			parseText = text.split(",");
			// initialize elements in the file
			if (parseText[0].equals("water")) {
				element.add(
						new Water("assets/water.png", Float.parseFloat(parseText[1]), Float.parseFloat(parseText[2])));
			} else if (parseText[0].equals("grass")) {
				element.add(
						new Grass("assets/grass.png", Float.parseFloat(parseText[1]), Float.parseFloat(parseText[2])));
			} else if (parseText[0].equals("tree")) {
				element.add(
						new Tree("assets/tree.png", Float.parseFloat(parseText[1]), Float.parseFloat(parseText[2])));
			} else if (parseText[0].equals("bus")) {
				element.add(new Car("assets/bus.png", Float.parseFloat(parseText[1]), Float.parseFloat(parseText[2]),
						Boolean.parseBoolean(parseText[3]), BUS_SPEED));
			} else if (parseText[0].equals("bulldozer")) {
				element.add(new Bulldozer("assets/bulldozer.png", Float.parseFloat(parseText[1]),
						Float.parseFloat(parseText[2]), Boolean.parseBoolean(parseText[3]), BULLDOZER_SPEED));
			} else if (parseText[0].equals("log")) {
				element.add(new Logs("assets/log.png", Float.parseFloat(parseText[1]), Float.parseFloat(parseText[2]),
						Boolean.parseBoolean(parseText[3]), LOG_SPEED));
			} else if (parseText[0].equals("longLog")) {
				element.add(new Logs("assets/longlog.png", Float.parseFloat(parseText[1]),
						Float.parseFloat(parseText[2]), Boolean.parseBoolean(parseText[3]), LONGLOG_SPEED));
			} else if (parseText[0].equals("bike")) {
				element.add(new Bike("assets/bike.png", Float.parseFloat(parseText[1]), Float.parseFloat(parseText[2]),
						Boolean.parseBoolean(parseText[3]), BIKE_SPEED));
			} else if (parseText[0].equals("racecar")) {
				element.add(new Car("assets/racecar.png", Float.parseFloat(parseText[1]),
						Float.parseFloat(parseText[2]), Boolean.parseBoolean(parseText[3]), CAR_SPEED));
			} else if (parseText[0].equals("turtle")) {
				element.add(new Turtle("assets/turtles.png", Float.parseFloat(parseText[1]),
						Float.parseFloat(parseText[2]), Boolean.parseBoolean(parseText[3]), TURTLES_SPEED));
			}
		}
		// close file
		reader.close();

		// initialize Lives
		for (int i = 0; i < INITLIVES; i++) {
			lives.add(new Lives("assets/lives.png", X_LIVES + i * OFFSET_LIVES, Y_LIVES));
		}
		// initialize Frog
		frog = new Frog("assets/frog.png", X_FROG, Y_FROG);
		// initialize Score
		for (int i = 0; i < TREEHOLES; i++) {
			score.add(new Score("assets/frog.png", OFFESET_TREEHOLES + 4 * i * PIXEL, PIXEL));
		}
		// count numbers of logs 
		countLog();
	}

	/**
	 * Update all the sprites in the game and handle timing.
	 * @param input The Input class object.
	 * @param delta Time passed since last frame (milliseconds).
	 * @throws SlickException
	 */
	public void update(Input input, int delta) throws SlickException {

		extraLifeAppearTime += delta;
		extraLifeDisappearTime += delta;
		turtleDisappearTime += delta;
		// after time interval, pick a random log
		if (extraLifeAppearTime >= randomTime * 1000) {
			pickRandomLog();
			extraLifeAppearTime = -EXTRALIFE_LASTTIME * 1000;
		}
		// after exist time, remove the extraLife
		if (extraLifeDisappearTime >= (randomTime + EXTRALIFE_LASTTIME) * 1000) {
			extraLife.remove(extraLife.size() - 1);
			extraLifeDisappearTime = 0;
			//when extraLife disappear, reselect a random time
			randomTime = setTimeInterval(TIME1, TIME2);
		}
		// handle "eat" extraLife logic
		if (!extraLife.isEmpty()) {
			if (frog.isContactSprite(extraLife.get(0))) {
				extraLife.remove(extraLife.size() - 1);
				this.addLife();
				extraLifeAppearTime = 0;
				extraLifeDisappearTime = 0;
				//when extraLife was eaten, reselect a random time
				randomTime = setTimeInterval(TIME1, TIME2);
			}
		}

		// update all of the elements in the game
		for (Sprite sprite : element) {
			sprite.update(input, delta);
		}
		// update frog
		frog.update(input, delta);

		// handle Vehicle collision logic
		for (Sprite sprite : element) {
			if (sprite instanceof Car || sprite instanceof Bike) {
				if (sprite.isContactSprite(frog)) {
					this.reduceLife();
				}
			}
		}

		// handle move along logic
		for (Sprite sprite : element) {
			// make sure frog not enter into Tree and Bulldozer
			if (sprite instanceof Tree || sprite instanceof Bulldozer) {
				frog.cannotEnter(sprite);
			}
			// let frog move along with Bulldozer
			if (sprite instanceof Bulldozer) {
				if (sprite.isContactSprite(frog)) {
					frog.moveAlong(delta, sprite);
					// check if been pushed by the screen
					if (frog.isTouchScreen()) {
						reduceLife();
					}
				}
			}
			// let frog and extraLife move along with Riders
			if (sprite instanceof Logs) {
				if (sprite.isContactSprite(frog)) {
					frog.moveAlong(delta, sprite);
				}
				if (!extraLife.isEmpty()) {
					if (sprite.isContactSprite(extraLife.get(0))) {
						extraLife.get(0).moveAlong(delta, sprite);
					}
				}
			}
			if (sprite instanceof Turtle) {
				if (sprite.isContactSprite(frog) && isTurtleVisible == true) {
					frog.moveAlong(delta, sprite);
				}
			}
		}
		// handle on water logic
		fallInWater();

		// Halt the game if remaining lives are empty
		if (lives.isEmpty()) {
			System.out.println("Sorry! Game Over!!");
			System.exit(0);
		}
	}

	/**
	 * Render the entire screen, so it reflects the current game state.
     * @param g The Slick graphics object, used for drawing.
     */
	public void render(Graphics g) {
		// render every element in the file
		for (Sprite sprite : element) {
			if (!(sprite instanceof Turtle)) {
				sprite.render();
			}
			// handle turtle render logic
			else if (turtleDisappearTime >= 0 && turtleDisappearTime <= TURTLE_LASTTIME * 1000) {
				isTurtleVisible = true;
				sprite.render();
			} else if (turtleDisappearTime > TURTLE_LASTTIME * 1000) {
				isTurtleVisible = false;
				turtleDisappearTime = -TURTLE_INTERVAL * 1000;
			}
		}
		// render lives
		for (Lives life : lives) {
			life.render();
		}
		// handle winning logic and render scores
		for (int i = 0; i < score.size(); i++) {
			if (frog.iscontactTreeHole(score.get(i))) {
				// if the hole has not been filled, get one score
				if (countHoles[i] != 1) {
					countHoles[i] = 1;
					countScores += 1;
					resetPosition();
				}
				// else the hole has been filled, lose one life
				else {
					reduceLife();
				}
			}
			if (countHoles[i] == 1) {
				score.get(i).render();
			}
		}
		// render extraLife
		if (!extraLife.isEmpty()) {
			extraLife.get(0).render();
		}
		// render frog
		frog.render();
	}

	/* method: reduce a life */
	private void reduceLife() {
		lives.remove(lives.size() - 1);
		resetPosition();
	}

	/* method: add a life */
	private void addLife() throws SlickException {
		lives.add(new Lives("assets/lives.png", X_LIVES + lives.size() * OFFSET_LIVES, Y_LIVES));
	}

	/* method: reset frog position */
	private void resetPosition() {
		frog.setX(X_FROG);
		frog.setY(Y_FROG);
		frog.getSpriteBox().setX(X_FROG);
		frog.getSpriteBox().setY(Y_FROG);
	}

	/* method: set time interval between 25-35s */
	private int setTimeInterval(int time1, int time2) {
		randomTime = random.nextInt(time2) % (time2 - time1 + 1) + time1;
		System.out.println("time interval is£º" + randomTime);
		return randomTime;
	}

	/* method: count the number of logs */
	private int countLog() {
		for (int i = 0; i < element.size(); i++) {
			if (element.get(i) instanceof Logs) {
				logIndex.add(i);
			}
		}
		return logIndex.size();
	}

	/* method: pick a random log and new the extraLife */
	private void pickRandomLog() throws SlickException {
		randomLog = random.nextInt(logIndex.size() - 1) % (logIndex.size() - 1 - 0 + 1) + 0;
		extraLife.add(new ExtraLife("assets/extralife.png", element.get(logIndex.get(randomLog)).getX(),
				element.get(logIndex.get(randomLog)).getY()));
	}
	
	/* method: on water logic */
	private void fallInWater() {
		// only when frog contact Water and Riders at the same time then it alive
		for (Sprite sprite1 : element) {
			if (frog.isContactSprite(sprite1)) {
				if (sprite1 instanceof Water) {
					for (Sprite sprite2 : element) {
						if (frog.isContactSprite(sprite2)) {
							if (sprite2 instanceof Logs || sprite2 instanceof Turtle && isTurtleVisible == true) {
								return;
							}
						}
					}
					lives.remove(lives.size() - 1);
					resetPosition();
				}
			}
		}
	}
	
	/**
	 * Getter: acquire current scores
	 * @return current scores
	 */
	public int getCountScores() {
		return this.countScores;
	}

}
