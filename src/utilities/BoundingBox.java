/**
 * BoundingBox complete class for SWEN20003: Object Oriented Software Development 2018
 * by Eleanor McMurtry, University of Melbourne
 */
package utilities;
import org.newdawn.slick.Image;

public class BoundingBox {
	private static final float FUZZ = 0.95f;
	private static final float MARGIN = (float) 1.5;
	private static final float HALFTILE = 24;

	private float left;
	private float top;
	private float width;
	private float height;
	private float x;
	private float y;

	/**
	 * Constructor1: initialize the boundingBox object according to the width and height.
	 * @param x The central x-coordinate of the image.
	 * @param y The central y-coordinate of the image.
	 * @param width The width of the image.
	 * @param height The height of the image.
	 */
	public BoundingBox(float x, float y, float width, float height) {
		setWidth(width);
		setHeight(height);
		setX(x);
		setY(y);
		this.x = x;
		this.y = y;
	}

	/**
	 * Constructor2: initialize the boundingBox object according to the image.
	 * @param img The image object.
	 * @param x The central x-coordinate of the image.
	 * @param y The central y-coordinate of the image.
	 */
	public BoundingBox(Image img, float x, float y) {
		setWidth(img.getWidth());
		setHeight(img.getHeight());
		setX(x);
		setY(y);		
	}

	/**
	 * Constructor3: initialize the boundingBox object according to the boundingBox object.
	 * @param bb The boundingBox object.
	 */
	public BoundingBox(BoundingBox bb) {
		width = bb.width;
		height = bb.height;
		left = bb.left;
		top = bb.top;
	}

	/**
	 * Setter: set the x-coordinate of the left edge.
	 * @param x The central x-coordinate of the image.
	 */
	public void setX(float x) {
		left = x - width / 2;
	}

	/**
	 * Setter: set the y-coordinate of the top edge.
	 * @param y The central y-coordinate of the image.
	 */
	public void setY(float y) {
		top = y - height / 2;
	}

	/**
	 * Setter: set the width of the box.
	 * @param w The width of the image.
	 */
	public void setWidth(float w) {
		width = w * FUZZ;
	}

	/**
	 * Setter: set the height of the box.
	 * @param h The height of the image.
	 */
	public void setHeight(float h) {
		height = h * FUZZ;
	}

	/**
	 * Getter: acquire x-coordinate of the left edge.
	 * @return x-coordinate of left edge.
	 */
	public float getLeft() {
		return left;
	}

	/**
	 * Getter: acquire y-coordinate of the top edge.
	 * @return y-coordinate of top edge.
	 */
	public float getTop() {
		return top;
	}

	/**
	 * Getter: acquire x-coordinate of the right edge.
	 * @return x-coordinate of right edge.
	 */
	public float getRight() {
		return left + width;
	}

	/**
	 * Getter: acquire y-coordinate of the bottom edge.
	 * @return y-coordinate of bottom edge.
	 */
	public float getBottom() {
		return top + height;
	}

	/**
	 * Getter: acquire width of the boundingBox.
	 * @return width of the box.
	 */
	public float getWidth() {
		return width;
	}

	/**
	 * Getter: acquire height of the boundingBox.
	 * @return height of the box.
	 */
	public float getHeight() {
		return height;
	}
	
	/**
	 * Getter: acquire x-coordinate.
	 * @return central x-coordinate.
	 */
	public float getX() {
		return this.x;
	}
	
	/**
	 * Getter: acquire y-coordinate.
	 * @return central y-coordinate.
	 */
	public float getY() {
		return this.y;
	}

	/**
	 * Check if two sprite contact with each other.
	 * @param other The boundingBox object.
	 * @return True if the two box contacts.
	 */
	public boolean intersects(BoundingBox other) {
		return !(other.left > getRight() || other.getRight() < left || other.top > getBottom()
				|| other.getBottom() < top);
	}

	/**
	 * Check if frog's left contact other Sprite.
	 * @param other The boundingBox object.
	 * @return True if the frog sprite's left edge contact with other's right edge.
	 */
	public boolean rightIntersects(BoundingBox other) {
		return (Math.abs((other.left - getRight())) <= MARGIN && this.getY() == other.getY());
	}

	/**
	 * Check if otherBox in this Box's range
	 * @param other The boundbingBox object.
	 * @return True if other sprite's box is in the range of other's box.
	 */
	public boolean isOtherBoxInMyRange(BoundingBox other) {
		if (other.getRight() - getLeft() >= HALFTILE  && getRight() - other.getLeft() >= HALFTILE && this.getY() == other.getY()) {
			return true;
		} else {
			return false;
		}
	}
}
