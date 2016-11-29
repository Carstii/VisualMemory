package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import memory.Memory;

public class Menu {
	
	private Memory mem;
	private HeatMap chart;
	private TelnetAbfrage ta;
	    
    private JLabel lbl = new JLabel("Aufl�sung: ");
    private JButton btnOK  = new JButton("OK");
    
    private ButtonGroup group = new ButtonGroup();
    private JRadioButton rbByte = new JRadioButton("Byte = grau");
    private JRadioButton rbBit = new JRadioButton("Bit = schwarz/wei�");
    private JRadioButton rbHeatMap = new JRadioButton("HeatMap");
    
    private JPanel panel = new JPanel();

    private JFrame frame = new JFrame();
    
    
    public Menu(TelnetAbfrage ta) {
    	Dimension d = new Dimension(600, 300);
    	this.ta = ta;
    	
    	frame.setTitle("Visual Memory - Men�");
    	frame.setLocationRelativeTo(null);
    	
    	group.add(rbBit);
    	group.add(rbByte);
    	group.add(rbHeatMap);
    	
    	panel.add(rbBit);
    	panel.add(rbByte);
    	panel.add(rbHeatMap);
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
    	
    	lbl.setText("Aufl�sung: " + ta.getResolutionText());
    	
    	frame.getContentPane().add(panel);
    }
    
    public class ShowDialog implements ActionListener {
    	JFrame frame;
    	
    	ShowDialog(JFrame aFrame) {
    		this.frame = aFrame;
    	}
    	@Override
    	public void actionPerformed(ActionEvent aEvent) {
        	
        	int row = 0;
        	int column = 0;
        	
    		int resolution = ta.getResolutionSize();
    		System.out.println(resolution);

    		row = ta.getRow();
    		column = ta.getColumn();
    		
        	mem = new Memory(resolution);
        	
        	for(int i = 0; i < (1920*1080); i+=10){
        		mem.placeObject(i, "Hallo Welt! djahiugfhfkaondcjfsadncoidshnmefiuggutdztsrdfsiugRFDF5sdgjwGDFZSfd");
        	}
        	//System.out.println(mem.toString());
        	
        	if(rbBit.isSelected() == true) {
        		chart = new HeatMap(mem, row, column, "Bit");
        	}
        	else if(rbByte.isSelected() == true) {
        		chart = new HeatMap(mem, row, column, "Byte");
        	}
        	else if(rbHeatMap.isSelected() == true) {
        		chart = new HeatMap(mem, row, column, "HeatMap");
        	}
        	
        	try {
				chart.showHeatChart();
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    }
}
