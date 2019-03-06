import org.newdawn.slick.SlickException;

/**
 * This class inherits from RideObject, which represents Logs and LongLogs.
 */
public class Logs extends RideObject{

	/**
	 * Constructor method, inherit from RideObject class
	 * @param imageSrc The image file path.
	 * @param x The central x-coordinate of the image.
	 * @param y The central y-coordinate of the image.
	 * @param objectDirection The default moving direction of the image.
	 * @param speed The moving speed of the image.
	 * @throws SlickException 
	 */
	public Logs(String imageSrc, float x, float y, boolean objectDirection, double speed) throws SlickException {
		super(imageSrc, x, y, objectDirection, speed);
		// TODO Auto-generated constructor stub
	}

}
