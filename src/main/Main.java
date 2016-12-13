package main;

import gui.Menu;
import telnet.Server;
import telnet.TelnetShell;

public class Main {

	public static void main(String[] args) {

		Menu menu = new Menu();
		menu.showGUI();
		while (menu.getMemory() == null) {

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

		TelnetShell ts = new TelnetShell(menu);
		Server serverOld = new Server(ts);
		Thread serverThread = new Thread(serverOld);
		System.out.println("Start");
		serverThread.start();

	}

}
