package memory;

public class MByte {

	public static final int BYTESIZE = 8;

	private boolean[] mByte;

	public MByte(){
		
//		System.out.println("constructor 1");

		
		setByte(new boolean[BYTESIZE]);
		
	}
	
	public MByte(boolean init) {
		this();

//		System.out.println("constructor 2");
		
		
		if(init) {
		orMByte(init);
		}

	}
	
	public MByte(MByte pattern) {

		mByte = pattern.getByte();
//		System.out.println("constructor 3\t" + this.toString());

		
	}

	public boolean[] getByte() {
		return mByte;
	}

	public void setByte(boolean[] b) {
		mByte = b;
	}

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
	
	private boolean xor(boolean x, boolean y) {
		
		return ((x || y) && ! (x && y));
		
	}
	
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
