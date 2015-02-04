package invoke;

import java.util.LinkedList;
import java.util.Scanner;

public class Invoke {

	public static void main(String[] args) {
		
		Scanner data = new Scanner(System.in);
		LinkedList<Person> people = new LinkedList<Person>();
		
		for(int i=0; i<3; i++){
			Person guy = new Person();
			System.out.println("Podaj imie");
			people.add(guy);
			
			guy.setName(data.nextLine());
			
		}

	}
}
