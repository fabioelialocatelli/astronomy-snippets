package starMaintainer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseEditor {

	private ArrayList<Object> returnedObjects = null;
	private Connection stellarConnection = null;
	private Connection spectralConnection = null;
	private PreparedStatement spectralAttributes = null;
	private PreparedStatement stellarAttributes = null;
	private PreparedStatement updateAttributes = null;
	private ResultSet databaseRetrieval = null;
	private ResultSetMetaData retrievalMetadata = null;

	public void anchorDatabases(String stellarSchema, String spectralSchema) {
		try {
			stellarConnection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/" + stellarSchema + "?characterEncoding=utf8", "root",
					"HadarToliman@#$");
			spectralConnection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/" + spectralSchema + "?characterEncoding=utf8", "root",
					"HadarToliman@#$");
		} catch (SQLException databaseException) {
			databaseException.printStackTrace();
		}
	}

	public ArrayList<Object> retrieveStellarDesignations(String constellation, String table) {
		try {
			spectralAttributes = stellarConnection.prepareStatement(
					"SELECT designation FROM " + table + " WHERE designation LIKE '%" + constellation + "';");
			databaseRetrieval = spectralAttributes.executeQuery();
			retrievalMetadata = databaseRetrieval.getMetaData();
			returnedObjects = new ArrayList<>();

			while (databaseRetrieval.next()) {
				for (int columnIndex = 1; columnIndex <= retrievalMetadata.getColumnCount(); columnIndex++) {
					returnedObjects.add(databaseRetrieval.getObject(columnIndex));
				}
			}
		} catch (NullPointerException | SQLException retrievalException) {
			retrievalException.printStackTrace();
		}
		return returnedObjects;
	}

	public ArrayList<Object> retrieveSpectralParameters(String spectralClass, String table) {
		try {
			spectralAttributes = spectralConnection.prepareStatement(
					"SELECT temperatureKelvin, magnitudeCorrection FROM " + table + " WHERE spectra = ?;");
			spectralAttributes.setString(1, spectralClass);
			databaseRetrieval = spectralAttributes.executeQuery();
			retrievalMetadata = databaseRetrieval.getMetaData();
			returnedObjects = new ArrayList<>();

			while (databaseRetrieval.next()) {
				for (int columnIndex = 1; columnIndex <= retrievalMetadata.getColumnCount(); columnIndex++) {
					returnedObjects.add(databaseRetrieval.getObject(columnIndex));
				}
			}
		} catch (NullPointerException | SQLException retrievalException) {
			retrievalException.printStackTrace();
		}
		return returnedObjects;
	}

	public ArrayList<Object> retrieveInputParameters(String designation, String table) {
		try {
			stellarAttributes = stellarConnection
					.prepareStatement("SELECT lightYears, absoluteMagnitude, spectralClass, stellarCategory FROM "
							+ table + " WHERE designation = ?;");
			stellarAttributes.setString(1, designation);
			databaseRetrieval = stellarAttributes.executeQuery();
			retrievalMetadata = databaseRetrieval.getMetaData();
			returnedObjects = new ArrayList<>();

			while (databaseRetrieval.next()) {
				for (int columnIndex = 1; columnIndex <= retrievalMetadata.getColumnCount(); columnIndex++) {
					returnedObjects.add(databaseRetrieval.getObject(columnIndex));
				}
			}
		} catch (NullPointerException | SQLException retrievalException) {
			retrievalException.printStackTrace();
		}
		return returnedObjects;
	}

	public void updateStar(Double parsecs, Double bolometricMagnitude, Double solarDiameter, Double absoluteLuminosity,
			Double bolometricLuminosity, String stellarCategory, Double innerBoundary, Double outerBoundary,
			Double gregorianYear, String designation, String table) {
		try {
			updateAttributes = stellarConnection.prepareStatement("UPDATE " + table
					+ " SET parsecs = ?, bolometricMagnitude = ?, solarDiameter = ?, absoluteLuminosity = ?, bolometricLuminosity = ?, stellarCategory = ?, innerBoundary = ?, outerBoundary = ?, gregorianYear = ? WHERE designation ='"
					+ designation + "';");
			updateAttributes.setDouble(1, parsecs);
			updateAttributes.setDouble(2, bolometricMagnitude);
			updateAttributes.setDouble(3, solarDiameter);
			updateAttributes.setDouble(4, absoluteLuminosity);
			updateAttributes.setDouble(5, bolometricLuminosity);
			updateAttributes.setString(6, stellarCategory);
			updateAttributes.setDouble(7, innerBoundary);
			updateAttributes.setDouble(8, outerBoundary);
			updateAttributes.setDouble(9, gregorianYear);
			updateAttributes.execute();
		} catch (SQLException updateException) {
			updateException.printStackTrace();
		}
	}
}
