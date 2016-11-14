package memory;

public class MemoryObject {
	
	private int index;
	private int size;
	
	public MemoryObject(int index, int size) {
		
		this.index = index;
		this.size = size;
		
	}
	
	public int getIndex() {
		return index;
	}
	
	public void setIndex(int index) {
		this.index = index;
	}
	
	public int getSize() {
		return size;
	}
	
	public void setSize(int length) {
		this.size = length;
	}

}
