package gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import controller.BirthdayController;
/**
 * @author Trey
 * BirthdayPanel is where all gui is put on screen
 */
public class BirthdayPanel extends JPanel {

	private TextField firstNameText;
	private TextField lastNameText;
	private TextField birthdayText;
	private JButton submitBirthday;

	private String firstNameTextPrompt;
	private String lastNameTextPrompt;
	private String birthdayTextPrompt;
	
	private TextField searchNameText;
	private JButton submitSearch;
	private String searchNameTextPrompt;
	
	private JButton submitGetAll;
	
	private JButton homeButton;
	
	private JButton deleteButton;
	
	private GridBagConstraints c;
	
	private BirthdayController birthdayController;

	/**
	 * Constructor - set layout 
	 */
	public BirthdayPanel() {
		setLayout(new GridBagLayout());
		c = new GridBagConstraints();
	}
	/**
	 * Home Screen that user sees
	 */
	public void homeScreen(){
		resetScreen();
		
		c.fill = GridBagConstraints.NONE;
		c.ipady = 20;
		c.ipadx = 40;
		c.insets = new Insets(0, 20, 20, 0); // side padding
		c.gridx = 0;
		c.gridy = 0;
		add(search(), c);
		c.fill = GridBagConstraints.NONE;
		c.ipady = 20;
		c.ipadx = 40;
		c.insets = new Insets(0, 20, 20, 0); // side padding
		c.gridx = 0;
		c.gridy = 2;
		add(addBirthday(), c);
		c.fill = GridBagConstraints.NONE;
		c.ipady = 20;
		c.ipadx = 40;
		c.insets = new Insets(0, 20, 20, 0); // side padding
		c.gridx = 0;
		c.gridy = 10;
		add(getAll(), c);
	}

	/**
	 * Search function allows user to search and birthdays
	 * on database. This also has all buttons needed
	 * @return
	 */
	public JPanel search(){
		JPanel searchPanel = new JPanel();
		GridBagConstraints c = new GridBagConstraints();
		searchPanel.setLayout(this.getLayout());
		searchNameText = new TextField(20);
		searchNameText.setForeground(Color.GRAY);
		searchNameTextPrompt = "Search Person's Birthday";
		searchNameText.setText(searchNameTextPrompt);
		submitSearch = new JButton("Search");
		
		c.fill = GridBagConstraints.NONE;
		c.ipady = 20;
		c.ipadx = 40;
		c.insets = new Insets(0, 20, 20, 0); // side padding
		c.gridx = 0;
		c.gridy = 0;
		searchPanel.add(searchNameText, c);
		c.fill = GridBagConstraints.NONE;
		c.ipady = 20;
		c.ipadx = 40;
		c.insets = new Insets(0, 20, 20, 0); // side padding
		c.gridx = 0;
		c.gridy = 1;
		
		searchPanel.add(submitSearch, c);
		
		submitSearch.addActionListener(getActionListener());
		searchNameText.addFocusListener(getFocusListener());
		
		return searchPanel;
	}
	/**
	 * Panel where member adds a new birthday in
	 * @return
	 */
	public JPanel addBirthday() {
		JPanel addBirthdayPanel = new JPanel();
		addBirthdayPanel.setLayout(this.getLayout());
		GridBagConstraints c = new GridBagConstraints();
		firstNameText = new TextField(20);
		firstNameText.setForeground(Color.GRAY);
		firstNameTextPrompt = "Enter Person First Name";
		firstNameText.setText(firstNameTextPrompt);

		lastNameText = new TextField(20);
		lastNameText.setForeground(Color.GRAY);
		lastNameTextPrompt = "Enter Person Last Name";
		lastNameText.setText(lastNameTextPrompt);

		birthdayText = new TextField(20);
		birthdayText.setForeground(Color.GRAY);
		birthdayTextPrompt = "Enter Person Birthday (Ex: YYYY-MM-DD)";
		birthdayText.setText(birthdayTextPrompt);
		
		submitBirthday = new JButton("Submit");
		
		c.fill = GridBagConstraints.NONE;
		c.ipady = 20;
		c.ipadx = 40;
		c.insets = new Insets(0, 20, 20, 0); // side padding
		c.gridx = 0;
		c.gridy = 0;
		addBirthdayPanel.add(firstNameText, c);
		c.fill = GridBagConstraints.NONE;
		c.ipady = 20;
		c.ipadx = 40;
		c.insets = new Insets(0, 20, 20, 0); // side padding
		c.gridx = 0;
		c.gridy = 1;
		addBirthdayPanel.add(lastNameText, c);
		c.fill = GridBagConstraints.NONE;
		c.ipady = 20;
		c.ipadx = 40;
		c.insets = new Insets(0, 20, 20, 0); // side padding
		c.gridx = 0;
		c.gridy = 2;
		addBirthdayPanel.add(birthdayText, c);
		c.fill = GridBagConstraints.NONE;
		c.ipady = 20;
		c.ipadx = 40;
		c.insets = new Insets(0, 20, 20, 0); // side padding
		c.gridx = 0;
		c.gridy = 3;
		addBirthdayPanel.add(submitBirthday, c);
		
		submitBirthday.addActionListener(getActionListener());
		firstNameText.addFocusListener(getFocusListener());
		lastNameText.addFocusListener(getFocusListener());
		birthdayText.addFocusListener(getFocusListener());
		
		return addBirthdayPanel;
	}
	/**
	 * Panel where member can select the get all button
	 * to see all birthdays
	 * @return
	 */
	public JPanel getAll(){
		JPanel getAllPanel = new JPanel();
		getAllPanel.setLayout(this.getLayout());
		GridBagConstraints c = new GridBagConstraints();
		submitGetAll = new JButton("Get All Birthdays");
		
		c.fill = GridBagConstraints.NONE;
		c.ipady = 20;
		c.ipadx = 40;
		c.insets = new Insets(0, 20, 20, 0); // side padding
		c.gridx = 0;
		c.gridy = 3;
		getAllPanel.add(submitGetAll, c);
		submitGetAll.addActionListener(getActionListener());
		
		return getAllPanel;
	}
	
	/**
	 * Take back to home page
	 */
	public JButton homeButton() {
		resetScreen();
		homeButton = new JButton("Home");
		homeButton.addActionListener(getActionListener());
		
		return homeButton;
	}
	/** 
	 * Adding home button is done a lot so this makes it easier
	 * @param x - gridx 
	 * @param y - gridy
	 */
	public void addHomeButton(int x, int y){
		c.fill = GridBagConstraints.NONE;
		c.ipady = 20;
		c.ipadx = 40;
		c.insets = new Insets(0, 20, 20, 0); // side padding
		c.gridx = x;
		c.gridy = y;
		add(homeButton(), c);
	}
	/**
	 * Make delete button
	 * @return
	 */
	public JButton deleteButton(){
		resetScreen();
		deleteButton = new JButton("Delete");
		deleteButton.addActionListener(getActionListener());
		
		return deleteButton;
	}
	/**
	 * Add the delete button where you want to according to parameters
	 * @param x - x value 
	 * @param y - y value 
	 */
	public void addDeleteButton(int x, int y){
		c.fill = GridBagConstraints.NONE;
		c.ipady = 20;
		c.ipadx = 40;
		c.insets = new Insets(0, 20, 20, 0); // side padding
		c.gridx = x;
		c.gridy = y;
		add(deleteButton(), c);
	}
	/**
	 * Used for adding JScrollPane where you want to according to given
	 * parameters
	 * @param pane
	 * @param x
	 * @param y
	 */
	public void addPane(JScrollPane pane, int x, int y){
		c.fill = GridBagConstraints.CENTER;
		c.ipady = x;
		c.ipadx = y;
		c.insets = new Insets(0, 20, 20, 0); // side padding
		c.gridx = 0;
		c.gridy = 1;
		pane.getViewport().setBackground(Color.WHITE);
		add(pane, c);
	}

	/**
	 * resetScreen is needed to update screen
	 */
	public void resetScreen() {
		removeAll();
		repaint();
		revalidate();
	}
	/**
	 * Pop up message where you can customize output
	 * @param fname
	 * @param lname
	 */
	public void confirmMessage(String fname, String lname){
		JOptionPane.showMessageDialog(this, fname + " " + lname + " has been entered");
	}
	
	/**
	 * getter method for first name textfield
	 * @return
	 */
	public TextField getFirstNameText() {
		return firstNameText;
	}
	/**
	 * getter method for last name text field
	 * @return
	 */
	public TextField getLastNameText() {
		return lastNameText;
	}
	/**
	 * getter method for birthday text field
	 * @return
	 */
	public TextField getBirthdayText() {
		return birthdayText;
	}
	/**
	 * getter method for submit birthday button
	 * @return
	 */
	public JButton getSubmitBirthday() {
		return submitBirthday;
	}
	/**
	 * getter method for first name text prompt
	 * @return
	 */
	public String getFirstNameTextPrompt() {
		return firstNameTextPrompt;
	}
	/**
	 * getter method for last name text prompt
	 * @return
	 */
	public String getLastNameTextPrompt() {
		return lastNameTextPrompt;
	}
	/**
	 * getter method for birthday text prompt
	 * @return
	 */
	public String getBirthdayTextPrompt() {
		return birthdayTextPrompt;
	}
	/**
	 * getter method for search name text field
	 * @return
	 */
	public TextField getSearchNameText() {
		return searchNameText;
	}
	/**
	 * getter method for submit search button
	 * @return
	 */
	public JButton getSubmitSearch() {
		return submitSearch;
	}
	/**
	 * getter method for search name text prompt
	 * @return
	 */
	public String getSearchNameTextPrompt() {
		return searchNameTextPrompt;
	}
	/**
	 * getter method for sumbit get all method
	 * @return
	 */
	public JButton getSubmitGetAll() {
		return submitGetAll;
	}
	/**
	 * getter method for home button
	 * @return
	 */
	public JButton getHomeButton() {
		return homeButton;
	}
	/**
	 * getter mehtod for delete button
	 * @return
	 */
	public JButton getDeleteButton() {
		return deleteButton;
	}
	/**
	 * Adds and actionlistener to the class
	 * @param controller
	 * @return
	 */
	public ActionListener addActionListener(BirthdayController controller){
		this.birthdayController = controller;
		
		return controller;
		
	}
	/**
	 * Adds a focus listener to the class
	 * @param controller
	 * @return
	 */
	public FocusListener addFocusListener(BirthdayController controller){
		this.birthdayController = controller;
		
		return this.birthdayController;
	}
	/**
	 * getter method for the action listener
	 * @return
	 */
	public ActionListener getActionListener(){
		return birthdayController;
	}
	/**
	 * getter method for the focus listener
	 * @return
	 */
	public FocusListener getFocusListener(){
		return birthdayController;
	} 
}
