package memory;

public class Memory {

	private MByte[] speicher;

	// Der Konstruktor erwartet einen int-Wert f�r die Gr��e des MByte-Arrays
	// "speicher"
	// Alle MBytes im MByte-Array "speicher" werden mit false (0) initialisiert
	public Memory(int size) {

		speicher = new MByte[size];

		for (int i = 0; i < speicher.length; i++) {

			speicher[i] = new MByte();

		}

	}

	// Der Konstruktor erwartet einen int-Wert f�r die Gr��e des MByte-Arrays
	// "speicher"
	// Als 2. Parameter wird ein boolean-Wert "init" �bergeben.
	// Alle MBytes im MByte-Array "speicher" werden mit "init" initialisiert
	public Memory(int size, boolean init) {

		speicher = new MByte[size];

		if (init) {

			for (int i = 0; i < speicher.length; i++) {

				speicher[i] = new MByte(init);

			}

		} else {

			for (int i = 0; i < speicher.length; i++) {

				speicher[i] = new MByte();

			}

		}

		// try {
		// Thread.sleep(1000);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }

	}

	// Der Konstruktor erwartet einen int-Wert f�r die Gr��e des MByte-Arrays
	// "speicher"
	// Als 2. Parameter wird ein MByte "pattern" �bergeben
	// Alle MBytes im MByte-Array "speicher" werden mit den Werten von "pattern"
	// initialisiert
	public Memory(int size, MByte pattern) {

		speicher = new MByte[size];

		for (int i = 0; i < speicher.length; i++) {

			speicher[i] = new MByte(pattern);

		}

	}

	// Standard Getter- und Setter-Methoden zur Instanzvariable "speicher"
	public MByte[] getSpeicher() {
		return speicher;
	}

	public void setSpeicher(MByte[] speicher) {
		this.speicher = speicher;
	}

	// Die �berladene Methode andMByte() ver�ndert ein MByte in dem MByte-Array
	// "speicher" mithilfe der logischen AND-Operation.
	// der erste �bergebene Parameter ist vom Typ int und bestimmt den index des
	// zu �ndernden MBytes im MByte-Array "speicher".
	// Der 2. Parameter bestimmt mit welchen Werten die AND-Operation
	// durchgef�hrt wird.
	// M�gliche Typen des 2. Parameters sind: MByte, boolean oder int.
	public void andMByte(int index, MByte pattern) {

		this.getSpeicher()[index].andMByte(pattern);

	}

	public void andMByte(int index, boolean b) {

		this.getSpeicher()[index].andMByte(b);

	}

	public void andMByte(int index, int pattern) {

		int counter = 0;

		do {

			if (index + counter >= speicher.length) {

				break;

			}

			pattern = this.getSpeicher()[index + counter].andMByte(pattern);
			counter++;

		} while (pattern > 0);

	}

	// Die Methode orMByte() funktioniert analog zur andMByte() Methode mit dem
	// einzigen Unterschied, dass nicht die logische AND-Opreration, sondern die
	// logische OR-Operation verwendet wird.
	public void orMByte(int index, MByte pattern) {

		this.getSpeicher()[index].orMByte(pattern);

	}

	public void orMByte(int index, boolean b) {

		this.getSpeicher()[index].orMByte(b);

	}

	public void orMByte(int index, int pattern) {

		int counter = 0;

		do {

			if (index + counter >= speicher.length) {

				break;

			}

			pattern = this.getSpeicher()[index + counter].orMByte(pattern);
			counter++;

		} while (pattern > 0);

	}

	// Die Methode xorMByte() funktioniert analog zur andMByte() Methode mit dem
	// einzigen Unterschied, dass nicht die logische AND-Opreration, sondern die
	// logische XOR-Operation verwendet wird.
	public void xorMByte(int index, MByte pattern) {

		this.getSpeicher()[index].xorMByte(pattern);

	}

	public void xorMByte(int index, boolean b) {

		this.getSpeicher()[index].xorMByte(b);

	}

	public void xorMByte(int index, int pattern) {

		int counter = 0;

		do {

			if (index + counter >= speicher.length) {

				break;

			}

			pattern = this.getSpeicher()[index + counter].xorMByte(pattern);
			counter++;

		} while (pattern > 0);

	}

	// toString() gibt ein Abbild des Speichers als String zur�ck
	public String toString() {

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < speicher.length; i++) {

			// Zeilenumbruch nach 30 mBytes
			if (i % 30 == 0 && i != 0) {

				sb.append("\n");

			}

			sb.append(speicher[i].toString());

		}

		return sb.toString();

	}

}
