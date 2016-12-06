package memory;

import java.util.LinkedList;

public class Memory implements Runnable{

	private final MByte[] memory;
	private final int[] heatmap;

	// Der Konstruktor erwartet einen int-Wert für die Größe des MByte-Arrays
	// "speicher"
	// Alle MBytes im MByte-Array "speicher" werden mit false (0) initialisiert
	// Der Speicher ist leer bzw. alle Bytes sind zum überschreiben freigegeben.
	public Memory(int size) {

		memory = new MByte[size];
		heatmap = new int[size];

		for (int i = 0; i < memory.length; i++) {

			memory[i] = new MByte();

		}

	}

	// Standard Getter- und Setter-Methoden zur Instanzvariable "memory"
	public MByte[] getMemory() {
		return memory;
	}

	// Returnt die Anzahl der Bits in memory
	public int getSize() {

		return memory.length * 8;

	}

	public int[] getHeatmap() {
		return heatmap;
	}

	private void incHeatmap(int index) {

		if(!(heatmap[index] >= 100)) {
			
			heatmap[index]++;
			
		}

	}

	private void decHeatmap() {

		for(int i = 0; i < heatmap.length; i++) {
			
			heatmap[i] *= 0.8; 
			
		}

	}
	
	public void resetHeatmap() {
		
		for(int i = 0; i < heatmap.length; i++) {
			
			heatmap[i] = 0;
			
		}
		
	}

	// Returnwerte:
	// false: index out of bounce: Plazieren der Daten fehlgeschlagen
	// true: Plazieren der Daten erfolgreich
	public boolean placeObject(int index, MByte[] data) {

		if ((data.length + index) > memory.length || index < 0) {

			return false;

		}

		for (int i = 0; i < data.length; i++) {

			memory[index + i] = data[i];
			incHeatmap(index + i);
		}

		return true;

	}

	public Boolean placeObject(int index, String text) {

		int[] asciiArray = new int[text.length()];

		for (int i = 0; i < text.length(); i++) {

			asciiArray[i] = (int) text.charAt(i);

		}

		return placeObject(index, asciiArray);

	}

	public Boolean placeObject(int index, int[] data) {

		LinkedList<MByte> mbList = new LinkedList<MByte>();

		for (int i = 0; i < data.length; i++) {

			Integer j = data[i];

			while (j != null) {

				MByte mb = new MByte();
				j = mb.setByte(j);
				mbList.add(mb);

			}

		}

		return placeObject(index, mbList.toArray(new MByte[0]));

	}

	public Boolean placeObject(int index, int data) {

		int[] arr = { data };

		return placeObject(index, arr);

	}

	// Speichert einen Integer-Wert (4 Byte) in memory.
	// Es werden keine vorangestellten Nullen weggeschnitten.
	// Es werden immer genau 4 Byte beschrieben.
	// Gibt 'false' bei index out of Bounce Error zurück.
	public boolean placeInteger(int index, int data) {

		if ((4 + index) > memory.length || index < 0) {

			return false;

		}

		String bin = Integer.toBinaryString(data);

		boolean[] bArray = new boolean[32];

		for (int i = 0; i < bin.length(); i++) {

			if (bin.charAt(bin.length() - 1 - i) == '1') {

				bArray[bArray.length - 1 - i] = true;

			} else {

				bArray[bArray.length - 1 - i] = false;

			}

		}

		for (int i = 0; i < 4; i++) {

			bArray = memory[index + i].setByte(bArray);
			incHeatmap(index + i);
		}

		return true;

	}

	// toString() gibt ein Abbild des Speichers als String zurück
	public String toString() {

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < memory.length; i++) {

			// Zeilenumbruch nach 30 mBytes
			if (i % 30 == 0 && i != 0) {

				sb.append("\n");

			}

			sb.append(memory[i].toString());

		}

		return sb.toString();

	}

	@Override
	public void run() {
		
		while(true) {
			
			decHeatmap();
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
	}

}
