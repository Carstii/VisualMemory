package telnet;

import gui.Menu;
import memory.MByte;

public class TelnetShell {
	
	public static boolean run = true;

	private Menu menu;

	public TelnetShell(Menu mem) {

		this.menu = mem;

	}

	public String readByte(int index) {

		if ((menu.getMemory().getSize() / MByte.BYTESIZE) <= index || index < 0) {

			return null;

		}

		return Integer.toHexString(Integer.parseInt(menu.getMemory().getMemory()[index].toString(), 2));

	}

	// erwartet einen Binary-String
	public boolean writeByte(int index, int data) {
		
		if(data < 0 || data > 255) {
			
			return false;
			
		}

		return menu.getMemory().placeObject(index, data);

	}
	
	public String getSize() {
		
		return Integer.toString(menu.getMemory().getSize());
		
	}
	
	public void switchServerView() {
		
		menu.switchView();
		
	}

}
