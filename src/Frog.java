import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import utilities.BoundingBox;

/**
 * This class inherits from Sprite, which represents the player: Frog.
 */
public class Frog extends Sprite {

	// set a flag: for solidable
	private int keyPressDirection = 0;
	private static final float TREEHOLE_WIDTH = 96;
	private static final float TREEHOLE_HEIGHT = 48;

	/**
	 * Constructor method, inherit from Sprite class.
	 * @param imageSrc The image file path.
	 * @param x The central x-coordinate of the image.
	 * @param y The central y-coordinate of the image.
	 * @throws SlickException
	 */
	public Frog(String imageSrc, float x, float y) throws SlickException {
		super(imageSrc, x, y);
	}

	@Override
	public void update(Input input, int delta) {
		// move 48 pixels when key pressed
		if (input.isKeyPressed(Input.KEY_UP)) {
			this.setY(this.getY() - this.getHeight());
			this.getSpriteBox().setY(this.getY());
			keyPressDirection = 1;
		}
		if (input.isKeyPressed(Input.KEY_DOWN)) {
			this.setY(this.getY() + this.getHeight());
			this.getSpriteBox().setY(this.getY());
			keyPressDirection = 2;
		}
		if (input.isKeyPressed(Input.KEY_LEFT)) {
			this.setX(this.getX() - this.getWidth());
			this.getSpriteBox().setX(this.getX());
			keyPressDirection = 3;
		}
		if (input.isKeyPressed(Input.KEY_RIGHT)) {
			this.setX(this.getX() + this.getWidth());
			this.getSpriteBox().setX(this.getX());
			keyPressDirection = 4;
		}

		// make sure frog not out of the screen
		if (this.getY() < World.PIXEL) {
			this.setY(World.PIXEL);
			this.getSpriteBox().setY(this.getY());
		}
		if (this.getX() < World.EDGE) {
			this.setX(World.EDGE);
			this.getSpriteBox().setX(this.getX());
		}
		if (this.getY() >= App.SCREEN_HEIGHT) {
			this.setY(World.Y_FROG);
			this.getSpriteBox().setY(this.getY());
		}
		if (this.getX() > (App.SCREEN_WIDTH - World.EDGE)) {
			this.setX((App.SCREEN_WIDTH - World.EDGE));
			this.getSpriteBox().setX(this.getX());
		}
	}

	/**
	 * Make sure frog cannot enter into other Sprite
	 * @param other The Sprite class object.
	 */
	public void cannotEnter(Sprite other) {
		// press Up
		if (this.getSpriteBox().intersects(other.getSpriteBox()) && keyPressDirection == 1) {
			this.setY(this.getY() + this.getHeight());
			this.getSpriteBox().setY(this.getY());
		}
		// press down
		if (this.getSpriteBox().intersects(other.getSpriteBox()) && keyPressDirection == 2) {
			this.setY(this.getY() - this.getHeight());
			;
			this.getSpriteBox().setY(this.getY());
		}
		// press left
		if (this.getSpriteBox().intersects(other.getSpriteBox()) && keyPressDirection == 3) {
			this.setX(this.getX() + this.getWidth());
			this.getSpriteBox().setX(this.getX());
		}
		// press right
		if (this.getSpriteBox().intersects(other.getSpriteBox()) && keyPressDirection == 4) {
			this.setX(this.getX() - this.getWidth());
			this.getSpriteBox().setX(this.getX());
		}
	}

	@Override
	public void moveAlong(int delta, Sprite other) {

		if (other.getObjectDirection() == true) {
			if (this.getX() > World.EDGE && this.getX() < (App.SCREEN_WIDTH - World.EDGE)) {
				this.setX((float) (this.getX() + delta * other.getSpeed()));
				this.getSpriteBox().setX(this.getX());
			}
		}

		if (other.getObjectDirection() == false) {
			if (this.getX() > World.EDGE && this.getX() < (App.SCREEN_WIDTH - World.EDGE)) {
				this.setX((float) (this.getX() - delta * other.getSpeed()));
				this.getSpriteBox().setX(this.getX());
			}
		}
	}
	
	/**
	 * Check if enter into tree hole
	 * @param other The Sprite class object.
	 * @return true if the frog contact a tree hole.
	 */
	public boolean iscontactTreeHole(Sprite other) {
		BoundingBox otherBox = new BoundingBox(other.getX(), other.getY(), TREEHOLE_WIDTH, TREEHOLE_HEIGHT);	
		return this.getSpriteBox().intersects(otherBox);				
	}
	
	/**
	 * Check if touch screen edge.
	 * @return true if the frog contact the screen edge.
	 */
	public boolean isTouchScreen() {
		return this.getX() >= (App.SCREEN_WIDTH - World.EDGE) || this.getX() <= World.EDGE;
	}

}
