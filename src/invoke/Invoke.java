package invoke;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.Scanner;

public class Invoke {

	public static void main(String[] args) {

		Scanner data = new Scanner(System.in);
		
		LinkedList<Person> people = new LinkedList<Person>();

		for (int i = 0; i < 3; i++) {

			Person guy = new Person();

			System.out.println("Podaj imie:");
			guy.setName(data.nextLine());

			System.out.println("Podaj nazwisko:");
			guy.setSurname(data.nextLine());
			
			System.out.println("Podaj wiek(int):");
			guy.setAge(Integer.parseInt(data.nextLine()));
			
			System.out.println("Podaj zatrudnienie (0-2 int):");
			int empCat = Integer.parseInt(data.nextLine());
			switch(empCat) {
			case 0 : {
				guy.setEmployment(EmpCat.EMPLOYED);
				break;
			}
			case 1 : {
				guy.setEmployment(EmpCat.SELF_EMPLOYED);
				break;
			}
			case 2 : {
				guy.setEmployment(EmpCat.UNEMPLOYED);
				break;
			}
			default : {
				guy.setEmployment(EmpCat.UNEMPLOYED);
				break;
			}
			}
			
			System.out.println("Podaj nr telefonu:");
			guy.setPhoneNumber(data.nextLine());
			
			people.add(guy);
		}
		
		data.close();
		
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("people.bin")))) {
			
			oos.writeInt(people.size());
			
			for(Person osoba : people) {
				oos.writeObject(osoba);
			}
			
		} catch (IOException e) {
			System.err.println("Couldn't create file");
		}
		
		ReadPerson.readPeople();
	}
}
