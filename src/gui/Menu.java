package gui;

import java.awt.Toolkit;

import memory.MByte;
import memory.Memory;

public class Menu {

	private Memory mem;

	private final int width;
	private final int height;

	private MemWindow memWindow;
	private MemHeatMap heatWindow;
	private MemGreyLevel greyLevel;

	public Menu() {

		width = Toolkit.getDefaultToolkit().getScreenSize().width / MByte.BYTESIZE;
		height = Toolkit.getDefaultToolkit().getScreenSize().height;

		mem = new Memory(width * height);

		System.out.println(mem.getSize() + " Bits");
		System.out.println(width + " x " + height);

		Thread memoryThread = new Thread(mem);
		memoryThread.start();

		memWindow = new MemWindow(width * MByte.BYTESIZE, height, 1, mem.getMemory());
		memWindow.start();

	}

	public Memory getMemory() {

		return mem;

	}

	public MemWindow getMemWindow() {

		return memWindow;

	}

	public MemHeatMap getHeatWindow() {

		return heatWindow;

	}

	public MemGreyLevel getGreyLevel() {

		return greyLevel;

	}

	public void switchView(int view) {

		switch (view) {

		case 1:
			heatWindow = null;
			greyLevel = null;

			memWindow = new MemWindow(width * MByte.BYTESIZE, height, 1, mem.getMemory());
			memWindow.start();

			break;

		case 2:

			greyLevel = null;
			memWindow = null;

			heatWindow = new MemHeatMap(width * MByte.BYTESIZE, height, 1, mem.getHeatmap());
			heatWindow.start();

			break;

		case 3:

			memWindow = null;
			heatWindow = null;

			greyLevel = new MemGreyLevel(width * MByte.BYTESIZE, height, 1, mem.getMemory());
			greyLevel.start();

			break;

		}

	}

}
