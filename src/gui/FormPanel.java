package gui;

import gui.listeners.FormListener;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

public class FormPanel extends JPanel {
	
	private static final long serialVersionUID = -8935448900550211127L;

	private JLabel nameLabel;
	private JLabel occupationLabel;
	private JLabel phoneLabel;
	private JTextField nameTextField;
	private JTextField occupationTextField;
	private JTextField phoneTextField;
	private JButton submitInfo;
	private FormListener formListener;
	private JList<AgeCategory> ageList;
	private JComboBox<String> empComboBox;
	private JCheckBox citizenCheck;
	private JTextField docIDTextField;
	private JLabel docIDLabel;
	private JRadioButton maleRadio;
	private JRadioButton femaleRadio;
	private ButtonGroup genderGroup;
	
	protected FormPanel() {
		
		Dimension dim = getPreferredSize();
		dim.width = 250;
		setPreferredSize(dim);
		setMinimumSize(dim);
		
		nameLabel = new JLabel("Name: ");
		occupationLabel = new JLabel("Occupation: ");
		phoneLabel = new JLabel("Phone number: ");
		
		nameTextField = new JTextField(10);
		occupationTextField = new JTextField(10);
		phoneTextField = new JTextField(10);
		
		maleRadio = new JRadioButton("Male");
		femaleRadio = new JRadioButton("Female");
		genderGroup = new ButtonGroup();
		
		submitInfo = new JButton("Submit");
		
		//set up ^
		
		maleRadio.setSelected(true);
		
		maleRadio.setActionCommand("Male");
		femaleRadio.setActionCommand("Female");
		
		genderGroup.add(maleRadio);
		genderGroup.add(femaleRadio);
		
		
		ageList = new JList<AgeCategory>();
		
		//JList set up
		
		DefaultListModel<AgeCategory> ageModel = new DefaultListModel<AgeCategory>();
		ageModel.addElement(new AgeCategory(0, "0-17"));
		ageModel.addElement(new AgeCategory(1, "18-40"));
		ageModel.addElement(new AgeCategory(2, "40+"));
		
		ageList.setPreferredSize(new Dimension(120, 66));
		ageList.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		ageList.setModel(ageModel);
		ageList.setSelectedIndex(0);
		
		empComboBox = new JComboBox<String>();
		
		//JComboBox set up
		
		DefaultComboBoxModel<String> empModel = new DefaultComboBoxModel<String>();
		empModel.addElement("Employed");
		empModel.addElement("Self-employed");
		empModel.addElement("Unemployed");
		empComboBox.setModel(empModel);
		empComboBox.setSelectedIndex(0);
		
		citizenCheck = new JCheckBox();
		docIDTextField = new JTextField(9);
		docIDLabel = new JLabel("Document ID: ");
		
		//set up ^
		
		docIDLabel.setEnabled(false);
		docIDTextField.setEnabled(false);
		
		citizenCheck.addActionListener((e) -> {
			
			//boolean isTicked = citizenCheck.isSelected();
			docIDTextField.setEnabled(citizenCheck.isSelected());
			docIDLabel.setEnabled(citizenCheck.isSelected());
		});
		
		//Mnemonics
		nameLabel.setDisplayedMnemonic(KeyEvent.VK_N);
		nameLabel.setLabelFor(nameTextField);
		
		occupationLabel.setDisplayedMnemonic(KeyEvent.VK_O);
		occupationLabel.setLabelFor(occupationTextField);
		
		phoneLabel.setDisplayedMnemonic(KeyEvent.VK_H);
		phoneLabel.setLabelFor(phoneTextField);
		
		submitInfo.setMnemonic(KeyEvent.VK_ENTER);
		
		//Submit set up
		
		submitInfo.addActionListener((e) -> {
				
			String name = nameTextField.getText();
		 	String occupation = occupationTextField.getText();
		 	AgeCategory age = ageList.getSelectedValue();
		 	
		 	String employment = (String)empComboBox.getSelectedItem();
		 	
		 	boolean plCitizen = citizenCheck.isSelected();
		 	String nrDow = docIDTextField.getText();
		 	
		 	String genderCommand = genderGroup.getSelection().getActionCommand();
		 	
		 	String phoneNumber = phoneTextField.getText();

			FormEvent ev = new FormEvent(this, name, occupation, age.getId(), employment, plCitizen, nrDow, genderCommand, phoneNumber);
			
			if(formListener != null) {
				formListener.formEventOccured(ev);
			}
			
			//RESET
			nameTextField.setText(null);
			occupationTextField.setText(null);
			phoneTextField.setText(null);
			ageList.setSelectedIndex(0);
			empComboBox.setSelectedIndex(0);
			docIDTextField.setText(null);
			citizenCheck.setSelected(false);
			docIDLabel.setEnabled(false);
			docIDTextField.setEnabled(false);
			maleRadio.setSelected(true);
		});
		
		Border innerBorder = BorderFactory.createTitledBorder("FormPanel");
		Border outerBorder = BorderFactory.createEmptyBorder(1, 1, 1, 1);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		layoutComponents();
	}
	
	public void layoutComponents() {
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		
		
		//Insets blankInset = new Insets(0, 0, 0, 0);
		//Insets fiveFromRightInset = new Insets(0, 0, 0, 5);
		
		gc.gridy = 0;
		
		gc.weightx = 1;
		gc.weighty = 0.05;
		
		//////////////ROW 1
		gc.gridx = 0;
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		add(nameLabel, gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		add(nameTextField, gc);

		//////////////ROW++
		gc.gridy++;
		
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		add(occupationLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		add(occupationTextField, gc);

		// ////////////ROW++
		gc.gridy++;

		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		add(phoneLabel, gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		add(phoneTextField, gc);

		//////////////ROW++
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 0.02;
		
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(new JLabel("Age: "), gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(ageList, gc);
		
		// ////////////ROW++
		gc.gridy++;

		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		add(new JLabel("Employment: "), gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(empComboBox, gc);
		
		// ////////////ROW++
		gc.gridy++;

		gc.gridx = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(new JLabel("Polish Citizen: "), gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(citizenCheck, gc);
		
		// ////////////ROW++
		gc.gridy++;

		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		add(docIDLabel, gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(docIDTextField, gc);
		
		// ////////////ROW++
		gc.gridy++;

		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		add(new JLabel("Gender: "), gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(maleRadio, gc);
		
		// ////////////ROW++
		gc.gridy++;

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(femaleRadio, gc);

		//////////////ROW++
		gc.gridy++;
		
		gc.weighty = 1;
		gc.gridx = 1;
		add(submitInfo, gc);
	}
	
	public void setFormListener(FormListener listener) {
		this.formListener = listener;
	}
}