package memory;

public class MByte {

	public static final int BYTESIZE = 8;

	private boolean[] mByte;

	// Der Konstruktor legt die Gr��e des boolean-Arrays "mByte" auf 8 fest und
	// gibt jedem Wert darin den Wert false
	public MByte() {

		setByte(new boolean[BYTESIZE]);

	}

	// Der Konstruktor legt die Gr��e des boolean-Arrays "mByte" auf 8 fest und
	// initialiseiert jeden Wert darin mit dem �bergebenen boolean-Wert "init"
	public MByte(boolean init) {

		this();

		if (init) {

			orMByte(init);

		}

	}

	// Der Konstruktor �bernimmt die Werte aus dem �bergebenen MByte "pattern"
	// und initialisiert damit die Instanzvariable "mByte"
	public MByte(MByte pattern) {

		setByte(pattern.getByte());

	}

	// Standard Get-Methode zur Instanzvariable "mByte"
	public boolean[] getByte() {
		return mByte;
	}

	// Set-Methode zur Instanzvariable "mByte".
	// Es wird die L�nge des �bergebenen boolean-Arrays "b" �berpr�ft:
	//
	// wenn "b" genau gleichlang wie BYTESIZE, wird "b" 1:1 �bernommen.
	//
	// wenn "b" l�nger als BYTESIZE, werden nur die ersten paar Werte (bis zu
	// BYTESIZE(8) ) �bernommen und der Rest in einem boolean-Array "helper" an
	// den Aufrufer zur�ckgegeben. L�sst sich die l�nge von "b" nicht glatt
	// durch BYTESIZE teilen, werden entsprechend viele Nullen bzw. false
	// vorangestellt.
	//
	// wenn "b" k�rzer als BYTESIZE werden Nullen vorangestellt.
	//
	// TODO: Diese Methode testen
	public boolean[] setByte(boolean[] b) {

		if (b.length == BYTESIZE) {

			mByte = b;

			return null;

		} else {

			mByte = new boolean[BYTESIZE];

			if (b.length > BYTESIZE) {

				int overflow = b.length % BYTESIZE;

				if (overflow == 0) {

					overflow = BYTESIZE;

				}

				for (int i = 0; i < overflow; i++) {

					mByte[BYTESIZE - overflow + i] = b[i];

				}

				boolean[] helper = new boolean[b.length - overflow];

				for (int i = 0; i < helper.length; i++) {

					helper[i] = b[overflow + i];

				}

				return helper;

			} else {

				for (int i = 0; i < b.length; i++) {

					mByte[BYTESIZE - b.length + i] = b[i];

				}

				return null;

			}

		}

	}

	// Die �berladene Methode andMByte() ver�ndert die Instanzvariable "mByte"
	// mithilfe der logischen AND-Operation.
	// Der �bergebene Parameter bestimmt mit welchen Werten die AND-Operation
	// durchgef�hrt wird.
	// M�gliche Typen des �bergebenen Parameters sind: MByte, boolean oder
	public void andMByte(MByte pattern) {

		boolean[] helper = pattern.getByte();

		for (int i = 0; i < BYTESIZE; i++) {

			this.mByte[i] = this.mByte[i] && helper[i];

		}

	}

	public void andMByte(boolean b) {

		for (int i = 0; i < BYTESIZE; i++) {

			this.mByte[i] = this.mByte[i] && b;

		}

	}

	public int andMByte(int pattern) {

		String s = Integer.toBinaryString(pattern);

		if (s.length() <= BYTESIZE) {

			for (int i = 0; i < s.length(); i++) {

				mByte[7 - i] = mByte[7 - i] && charToBool(s.charAt(s.length() - 1 - i));

			}

			return 0;

		} else {

			int overflow = s.length() % BYTESIZE;

			if (overflow == 0) {

				overflow = BYTESIZE;

			}

			for (int i = 0; i < overflow; i++) {

				mByte[BYTESIZE - overflow + i] = mByte[BYTESIZE - overflow + i] && charToBool(s.charAt(i));

			}

			return Integer.parseUnsignedInt(s.substring(overflow), 2);

		}

	}

	// Die Methode orMByte() funktioniert analog zur andMByte() Methode mit dem
	// einzigen Unterschied, dass nicht die logische AND-Opreration, sondern die
	// logische OR-Operation verwendet wird.
	public void orMByte(MByte pattern) {

		boolean[] helper = pattern.getByte();

		for (int i = 0; i < BYTESIZE; i++) {

			this.mByte[i] = this.mByte[i] || helper[i];

		}

	}

	public void orMByte(boolean b) {

		for (int i = 0; i < BYTESIZE; i++) {

			this.mByte[i] = this.mByte[i] || b;

		}

	}

	public int orMByte(int pattern) {

		String s = Integer.toBinaryString(pattern);

		if (s.length() <= BYTESIZE) {

			for (int i = 0; i < s.length(); i++) {

				mByte[7 - i] = mByte[7 - i] || charToBool(s.charAt(s.length() - 1 - i));

			}

			return 0;

		} else {

			int overflow = s.length() % BYTESIZE;

			if (overflow == 0) {

				overflow = BYTESIZE;

			}

			for (int i = 0; i < overflow; i++) {

				mByte[BYTESIZE - overflow + i] = mByte[BYTESIZE - overflow + i] || charToBool(s.charAt(i));

			}

			return Integer.parseUnsignedInt(s.substring(overflow), 2);

		}

	}

	// Die Methode orMByte() funktioniert analog zur andMByte() Methode mit dem
	// einzigen Unterschied, dass nicht die logische AND-Opreration, sondern die
	// logische XOR-Operation verwendet wird.
	public void xorMByte(MByte pattern) {

		boolean[] helper = pattern.getByte();

		for (int i = 0; i < BYTESIZE; i++) {

			this.mByte[i] = xor(this.mByte[i], helper[i]);

		}

	}

	public void xorMByte(boolean b) {

		for (int i = 0; i < BYTESIZE; i++) {

			this.mByte[i] = xor(this.mByte[i], b);

		}

	}
	
	public int xorMByte(int pattern) {

		String s = Integer.toBinaryString(pattern);

		if (s.length() <= BYTESIZE) {

			for (int i = 0; i < s.length(); i++) {

				mByte[7 - i] = xor(mByte[7 - i], charToBool(s.charAt(s.length() - 1 - i)));

			}

			return 0;

		} else {

			int overflow = s.length() % BYTESIZE;

			if (overflow == 0) {

				overflow = BYTESIZE;

			}

			for (int i = 0; i < overflow; i++) {

				mByte[BYTESIZE - overflow + i] = xor(mByte[BYTESIZE - overflow + i], charToBool(s.charAt(i)));

			}

			return Integer.parseUnsignedInt(s.substring(overflow), 2);

		}

	}

	// Logik zur Durchf�hrung der XOR-Operation mit den �bergebenen
	// boolean-Werten "x" und "y", da es keinen eigenen XOR-Operator gibt
	private boolean xor(boolean x, boolean y) {

		return ((x || y) && !(x && y));

	}

	private boolean charToBool(char c) {

		if (c == '1') {

			return true;

		} else {

			return false;

		}

	}

	// toString() gibt ein Abbild der Instanzvariable "mByte" als String zur�ck.
	// Der String entspricht der L�nge von "mByte" und besteht nur aus den
	// Zeichen '1' und '0'
	public String toString() {

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < mByte.length; i++) {

			if (mByte[i]) {

				sb.append("1");

			} else {

				sb.append("0");

			}

		}

		return sb.toString();

	}

}
