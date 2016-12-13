package gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import memory.MByte;
import memory.Memory;

public class Menu {

	private Memory mem;

	private JButton btnOK = new JButton("OK");
	private JPanel panelAuflosung = new JPanel();
	private JPanel panelAnsicht = new JPanel();
	private ButtonGroup bgAuflosung = new ButtonGroup();
	private ButtonGroup bgAnsicht = new ButtonGroup();
	
	private int width;
	private int height;

	private MemWindow memWindow;
	private MemHeatMap heatWindow;

	private JFrame frame = new JFrame();

	public Menu() {

		Dimension panelDim = new Dimension(600, 200);

		frame.setTitle("Visual Memory - Menü");
		frame.setLocationRelativeTo(null);

		panelAuflosung.setMinimumSize(panelDim);
		panelAuflosung.setMaximumSize(panelDim);
		panelAuflosung.setPreferredSize(panelDim);

		panelAnsicht.setMinimumSize(panelDim);
		panelAnsicht.setMaximumSize(panelDim);
		panelAnsicht.setPreferredSize(panelDim);

		frame.setLayout(new GridLayout());
		frame.add(panelAuflosung);
		frame.add(panelAnsicht);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);

		buildAuflosungContent();
	}

	public Memory getMemory() {

		return mem;

	}

	public MemWindow getMemWindow() {

		return memWindow;

	}

	public MemHeatMap getHeatWindow() {

		return heatWindow;

	}
	
	public void switchView() {
		
		if(heatWindow == null) {
			
			memWindow = null;
			
			heatWindow = new MemHeatMap(width * MByte.BYTESIZE, height, 1, mem.getHeatmap());
			heatWindow.start();
			
		} else if(memWindow == null) {
			
			heatWindow = null;
			
			memWindow = new MemWindow(width * MByte.BYTESIZE, height, 1, mem.getMemory());
			memWindow.start();
			
		}
		
	}

	public void showGUI() {
		frame.setVisible(true);
	}
		
	private void buildAuflosungContent() {

		JRadioButton radioA = new JRadioButton("800x600");
		radioA.setActionCommand("800x600");

		JRadioButton radioB = new JRadioButton("1920x1080");
		radioB.setActionCommand("1920x1080");

		JRadioButton radioC = new JRadioButton("2560x1440");
		radioC.setActionCommand("2560x1440");

		JRadioButton radioD = new JRadioButton("4096x2160");
		radioD.setActionCommand("4096x2160");

		bgAuflosung.add(radioA);
		bgAuflosung.add(radioB);
		bgAuflosung.add(radioC);
		bgAuflosung.add(radioD);

		radioA.setSelected(true);

		panelAuflosung.add(radioA);
		panelAuflosung.add(radioB);
		panelAuflosung.add(radioC);
		panelAuflosung.add(radioD);
		panelAuflosung.add(btnOK);

		JRadioButton radioAnsA = new JRadioButton("Pixelansicht");
		radioAnsA.setActionCommand("Pixelansicht");

		JRadioButton radioAnsB = new JRadioButton("HeatMap");
		radioAnsB.setActionCommand("HeatMap");

		bgAnsicht.add(radioAnsA);
		bgAnsicht.add(radioAnsB);

		radioAnsA.setSelected(true);

		panelAnsicht.add(radioAnsA);
		panelAnsicht.add(radioAnsB);

		btnOK.addActionListener(new ShowDialog());
		btnOK.setActionCommand("OK");
		frame.getContentPane().add(panelAuflosung);
		frame.getContentPane().add(panelAnsicht);
	}

	public class ShowDialog implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent aEvent) {

			String cs = bgAuflosung.getSelection().getActionCommand();

			System.out.println(Integer.parseInt(cs.substring(0, cs.indexOf('x'))) + " x "
					+ Integer.parseInt(cs.substring(cs.indexOf('x') + 1)));

			width = Integer.parseInt(cs.substring(0, cs.indexOf('x'))) / MByte.BYTESIZE;
			height = Integer.parseInt(cs.substring(cs.indexOf('x') + 1));

			mem = new Memory(width * height);
			Thread memoryThread = new Thread(mem);
			memoryThread.start();
			
			System.out.println(mem.getSize() + " Bits");

			if (bgAnsicht.getSelection().getActionCommand() == "Pixelansicht") {

				memWindow = new MemWindow(width * MByte.BYTESIZE, height, 1, mem.getMemory());
				memWindow.start();

			} else if (bgAnsicht.getSelection().getActionCommand() == "HeatMap") {

				heatWindow = new MemHeatMap(width * MByte.BYTESIZE, height, 1, mem.getHeatmap());
				heatWindow.start();

			}
			
			frame.setEnabled(false);
			frame.setVisible(false);

//			panelAuflosung.removeAll();
//			panelAnsicht.removeAll();
//			panelAuflosung.repaint();
//			panelAnsicht.repaint();

		}
	}
}
