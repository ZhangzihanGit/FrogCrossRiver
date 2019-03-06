import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import utilities.BoundingBox;

/**
 * This class represents a single sprite in the game.
 */
public abstract class Sprite {

	private Image sprite;
	private BoundingBox spriteBox;
	private float x;
	private float y;	
	private boolean objectDirection;
	private float speed;

	/**
	 * Constructor1: setup the static object.
	 * @param imageSrc The image file path.
	 * @param x The central x-coordinate of the image.
	 * @param y The central y-coordinate of the image.
	 * @throws SlickException
	 */
	public Sprite(String imageSrc, float x, float y) throws SlickException {

		sprite = new Image(imageSrc);
		spriteBox = new BoundingBox(x, y, this.getWidth(), this.getHeight());
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Constructor2: setup the self-moving object.
	 * @param imageSrc The image file path.
	 * @param x The central x-coordinate of the image.
	 * @param y The central y-coordinate of the image.
	 * @param objectDirection The default moving direction of the image.
	 * @param speed The moving speed of the image.
	 * @throws SlickException
	 */
	public Sprite(String imageSrc, float x, float y,
			boolean objectDirection, double speed) throws SlickException {

		sprite = new Image(imageSrc);
		spriteBox = new BoundingBox(x, y, this.getWidth(), this.getHeight());
		this.x = x;
		this.y = y;
		this.objectDirection = objectDirection;
		this.speed = (float) speed;
	}

	/**
	 * Update the sprite state for a frame
	 * @param input The Input class object.
	 * @param delta Time passed since last frame (milliseconds).
	 */
	public void update(Input input, int delta) { }
	
	/**
	 * let the sprite move along with others
	 * @param delta Time passed since last frame (milliseconds).
	 * @param other Other Sprite object.
	 */
	public void moveAlong(int delta, Sprite other) { }

	/**
	 * draw the sprite.
	 */
	public void render() {
		sprite.drawCentered(x, y);
	}

	/**
	 * Check if it contacts with other Sprite
	 * @param other The Sprite class object.
	 * @return true if it contacts with other.
	 */
	public boolean isContactSprite(Sprite other) {	
		BoundingBox otherBox = new BoundingBox(other.getX(), other.getY(),
				other.getWidth(), other.getHeight());	
		if (spriteBox.intersects(otherBox)) {
			return true;
		}else {
			return false;
		}
	}

	/**
	 * Setter: set x-coordinate.
	 * @param x The central x-coordinate of the image. 
	 */
	public void setX(float x) {
		this.x = x;
	}
	
	/**
	 * Setter: set y-coordinate.
	 * @param y The central y-coordinate of the image.
	 */
	public void setY(float y) {
		this.y = y;
	}
	
	/**
	 * Setter: set object direction.
	 * @param direction The direction of the sprite.
	 */
	public void setObjectDirection(boolean direction) {
		this.objectDirection = direction;
	}

	/**
	 * Getter: acquire x-coordinate. 
	 * @return central x-coordinate.
	 */
	public float getX() {
		return x;
	}

	/**
	 * Getter: acquire y-coordinate. 
	 * @return central y-coordinate.
	 */
	public float getY() {
		return y;
	}

	/**
	 * Getter: acquire this Image object. 
	 * @return Image class object itself.
	 */
	public Image getSprite() {
		return sprite;
	}

	/**
	 * Getter: acquire the width of this sprite. 
	 * @return width of the sprite.
	 */
	public float getWidth() {
		return sprite.getWidth();
	}

	/**
	 * Getter: acquire the height of this sprite. 
	 * @return height of the sprite.
	 */
	public float getHeight() {
		return sprite.getHeight();
	}

	/**
	 * Getter: acquire this BoundingBox object. 
	 * @return BoundingBox class object.
	 */
	public BoundingBox getSpriteBox() {
		return this.spriteBox;
	}
	
	/**
	 * Getter: acquire the direction of the object. 
	 * @return direction of the sprite.
	 */
	public boolean getObjectDirection() {
		return this.objectDirection;
	}
	
	/**
	 * Getter: acquire the speed of the object. 
	 * @return speed of the sprite.
	 */
	public float getSpeed() {
		return this.speed;
	}
}
