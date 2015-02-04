package invoke;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.LinkedList;

public class ReadPerson {
	
	public static LinkedList<Person> readPeople() {
		
		LinkedList<Person> people = new LinkedList<Person>();
		
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("people.bin"))) {
			
			int s = ois.readInt();
			
			for(int i=0; i < s; i++) {
				
				people.add((Person)ois.readObject());
				
				System.out.println(people.get(i));
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return people;
	}
}
