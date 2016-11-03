package GUI_test;

import java.awt.BorderLayout;
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
	
    private JFrame frame = new JFrame();
	
	public HeatMap(Memory array, int row, int column) {
		this.setArray(array);
		createNewArray2D(this.arrayOfMemory, row, column);
		chart = new HeatChart(this.arrayOfMemory2D);
	}
	
	public void setArray(Memory array) {
		this.arrayOfMemory = createDoubleArray(array.toString());
	}
	
	public double[] createDoubleArray(String value) {
		String[] tmp = value.split(" ");
		double[] bytes = new double[tmp.length];
		
		for(int i= 0; i<tmp.length;i++) {
			bytes[i] = Double.parseDouble(tmp[i]);
		}
		return bytes;
	}
	
	public void createNewArray2D(double[] array, int row, int column) {
		int counter = 0;
		arrayOfMemory2D = new double[row][column];
		for(int x = 0; x < arrayOfMemory2D.length; x++){
    		
    		for(int y = 0; y < arrayOfMemory2D[0].length; y++) {
    			
    			arrayOfMemory2D[x][y] = array[counter];
    			counter++;	
    		}	
    	} 
	}
	
	public void showHeatChart() throws IOException {
		frame.setTitle("Visual Memory - HeatMap");

		ImageIcon image = new ImageIcon(chart.getChartImage());
		JLabel label = new JLabel("", image, JLabel.CENTER);
		JPanel panel = new JPanel(new BorderLayout());
		panel.add( label, BorderLayout.CENTER );

		System.out.println("FERTIG");	
	
		frame.add(panel);
				
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
	}
}
