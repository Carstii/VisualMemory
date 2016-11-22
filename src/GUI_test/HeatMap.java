package GUI_test;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.tc33.jheatchart.HeatChart;

import memory.Memory;

public class HeatMap {
	double[] arrayOfMemory;
	double[][] arrayOfMemory2D;
	HeatChart chart;
	String auswahl = "";
	
    private JFrame frame = new JFrame();
	
	public HeatMap(Memory array, int row, int column, String auswahl) {
		this.auswahl = auswahl;
		this.setArray(array);
		createNewArray2D(this.arrayOfMemory, row, column);
		chart = new HeatChart(this.arrayOfMemory2D);
	}
	
	public void setArray(Memory array) {
		if(auswahl == "Bit") {
			this.arrayOfMemory = createDoubleBitArray(array.toString());
		}
		else if(auswahl == "Byte") {
			this.arrayOfMemory = createDoubleByteArray(array.toString());
		}
		else if(auswahl == "HeatMap") {
			this.arrayOfMemory = createDoubleHeatMapArray(array.toString());
		}
	}
	
	public double[] createDoubleBitArray(String value) {
		String s = value.replace(" ", "");
		s = s.replace("\n", "");
		double[] bytes = new double[s.length()];
		for(int i = 0; i < s.length(); i++) {
			
			bytes[i] = Double.parseDouble(""+s.charAt(i));
			
		}
		
		return bytes;
	}
	
	public double[] createDoubleByteArray(String value) {
		int count = 0;
		String s = value.replace(" ", "");
		s = s.replace("\n", "");
		double[] bytes = new double[s.length()];
		for(int i = 0; i < s.length(); i++) {
			
			bytes[i] = Double.parseDouble(""+s.charAt(i));
			
		}
		
		return bytes;
	}
	
	public double[] createDoubleHeatMapArray(String value) {
		String s = value.replace(" ", "");
		s = s.replace("\n", "");
		double[] bytes = new double[s.length()];
		for(int i = 0; i < s.length(); i++) {
			
			bytes[i] = Double.parseDouble(""+s.charAt(i));
			
		}
		
		return bytes;
	}
	
	public void createNewArray2D(double[] array, int row, int column) {
		int counter = 0;
		arrayOfMemory2D = new double[column][row];
		for(int y = 0; y < arrayOfMemory2D.length; y++){
    		
    		for(int x = 0; x < arrayOfMemory2D[0].length; x++) {
    			
    			arrayOfMemory2D[y][x] = array[counter];
    			//System.out.print(arrayOfMemory2D[y][x] + "+");
    			counter++;	
    		}	
    		//System.out.println();
    	} 
	}
	
	public void showHeatChart() throws IOException {
		frame.setTitle("Visual Memory - HeatMap");
		
		chart.setCellSize(new Dimension(1, 1));
		chart.setShowXAxisValues(false);
		chart.setShowYAxisValues(false);
		ImageIcon image = new ImageIcon(chart.getChartImage());
		chart.saveToFile(new File("TestNEU.png"));
		JLabel label = new JLabel("", image, JLabel.CENTER);
		JPanel panel = new JPanel(new BorderLayout());
		panel.add( label, BorderLayout.CENTER );

		System.out.println("FERTIG");	
	
		frame.add(panel);
				
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /*Vollbild*/
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        frame.setVisible(true);
	}
}

