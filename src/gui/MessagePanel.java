package gui;

import gui.listeners.ProgressDialogListener;

import java.awt.BorderLayout;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ExecutionException;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.SwingWorker;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import controller.StimulatedMessageServer;
import data.Message;

public class MessagePanel extends JPanel implements ProgressDialogListener {
	
	private static final long serialVersionUID = -4998576709728752117L;
	
	private JTree serverTree;
	private ServerTreeCellRenderer treeRenderer;
	private ServerTreeCellEditor treeEditor;
	
	private Set<Integer> selectedServers;
	private StimulatedMessageServer mServer;
	
	private SwingWorker<List<Message>, Integer> sw;
	
	private ProgressDialog progressDialog;
	private TextPanel textPanel;
	private JList<Message> msgList;
	private JSplitPane upperPane;
	private JSplitPane lowerPane;
	
	private DefaultListModel<Message> listModel;
	private MessageListRenderer msgListRenderer;
	
	protected MessagePanel(JFrame parent) {

		listModel = new DefaultListModel<Message>();
		progressDialog = new ProgressDialog(parent, "Downloading Messages...");
		progressDialog.setProgressDialogListener(this);
		
		treeRenderer = new ServerTreeCellRenderer();
		treeEditor = new ServerTreeCellEditor();
		
		selectedServers = new TreeSet<Integer>();
		mServer = new StimulatedMessageServer();
		
		serverTree = new JTree(createTree());
		serverTree.setCellRenderer(treeRenderer);
		serverTree.setCellEditor(treeEditor);
		serverTree.setEditable(true);
		
		serverTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		
		msgListRenderer = new MessageListRenderer();
		
		mServer.setSelectedServers(selectedServers);
		
		treeEditor.addCellEditorListener(new CellEditorListener() {
			
			@Override
			public void editingStopped(ChangeEvent e) {
				
				ServerInfo info = (ServerInfo)treeEditor.getCellEditorValue();
				
				if(info.isChecked()) {
					selectedServers.add(info.getId());
				}
				else if(!info.isChecked()) {
					selectedServers.remove(info.getId());
				}
				
				mServer.setSelectedServers(selectedServers);
				
				retrieveMessages();
			}
			
			@Override
			public void editingCanceled(ChangeEvent e) {
			}
		});
		
		setLayout(new BorderLayout());
		
		textPanel = new TextPanel();
		msgList = new JList<Message>(listModel);
		msgList.setCellRenderer(msgListRenderer);
		
		msgList.addListSelectionListener((e) -> {
			
			Message msg = msgList.getSelectedValue();

			if(msg != null) {
				textPanel.setText(String.format("Title: %s\n\n%s",
						msg.getTitle(), 
						msg.getText()));
			}
		});
		
		lowerPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, new JScrollPane(msgList), textPanel);
		upperPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, new JScrollPane(serverTree), lowerPane);
		
		//textPanel.setMinimumSize(new Dimension(10, 70));
		//msgList.setMinimumSize(new Dimension(10, 70));
		
		upperPane.setResizeWeight(0.3);
		lowerPane.setResizeWeight(0.5);
		
		lowerPane.setBorder(null);
		upperPane.setBorder(null);
		
		add(upperPane, BorderLayout.CENTER);
	}
	
	private void retrieveMessages() {
		
		progressDialog.setMaximum(mServer.getMessageCount());
		
		progressDialog.setVisible(true);
		
		sw = new SwingWorker<List<Message>, Integer>() {

			protected List<Message> doInBackground() throws Exception {
				
				int cnt = 0;
				
				List<Message> retrievedMessageList = new LinkedList<Message>();
				
				for(Message msg : mServer) {
					
					if(isCancelled()) {
						break;
					}
					
					//textPanel.appendText(msg.toString());
					retrievedMessageList.add(msg);
					cnt++;
					publish(cnt);
				}
				
				return retrievedMessageList;
			}

			protected void done() {
				
				progressDialog.setVisible(false);
				
				if(isCancelled()) {
					return;
				}
				
				try {
					
					List<Message> retrievedMessageList = get();
					listModel.removeAllElements();
					
					for(Message msg : retrievedMessageList) {
						listModel.addElement(msg);
					}
					
					msgList.setSelectedIndex(0);
					
				} catch (InterruptedException e) {
				} catch (ExecutionException e) {
				}
			}

			protected void process(List<Integer> msgCount) {
				
				progressDialog.setValue(msgCount.get(msgCount.size() - 1));
			}
		};
		
		sw.execute();
	}
	
	private DefaultMutableTreeNode createTree() {
		
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("Servers");
		
		DefaultMutableTreeNode branch0 = new DefaultMutableTreeNode("Poland");
		
		branch0.add(new DefaultMutableTreeNode(new ServerInfo(0, "Reda", selectedServers.contains(0))));
		branch0.add(new DefaultMutableTreeNode(new ServerInfo(1, "Gdynia", selectedServers.contains(1))));
		branch0.add(new DefaultMutableTreeNode(new ServerInfo(2, "Gdansk", selectedServers.contains(2))));
		
		DefaultMutableTreeNode branch1 = new DefaultMutableTreeNode("UK");
		
		branch1.add(new DefaultMutableTreeNode(new ServerInfo(3, "London", selectedServers.contains(3))));
		branch1.add(new DefaultMutableTreeNode(new ServerInfo(4, "Edinburgh", selectedServers.contains(4))));
		
		top.add(branch0);
		top.add(branch1);
		
		return top;
	}

	public void cancelButtonPressed() {
		if(sw != null) {
			sw.cancel(true);
		}
	}
	
	public void refresh() {
		retrieveMessages();
	}
}
