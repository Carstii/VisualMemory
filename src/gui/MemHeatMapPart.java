package gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

import memory.MByte;

public class MemHeatMapPart extends JComponent implements Runnable {

	private final int WIDTH;
	private final int HEIGHT;

	private final int PARTNUM;

	private final int[] HEATMAP;

	public MemHeatMapPart(int wIDTH, int hEIGHT, int[] hEATMAP, int pARTNUM) {

		super();

		this.WIDTH = wIDTH;
		this.HEIGHT = hEIGHT;

		this.PARTNUM = pARTNUM;

		this.HEATMAP = hEATMAP;

		setDoubleBuffered(true);

	}

	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		final int wIDTHBYTE = WIDTH / MByte.BYTESIZE;

		for (int y = 0; y < HEIGHT; y++) {

			for (int x = 0; x < wIDTHBYTE; x++) {

				int index = (wIDTHBYTE * HEIGHT) * PARTNUM + x + wIDTHBYTE * y;

				if (HEATMAP[index] <1) {

					g.setColor(new Color(0, 0, 0));

				} else if (HEATMAP[index] <= 5) {

					g.setColor(new Color(128, 0, 0));

				} else if (HEATMAP[index] <= 10) {

					g.setColor(new Color(255, 0, 0));

				} else if (HEATMAP[index] <= 25) {

					g.setColor(new Color(255, 128, 0));

				} else if (HEATMAP[index] <= 50) {

					g.setColor(new Color(255, 255, 0));

				}else if (HEATMAP[index] <= 75) {

					g.setColor(new Color(255, 255, 128));

				} else {

					g.setColor(new Color(255, 255, 255));

				}

				g.drawLine(x * MByte.BYTESIZE, y, x * MByte.BYTESIZE + 7, y);

			}

		}

	}

	@Override
	public void run() {

		while (true) {

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			paintComponent(this.getGraphics());

		}

	}

}
