package controller;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import data.Message;

public class StimulatedMessageServer implements Iterable<Message> {

	private Map<Integer, LinkedList<Message>> messages;
	
	private LinkedList<Message> selected;

	public StimulatedMessageServer() {

		messages = new TreeMap<Integer, LinkedList<Message>>();
		selected = new LinkedList<Message>();

		LinkedList<Message> list = new LinkedList<Message>();

		list.add(new Message("Message 0", "Text of message 0, server 0"));
		list.add(new Message("Message 1", "Text of message 1, server 0"));
		list.add(new Message("Message 2", "Text of message 2, server 0"));
		list.add(new Message("Message 3", "Text of message 3, server 0"));
		list.add(new Message("Message 4", "Text of message 4, server 0"));
		list.add(new Message("Message 5", "Text of message 5, server 0"));

		messages.put(0, list);

		/*list = new LinkedList<Message>();

		list.add(new Message("Message 0", "Text of message 0, server 1"));
		list.add(new Message("Message 1", "Text of message 1, server 1"));
		
		messages.put(1, list);*/
		
		list = new LinkedList<Message>();

		list.add(new Message("Message 6", "Text of message 6, server 2"));
		list.add(new Message("Message 7", "Text of message 7, server 2"));
		list.add(new Message("Message 8", "Text of message 8, server 2"));
		
		messages.put(2, list);
		
		list = new LinkedList<Message>();

		list.add(new Message("Message 9", "Text of message 9, server 3"));
		
		messages.put(3, list);
		
		list = new LinkedList<Message>();

		list.add(new Message("Message 10", "Text of message 10, server 4"));
		list.add(new Message("Message 11", "Text of message 11, server 4"));
		list.add(new Message("Message 12", "Text of message 12, server 4"));
		list.add(new Message("Message 13", "Text of message 13, server 4"));
		
		messages.put(4, list);
	}
	
	public void setSelectedServers(Set<Integer> serverSet) {
		
		selected.clear();
		
		for(Integer id : serverSet) {
			
			if(messages.containsKey(id)) {
				selected.addAll(messages.get(id));
			}
		}
	}
	
	public int getMessageCount() {
		return selected.size();
	}

	@Override
	public Iterator<Message> iterator() {	
		return new MessageIterator(selected);
	}
}
