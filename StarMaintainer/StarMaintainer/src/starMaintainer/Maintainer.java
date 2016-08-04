package starMaintainer;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Maintainer {

	public static void main(String[] args) {

		String designation = "Vulpeculae";
		String stellarTable = "flamsteedStar";
		String spectralTable = "spectraData";

		DecimalFormat magnitudeFormatter = new DecimalFormat("#.##");
		magnitudeFormatter.setRoundingMode(RoundingMode.FLOOR);

		DatabaseEditor spectralClassInstance = new DatabaseEditor();
		StellarCalculator stellarCalculatorInstance = new StellarCalculator();

		spectralClassInstance.anchorDatabases("flamsteed", "spectra");

		ArrayList<Object> stars = spectralClassInstance.retrieveStellarDesignations(designation, stellarTable);
		for (Integer starCounter = 0; starCounter < stars.size(); starCounter++) {
			String currentStar = stars.get(starCounter).toString();

			ArrayList<Object> stellarParameters = spectralClassInstance.retrieveInputParameters(currentStar,
					stellarTable);
			Double lightYears = Double.parseDouble(stellarParameters.get(0).toString());
			Double absoluteMagnitude = Double.parseDouble(stellarParameters.get(1).toString());

			String spectralClass = stellarParameters.get(2).toString();
			String stellarCategory = stellarParameters.get(3).toString();
			String spectra = spectralClass + stellarCategory;

			ArrayList<Object> spectralParameters = spectralClassInstance.retrieveSpectralParameters(spectra,
					spectralTable);
			Double stellarTemperature = Double.parseDouble(spectralParameters.get(0).toString());
			Double magnitudeCorrection = Double.parseDouble(spectralParameters.get(1).toString());
			Double bolometricMagnitude = Double
					.parseDouble(magnitudeFormatter.format(absoluteMagnitude + magnitudeCorrection));

			Double parsecs = Double.parseDouble(stellarCalculatorInstance.distanceConverter(1, lightYears));
			Double solarDiameter = Double
					.parseDouble(stellarCalculatorInstance.solarDiameter(stellarTemperature, bolometricMagnitude));
			Double absoluteLuminosity = Double
					.parseDouble(stellarCalculatorInstance.absoluteLuminosity(absoluteMagnitude));
			Double bolometricLuminosity = Double
					.parseDouble(stellarCalculatorInstance.bolometricLuminosity(bolometricMagnitude));

			ArrayList<String> habitableBoundaries = stellarCalculatorInstance.habitableBoundaries(bolometricLuminosity);
			Double innerBoundary = Double.parseDouble(habitableBoundaries.get(0));
			Double outerBoundary = Double.parseDouble(habitableBoundaries.get(1));
			Double gregorianYear = Double.parseDouble(habitableBoundaries.get(2));

			System.out.print("CURRENT STAR: " + currentStar + " ");
			System.out.print("PARSECS: " + parsecs + " ");
			System.out.print("BOLOMETRIC MAGNITUDE: " + bolometricMagnitude + " ");
			System.out.print("SOLAR DIAMETER: " + solarDiameter + " ");
			System.out.print("ABSOLUTE LUMINOSITY: " + absoluteLuminosity + " ");
			System.out.print("BOLOMETRIC LUMINOSITY: " + absoluteLuminosity + " ");
			System.out.print("INNER BOUNDARY: " + innerBoundary + " ");
			System.out.print("OUTER BOUNDARY: " + outerBoundary + " ");
			System.out.println("GREGORIAN YEAR: " + gregorianYear);

			spectralClassInstance.updateStar(parsecs, bolometricMagnitude, solarDiameter, absoluteLuminosity,
					bolometricLuminosity, stellarCategory, innerBoundary, outerBoundary, gregorianYear, currentStar,
					stellarTable);

		}
	}
}
