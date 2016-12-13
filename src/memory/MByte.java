package memory;

public class MByte {

	public static final int BYTESIZE = 8;

	private boolean[] mByte;

	// Der Konstruktor legt die Größe des boolean-Arrays "mByte" auf 8 fest und
	// gibt jedem Wert darin den Wert false
	public MByte() {

		setByte(new boolean[BYTESIZE]);

	}

	// Standard Get-Methode zur Instanzvariable "mByte"
	public boolean[] getByte() {
		return mByte;
	}

	// Set-Methode zur Instanzvariable "mByte".
	// Es wird die Länge des übergebenen boolean-Arrays "b" überprüft:
	//
	// wenn "b" genau gleichlang wie BYTESIZE, wird "b" 1:1 übernommen.
	//
	// wenn "b" länger als BYTESIZE, werden nur die ersten paar Werte (bis zu
	// BYTESIZE(8) ) übernommen und der Rest in einem boolean-Array "helper" an
	// den Aufrufer zurückgegeben. Lässt sich die länge von "b" nicht glatt
	// durch BYTESIZE teilen, werden entsprechend viele Nullen bzw. false
	// vorangestellt.
	//
	// wenn "b" kürzer als BYTESIZE werden Nullen vorangestellt.
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
	
	public Integer setByte(int x) {
		
		boolean[] ba = setByte(intToBoolArray(x));
		
		if(ba == null) {
			
			return null;
			
		} else {
			
			return boolArrayToInt(ba);
			
		}
		
		
		
	}

	private boolean charToBool(char c) {

		if (c == '1') {

			return true;

		} else {

			return false;

		}

	}
	
	private char boolToChar(boolean b) {
		
		if(b) {
			
			return '1';
			
		} else {
			
			return '0';
			
		}
		
	}
	
	private boolean[] intToBoolArray(int x) {
		
		String s = Integer.toBinaryString(x);
		
		boolean[] b = new boolean[s.length()];
		
		for(int i = 0; i < s.length(); i++) {
			
			b[i] = charToBool(s.charAt(i));
			
		}
		
		return b;
		
	}
	
	private int boolArrayToInt(boolean[] ba) {
		
		StringBuilder s = new StringBuilder();
		
		for(boolean b : ba) {
			
			s.append(boolToChar(b));
			
		}
		
		return Integer.parseInt(s.toString(), 2);
		
	}

	// toString() gibt ein Abbild der Instanzvariable "mByte" als String zurück.
	// Der String entspricht der Länge von "mByte" und besteht nur aus den
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
