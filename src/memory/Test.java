package memory;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//
		// MByte a = new MByte();
		// boolean[] z = {false,true,true,false,false,true,true,true};
		// a.setByte(z);
		//
		// boolean[] y = {false,false,false,false,false,false,false,false};
		// MByte b = new MByte();
		// b.setByte(y);
		//
		// boolean[] x = {true,true,true,true,true,true,true,true};
		// MByte c = new MByte();
		// c.setByte(x);
		//
		// Memory speicher = new Memory(480, true);
		//
		//
		// for(int i = 0; i < speicher.getMemory().length; i++) {
		//
		// if(i%2 == 0 && i !=0) {
		// //System.out.println("\n" + i);
		// speicher.andMByte(i, false);
		// }
		//
		// }
		//
		// speicher.getMemory()[58].setByte(z);
		//
		// speicher.orMByte(300, 178);
		// speicher.andMByte(321, 117);
		// speicher.xorMByte(200, 235);
		//
		// speicher.orMByte(250, 1485621);
		// speicher.xorMByte(479, 857231962);
		// speicher.xorMByte(100, 941203747);
		//
		// System.out.println(speicher.toString());
		//

//		MByte[] mb = new MByte[10];
//
//		for (int i = 0; i < mb.length; i++) {
//
//			mb[i] = new MByte();
//
//		}
//
//		for (MByte m : mb) {
//
//			System.out.println(m.toString());
//
//		}
//
//		System.out.println();
//
//		Integer i = 123456789;
//		
//		int index = 1;
//		
//		while(i != null) {
//			
//			i = mb[index].setByte(i);
//			index++;
//		}
//
//		for (MByte m : mb) {
//
//			System.out.println(m.toString());
//
//		}
		
		Memory mem = new Memory(90);
		
		System.out.println(mem.toString());
		
		int[] x = new int[20];
		
		for(int i = 0; i < 20; i++) {
			
			x[i] = 250 + i;
			
		}
		
		mem.addFile("File1");
		mem.addFile("File2", 123456789);
		mem.addFile("Textdatei", "Hallo Welt! Hier bin Ich!!!");
		mem.addFile("file3", x);
		
		System.out.println();
		
		System.out.println(mem.toString());

	}

}
