import org.newdawn.slick.SlickException;
import utilities.BoundingBox;

/**
 * This class inherits from VehicleObject, which represents Bulldozer
 */
public class Bulldozer extends VehicleObject{
	
	/**
	 * Constructor method, inherit from VehicleObject class
	 * @param imageSrc The image file path.
	 * @param x The central x-coordinate of the image.
	 * @param y The central y-coordinate of the image.
	 * @param objectDirection The default moving direction of the image.
	 * @param speed The moving speed of the image.
	 * @throws SlickException 
	 */
	public Bulldozer(String imageSrc, float x, float y, boolean objectDirection, double speed) throws SlickException {
		super(imageSrc, x, y, objectDirection, speed);
	}
	
	@Override
	public boolean isContactSprite(Sprite other) {		
		BoundingBox otherBox = new BoundingBox(other.getX(),
				other.getY(), other.getWidth(), other.getHeight());
		return this.getSpriteBox().rightIntersects(otherBox);
	}
}
