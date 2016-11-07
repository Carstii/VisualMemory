package telnet;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class MainPanel extends JPanel {
	private JTextField textField;
	private JButton btnNewButton;

	/**
	 * Create the panel.
	 */
	public MainPanel() {
		
		JLabel lblNewLabel = new JLabel("Datei: ");
		add(lblNewLabel);
		
		textField = new JTextField();
		add(textField);
		textField.setColumns(10);
		
		btnNewButton = new JButton("Auswählen");
		add(btnNewButton);

	}

	public JTextField getTextField() {
		return textField;
	}

	public void setTextField(JTextField textField) {
		this.textField = textField;
	}

	public JButton getBtnNewButton() {
		return btnNewButton;
	}

	public void setBtnNewButton(JButton btnNewButton) {
		this.btnNewButton = btnNewButton;
	}
	
	

}
