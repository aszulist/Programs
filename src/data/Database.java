package data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import data.listeners.CntChangeListener;

public class Database {
	
	private List<Person> people;
	
	private Connection con;
	
	private CntChangeListener cntListener;
	
	public Database() {
		people = new LinkedList<Person>();
	}
	
	public void connect() throws Exception {
		
		if(con != null) {
			return;
		}
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new Exception("Driver not found");
		}
		
		String connectionURL = "jdbc:mysql://localhost:3306/swingtest";
		con = DriverManager.getConnection(connectionURL, "root", "");
	}
	
	public void disconnect() {
		
		if(con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	public void save() throws SQLException {
		
		PreparedStatement check = 
				con.prepareStatement
				("select count(*) as count from people where id=?");
		
		PreparedStatement insert = 
				con.prepareStatement
				("insert into people (id, name, age, employment, docid, plcitizen, gender, occupation, phone) value(?, ?, ?, ?, ?, ?, ?, ?, ?)");
			
		PreparedStatement update = 
				con.prepareStatement
				("update people set name=?, age=?, employment=?, docid=?, plcitizen=?, gender=?, occupation=?, phone=? where id=?");
			
		for(Person person : people) {
			
			int id = person.getId();
			
			check.setInt(1, id);
			ResultSet checkRes = check.executeQuery();
		
			checkRes.next();
			int count = checkRes.getInt(1);
			
			if(count == 0) {
				
				int col = 1;
				insert.setInt(col++, id);
				insert.setString(col++, person.getName());
				insert.setString(col++, person.getAge().name());
				insert.setString(col++, person.getEmployment().name());
				insert.setString(col++, person.getDocID());
				if(person.isPLCitizen()) {
					insert.setInt(col++, 1);
				} else {
					insert.setInt(col++, 0);
				}
				insert.setString(col++, person.getGender().name());
				insert.setString(col++, person.getOccupation());
				insert.setString(col++, person.getPhoneNumber());
				
				insert.executeUpdate();
				
			} else {
				
				int col = 1;
				update.setString(col++, person.getName());
				update.setString(col++, person.getAge().name());
				update.setString(col++, person.getEmployment().name());
				update.setString(col++, person.getDocID());
				if(person.isPLCitizen()) {
					update.setInt(col++, 1);
				} else {
					update.setInt(col++, 0);
				}
				update.setString(col++, person.getGender().name());
				update.setString(col++, person.getOccupation());
				update.setString(col++, person.getPhoneNumber());
				update.setInt(col++, id);
				
				update.executeUpdate();
			}
			
			checkRes.close();
		}
		
		update.close();
		insert.close();
		check.close();
	}
	
	public void load() throws SQLException {
		
		people.clear();
		Statement select = con.createStatement();
		
		ResultSet results = 
			select.executeQuery("select id, name, age, employment, docid, plcitizen, gender, occupation, phone from people order by id");
		
		while(results.next()) {
			if(results.getInt("plcitizen") == 1) {
				
				addPerson(new Person(
					results.getInt("id"),
					results.getString("name"),
					results.getString("occupation"),
					AgeCategoryEnumerated.valueOf(results.getString("age")),
					EmploymentCategory.valueOf(results.getString("employment")),
					true,
					results.getString("docid"),
					GenderCategory.valueOf(results.getString("gender")),
					results.getString("phone")));
			} else {
				
				addPerson(new Person(
					results.getInt("id"),
					results.getString("name"),
					results.getString("occupation"),
					AgeCategoryEnumerated.valueOf(results.getString("age")),
					EmploymentCategory.valueOf(results.getString("employment")),
					false,
					null,
					GenderCategory.valueOf(results.getString("gender")),
					results.getString("phone")));
			}
		}
		
		results.close();
		select.close();
	}
	
	public void addPerson(Person person) {
		people.add(person);
	}
	
	public List<Person> getPeople() {
		return Collections.unmodifiableList(people);
	}
	
	public void saveToFile(File file) throws FileNotFoundException, IOException {
		
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		Person[] people = this.people.toArray(new Person[this.people.size()]);
		
		oos.writeObject(people);
		
		oos.close();
	}
	
	public void loadFromFile(File file) throws FileNotFoundException, IOException, ClassNotFoundException {
		
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);
		
		Person[] people = (Person[])ois.readObject();
		
		this.people.clear();
		this.people.addAll(Arrays.asList(people));
		
		Person.setCnt(this.people.get(this.people.size()-1).getId()+1);
		
		ois.close();
	}
	
	public void removePerson(int index) {
		people.remove(index);
		refreshIndexes();
		if(cntListener != null) {
			cntListener.firePersonChangeEvent();
		}
	}
	
	public void refreshIndexes() {
		
		int index = 1;
		
		for(Person guy : this.people) {
			guy.setId(index);
			index++;
		}
		Person.setCnt(index);
	}
	
	public void setCntChangeListener(CntChangeListener listener) {
		this.cntListener = listener;
	}
}
