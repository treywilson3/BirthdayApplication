package model;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 * @author Trey
 * Used to make JTable and modify JTable
 */
public class Table {

	/**
	 * Builds a jtable
	 * @param rs - the data that is returned from database
	 * @return jtable
	 * @throws SQLException
	 */
	public static DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {
		ResultSetMetaData metaData = rs.getMetaData();
		// names of columns
		Vector<String> columnNames = new Vector<String>();
		int columnCount = metaData.getColumnCount();
		for (int column = 1; column <= columnCount; column++) {
			columnNames.add(metaData.getColumnName(column));
		}

		// data of the table
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		while (rs.next()) {
			Vector<Object> vector = new Vector<Object>();
			for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
				vector.add(rs.getObject(columnIndex));
			}
			data.add(vector);
		}
		return new DefaultTableModel(data, columnNames);

	}
	/**
	 * Sets column sizes of jtable
	 * @param table - table you want to modify
	 * @param size - the size you want the columns to be
	 */
	public static void columnSize(JTable table, int size) {
		for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
			javax.swing.table.TableColumn column = table.getColumnModel().getColumn(i);
			column.setPreferredWidth(size);
		}
	}
	 public static boolean isCellEditable(int row, int column) {
	       //all cells false
	       return false;
	    }
}
