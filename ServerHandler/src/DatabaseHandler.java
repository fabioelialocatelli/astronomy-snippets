

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * En Route Application-Server DatabaseHandler = Database connector responsible
 * of sending requests to database Version 0.1a Created by Fabio Locatelli on
 * 19/08/2016 Last Modified by Fabio Locatelli on 19/08/2016
 */

public class DatabaseHandler {
	private String databaseEncoding = null;
	private String databaseHost = null;
	private String databaseSchema = null;
	private Connection databaseConnection = null;
	//private Integer affectedRows = null;
	private ResultSet rowData = null;
	private CallableStatement storedProcedure = null;
	private Integer loginCode = null;

	public void anchorDatabase() {

		databaseEncoding = "utf8";
		databaseSchema = "EnRoute";
		databaseHost = "enroute.c3mxtfqymfw6.ap-southeast-2.rds.amazonaws.com";

		try {
			databaseConnection = DriverManager.getConnection("jdbc:mysql://" + databaseHost + ":3306/" + databaseSchema
					+ "?characterEncoding=" + databaseEncoding, "enRouteTeam", "projectenroute2016");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void logIn(/*String googleID*/) {
		//Integer loginCode = 0;
		try {
			storedProcedure = databaseConnection.prepareCall("CALL logIn(?, ?);");
			storedProcedure.setString(1, "128615464818353674980");
			storedProcedure.registerOutParameter(2, java.sql.Types.INTEGER);
			storedProcedure.execute();
			
			//loginCode = storedProcedure.getInt(2);
			System.out.println(storedProcedure.getInt(2));

		} catch (SQLException e) {
			e.printStackTrace();
		}
		//return loginCode;
	}

	public void createAccount() {
		// STUB
	}

}
