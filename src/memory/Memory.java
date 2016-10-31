package memory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.TreeMap;
import java.util.TreeSet;

public class Memory {

	private final MByte[] memory;
	private TreeMap<String, File> fileIndex;
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
		setFileIndex(new TreeMap<String, File>());
		bytesUsed = 0;

	}

	// !! Dieser Konstruktor dient nur zu Testzwecken !!
	// Der Konstruktor erwartet einen int-Wert für die Größe des MByte-Arrays
	// "speicher"
	// Als 2. Parameter wird ein boolean-Wert "init" übergeben.
	// Alle MBytes im MByte-Array "speicher" werden mit "init" initialisiert
	public Memory(int size, boolean init) {

		memory = new MByte[size];

		if (init) {

			for (int i = 0; i < memory.length; i++) {

				memory[i] = new MByte(init);

			}

		} else {

			for (int i = 0; i < memory.length; i++) {

				memory[i] = new MByte();

			}

		}

		setMemoryIndex(new boolean[size]);
		setFileIndex(new TreeMap<String, File>());

	}

	// !! Dieser Konstruktor dient nur zu Testzwecken !!
	// Der Konstruktor erwartet einen int-Wert für die Größe des MByte-Arrays
	// "speicher"
	// Als 2. Parameter wird ein MByte "pattern" übergeben
	// Alle MBytes im MByte-Array "speicher" werden mit den Werten von "pattern"
	// initialisiert
	public Memory(int size, MByte pattern) {

		memory = new MByte[size];

		for (int i = 0; i < memory.length; i++) {

			memory[i] = new MByte(pattern);

		}

		setMemoryIndex(new boolean[size]);
		setFileIndex(new TreeMap<String, File>());

	}

	// Standard Getter- und Setter-Methoden zur Instanzvariable "speicher"
	public MByte[] getMemory() {
		return memory;
	}

	// public void setMemory(MByte[] memory) {
	// this.memory = memory;
	// }

	// Standard Getter- und Setter-Methoden zur Instanzvariable "dateiIndex"
	public TreeMap<String, File> getFileIndex() {
		return fileIndex;
	}

	public void setFileIndex(TreeMap<String, File> dateiIndex) {
		this.fileIndex = dateiIndex;
	}

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

	public TreeSet<String> getFileList() {

		return (TreeSet<String>) fileIndex.keySet();

	}

	public File getFile(String fileName) {

		return fileIndex.get(fileName);

	}

	public MByte[] getFileData(String name) {

		if (!fileIndex.containsKey(name)) {

			return null;

		}

		MByte[] data = new MByte[fileIndex.get(name).getSize()];

		for (int i = 0; i < fileIndex.get(name).getSize(); i++) {

			data[i] = memory[fileIndex.get(name).getIndex().get(i)];

		}

		return data;

	}

	// Datei speichern
	// Die addFile()-Methoden sind nicht Threadsicher/atomar.
	public void addFile(String name) {

		File file = new File(checkFileName(name));

		fileIndex.put(file.getName(), file);

	}

	// Returnwerte:
	// false: fehlgeschlagen: index out of bounce
	// true: Plazierung erfolgreich
	public boolean addFile(String name, MByte[] data) {

		File file = new File(checkFileName(name), placeData(data, calcDataPosition(data.length)));

		if (file.getIndex() == null) {

			return false;

		}

		fileIndex.put(file.getName(), file);

		return true;
		
	}

	public void addFile(String name, String text) {
		
		int[] asciiArray = new int[text.length()];
		
		for(int i = 0; i < text.length(); i++) {
			
			asciiArray[i] = (int) text.charAt(i);
			
		}
		
		addFile(name, asciiArray);

	}

	public void addFile(String name, int[] data) {
		
		LinkedList<MByte> mbList = new LinkedList<MByte>();
		
		for(int i = 0; i < data.length; i++) {
			
			Integer j = data[i];
			
			while(j != null) {
				
				MByte mb = new MByte();
				j = mb.setByte(j);
				mbList.add(mb);
				
			}
						
		}
		
		addFile(name, mbList.toArray(new MByte[0]));

	}

	public void addFile(String name, int number) {
		
		int[] arr = {number};
		
		addFile(name, arr);

	}

	public void deleteFile(String name) {
		
		// TODO

	}

	// Returnwerte:
	// null: zu kopierende Datei nicht gefunden
	// false: nicht genügend Speicherplatz: Kopieren fehlgeschlagen
	// true: Datei erfolgreich kopiert
	public Boolean copyFile(String name, String nameCopy) {

		if (!fileIndex.containsKey(name)) {

			return null;

		}

		if (getBytesFree() < fileIndex.get(name).getSize()) {

			return false;

		}

		return addFile(nameCopy, getFileData(name));

	}

	public void renameFile(String name, String nameNew) {
		
		// TODO

	}

	public void encryptFile(String FileName, File key) {

		// TODO Onetimepad

	}

	public void decryptFile(String FileName, File key) {

		// TODO Onetimepad

	}
	
	public void encryptMemory(File key) {

		// TODO Onetimepad

	}

	public void decryptMemory(File key) {

		// TODO Onetimepad

	}
	
	public void defragment() {
		
		// TODO
		
	}

	// Die überladene Methode andMByte() verändert ein MByte in dem MByte-Array
	// "speicher" mithilfe der logischen AND-Operation.
	// der erste übergebene Parameter ist vom Typ int und bestimmt den index des
	// zu ändernden MBytes im MByte-Array "speicher".
	// Der 2. Parameter bestimmt mit welchen Werten die AND-Operation
	// durchgeführt wird.
	// Mögliche Typen des 2. Parameters sind: MByte, boolean oder int.
	public void andMByte(int index, MByte pattern) {

		this.getMemory()[index].andMByte(pattern);

	}

	public void andMByte(int index, boolean b) {

		this.getMemory()[index].andMByte(b);

	}

	public void andMByte(int index, int pattern) {

		int counter = 0;

		do {

			if (index + counter >= memory.length) {

				break;

			}

			pattern = this.getMemory()[index + counter].andMByte(pattern);
			counter++;

		} while (pattern > 0);

	}

	// Die Methode orMByte() funktioniert analog zur andMByte() Methode mit dem
	// einzigen Unterschied, dass nicht die logische AND-Opreration, sondern die
	// logische OR-Operation verwendet wird.
	public void orMByte(int index, MByte pattern) {

		this.getMemory()[index].orMByte(pattern);

	}

	public void orMByte(int index, boolean b) {

		this.getMemory()[index].orMByte(b);

	}

	public void orMByte(int index, int pattern) {

		int counter = 0;

		do {

			if (index + counter >= memory.length) {

				break;

			}

			pattern = this.getMemory()[index + counter].orMByte(pattern);
			counter++;

		} while (pattern > 0);

	}

	// Die Methode xorMByte() funktioniert analog zur andMByte() Methode mit dem
	// einzigen Unterschied, dass nicht die logische AND-Opreration, sondern die
	// logische XOR-Operation verwendet wird.
	public void xorMByte(int index, MByte pattern) {

		this.getMemory()[index].xorMByte(pattern);

	}

	public void xorMByte(int index, boolean b) {

		this.getMemory()[index].xorMByte(b);

	}

	public void xorMByte(int index, int pattern) {

		int counter = 0;

		do {

			if (index + counter >= memory.length) {

				break;

			}

			pattern = this.getMemory()[index + counter].xorMByte(pattern);
			counter++;

		} while (pattern > 0);

	}
	
	private int calcDataPosition(int dataSize) {
		
		int firstFreeIndex = 0;
		int freeIndexPointer = 0;
		boolean prevByteUsed = true;
		int freeByteCounter = 0;

		for (int i = 0; i < memoryIndex.length; i++) {

			if (!memoryIndex[i]) {

				firstFreeIndex = i;
				break;

			}

		}

		for (int i = firstFreeIndex; i < memoryIndex.length; i++) {

			if (memoryIndex[i]) {

				freeByteCounter = 0;

			} else {

				freeByteCounter++;

			}

			if (!memoryIndex[i] && prevByteUsed) {

				freeIndexPointer = i;

			}

			if (freeByteCounter >= dataSize) {

				return freeIndexPointer;

			}

			prevByteUsed = memoryIndex[i];

		}

		// Plazierung mit Fragmentierung
		return firstFreeIndex;
		
	}

	private String checkFileName(String name) {

		int counter = 0;
		String helper = name;

		while (fileIndex.containsKey(helper)) {

			counter++;
			helper = name + "-" + counter;

		}

		return helper;

	}

	// Returnwerte:
	// null: index out of bounce: Plazieren der Daten fehlgeschlagen
	// else Plazieren der Daten erfolgreich
	private ArrayList<Integer> placeData(MByte[] data, int index) {

		ArrayList<Integer> indexFileData = new ArrayList<Integer>(data.length);

		for (int i = 0; i < data.length; i++) {

			if (index + i >= memory.length || index < 0) {

				for (int j : indexFileData) {

					memoryIndex[j] = false;

				}

				return null;

			}

			while (memoryIndex[index + i]) {

				index++;

				if (index + i >= memoryIndex.length) {
					
					for (int j : indexFileData) {

						memoryIndex[j] = false;

					}

					return null;

				}

			}

			placeByte(data[i], index + i);
			indexFileData.add(index + i);

		}

		return indexFileData;

	}

	// Plaziert ein MByte im speicher am angegebenen Index.
	// Returnwerte:
	// null: index out of bounce
	// false: Speicherbereich bereits belegt: Plazieren der Daten fehlgeschlagen
	// true: Plazieren der Daten erfolgreich
	private Boolean placeByte(MByte data, int index) {

		if (index >= memory.length || index < 0) {

			return null;

		}

		if (memoryIndex[index]) {

			return false;

		}

		memory[index] = data;
		memoryIndex[index] = true;

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
