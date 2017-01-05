package main;

import gui.Menu;
import telnet.Server;
import telnet.TelnetShell;

public class Main {

	public static void main(String[] args) {

		Menu menu = new Menu();
		TelnetShell ts = new TelnetShell(menu);
		Server server = new Server(ts, 2300);
		Thread serverThread = new Thread(server);
		System.out.println("Start");
		serverThread.start();

	}

}
