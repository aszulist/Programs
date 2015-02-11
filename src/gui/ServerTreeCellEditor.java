package gui;

import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellEditor;
import javax.swing.tree.TreePath;

public class ServerTreeCellEditor extends AbstractCellEditor implements TreeCellEditor {

	private static final long serialVersionUID = 3962807358314959609L;

	private ServerTreeCellRenderer stcr;
	private JCheckBox checkBox;
	private ServerInfo srvInfo;
	
	protected ServerTreeCellEditor() {
		stcr = new ServerTreeCellRenderer();
	}
	
	@Override
	public Component getTreeCellEditorComponent(JTree tree, Object value,
			boolean isSelected, boolean expanded, boolean leaf, int row) {
	
		if (leaf) {
			
			srvInfo = (ServerInfo)((DefaultMutableTreeNode)value).getUserObject();
			
			checkBox = 
					(JCheckBox)stcr.getTreeCellRendererComponent(tree, value, isSelected, expanded, leaf, row, true);

			checkBox.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					
					fireEditingStopped();
					checkBox.removeItemListener(this);
				}
			});
		}
		
		return stcr.getTreeCellRendererComponent(tree, value, isSelected, expanded, leaf, row, true);
	}
	
	@Override
	public Object getCellEditorValue() {
		
		srvInfo.setIsChecked(checkBox.isSelected());
		return srvInfo;
	}

	@Override
	public boolean isCellEditable(EventObject ev) {
		
		if(!(ev instanceof MouseEvent)) {
			return false;
		}
		
		MouseEvent mouseEv = (MouseEvent)ev;
		JTree tree = (JTree)ev.getSource();
		
		TreePath path = tree.getPathForLocation(mouseEv.getX(), mouseEv.getY());
		
		if(path == null || path.getLastPathComponent() == null) {
			return false;
		}
	
		DefaultMutableTreeNode treeNode =
				(DefaultMutableTreeNode) path.getLastPathComponent();
		
		return treeNode.isLeaf();
	}
}
