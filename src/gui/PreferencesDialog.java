package gui;

import gui.listeners.PreferencesListener;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

public class PreferencesDialog extends JDialog{

	private static final long serialVersionUID = 3195174470241088921L;
	
	private JButton submitButton;
	private JButton cancelButton;
	private JSpinner portSpinner;
	private SpinnerNumberModel spinnerModel;
	private JTextField userField;
	private JPasswordField passField;
	private PreferencesListener preferencesListener;
	
	protected PreferencesDialog(JFrame owner) {
		
		super(owner, "Preferences", false);
		
		submitButton = new JButton("Ok");
		cancelButton = new JButton("Cancel");
		
		spinnerModel = new SpinnerNumberModel(3306, 0, 9999, 1);
		portSpinner = new JSpinner(spinnerModel);
		
		userField = new JTextField(10);
		passField = new JPasswordField(10);
		
		passField.setEchoChar('*');
		
		layoutControls();
		
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(preferencesListener != null) {
					preferencesListener.preferencesSet(userField.getText(),
							new String(passField.getPassword()),
							(Integer)portSpinner.getValue());
				}

				setVisible(false);
			}
		});
		
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		
		setSize(270,250);
		setLocationRelativeTo(owner);
	}
	
	public void setPreferencesListener(PreferencesListener listener) {
		this.preferencesListener = listener;
	}
	
	public void setDefault(String username, String password, int port) {
		
		userField.setText(username);
		passField.setText(password);
		portSpinner.setValue(port);
	}
	
	public void layoutControls() {
		
		JPanel controlsPanel = new JPanel();
		JPanel buttonsPanel = new JPanel();

		controlsPanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createEmptyBorder(2, 2, 2, 2),
				BorderFactory.createTitledBorder("Database Preferences")));
		
		Dimension btnSize = cancelButton.getPreferredSize();
		submitButton.setPreferredSize(btnSize);
		
		controlsPanel.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		Insets toRight =  new Insets(0, 0, 0, 10);
		Insets none =  new Insets(0, 0, 0, 0);
		
		gc.weightx = 1;
		gc.weighty = 1;
		gc.gridy = 0;
		
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.EAST;
		gc.insets = toRight;
		
		controlsPanel.add(new JLabel("Username: "), gc);
		
		gc.gridx++;
		gc.anchor = GridBagConstraints.WEST;
		gc.insets = none;
		
		controlsPanel.add(userField, gc);
		
		//ROW++
		
		gc.gridy++;
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.EAST;
		gc.insets = toRight;
		
		controlsPanel.add(new JLabel("Password: "), gc);
		
		gc.gridx++;
		gc.anchor = GridBagConstraints.WEST;
		gc.insets = none;
		
		controlsPanel.add(passField, gc);
		
		//ROW++
		
		gc.gridy++;
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.EAST;
		gc.insets = toRight;
		
		controlsPanel.add(new JLabel("Port: "), gc);
		
		gc.gridx++;
		gc.anchor = GridBagConstraints.WEST;
		gc.insets = none;
		
		controlsPanel.add(portSpinner, gc);
		
		//ROW++ // PANEL SWITCH // BUTTONS
		
		buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		buttonsPanel.add(submitButton);
		buttonsPanel.add(cancelButton);
		
		//Layout
		
		setLayout(new BorderLayout());
		add(controlsPanel, BorderLayout.CENTER);
		add(buttonsPanel, BorderLayout.SOUTH);
	}
}
