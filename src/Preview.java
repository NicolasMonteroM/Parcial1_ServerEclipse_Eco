import processing.core.PApplet;
import processing.core.PConstants;

public class Preview {

	private PApplet app;
	private int posX, posY, impLevel;
	private String reminder;

	public Preview(PApplet app, int posX, int posY, String reminder, int impLevel) {
		this.app = app;
		this.posX = posX;
		this.posY = posY;
		this.reminder = reminder;
		this.impLevel = impLevel;

	}

	public void drawPreview() {
		
		app.fill(0x80FFFFFF);
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
		app.fill(255);
		app.text("Preview", posX, posY + 50, 175, 55);

	}

}
