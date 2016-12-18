package gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

import memory.MByte;
import telnet.TelnetShell;

public class MemGreyLevelPart extends JComponent implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int WIDTH;
	private final int HEIGHT;

	private final int PARTNUM;

	private final MByte[] MEMORY;

	public MemGreyLevelPart(int wIDTH, int hEIGHT, MByte[] mEMORY, int pARTNUM) {

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

		final int wIDTHBYTE = WIDTH / MByte.BYTESIZE;

		int index;
		int value;

		for (int y = 0; y < HEIGHT; y++) {

			for (int x = 0; x < wIDTHBYTE; x++) {

				index = (wIDTHBYTE * HEIGHT) * PARTNUM + x + wIDTHBYTE * y;

				value = Integer.parseInt(MEMORY[index].toString(), 2);
				g.setColor(new Color(value, value, value));
				g.drawLine(x * MByte.BYTESIZE, y, x * MByte.BYTESIZE + 7, y);

			}

		}

	}

	@Override
	public void run() {

		while (TelnetShell.run) {

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			paintComponent(this.getGraphics());

		}

	}

}
