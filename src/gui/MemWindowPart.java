package gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

import memory.MByte;

public class MemWindowPart extends JComponent implements Runnable {

	private final int WIDTH;
	private final int HEIGHT;

	private final int PARTNUM;

	private final MByte[] MEMORY;

	public MemWindowPart(int wIDTH, int hEIGHT, MByte[] mEMORY, int pARTNUM) {

		super();

		this.WIDTH = wIDTH;
		this.HEIGHT = hEIGHT;

		this.PARTNUM = pARTNUM;

		this.MEMORY = mEMORY;

		setDoubleBuffered(true);

	}

	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		this.setDoubleBuffered(true);
		final int wIDTHBYTE = WIDTH / MByte.BYTESIZE;

		for (int y = 0; y < HEIGHT; y++) {

			for (int x = 0; x < wIDTHBYTE; x++) {

				for (int z = 0; z < MByte.BYTESIZE; z++) {

					if (MEMORY[(wIDTHBYTE * HEIGHT) * PARTNUM + x + wIDTHBYTE * y].getByte()[z]) {

						g.setColor(Color.black);

					} else {

						g.setColor(Color.white);

					}

					g.drawLine(x * MByte.BYTESIZE + z, y, x * MByte.BYTESIZE + z, y);

				}

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
