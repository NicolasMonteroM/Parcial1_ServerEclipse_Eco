import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.google.gson.Gson;

import processing.core.PApplet;
import processing.core.PConstants;

public class Main extends PApplet {

	private Preview p;
	private String reminderData;
	private ArrayList<Reminder> reminders;

	public static void main(String[] args) {
		PApplet.main(Main.class.getName());
	}

	public void settings() {

		size(1200, 700);

	}

	public void setup() {

		reminders = new ArrayList<Reminder>();

		// <–– Example ––>
		reminders.add(new Reminder(this, 300, 200, "Este es un recordatorio de prueba", 1));
		reminders.add(new Reminder(this, 600, 400, "Este es un recordatorio de prueba", 2));

		new Thread(() -> {
			try {
				ServerSocket server = new ServerSocket(5000);
				System.out.println("Waiting connection...");

				Socket socket = server.accept();
				System.out.println("Connected");

				InputStream is = socket.getInputStream();
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader breader = new BufferedReader(isr);

				while (true) {

					String dataReceived = breader.readLine();
					Gson gson = new Gson();
					reminderData = gson.fromJson(dataReceived, String.class);

					arrangeInfo(reminderData);

					System.out.println("" + reminderData);

				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}).start();

	}

	public void draw() {

		background(30);
		drawReminders();
		drawPreview();

		// <–– Instruction ––>
		fill(80);
		textAlign(PConstants.CENTER, PConstants.CENTER);
		text("Puedes mover los recordatorios y organizarlos. No los arrastres muy cerca :)", width / 2, height - 20);

	}

	public void drawReminders() {

		for (Reminder r : this.reminders) {
			r.drawReminder();
		}

	}

	public void drawPreview() {

		if (p != null) {
			p.drawPreview();
		}

	}

	public void arrangeInfo(String s) {

		String[] data = s.split(",");

		// <–– Coordinates ––>
		int posX = parseInt(data[0]);
		int posY = parseInt(data[1]);

		// <–– Text ––>
		String reminderTxt = data[2];

		// <–– Importance info ––>
		int impLevel = parseInt(data[3]);

		// <–– Preview value ––>
		int previewValue = parseInt(data[4]);

		if (previewValue == 1) {

			createPreview(posX, posY, reminderTxt, impLevel);
			System.out.println("Preview mode");

		} else {

			addReminder(posX, posY, reminderTxt, impLevel);
		}

	}

	// <–– Create reminder ––>
	public void addReminder(int posX, int posY, String reminderTxt, int impLevel) {

		p = null;
		reminders.add(new Reminder(this, posX, posY, reminderTxt, impLevel));

	}

	// <–– Create preview ––>
	public void createPreview(int posX, int posY, String reminderTxt, int impLevel) {

		p = new Preview(this, posX, posY, reminderTxt, impLevel);

	}

	// <–– Move reminder ––>
	public void mouseDragged() {

		for (int i = 0; i < reminders.size(); i++) {

			if (dist(mouseX, mouseY, reminders.get(i).getPosX(), reminders.get(i).getPosY()) < 100) {

				reminders.get(i).setPosX(mouseX);
				reminders.get(i).setPosY(mouseY);

			}
		}
	}

	// <–– Release reminder ––>
	public void mouseReleased() {

		int posX = mouseX;
		int posY = mouseY;

		for (int i = 0; i < reminders.size(); i++) {

			if (dist(mouseX, mouseY, reminders.get(i).getPosX(), reminders.get(i).getPosY()) < 100) {

				reminders.get(i).setPosX(posX);
				reminders.get(i).setPosY(posY);

			}
		}

	}

}
