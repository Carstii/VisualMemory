package memory;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MByte a = new MByte();
		boolean[] z = {false,true,true,false,false,true,true,true};
		a.setByte(z);
		
		boolean[] y = {false,false,false,false,false,false,false,false};
		MByte b = new MByte();
		b.setByte(y);
		
		boolean[] x = {true,true,true,true,true,true,true,true};
		MByte c = new MByte();
		c.setByte(x);
		
		Memory speicher = new Memory(480, true);
		
		
           for(int i = 0; i < speicher.getSpeicher().length; i++) {
			
			if(i%2 == 0 && i !=0) {
				//System.out.println("\n" + i);
				speicher.andMByte(i, false);
			}
			
		}
		
		speicher.getSpeicher()[58].setByte(z);
		
		System.out.println(speicher.toString());
		

	}

}
