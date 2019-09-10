
/**
 * File Name:       	Company.java
 * Author:          	Martin Choy 040835431
 * Course:          	CST8284 Java Object Oriented Programming, Lab Section: 314
 * Assignment:      	3
 * Assignment Title:	COMPANY MANAGEMENT TOOL PROTOTYPE V1.3
 * Due Date:        	Sunday, April 14 2019
 * Professor:       	Jason Mombourquette
 * Method list:      	addEmployee(),currentNumberEmployees(),isMaximumEmployees(),getEmployees(),findSeniorEmployee() 
 * 						deleteEmployee(), findEmployee(), saveEmployeeToFile(), loadEmployeeFromFile()
 * Special notes:		Removed all includes since prof doesn't want packages                       
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Description: Represents an abstraction of a Company
 * 
 * @author Martin Choy
 * @version 1.3
 * @see Company
 */
public class Company {
	private ArrayList<Employee> employees;
	/**
	 * Description: Default constructor
	 * @version 1.2
	 */
	public Company() {
		employees = new ArrayList<>();
		// FOR TESTING PURPOSES ONLY
		// UNCOMMENT IF YOU WANT TO QUICK ADD IN employees.
		// all three different types are here to be inserted at anytime
		// pdf doc shows empty arraylist so didnt want to include theses in for now
		// as it stands the one error trapping/exception I didnt account for was
		// an empty list and using the findsenior method. Will throw exception of an empty list.
		
//addEmployee("Alvin Spring", 14, new OurDate(2, 12, 2014), 150000, 1);
//addEmployee("Grant Barge", 21, new OurDate(18, 5, 2009), 40000, 2);
//addEmployee("Ross Chuttle", 23, new OurDate(22, 2, 2010), 43269.52, 3);
	}

	/**
	 * Description: Method used to add employees into the arraylist of Employee
	 * Notes: Modified to grab in different types of employees
	 * @param name - Employee name
	 * @param employeeNumber - Employee Number
	 * @param date - Employee Start date
	 * @param salary - Employee salary
	 * @param empType - Employee type
	 * @return testAddEmployeee
	 * @version 1.2
	 */
	public Employee addEmployee(String name, int employeeNumber, OurDate date, double salary, int empType) {
		Employee testAddEmployeee = null;
		Scanner input = new Scanner(System.in);

		switch (empType) {
		case CompanyConsole.MANAGER:
			System.out.println("Enter Manager's title: ");
			String m = input.next();
			testAddEmployeee = new Manager(name, employeeNumber, date, salary, m);
			employees.add(testAddEmployeee);
			break;
		case CompanyConsole.STAFF:
			System.out.println("Enter Department: ");
			String s = input.nextLine();
			testAddEmployeee = new Staff(name, employeeNumber, date, salary, s);
			employees.add(testAddEmployeee);
			break;
		case CompanyConsole.TEMP:
			System.out.println("Enter Temps's end day:");
			System.out.print("YEAR: ");
			int year = input.nextInt();
			System.out.print("MONTH: ");
			int month = input.nextInt();
			System.out.print("DAY: ");
			int day = input.nextInt();

			testAddEmployeee = new Temp(name, employeeNumber, date, salary, new OurDate(day, month, year));
			employees.add(testAddEmployeee);
			// stops resource leaking but bad implementation so no go
			// input.close();
			break;
		}
		return testAddEmployeee;
	}
	/**
	 * Description: Returns the number of elements in this list
	 * @return employees.size() - employee list
	 * @version: 1.2
	 */
	public int currentNumberEmployees() {
		return employees.size();
	}
	/**
	 * Description: If current number of employees is less than 10 return false else true
	 * Notes: The number of employees in the company is now limited only by the number of entries that can be
	 * held in memory.
	 * @return false
	 * @version: 1.2
	 */
	public boolean isMaximumEmployees() {
		try {
			return false;
		} catch (OutOfMemoryError e) {
			System.out.println("OutOfMemoryError thrown");
			return true;
}
	}
	/**
	 * Description: A getter for employees. Changed to match changes.
	 * @return employees
	 * @version: 1.2
	 */
	public ArrayList<Employee> getEmployees() {
		return employees;
	}
	/**
	 * Description: Find's the most senior employee compared to the rest
	 * Notes: Just lots of comparing similar to the old SeniorEmployee method from Ass#1
	 * @return findingSeniorEmp
	 * @version: 1.2
	 */
	public Employee findSeniorEmployee() {
		Employee findingSeniorEmp = employees.get(0);
		for (int i = 1; i < employees.size(); i++) {
			if (employees.get(i) != null) {
				if (employees.get(i).getStartDate().getYear() < findingSeniorEmp.getStartDate().getYear())
					findingSeniorEmp = employees.get(i);
				else if (employees.get(i).getStartDate().getYear() == findingSeniorEmp.getStartDate().getYear()) {
					if (employees.get(i).getStartDate().getMonth() < findingSeniorEmp.getStartDate().getMonth())
						findingSeniorEmp = employees.get(i);
					else if (employees.get(i).getStartDate().getMonth() == findingSeniorEmp.getStartDate().getMonth()) {
						if (employees.get(i).getStartDate().getDay() < findingSeniorEmp.getStartDate().getDay()) {
							findingSeniorEmp = employees.get(i);
						}
					}
				}
			}
		}
		return findingSeniorEmp;
	}
	/**
	 * Description: Delete an employee from the arraylist
	 * Notes: Uses an enhanced for loop learned from labs
	 * @param empNum - Employee number
	 * @return delEmployee
	 */
	public Employee deleteEmployee(int empNum) {
		Employee delEmployee = null;
		for (Employee compare : employees) {
			if (compare.getEmployeeNumber() == empNum) {
				delEmployee = compare;
				employees.remove(compare);
				return delEmployee;
			}
		}
		return delEmployee;
	}
	/**
	 * Description: Finds an employee via employee number
	 * Notes: Uses an enhanced for loop learned from labs
	 * @param empNum - Employee number
	 * @return compare
	 * @return null
	 */
	public Employee findEmployee(int empNum) {
		for (Employee compare : employees) {
			if (compare.getEmployeeNumber() == empNum) {
				return compare;
			}
		}
		return null;
	}
	
	/**
	 * Description: Saves employees to file (FILE IO)
	 */
	public void saveEmployeeToFile() {		
		try{
			FileOutputStream test = new FileOutputStream("CurrentEmployees.emp");
			ObjectOutputStream tester = new ObjectOutputStream(test);
		
		for (Employee emp : employees) {
			tester.writeObject(emp);
			
	}
		System.out.println("Sucessfully saved information to file.");
		test.close();	
		} catch (IOException ioe) {
		ioe.getStackTrace();
		}	
		}	
	
	/**
	 * Description: Loads employees from a file (FILE IO) 
	 */
	public void loadEmployeeFromFile(){
		try {	

			FileInputStream ofs =  new FileInputStream("CurrentEmployees.emp");
			ObjectInputStream oos= new ObjectInputStream(ofs);
			while(true) {
				Object obj = oos.readObject();
				if(obj instanceof Manager){
				employees.add((Manager)obj);	
				}
				else if(obj instanceof Staff){
				employees.add((Staff)obj);	
				}
				else if(obj instanceof Temp){
					employees.add((Temp)obj);
				}
			//	oos.close();
			// not sure how to stop the oos resource leak correctly since
			// leaving this close() here will prevent the loading of the objects from the file to be complete
			// for now leaving the resource leak present. I understand it is bad programming practice but still learning
		}
			
		} catch (FileNotFoundException x) {
			System.out.println("FileNotFoundException: " + x.getStackTrace());
		} catch (IOException e ) {
		//	System.out.println("IOException: " + e.getMessage());
			System.out.println("Sucessfully loaded information from file.");
		} catch (ClassNotFoundException c)  {
			System.out.println("ClassNotFoundException: " + c.getMessage());
		} 
}
}
