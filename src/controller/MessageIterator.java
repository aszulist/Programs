package controller;

import java.util.Iterator;
import java.util.LinkedList;

import data.Message;

public class MessageIterator implements Iterator<Message>{

	private Iterator<Message> iter;
	
	public  MessageIterator(LinkedList<Message> messageList) {
		iter = messageList.iterator();
	}
	
	@Override
	public boolean hasNext() {
		return iter.hasNext();
	}

	@Override
	public Message next() {
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
		}
		return iter.next();
	}
}
