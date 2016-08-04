package starCompanion;

import java.io.File;

public class FileCleaner implements Runnable {

	private File descriptionDirectory = null;
	private File datasetDirectory = null;
	private File[] descriptions = null;
	private File[] statements = null;
	private String descriptionsPath = null;
	private String datasetPath = null;
	private String mysqlPath = null;
	private String oraclePath = null;

	public FileCleaner(String descriptionsLocation, String statementsLocation, String mysql, String oracle) {
		this.descriptionsPath = descriptionsLocation;
		this.datasetPath = statementsLocation;
		this.mysqlPath = mysql;
		this.oraclePath = oracle;
	}

	public void cleanDirectories() {

		datasetDirectory = new File(mysqlPath);
		statements = datasetDirectory.listFiles();
		for (File statement : statements) {
			if (statement.isFile()) {
				statement.delete();
			}
		}

		datasetDirectory = new File(oraclePath);
		statements = datasetDirectory.listFiles();
		for (File statement : statements) {
			if (statement.isFile()) {
				statement.delete();
			}
		}

		datasetDirectory = new File(datasetPath);
		statements = datasetDirectory.listFiles();
		for (File statement : statements) {
			if (statement.isFile()) {
				statement.delete();
			}
		}

		descriptionDirectory = new File(descriptionsPath);
		descriptions = descriptionDirectory.listFiles();
		for (File starDescription : descriptions) {
			if (starDescription.isFile()) {
				starDescription.delete();
			}
		}
	}

	@Override
	public void run() {
		cleanDirectories();
	}
}
