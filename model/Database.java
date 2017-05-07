package model;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JTable;

class Database {
	/**
	 * Create a connection to mysql database
	 * 
	 * @return connection
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static java.sql.Connection createConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		final String DB_URL = "jdbc:mysql://localhost:3306/Birthdays?useOldAliasMetadataBehavior=true&autoReconnect=true&useSSL=false";
		final String USER = "";
		final String PASS = "";

		return DriverManager.getConnection(DB_URL, USER, PASS);
	}

	/**
	 * Need to close connection after use so call this method when needed
	 * 
	 * @param conn
	 *            - connection to database
	 */
	public static void close(java.sql.Connection conn) {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Need to close statements after use so call this method
	 * 
	 * @param st
	 *            - query statement
	 */
	public static void close(Statement st) {
		try {
			if (st != null) {
				st.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Need to close prepared statements after use so call this method
	 * 
	 * @param pst
	 *            - the prepared statement
	 */
	public static void close(PreparedStatement pst) {
		try {
			if (pst != null) {
				pst.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * To print information, you need the result set. That result set needs to
	 * be closed after use so call this method
	 * 
	 * @param rs
	 *            - the result set
	 */
	public static void close(ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * When using transactions, you need to rollback if the transaction was not
	 * successful. Call this class if needed
	 * 
	 * @param conn
	 *            - the connection to the database
	 */
	public static void rollback(java.sql.Connection conn) {
		try {
			if (conn != null) {
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Add birthday to database
	 * @param fname
	 * @param lname
	 * @param birthday
	 */
	public static void birthday(String fname, String lname, String birthday){
		java.sql.Connection conn = null;
		java.sql.PreparedStatement pStmt = null;
		try {
			conn = createConnection();

			java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(birthday);
			// because PreparedStatement#setDate(..) expects a java.sql.Date
			// argument
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			pStmt = conn.prepareStatement(
					"insert into Birthdays(FIRST_NAME, LAST_NAME, BIRTHDAY) VALUES"
							+ "(?, ?, ?)");
			pStmt.setString(1, fname);
			pStmt.setString(2, lname);
			pStmt.setDate(3, sqlDate);
			pStmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			close(pStmt);
			close(conn);
		} // end finally try
	}
	/**
	 * Search database for birthday then make table
	 * @param searchThis
	 * @return
	 */
	public static JTable search(String searchThis){
		java.sql.Connection conn = null;
		java.sql.PreparedStatement pStmt = null;
		ResultSet rs = null;
		JTable table = null;
		try {
			conn = createConnection();
			System.out.println("Creating statement...");
			pStmt = conn.prepareStatement("select concat(first_name, ' ', last_name) AS Name, BIRTHDAY AS 'Birthday (yyyy-mm-dd)' from Birthdays where ? in (concat(FIRST_NAME, ' ', LAST_NAME), FIRST_NAME, LAST_NAME)");
			pStmt.setString(1, searchThis);
			rs = pStmt.executeQuery();
			table = new JTable(Table.buildTableModel(rs));
			Table.columnSize(table, 90);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			close(pStmt);
			close(conn);
		} // end finally try
		return table;
	}
	/**
	 * get all birthdays from database
	 * @return jtable
	 */
	public static JTable getBirthdays(){
		java.sql.Connection conn = null;
		java.sql.PreparedStatement pStmt = null;
		ResultSet rs = null;
		JTable table = null;
		try {
			conn = createConnection();
			System.out.println("Creating statement...");
			//pStmt = conn.prepareStatement("select FIRST_NAME as First, LAST_NAME as Last, BIRTHDAY as Birthday from Birthdays");
			pStmt = conn.prepareStatement("select concat(first_name, ' ', last_name) AS Name, BIRTHDAY AS 'Birthday (yyyy-mm-dd)' from Birthdays");
			rs = pStmt.executeQuery();
			table = new JTable(Table.buildTableModel(rs));
			Table.columnSize(table, 150);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			close(pStmt);
			close(conn);
		} // end finally try
		return table;
	}
}
