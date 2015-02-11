package gui;

import gui.listeners.ToolbarListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.border.EtchedBorder;


public class Toolbar extends JToolBar implements ActionListener{
	
	private static final long serialVersionUID = -3423439769286911843L;
	
	private JButton save;
	private JButton refresh;
	
	private ToolbarListener toolbarListener;
	
	protected Toolbar() {
		
		setFloatable(true);
		setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		
		save = new JButton();
		save.setIcon(Uti.createIcon("/images/s26vB.png", "Save"));
		save.setToolTipText("Save");
		
		refresh = new JButton();
		refresh.setIcon(Uti.createIcon("/images/r26vB.png", "Refresh"));
		refresh.setToolTipText("Refresh");
		
		save.addActionListener(this);
		refresh.addActionListener(this);
		
		save.setPreferredSize(refresh.getPreferredSize());
		
		add(save);
		//addSeparator();
		add(refresh);
	}

	public void setToolbarListener(ToolbarListener listener) {
		this.toolbarListener = listener;
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if (toolbarListener != null) {
			JButton clicked = (JButton)e.getSource();
			
			if (clicked == save) {
				toolbarListener.saveEventOccured();
			} else if (clicked == refresh) {
				toolbarListener.refreshEventOccured();
			}
		}
	}
}
