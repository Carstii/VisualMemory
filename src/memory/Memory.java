package memory;

import java.util.LinkedHashMap;
import java.util.LinkedList;

public class Memory {

	private final MByte[] memory;
	private LinkedHashMap<Integer, MemoryObject> memObjMap;
	private boolean[] memoryIndex;
	private int bytesUsed;
	private int bytesFree;

	// Der Konstruktor erwartet einen int-Wert für die Größe des MByte-Arrays
	// "speicher"
	// Alle MBytes im MByte-Array "speicher" werden mit false (0) initialisiert
	// Der Speicher ist leer bzw. alle Bytes sind zum überschreiben freigegeben.
	public Memory(int size) {

		memory = new MByte[size];

		for (int i = 0; i < memory.length; i++) {

			memory[i] = new MByte();

		}

		setMemoryIndex(new boolean[size]);
		memObjMap = new LinkedHashMap<Integer, MemoryObject>();
		bytesUsed = 0;

	}

	// Standard Getter- und Setter-Methoden zur Instanzvariable "speicher"
	public MByte[] getMemory() {
		return memory;
	}

	// public void setMemory(MByte[] memory) {
	// this.memory = memory;
	// }

	// Standard Getter- und Setter-Methoden zur Instanzvariable "index"
	public boolean[] getMemoryIndex() {
		return memoryIndex;
	}

	public void setMemoryIndex(boolean[] memoryIndex) {
		this.memoryIndex = memoryIndex;
	}

	// Standard Getter-methode zur Instanzvariable "bytesBelegt"
	public int getBytesUsed() {
		setBytesUsed();
		return bytesUsed;
	}

	// Aktualisiert die Instanzvariable bytesBelegt: Es werden alle belegten
	// Bytes im Speicher gezählt und der entsprechende Wert in "bytesBelegt"
	// abgespeichert.
	public void setBytesUsed() {

		int counter = 0;

		for (boolean b : memoryIndex) {

			if (b) {

				counter++;

			}

		}

		bytesUsed = counter;

	}

	// Standard Getter-methode zur Instanzvariable "bytesFrei"
	public int getBytesFree() {
		setBytesFree();
		return bytesFree;
	}

	// Aktualisiert die Instanzvariable bytesBelegt: Die Anzahl der Freien Bytes
	// werden durch die Differenz der Gesamtspeichergröße und dem Belegten
	// Speicher ermittelt
	public void setBytesFree() {

		this.bytesFree = memory.length - getBytesUsed();

	}

	// Returnwerte:
	// null: index out of bounce: Plazieren der Daten fehlgeschlagen
	// false: Speicher bei index schon belegt
	// true: Plazieren der Daten erfolgreich
	public Boolean placeObject(int index, MByte[] data) {

		if ((data.length + index) > memory.length || index < 0) {

			return null;

		}

		for (int i = 0; i < data.length; i++) {

			if (memoryIndex[index + i]) {

				return false;

			}

		}

		for (int i = 0; i < data.length; i++) {

			memory[index + i] = data[i];
			memoryIndex[index + i] = true;

			memObjMap.put(index, new MemoryObject(index, data.length));

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
		
		int[] arr = {data};
		
		return placeObject(index, arr);
		
	}
	
	
	// Returnwerte:
	// false: Objectname nicht gefunden
	// true: erfolgreich

	public boolean deleteObject(int index) {

		if (!memObjMap.containsKey(index)) {

			return false;

		}

		for (int i = 0; i < memObjMap.get(index).getSize(); i++) {

			memoryIndex[memObjMap.get(index).getIndex() + i] = false;

		}

		memObjMap.remove(index);
		return true;

	}
	
	// Returnwerte:
	// null: Objectname nicht gefunden

	public MByte[] getObjData(int index) {

		if (!memObjMap.containsKey(index)) {

			return null;

		}
		
		MByte[] data = new MByte[memObjMap.get(index).getSize()];
		
		for(int i = 0; i < data.length; i++) {
			
			data[i] = memory[memObjMap.get(index).getIndex() + i];
			
		}
		
		return data;

	}
	
	// Returnwerte:
	// null: Objectname nicht gefunden
	// false: Datengröße ungleich Objectgröße
	// true: erfolgreich
	
	public Boolean writeObjData(MByte[] data, int index) {
		
		if(!memObjMap.containsKey(index)) {
			
			return null;
			
		}
		
		if(data.length != memObjMap.get(index).getSize()) {
			
			return false;
			
		}
		
		for(int i = 0; i < data.length; i++) {
			
			memory[memObjMap.get(index).getIndex() + i] = data[i];
			
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

}
