import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * This class inherits from VehicleObject, which represents Bikes
 */
public class Bike extends VehicleObject {

	private float objectX;

	/**
	 * Constructor method, inherit from VehicleObject class
	 * @param imageSrc The image file path.
	 * @param x The central x-coordinate of the image.
	 * @param y The central y-coordinate of the image.
	 * @param objectDirection The default moving direction of the image.
	 * @param speed The moving speed of the image.
	 * @throws SlickException 
	 */
	public Bike(String imageSrc, float x, float y, boolean objectDirection, double speed) throws SlickException {
		super(imageSrc, x, y, objectDirection, speed);
	}

	@Override
	public void update(Input input, int delta) {
		objectX = this.getX();
		// if true, move left to right
		if (this.getObjectDirection() == true) {
			if (objectX >= 24 && objectX < 1000) {
				this.setX((float) (objectX + delta * this.getSpeed()));
				this.getSpriteBox().setX(this.getX());
			} else if (objectX >= 1000) {
				this.setX(1000);
				this.setObjectDirection(false);
			}

		}
		// if false, move right to left
		else if (this.getObjectDirection() == false) {
			if (objectX > 24 && objectX <= 1000) {
				this.setX((float) (objectX - delta * this.getSpeed()));
				this.getSpriteBox().setX(this.getX());
			} else if (objectX <= 24) {
				this.setX(24);
				this.setObjectDirection(true);
			}
		}
	}

}
