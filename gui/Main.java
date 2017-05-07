package gui;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import controller.BirthdayController;
import model.BirthdayFunctions;

import javax.swing.UnsupportedLookAndFeelException;

/**
 * @author Trey Main creates Jframe and menu bar
 */
public class Main {
	private JFrame frame;
	private JMenuBar menuBar;

	/**
	 * Create frame, set menubar, and call guestPanel
	 */
	public Main(){
		// Create Frame
		frame = new JFrame();

		// Set title, size, layout, and add menu bar
		frame.setTitle("Birthday");
		frame.setSize(700, 600);
		frame.setLayout(new GridBagLayout());
		frame.setJMenuBar(createMenuBar());

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Create model, view, controller
		BirthdayFunctions birthdayFunctions = new BirthdayFunctions();
		BirthdayPanel birthdayPanel = new BirthdayPanel();
		BirthdayController birthdayController = new BirthdayController(birthdayPanel, birthdayFunctions);
		
		// Add controller listeners to view
		birthdayPanel.addActionListener(birthdayController);
		birthdayPanel.addFocusListener(birthdayController);
		
		frame.add(birthdayPanel);
		frame.setVisible(true);
		
		// Show the home screen
		birthdayController.start();
		

	}
	/**
	 * Create a menu bar for the appllicatoin
	 * @return
	 */
	public JMenuBar createMenuBar() {
		// Create menubar
		menuBar = new JMenuBar();
		// file will be added to menu bar
		JMenu fileMenu = new JMenu("File");

		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.setMnemonic(KeyEvent.VK_X);
		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		fileMenu.add(exitItem);
		// Have prompt so app doesn't automatically close
		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int action = JOptionPane.showConfirmDialog(frame, "Do you really want to exit the application?",
						"Confirm Exit", JOptionPane.OK_CANCEL_OPTION);
				if (action == JOptionPane.OK_OPTION) {
					System.exit(0);
				}
			}
		});
		menuBar.add(fileMenu);
		return menuBar;
	}
	/**
	 * Main. Add nimbus LAF
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
						if ("Nimbus".equals(info.getName())) {
							UIManager.setLookAndFeel(info.getClassName());
							break;
						}
					}
				} catch (Exception e) {
					try {
						UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
					} catch (Exception ex) {

					}
				}
				new Main();
			}
		});
	
	}

}
