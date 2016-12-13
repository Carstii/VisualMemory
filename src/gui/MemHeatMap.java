package gui;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JWindow;

public class MemHeatMap {
	private final int WIDTH;
	private final int HEIGHT;

	private final int THREADANZAHL;

	private final int[] HEATMAP;
	
	public MemHeatMap(int wIDTH, int hEIGHT, int tHREADANZAHL, int[] hEATMAP) {

		super();
		WIDTH = wIDTH;
		HEIGHT = hEIGHT;
		THREADANZAHL = tHREADANZAHL;
		HEATMAP = hEATMAP;

	}

	public void start() {

		JWindow window = new JWindow();

		window.setSize(WIDTH, HEIGHT);
		window.setLayout(new GridLayout(0, 1));
		window.setBackground(Color.white);

		MemHeatMapPart[] parts = new MemHeatMapPart[THREADANZAHL];

		for (int i = 0; i < THREADANZAHL; i++) {

			parts[i] = new MemHeatMapPart(WIDTH, HEIGHT / THREADANZAHL, HEATMAP, i);
			window.add(parts[i]);

			window.setVisible(true);
			Thread guiThread = new Thread(parts[i]);
			guiThread.start();
			

		}

	}

}
