import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import utilities.BoundingBox;

/**
 * This class inherits from Sprite, which represents all the RideObjects,
 * including Logs, LongLogs and Turtles.
 */
public abstract class RideObject extends Sprite {

	private float objectX;

	/**
	 * Constructor method, inherit from Sprite class
	 * 
	 * @param imageSrc        The image file path.
	 * @param x               The central x-coordinate of the image.
	 * @param y               The central y-coordinate of the image.
	 * @param objectDirection The default moving direction of the image.
	 * @param speed           The moving speed of the image.
	 * @throws SlickException
	 */
	public RideObject(String imageSrc, float x, float y, boolean objectDirection, double speed) throws SlickException {
		super(imageSrc, x, y, objectDirection, speed);
	}

	@Override
	public void update(Input input, int delta) {
		objectX = this.getX();
		// if true, move left to right
		if (this.getObjectDirection() == true) {
			if (objectX >= (0 - this.getWidth() / 2) && objectX <= (App.SCREEN_WIDTH + this.getWidth() / 2)) {
				this.setX((float) (objectX + delta * this.getSpeed()));
				this.getSpriteBox().setX(this.getX());
			}

			if (objectX > (App.SCREEN_WIDTH + this.getWidth() / 2) || objectX < (0 - this.getWidth() / 2)) {
				this.setX(0 - this.getWidth() / 2);
				this.getSpriteBox().setX(this.getX());
			}
		}
		// if false, move right to left
		if (this.getObjectDirection() == false) {
			if (objectX >= (0 - this.getWidth() / 2) && objectX <= (App.SCREEN_WIDTH + this.getWidth() / 2)) {
				this.setX((float) (objectX - delta * this.getSpeed()));
				this.getSpriteBox().setX(this.getX());
			}
			if (objectX > (App.SCREEN_WIDTH + this.getWidth() / 2) || objectX < (0 - this.getWidth() / 2)) {
				this.setX(App.SCREEN_WIDTH + this.getWidth() / 2);
				this.getSpriteBox().setX(this.getX());
			}
		}
	}

	@Override
	public boolean isContactSprite(Sprite other) {

		BoundingBox otherBox = new BoundingBox(other.getX(), other.getY(), other.getWidth(), other.getHeight());
		if (this.getSpriteBox().isOtherBoxInMyRange(otherBox)) {
			return true;
		} else {
			return false;
		}
	}

}
