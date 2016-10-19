package memory;

public class Memory {

	private MByte[] speicher;

	
	public Memory(int size) {
		//System.out.println("Memory Constructor 1");

		speicher = new MByte[size];

		for (int i = 0; i < speicher.length; i++) {

			speicher[i] = new MByte();

		}

	}

	public Memory(int size, boolean init) {
		//System.out.println("Memory Constructor 2");
		this(size);
		
		for (int i = 0; i < speicher.length; i++) {

			speicher[i] = new MByte(init);

		}
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	public Memory(int size, MByte pattern) {
		//System.out.println("Memory Constructor 3");
		
		this(size);

		for (int i = 0; i < speicher.length; i++) {

			speicher[i] = new MByte(pattern);
			
		}

	}

	public MByte[] getSpeicher() {
		return speicher;
	}

	public void setSpeicher(MByte[] speicher) {
		this.speicher = speicher;
	}

	public void andMByte(int index, MByte pattern) {

		this.getSpeicher()[index].andMByte(pattern);

	}

	public void andMByte(int index, boolean b) {

		this.getSpeicher()[index].andMByte(b);
		
	}

	public void orMByte(int index, MByte pattern) {

		this.getSpeicher()[index].orMByte(pattern);

	}

	public void orMByte(int index, boolean b) {
						
		this.getSpeicher()[index].orMByte(b);
		
	}
	
	public void xorMByte(int index, MByte pattern) {

		this.getSpeicher()[index].xorMByte(pattern);

	}

	public void xorMByte(int index, boolean b) {
						
		this.getSpeicher()[index].xorMByte(b);
		
	}
	
	public String toString() { // gibt ein Abbild des Speichers als String
								// zurück

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < speicher.length; i++) {

			if (i % 30 == 0 && i != 0) { // Zeilenumbruch nach 40 Zeichen

				sb.append("\n");

			}

			sb.append(speicher[i].toString());

		}

		return sb.toString();

	}

}
