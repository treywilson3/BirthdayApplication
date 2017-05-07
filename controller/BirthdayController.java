package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import gui.BirthdayPanel;
import model.BirthdayFunctions;
/**
 * @author Trey
 * BirthdayController controls all functions of BirthdayPanel class
 */
public class BirthdayController implements ActionListener, FocusListener {

	private BirthdayPanel birthdayPanel;
	private BirthdayFunctions birthdayFunctions;
	/**
	 * Constructor for BirthdayController
	 * @param birthdayPanel - Panel used to display application
	 * @param birthdayFunctions - Functions that the application can do
	 *  
	 */
	public BirthdayController(BirthdayPanel birthdayPanel, BirthdayFunctions birthdayFunctions) {
		this.birthdayPanel = birthdayPanel;
		this.birthdayFunctions = birthdayFunctions;
		
	}
	/**
	 * Take user to home screen
	 */
	public void start(){
		birthdayPanel.homeScreen();
	}
	/**
	 * Action Listener for BirthdayPanel
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == birthdayPanel.getSubmitBirthday()) {
			String fname = (String) birthdayPanel.getFirstNameText().getText();
			String lname = (String) birthdayPanel.getLastNameText().getText();
			String birthday = (String) birthdayPanel.getBirthdayText().getText();
			birthdayFunctions.addBirthday(fname, lname, birthday);
			birthdayPanel.confirmMessage(fname, lname);
			birthdayPanel.homeScreen();
		} else if (e.getSource() == birthdayPanel.getSubmitSearch()) {
			birthdayPanel.resetScreen();

			birthdayPanel.addHomeButton(0, 2);
			
			String userSearch = (String) birthdayPanel.getSearchNameText().getText();
			JTable table = birthdayFunctions.searchBirthday(userSearch);
			JScrollPane pane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
					JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			birthdayPanel.addPane(pane, 300, 400);
		} else if (e.getSource() == birthdayPanel.getSubmitGetAll()) {
			birthdayPanel.resetScreen();
			
			birthdayPanel.addHomeButton(0, 2);
			// addDeleteButton(1,2);

			JTable table = birthdayFunctions.getBirthdays();
			JScrollPane pane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
					JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			birthdayPanel.addPane(pane, 300, 400);
		} else if (e.getSource() == birthdayPanel.getHomeButton()) {
			birthdayPanel.homeScreen();
		}
	}
	/**
	 * Focus Listener for BirthdayPanel
	 * This is used to help with user interaction
	 * Will highlight text when textbox clicked on 
	 * and have a prompt
	 */
	public void focusGained(FocusEvent e) {		
		if (e.getSource() == birthdayPanel.getFirstNameText()) {
			birthdayPanel.getFirstNameText().setForeground(Color.BLACK);
			if (birthdayPanel.getFirstNameText().getText().equals(birthdayPanel.getFirstNameTextPrompt())) {
				birthdayPanel.getFirstNameText().selectAll();
			}
		} else if (e.getSource() == birthdayPanel.getLastNameText()) {
			birthdayPanel.getLastNameText().setForeground(Color.BLACK);
			if (birthdayPanel.getLastNameText().getText().equals(birthdayPanel.getLastNameTextPrompt())) {
				birthdayPanel.getLastNameText().selectAll();
			}
		} else if (e.getSource() == birthdayPanel.getBirthdayText()) {
			birthdayPanel.getBirthdayText().setForeground(Color.BLACK);
			if (birthdayPanel.getBirthdayText().getText().equals(birthdayPanel.getBirthdayTextPrompt())) {
				birthdayPanel.getBirthdayText().selectAll();
			}
		} else if (e.getSource() == birthdayPanel.getSearchNameText()) {
			birthdayPanel.getSearchNameText().setForeground(Color.BLACK);
			if (birthdayPanel.getSearchNameText().getText().equals(birthdayPanel.getSearchNameTextPrompt())) {
				birthdayPanel.getSearchNameText().selectAll();
			}

		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource() == birthdayPanel.getFirstNameText()) {
			if (birthdayPanel.getFirstNameText().getText().length() == 0
					|| birthdayPanel.getFirstNameText().getText().equals(birthdayPanel.getFirstNameTextPrompt())) {
				birthdayPanel.getFirstNameText().setForeground(Color.GRAY);
				birthdayPanel.getFirstNameText().setText(birthdayPanel.getFirstNameTextPrompt());
			}
		} else if (e.getSource() == birthdayPanel.getLastNameText()) {
			if (birthdayPanel.getLastNameText().getText().length() == 0
					|| birthdayPanel.getLastNameText().getText().equals(birthdayPanel.getLastNameTextPrompt())) {
				birthdayPanel.getLastNameText().setForeground(Color.GRAY);
				birthdayPanel.getLastNameText().setText(birthdayPanel.getLastNameTextPrompt());
			}
		} else if (e.getSource() == birthdayPanel.getBirthdayText()) {
			if (birthdayPanel.getBirthdayText().getText().length() == 0
					|| birthdayPanel.getBirthdayText().getText().equals(birthdayPanel.getBirthdayTextPrompt())) {
				birthdayPanel.getBirthdayText().setForeground(Color.GRAY);
				birthdayPanel.getBirthdayText().setText(birthdayPanel.getBirthdayTextPrompt());
			}
		} else if (e.getSource() == birthdayPanel.getSearchNameText()) {
			if (birthdayPanel.getSearchNameText().getText().length() == 0
					|| birthdayPanel.getSearchNameText().getText().equals(birthdayPanel.getSearchNameTextPrompt())) {
				birthdayPanel.getSearchNameText().setForeground(Color.GRAY);
				birthdayPanel.getSearchNameText().setText(birthdayPanel.getSearchNameTextPrompt());
			}
		}

	}
}
