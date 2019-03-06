import org.newdawn.slick.SlickException;

/**
 * This class inherits from Sprite, which represents ExtraLife.
 */
public class ExtraLife extends Sprite {
	// default direction is left->right
	private boolean direction = true;
	private int moveIntervalTime = 0;
	private static final int EXTRALIFE_MOVEINTERVAL = 2;

	/**
	 * Constructor method, inherit from Sprite class.
	 * @param imageSrc The image file path.
	 * @param x The central x-coordinate of the image.
	 * @param y The central y-coordinate of the image.
	 * @throws SlickException
	 */
	public ExtraLife(String imageSrc, float x, float y) throws SlickException {
		super(imageSrc, x, y);
	}

	@Override
	public void moveAlong(int delta, Sprite other) {

		moveIntervalTime += delta;
		// every 2 seconds the extraLife move a tile
		if (moveIntervalTime >= EXTRALIFE_MOVEINTERVAL * 1000) {
			this.moveTile(other);
			moveIntervalTime = 0;
		}
		//if move from left to right
		if (other.getObjectDirection() == true) {		
			if (other.getX() >= (0 - other.getWidth() / 2)
					&& other.getX() <= (App.SCREEN_WIDTH + other.getWidth() / 2)) {
				this.setX((float) (this.getX() + delta * other.getSpeed()));
				this.getSpriteBox().setX(this.getX());
			}
			if (other.getX() > (App.SCREEN_WIDTH + other.getWidth() / 2) || other.getX() < (0 - other.getWidth() / 2)) {
				this.setX(0 - other.getWidth() / 2 + this.getX() - other.getX());
				this.getSpriteBox().setX(this.getX());
			}
		}
		//if move from right to left
		if (other.getObjectDirection() == false) {
			if (other.getX() >= (0 - other.getWidth() / 2)
					&& other.getX() <= (App.SCREEN_WIDTH + other.getWidth() / 2)) {
				this.setX((float) (this.getX() - delta * other.getSpeed()));
				this.getSpriteBox().setX(this.getX());
			}
			if (other.getX() > (App.SCREEN_WIDTH + other.getWidth() / 2) || other.getX() < (0 - other.getWidth() / 2)) {
				this.setX(App.SCREEN_WIDTH + other.getWidth() / 2 + this.getX() - other.getX());
				this.getSpriteBox().setX(this.getX());
			}

		}
	}
	
	/**
	 * Self-move one tile every call.
	 * @param other The Sprite class object.
	 */
	public void moveTile(Sprite other) {
		//if direction is left to right
		if (this.direction == true) {			
			if (this.getSpriteBox().getRight() < other.getSpriteBox().getRight()) {				
				this.setX(this.getX() + World.PIXEL);
				this.getSpriteBox().setX(this.getX());
			} else {
				this.direction = false;
			}
		}
		//if direction is right to left
		if (this.direction == false) {
			if (this.getSpriteBox().getLeft() > other.getSpriteBox().getLeft()) {
				this.setX(this.getX() - World.PIXEL);
				this.getSpriteBox().setX(this.getX());
			} else {
				this.direction = true;
			}
		}

	}
}
