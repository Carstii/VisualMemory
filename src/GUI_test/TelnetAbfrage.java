package GUI_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TelnetAbfrage {
	
	private int input;
	private int resolutionSize;
	private String resolutionText;
	private int row;
	private int column;
	Menu men;
	
	BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
	
	TelnetAbfrage() {
		System.out.println("Bitte waehlen Sie eine Aufloesung!");
		System.out.println("1: 1920 x 1080");
		System.out.println("2: 1024 x 768");
		System.out.println("3: 800 x 600");
		
		try {
			
			input = Integer.parseInt(buffer.readLine());
			System.out.println(input);
			setResolutionSize(Resolution(input));
		} catch (NumberFormatException e) {
			System.out.println("Falsche Eingabe");
		} catch (IOException e) {
			System.out.println("Falsche Eingabe");
		}
	
		men = new Menu(this);
		men.showGUI();
	}
	
	public int getResolutionSize() {
		return resolutionSize;
	}

	public void setResolutionSize(int resolutionSize) {
		this.resolutionSize = resolutionSize;
	}

	public String getResolutionText() {
		return resolutionText;
	}

	public void setResolutionText(String resolutionText) {
		this.resolutionText = resolutionText;
	}

	private int Resolution(int menu) {
		int size = 0;
		String text = "";
		int row = 0;
		int column = 0;
		
		switch (input) {
			case 1: size = 1980 * 1080; text = "1920 x 1080"; row = 1980; column = 1080; break;
			case 2: size = 1024 * 768; text = "1024 x 768"; row = 1024; column = 768; break;
			case 3: size = 800 * 600; text = "800 x 600"; row = 800; column = 600; break;
		}
		
		setResolutionText(text);
		setRow(row);
		setColumn(column);
		
		return size;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}
}
