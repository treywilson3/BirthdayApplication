package model;

import javax.swing.JTable;

/**
 * @author Trey
 * Functions user can do 
 */
public class BirthdayFunctions {
	
	/**
	 * Add a birthday to the database
	 * @param fname - first name 
	 * @param lname - last name
	 * @param birthday - given birthday
	 */
	public void addBirthday(String fname, String lname, String birthday){
		Database.birthday(fname, lname, birthday);
	}
	/**
	 * Search data given from user to see if birthday exist
	 * @param userWant - data given from user
	 * @return jtable
	 */
	public JTable searchBirthday(String userWant){
		return Database.search(userWant);
	}
	/** 
	 * get all birthdays that are in database
	 * @return jtable
	 */
	public JTable getBirthdays(){
		return Database.getBirthdays();
	}
}
