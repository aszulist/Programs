package invoke;

import gui.MainFrame;

import javax.swing.SwingUtilities;


public class InvokeMainFrame {
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(() -> new MainFrame("VForm alpha 0.8.0"));
	}
}
