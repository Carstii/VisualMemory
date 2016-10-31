package memory;

import java.util.ArrayList;

public class File {

	private String name;
	private ArrayList<Integer> index;
	private int size;

	public File(String name) {

		setName(name);
		index = new ArrayList<Integer>();
		setSize();

	}

	public File(String name, int size) {

		setName(name);
		index = new ArrayList<Integer>(size);
		setSize();

	}

	public File(String name, ArrayList<Integer> index) {

		setName(name);
		setIndex(index);
		setSize();

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {

		this.name = name;

	}

	public ArrayList<Integer> getIndex() {
		return index;
	}

	public void setIndex(ArrayList<Integer> index) {
		this.index = index;
	}

	public int getSize() {
		setSize();
		return size;
	}

	public void setSize() {

		if (index == null) {

			size = 0;

		} else {

			this.size = index.size();

		}
		
	}

}
