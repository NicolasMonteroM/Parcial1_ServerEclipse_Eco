import processing.core.PApplet;
import processing.core.PConstants;

public class Reminder {

	private PApplet app;
	private int posX, posY, impLevel;
	private String reminder;

	public Reminder(PApplet app, int posX, int posY, String reminder, int impLevel) {
		this.app = app;
		this.posX = posX;
		this.posY = posY;
		this.reminder = reminder;
		this.impLevel = impLevel;

	}

	public void drawReminder() {

		app.fill(255, 255, 255);
		app.noStroke();
		app.rectMode(PConstants.CENTER);
		app.rect(posX, posY, 200, 80, 15);

		// <–– Importance level ––>

		int impColor = 0;

		if (impLevel == 1) {
			impColor = 0xFF63F498;
		} else if (impLevel == 2) {
			impColor = 0xFFF6CC4D;
		} else if (impLevel == 3) {
			impColor = 0xFFF33E62;
		}

		app.fill(impColor);
		app.rect(posX, posY - 35, 200, 10, 15, 15, 0, 0);

		// <–– Reminder text ––>
		app.fill(100);
		app.textAlign(PConstants.CENTER, PConstants.CENTER);
		app.text(reminder, posX, posY, 175, 55);

	}
	
	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

}
