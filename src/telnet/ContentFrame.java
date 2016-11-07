package telnet;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;

public class ContentFrame extends JFrame {

	private CardLayout cl;
	private JPanel contentPane;
	private StartPanel startPanel;
	private MainPanel mainPanel;
	
	private Client client;
	
	/**
	 * Create the frame.
	 */
	public ContentFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(350, 300);
		setLocationRelativeTo(null);
		
		cl = new CardLayout();
		
		contentPane = new JPanel(cl);
		
		startPanel = new StartPanel();
		mainPanel = new MainPanel();
		
		contentPane.add(startPanel, "START");
		contentPane.add(mainPanel, "MAIN");
		
		
		setContentPane(contentPane);
		
		initButtons();
		
		cl.show(contentPane, "START");
		
	}
	
	public void initButtons(){
		startPanel.getBtnNewButton().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				String address = startPanel.getTextField().getText();
				
				if(address.isEmpty()){
					JOptionPane.showMessageDialog(null, "Ungültige Adresse");
				}else{
					try {
						client = new Client(address, 23);
						
						cl.show(contentPane, "MAIN");
					} catch (UnknownHostException e) {
						JOptionPane.showMessageDialog(null, "Host nicht gefunden!");
						e.printStackTrace();
					} catch (IOException e) {
						JOptionPane.showMessageDialog(null, "Beim Aufbau der Verbindung ist ein Feher aufgetreten");
						e.printStackTrace();
					}
				}
			}
		});
		
		mainPanel.getBtnNewButton().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				JFileChooser chooser = new JFileChooser();
		        
		        chooser.showOpenDialog(null);
		        
		        File f = chooser.getSelectedFile();
		        if( f != null){
		        	try {
						client.sendFileToServer(chooser.getSelectedFile());
			        }catch(FileNotFoundException e){
			        	JOptionPane.showMessageDialog(null, "Die von ihnen ausgewaehlte Datei konnte nicht gefunden werden!");
			        	e.printStackTrace();
			        }catch (IOException e) {
						JOptionPane.showMessageDialog(null, "Fehler beim Laden der Datei");
						e.printStackTrace();
					}
		        }
			}
		});
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ContentFrame frame = new ContentFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
