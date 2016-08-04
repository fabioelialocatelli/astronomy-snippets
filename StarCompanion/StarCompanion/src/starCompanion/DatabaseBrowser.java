package starCompanion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

public class DatabaseBrowser {

	private ArrayList<Vector<?>> databaseObjects = null;
	private Connection localConnection = null;
	private PreparedStatement databaseStatement = null;
	private ResultSet databaseRetrieval = null;
	private ResultSetMetaData retrievalMetadata = null;
	private String databasePath = null;

	public DatabaseBrowser(String databasePath) {
		this.databasePath = databasePath;
	}

	public String anchorDatabase() {
		String exceptionMessage = null;
		try {
			Class.forName("org.sqlite.JDBC").newInstance();
			localConnection = DriverManager.getConnection("jdbc:sqlite:" + databasePath);
		} catch (ClassNotFoundException | IllegalAccessException | InstantiationException
				| SQLException databaseException) {
			exceptionMessage = databaseException.getMessage();
		}
		return exceptionMessage;
	}

	public ArrayList<Vector<?>> displayConstellation(String latinDesignation) {
		try {
			databaseObjects = new ArrayList<>();
			databaseStatement = localConnection
					.prepareStatement("SELECT * FROM `bayerDesignation` WHERE `designation` LIKE ?;");
			databaseStatement.setString(1, "%" + latinDesignation);

			databaseRetrieval = databaseStatement.executeQuery();
			retrievalMetadata = databaseRetrieval.getMetaData();

			Vector<String> columnDenominations = new Vector<>();
			Vector<Vector<Object>> recordObjects = new Vector<Vector<Object>>();

			for (int columnIndex = 1; columnIndex <= retrievalMetadata.getColumnCount(); columnIndex++) {
				columnDenominations.add(retrievalMetadata.getColumnName(columnIndex));
			}

			while (databaseRetrieval.next()) {
				Vector<Object> retrievedRow = new Vector<>();
				for (int columnIndex = 1; columnIndex <= retrievalMetadata.getColumnCount(); columnIndex++) {
					retrievedRow.add(databaseRetrieval.getObject(columnIndex));
				}
				recordObjects.add(retrievedRow);
			}
			databaseObjects.add(recordObjects);
			databaseObjects.add(columnDenominations);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return databaseObjects;
	}

	public ArrayList<Vector<?>> displayStars(String selectionCriteria, Integer sortingOption) {
		try {
			databaseObjects = new ArrayList<>();

			if (sortingOption == 0) {
				databaseStatement = localConnection
						.prepareStatement("SELECT * FROM `classicDesignation` ORDER BY " + selectionCriteria + " ASC;");
			} else if (sortingOption == 1) {
				databaseStatement = localConnection.prepareStatement(
						"SELECT * FROM `classicDesignation` ORDER BY " + selectionCriteria + " DESC;");
			}

			databaseRetrieval = databaseStatement.executeQuery();
			retrievalMetadata = databaseRetrieval.getMetaData();

			Vector<String> columnDenominations = new Vector<>();
			Vector<Vector<Object>> recordObjects = new Vector<Vector<Object>>();

			for (int columnIndex = 1; columnIndex <= retrievalMetadata.getColumnCount(); columnIndex++) {
				columnDenominations.add(retrievalMetadata.getColumnName(columnIndex));
			}

			while (databaseRetrieval.next()) {
				Vector<Object> retrievedRow = new Vector<>();
				for (int columnIndex = 1; columnIndex <= retrievalMetadata.getColumnCount(); columnIndex++) {
					retrievedRow.add(databaseRetrieval.getObject(columnIndex));
				}
				recordObjects.add(retrievedRow);
			}
			databaseObjects.add(recordObjects);
			databaseObjects.add(columnDenominations);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return databaseObjects;
	}

}
