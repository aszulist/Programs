package invoke;

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
			guy.setAge(data.nextInt());
			
			System.out.println("Podaj zatrudnienie (0-2 int):");
			int empCat = data.nextInt();
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
	}
}
