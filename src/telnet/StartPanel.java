package telnet;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class StartPanel extends JPanel {
	private JTextField textField;
	private JButton btnNewButton;
	private JLabel lblAuflsung;
	private JRadioButton rdbtn800;
	private JRadioButton rdbtn1024;
	private JRadioButton rdbtnMax;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Create the panel.
	 */
	public StartPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{85, 62, 116, 91, 0, 0};
		gridBagLayout.rowHeights = new int[]{25, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblNewLabel = new JLabel("IP-Adresse");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 0;
		add(lblNewLabel, gbc_lblNewLabel);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 0;
		add(textField, gbc_textField);
		textField.setColumns(10);
		
		lblAuflsung = new JLabel("Aufl\u00F6sung");
		GridBagConstraints gbc_lblAuflsung = new GridBagConstraints();
		gbc_lblAuflsung.insets = new Insets(0, 0, 5, 5);
		gbc_lblAuflsung.gridx = 1;
		gbc_lblAuflsung.gridy = 1;
		add(lblAuflsung, gbc_lblAuflsung);
		
		rdbtn800 = new JRadioButton("800x600");
		buttonGroup.add(rdbtn800);
		GridBagConstraints gbc_rdbtn800 = new GridBagConstraints();
		gbc_rdbtn800.fill = GridBagConstraints.HORIZONTAL;
		gbc_rdbtn800.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtn800.gridx = 2;
		gbc_rdbtn800.gridy = 1;
		add(rdbtn800, gbc_rdbtn800);
		
		rdbtn1024 = new JRadioButton("1024x768");
		buttonGroup.add(rdbtn1024);
		GridBagConstraints gbc_rdbtn1024 = new GridBagConstraints();
		gbc_rdbtn1024.fill = GridBagConstraints.HORIZONTAL;
		gbc_rdbtn1024.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtn1024.gridx = 3;
		gbc_rdbtn1024.gridy = 1;
		add(rdbtn1024, gbc_rdbtn1024);
		
		rdbtnMax = new JRadioButton("max");
		buttonGroup.add(rdbtnMax);
		GridBagConstraints gbc_rdbtnMax = new GridBagConstraints();
		gbc_rdbtnMax.fill = GridBagConstraints.HORIZONTAL;
		gbc_rdbtnMax.insets = new Insets(0, 0, 5, 0);
		gbc_rdbtnMax.gridx = 4;
		gbc_rdbtnMax.gridy = 1;
		add(rdbtnMax, gbc_rdbtnMax);
		
		btnNewButton = new JButton("Verbinden");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnNewButton.gridx = 4;
		gbc_btnNewButton.gridy = 2;
		add(btnNewButton, gbc_btnNewButton);

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
