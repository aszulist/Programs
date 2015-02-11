package gui;

import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;

public class ServerTreeCellRenderer implements TreeCellRenderer {

	private JCheckBox leafRenderer;
	private DefaultTreeCellRenderer cellRenderer; //non leaf renderer
	
	public ServerTreeCellRenderer() {
		
		leafRenderer = new JCheckBox();
		cellRenderer = new DefaultTreeCellRenderer();
		
		cellRenderer.setLeafIcon(Uti.createIcon("/images/database20.png", null));
		cellRenderer.setOpenIcon(Uti.createIcon("/images/minusServer.png", null));
		cellRenderer.setClosedIcon(Uti.createIcon("/images/plusServer.png", null));
	}
	
	public Component getTreeCellRendererComponent(JTree tree, Object value,
			boolean selected, boolean expanded, boolean leaf, int row,
			boolean hasFocus) {
		
		if(leaf) {
			
			if(selected) {
				leafRenderer.setForeground(UIManager.getColor("Tree.selectionForeground"));
				leafRenderer.setBackground(UIManager.getColor("Tree.selectionBackground"));
			} else {
				leafRenderer.setForeground(UIManager.getColor("Tree.textForeground"));
				leafRenderer.setBackground(UIManager.getColor("Tree.textBackground"));
			}
			
			try {
				ServerInfo info = (ServerInfo)((DefaultMutableTreeNode)value).getUserObject();
				leafRenderer.setText(info.toString());
				leafRenderer.setSelected(info.isChecked());
				
			} catch(NullPointerException e) {
				System.err.println("ServerTreeCellRenderer failure");
			}
			
			return leafRenderer;
		}
		else {
			return cellRenderer.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
		}
	}
}
