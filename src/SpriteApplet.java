import java.awt.Color;
import java.awt.Graphics2D;

public class SpriteApplet {

	float boundBoxX = 0;
	float boundBoxY = 0;
	float x = 0;
	float y = 0;
	Color color;
	float rotation = 0;

	public float getBoundBoxX() {
		return boundBoxX;
	}

	public void setBoundBoxX(float boundBoxX) {
		this.boundBoxX = boundBoxX;
	}

	public float getBoundBoxY() {
		return boundBoxY;
	}

	public void setBoundBoxY(float boundBoxY) {
		this.boundBoxY = boundBoxY;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public float getRotation() {
		return rotation;
	}

	public void setRotation(float rotation) {
		this.rotation = rotation;
	}

	void render(Graphics2D g) {
		g.setColor(color);
		g.rotate(rotation, x, y);

		g.fillRect((int) (x - (boundBoxX / 2)), (int) (y - (boundBoxY / 2)),
				(int) (boundBoxX), (int) (boundBoxY));
		g.rotate(-rotation, x, y);
	}

	float transpx(float theta, float ox, float oy, float px, float py) {
		return (float) (Math.cos(theta) * (px - ox) - Math.sin(theta)
				* (py - oy) + ox);
	}

	float transpy(float theta, float ox, float oy, float px, float py) {
		return (float) (Math.sin(theta) * (px - ox) + Math.cos(theta)
				* (py - oy) + oy);
	}

	boolean isInside(float x_, float y_) {
		float xo = x_;
		x_ = transpx((float) -Math.toRadians(rotation), x, y, x_, y_);
		y_ = transpy((float) -Math.toRadians(rotation), x, y, xo, y_);

		if ((x_ < x + (boundBoxX / 2) && x_ > x - (boundBoxX / 2))
				&& (y_ < y + (boundBoxY / 2) && y_ > y - (boundBoxY / 2))) {
			return true;
		}
		return false;
	}

}