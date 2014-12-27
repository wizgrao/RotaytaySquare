import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class TextSpriteApplet extends SpriteApplet {

	String text = "Change Me";
	float marginX = 0;
	float marginY = 0;

	public float getMarginY() {
		return marginY;
	}

	public void setBoundBoxX(float xx) {
		boundBoxX = xx;

	}

	public void setBoundBoxY(float yy) {

		boundBoxX = yy;

	}

	public void setMarginY(float marginY) {
		this.marginY = marginY;

	}

	Font f;
	Color fColor;

	public TextSpriteApplet() {

		f = new Font("Arial", Font.BOLD, 40);

	}

	public Font getF() {
		return f;

	}

	public void setF(Font f) {
		this.f = f;

	}

	public Color getfColor() {
		return fColor;
	}

	public void setfColor(Color fColor) {
		this.fColor = fColor;
	}

	public float getMarginx() {
		return marginX;
	}

	public void setMarginX(float margin) {
		this.marginX = margin;

	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;

	}

	void render(Graphics2D g) {
		g.setFont(f);
		boundBoxX = g.getFontMetrics().stringWidth(text) + 2 * marginX;
		boundBoxY = f.getSize() + 2 * marginY;
		super.render(g);
		g.rotate(rotation, x, y);

		g.setColor(fColor);

		g.drawString(text, x - g.getFontMetrics().stringWidth(text) / 2,
				y + f.getSize() / 2);
		g.rotate(-rotation, x, y);
	}

}