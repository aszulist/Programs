package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import data.Message;

public class MessageListRenderer implements ListCellRenderer<Message> {

	private JLabel panelLabel;
	
	public MessageListRenderer() {
		
		panelLabel = new JLabel();
		
		try {
			panelLabel.setFont(Uti.createFont("/fonts/Existence-Light.ttf").deriveFont(Font.PLAIN, 15));
			
		} catch (FontFormatException e) {
			System.out.println("Wrong format");
		} catch (IOException e) {
			System.out.println("Font file is corrupted");
		}
		
		panelLabel.setIcon(Uti.createIcon("/images/msg.png", "Warn"));
	}
	
	@Override
	public Component getListCellRendererComponent(@SuppressWarnings("rawtypes") JList list, Message value,
			int index, boolean isSelected, boolean cellHasFocus) {
		
		panelLabel.setText(value.getTitle());
		//panelLabel.setBackground(cellHasFocus ? UIManager.getColor("Tree.selectionBackground") : Color.WHITE);
		panelLabel.setBackground(cellHasFocus ? Color.ORANGE : Color.WHITE);
		panelLabel.setOpaque(true);
		
		return panelLabel;
	}
}
