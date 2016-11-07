package telnet;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class StartPanel extends JPanel {
	private JTextField textField;
	private JButton btnNewButton;

	/**
	 * Create the panel.
	 */
	public StartPanel() {
		
		JLabel lblNewLabel = new JLabel("IP-Adresse");
		add(lblNewLabel);
		
		textField = new JTextField();
		add(textField);
		textField.setColumns(10);
		
		btnNewButton = new JButton("Verbinden");
		add(btnNewButton);

	}

	public JButton getBtnNewButton() {
		return btnNewButton;
	}

	public void setBtnNewButton(JButton btnNewButton) {
		this.btnNewButton = btnNewButton;
	}

	public JTextField getTextField() {
		return textField;
	}

	public void setTextField(JTextField textField) {
		this.textField = textField;
	}
	
}
