package main;

import java.util.Random;

import gui.MemWindow;
import gui.Menu;
import memory.Memory;
import telnet.Server;
import telnet.TelnetShell;

public class Main {

	public static void main(String[] args) {
		
		
//		 int width = 800;
//		 int height = 600;

//		int width = 1920;
//		int height = 1080;

//		 int width = 2560;
//		 int height = 1440;
		 
		 Menu menu = new Menu();
		 menu.showGUI();
		 while(menu.getMemory() == null) {
			 
			 try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			 
		 }
//		 Memory mem = new Memory(width * height / 8);
		 
		 TelnetShell ts = new TelnetShell(menu);
		 Server server = new Server(ts);
		 Thread serverThread = new Thread(server);
		 
		 serverThread.start();
		 
		 
		 Random random = new Random();
//
		

		// @SuppressWarnings("resource")
		// Scanner scanner = new Scanner(System.in);

//		 MemWindow memWindow = new MemWindow(width, height, 1, mem.getMemory());
//		 memWindow.start();

//		MemHeatMap heatWindow = new MemHeatMap(width, height, 1, mem.getHeatmap());
//		heatWindow.start();

		// int index = 0;
		while (true) {
			try {
//				int input = scanner.nextInt();
				menu.getMemory().placeInteger(random.nextInt(menu.getMemory().getSize() / 8), random.nextInt());
				// index += 4;
				//
				// if(index >= mem.getSize()/8) {
				//
				// index = 0;
				//
				// }

//				 Thread.sleep(1000);

			} catch (Exception e) {

				System.out.println("error!");
				// scanner.nextLine();
			}

		}

		// while (true) {
		//
		// for (int i = 0; i < 1; i++) {
		//
		// mem.placeObject(index, 0x0);
		// index += 4;
		// if (index > mem.getSize() / 8) {
		//
		// index = 0;
		//
		// }
		//
		// try {
		// Thread.sleep(1);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		//
		// }
		//
		// for (int i = 0; i < 1; i++) {
		//
		// mem.placeObject(index, 0xFFFFFFFF);
		// index += 4;
		//
		// if (index > mem.getSize() / 8) {
		//
		// index = 0;
		//
		// }
		//
		// try {
		// Thread.sleep(1);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		//
		// }
		//
		// }

	}

}
