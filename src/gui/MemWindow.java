package gui;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JWindow;

import memory.MByte;

public class MemWindow {

	private final int WIDTH;
	private final int HEIGHT;

	private final int THREADANZAHL;

	private final MByte[] MEMORY;

	private Thread guiThread;

	public MemWindow(int wIDTH, int hEIGHT, int tHREADANZAHL, MByte[] mEMORY) {

		WIDTH = wIDTH;
		HEIGHT = hEIGHT;
		THREADANZAHL = tHREADANZAHL;
		MEMORY = mEMORY;

	}

	public void start() {

		JWindow window = new JWindow();

		window.setSize(WIDTH, HEIGHT);
		window.setLayout(new GridLayout(0, 1));
		window.setBackground(Color.white);

		MemWindowPart[] parts = new MemWindowPart[THREADANZAHL];

		for (int i = 0; i < THREADANZAHL; i++) {

			parts[i] = new MemWindowPart(WIDTH, HEIGHT / THREADANZAHL, MEMORY, i);
			window.add(parts[i]);

			window.setVisible(true);
			guiThread = new Thread(parts[i]);
			guiThread.start();

		}

	}

}