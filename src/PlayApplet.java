import java.applet.AudioClip;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JApplet;

@SuppressWarnings("serial")
public class PlayApplet extends JApplet
		implements
			Runnable,
			KeyListener,
			MouseListener,
			MouseMotionListener {
	Image bb;
	Graphics bg;
	SpriteApplet square;
	TextSpriteApplet glow;
	public boolean isRunning = false;
	public int radius = 25;
	boolean sp = false;
	boolean ent = false;
	OutlineSpriteApplet goal;
	boolean firstLose = true;
	int level;
	int timer;
	int msecs;
	int timeLeft;
	boolean lose = false;
	int[] curve = {30, 15, 12, 10, 7, 5, 3, 2};
	int finalCurve = 1;

	Boolean spacePressed = false;
	Boolean won = false;
	AudioClip soundFile1;

	public void start() {

		setBackground(Color.black);
		isRunning = true;
		new Thread(this).start();
		addKeyListener(this);
		bb = createImage(getWidth(), getHeight());
		bg = bb.getGraphics();

	}

	public void stop() {
		isRunning = false;
	}

	public void init() {
		soundFile1 = getAudioClip(getDocumentBase(), "music.au");
		soundFile1.play();
		setFocusable(true);
		requestFocusInWindow();

		addKeyListener(this);
		addMouseListener(this);

		glow = new TextSpriteApplet();
		glow.setText("YOLO");
		glow.setfColor(Color.white);
		glow.setColor(new Color(0, 0, 0, 0));

		square = new SpriteApplet();
		square.setX(getSize().width / 2);
		square.setY(getSize().height / 2);
		square.setColor(java.awt.Color.white);
		square.setBoundBoxX(128);
		square.setBoundBoxY(128);

		goal = new OutlineSpriteApplet();
		goal.setX(square.getX());
		goal.setY(square.getY());
		goal.setColor(Color.yellow);
		goal.setBoundBoxX(square.getBoundBoxX());
		goal.setBoundBoxY(square.getBoundBoxY());
	}

	void update() {
		float delta = 15f;
		msecs += delta;
		if (msecs >= 1000) {
			msecs -= 1000;
			timer += 1;
		}
		if (level < curve.length)
			timeLeft = curve[level] - timer;
		else
			timeLeft = finalCurve - timer;
		if (sp) {
			if (!lose) {
				square.setRotation((float) (square.getRotation() + delta * .014));
				spacePressed = true;
				firstLose = true;
			}
			if (won) {
				square.setRotation(0);
				won = false;
				spacePressed = false;
				timer = 0;
				level++;
			}
		}

		if (ent) {
			if (lose) {
				square.setRotation(0);
				lose = false;
				spacePressed = false;
				timer = 0;
				level = 0;
			}
		}

		if ((square.getRotation() % 90 < 1.25 || square.getRotation() % 90 > 88.75)
				&& spacePressed == true && !sp) {
			won = true;

		} else
			won = false;
		if (timeLeft < 1 && !won) {
			lose = true;
			if (firstLose == true) {
				firstLose = false;

			}

		}

	}

	public void paint(Graphics g) {

		Graphics2D g2d = (Graphics2D) bg;
		g2d.setBackground(Color.BLACK);
		g2d.clearRect(0, 0, getSize().width, getSize().height);

		if (won) {
			glow.setText(String.format("Level up to %d. Space to continue",
					level + 2));
			glow.render(g2d);
		} else if (!lose) {
			glow.setText(String.format("Time left: %d\n  Level: %d", timeLeft,
					level + 1));
			glow.render(g2d);

		} else if (lose) {
			glow.setText(String.format("Game over Level: %d%n Retry?",
					level + 1));
			glow.render(g2d);
		}

		square.render(g2d);
		g2d.setStroke(new BasicStroke(3));
		goal.render(g2d);
		g.drawImage(bb, 0, 0, this);

	}

	public void run() {

		while (isRunning) {
			update();
			requestFocusInWindow();
			glow.setX(getWidth() / 2);
			glow.setY(glow.getBoundBoxY() / 2);

			repaint();
			// if (sp && !lose)
			// square.setRotation((float) (square.getRotation()));
			// Create Graphics2D object, cast g as a Graphics2D
			square.setX(getSize().width / 2);
			square.setY(getSize().height / 2);
			goal.setX(square.getX());
			goal.setY(square.getY());
			try {
				Thread.sleep(15);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			sp = true;
		}
		System.out.print("asdf");
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			ent = true;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			sp = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			ent = false;
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}
