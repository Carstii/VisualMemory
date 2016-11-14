package GUI_test;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import memory.Memory;

public class Menu {
	
	private Memory mem;
	private HeatMap chart;
	private TelnetAbfrage ta;
	    
    private JLabel lbl = new JLabel("Auflösung: ");
    private JButton btnOK  = new JButton("OK");
    private JPanel panel = new JPanel();

    private JFrame frame = new JFrame();
    
    
    public Menu() {
    	Dimension d = new Dimension(600, 300);
    	
    	frame.setTitle("Visual Memory - Menü");
    	frame.setLocationRelativeTo(null);
    	
    	panel.setMinimumSize(d);
    	panel.setMaximumSize(d);
    	panel.setPreferredSize(d);
        
        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        
        buildContent(frame);
    }
    
    public void showGUI() {
    	frame.setVisible(true);
    }
    
    private void buildContent(JFrame aFrame) {
    	   	
    	btnOK.addActionListener(new ShowDialog(aFrame));
    	
    	panel.add(lbl);
    	panel.add(btnOK);
    	
    	frame.getContentPane().add(panel);
    }
    
    public class ShowDialog implements ActionListener {
    	JFrame frame;
    	
    	ShowDialog(JFrame aFrame) {
    		this.frame = aFrame;
    	}
    	@Override
    	public void actionPerformed(ActionEvent aEvent) {
        	ta = new TelnetAbfrage(0);
        	int row = 0;
        	int column = 0;
        	
    		int resolution = ta.getResolutionSize();
    		lbl.setText(ta.getResolutionText());
    		row = ta.getRow();
    		column = ta.getColumn();
    		
        	mem = new Memory(resolution);
        	
        	for(int i = 0; i < 5000; i++){
        		
        		mem.addFile("Textdatei", "Hallo Welt! djahiugfhfkaondcjfsadncoidshnmefkkhgd");
        	}
        	
        	
        	chart = new HeatMap(mem, row, column);
        	
        	try {
				chart.showHeatChart();
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    }
}
