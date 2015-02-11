package gui;

import gui.listeners.ProgressDialogListener;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

public class ProgressDialog extends JDialog{

	private static final long serialVersionUID = 6789319580652465647L;

	private JButton cancelButton;
	private JProgressBar progressBar;
	
	private ProgressDialogListener progressListener;
	
	protected ProgressDialog(Window parent, String title) {
		
		super(parent, title, ModalityType.APPLICATION_MODAL);
		
		cancelButton = new JButton("Cancel");
		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		progressBar.setString("Retrieving Messages...");
		
		setLocationRelativeTo(parent);
		setSize(400, 200);
		setResizable(false);
		
		setLayout(new FlowLayout());
		
		Dimension dim = cancelButton.getPreferredSize();
		dim.width = 400;
		
		progressBar.setPreferredSize(dim);
		
		add(progressBar);
		add(cancelButton);

		cancelButton.addActionListener((e) -> {
			if(progressListener != null) {
				progressListener.cancelButtonPressed();
			}
		});
		
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent arg0) {
				if(progressListener != null) {
					progressListener.cancelButtonPressed();
				}
			}
		});
		
		pack();
	}
	
	public void setMaximum(int maximum) {
		progressBar.setMaximum(maximum);
	}
	
	public void setValue(int value) {
		
		progressBar.setString(String.format("%d %% completed", 100 * value / progressBar.getMaximum()));
		progressBar.setValue(value);
	}
	
	@Override
	public void setVisible(boolean b) {
		
		SwingUtilities.invokeLater(() -> {
		
			if(!b) {
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					System.out.println("setVisible ProgressDialog");
				} finally {
					setCursor(Cursor.getDefaultCursor());
				}
			} else {
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				progressBar.setValue(0);
			}
		
			ProgressDialog.super.setVisible(b);
		});
	}
	
	public void setProgressDialogListener(ProgressDialogListener listener) {
		this.progressListener = listener;
	}
}