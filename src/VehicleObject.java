import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * This class inherits from Sprite, which represents all the Vehicle,
 * including Car, Bike and Bulldozer.
 */
public abstract class VehicleObject extends Sprite {

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
	public VehicleObject(String imageSrc, float x, float y, boolean objectDirection, double speed)
			throws SlickException {
		super(imageSrc, x, y, objectDirection, speed);
	}

	@Override
	public void update(Input input, int delta) {
		objectX = this.getX();
		// if true, move left to right
		if (this.getObjectDirection() == true){
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
}
