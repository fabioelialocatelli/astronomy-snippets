package simbadConnector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class SimbadConnector {

	public static void main(String[] args) {

		int connectionCode = 0;
		URL descriptionURL = null;
		HttpURLConnection sourceConnection = null;
		BufferedReader source = null;
		StringBuilder unrefinedHTML = null;
		String option = null;
		String precedingData = null;

		Document page = null;
		Connection databaseConnection = null;
		PreparedStatement databaseManipulation = null;

		String[] bayerLetters = { "alf", "bet", "gam", "del", "eps", "zet", "eta", "tet", "iot", "kap", "lam", "mu",
				"nu", "ksi", "omi", "pi", "rho", "sig", "tau", "ups", "phi", "psi", "chi", "ome" };

		String[] bayerConstellations = { "and", "ant", "aps", "aqr", "aql", "ara", "ari", "aur", "boo", "cae", "cnc",
				"cma", "cap", "car", "cas", "cen", "cep", "cet", "cha", "cir", "col", "cra", "crb", "crv", "crt", "cru",
				"cyg", "dor", "dra", "eri", "for", "gem", "gru", "her", "hor", "hya", "hyi", "ind", "leo", "lep", "lib",
				"lup", "lyr", "men", "mic", "mus", "nor", "oct", "oph", "ori", "pav", "peg", "per", "phe", "pic", "psc",
				"psa", "pup", "pyx", "ret", "sgr", "sco", "scl", "sct", "ser", "sex", "tau", "tel", "tra", "tuc", "uma",
				"umi", "vel", "vir", "vol", };

		String[] flamsteedNumbers = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15",
				"16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32",
				"33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49",
				"50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61", "62", "63", "64", "65", "66",
				"67", "68", "69", "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "80", "81", "82", "83",
				"84", "85", "86", "87", "88", "89", "90", "91", "92", "93", "94", "95", "96", "97", "98", "99", "100",
				"101", "102", "103", "104", "105", "106", "107", "108", "109", "110", "111", "112", "113", "114", "115",
				"116", "117", "118", "119", "120", "121", "122", "123", "124", "125", "126", "127", "128", "129", "130",
				"131", "132", "133", "134", "135", "136", "137", "138", "139", "140", "141", "142", "143", "144",
				"145" };

		String[] flamsteedConstellations = { "cam", "cvn", "cmi", "com", "del", "equ", "lac", "lmi", "lyn", "mon",
				"sge", "sex", "tau", "tri", "vul" };

		option = "flamsteed";

		switch (option) {

		case "bayer":

			for (int i = 0; i < bayerConstellations.length; i++) {
				for (int j = 0; j < bayerLetters.length; j++) {

					String bayerLetter = bayerLetters[j];
					String constellation = bayerConstellations[i];
					String star = bayerLetter + "+" + constellation;

					String simbadURL = "http://simbad.u-strasbg.fr/simbad/sim-id?Ident=" + star
							+ "&NbIdent=1&Radius=2&Radius.unit=arcmin&submit=submit+id";
					String stellarSchema = "bayer";
					String stellarTable = "bayerstar";

					String databaseIdentifier = null;
					String databaseConstellation = null;

					switch (bayerLetter) {
					case "alf":
						databaseIdentifier = "α";
						break;
					case "bet":
						databaseIdentifier = "β";
						break;
					case "gam":
						databaseIdentifier = "γ";
						break;
					case "del":
						databaseIdentifier = "δ";
						break;
					case "eps":
						databaseIdentifier = "ε";
						break;
					case "zet":
						databaseIdentifier = "ζ";
						break;
					case "eta":
						databaseIdentifier = "η";
						break;
					case "tet":
						databaseIdentifier = "θ";
						break;
					case "iot":
						databaseIdentifier = "ι";
						break;
					case "kap":
						databaseIdentifier = "κ";
						break;
					case "lam":
						databaseIdentifier = "λ";
						break;
					case "mu":
						databaseIdentifier = "μ";
						break;
					case "nu":
						databaseIdentifier = "ν";
						break;
					case "ksi":
						databaseIdentifier = "ξ";
						break;
					case "omi":
						databaseIdentifier = "ο";
						break;
					case "pi":
						databaseIdentifier = "π";
						break;
					case "rho":
						databaseIdentifier = "ρ";
						break;
					case "sig":
						databaseIdentifier = "σ";
						break;
					case "tau":
						databaseIdentifier = "τ";
						break;
					case "ups":
						databaseIdentifier = "υ";
						break;
					case "phi":
						databaseIdentifier = "φ";
						break;
					case "chi":
						databaseIdentifier = "χ";
						break;
					case "psi":
						databaseIdentifier = "ψ";
						break;
					case "ome":
						databaseIdentifier = "ω";
						break;
					}

					switch (constellation) {
					case "and":
						databaseConstellation = "Andromedae";
						break;
					case "ant":
						databaseConstellation = "Antliae";
						break;
					case "aps":
						databaseConstellation = "Apodis";
						break;
					case "aqr":
						databaseConstellation = "Aquarii";
						break;
					case "aql":
						databaseConstellation = "Aquilae";
						break;
					case "ara":
						databaseConstellation = "Arae";
						break;
					case "ari":
						databaseConstellation = "Arietis";
						break;
					case "aur":
						databaseConstellation = "Aurigae";
						break;
					case "boo":
						databaseConstellation = "Bootis";
						break;
					case "cae":
						databaseConstellation = "Caeli";
						break;
					case "cnc":
						databaseConstellation = "Cancri";
						break;
					case "cma":
						databaseConstellation = "Canis Majoris";
						break;
					case "cap":
						databaseConstellation = "Capricorni";
						break;
					case "car":
						databaseConstellation = "Carinae";
						break;
					case "cas":
						databaseConstellation = "Cassiopeiae";
						break;
					case "cen":
						databaseConstellation = "Centauri";
						break;
					case "cep":
						databaseConstellation = "Cephei";
						break;
					case "cet":
						databaseConstellation = "Ceti";
						break;
					case "cha":
						databaseConstellation = "Chamaeleontis";
						break;
					case "cir":
						databaseConstellation = "Circini";
						break;
					case "col":
						databaseConstellation = "Columbae";
						break;
					case "cra":
						databaseConstellation = "Coronae Australis";
						break;
					case "crb":
						databaseConstellation = "Coronae Borealis";
						break;
					case "crv":
						databaseConstellation = "Corvi";
						break;
					case "crt":
						databaseConstellation = "Crateris";
						break;
					case "cru":
						databaseConstellation = "Crucis";
						break;
					case "cyg":
						databaseConstellation = "Cygni";
						break;
					case "dor":
						databaseConstellation = "Doradus";
						break;
					case "dra":
						databaseConstellation = "Draconis";
						break;
					case "eri":
						databaseConstellation = "Eridani";
						break;
					case "for":
						databaseConstellation = "Fornacis";
						break;
					case "gem":
						databaseConstellation = "Geminorum";
						break;
					case "gru":
						databaseConstellation = "Gruis";
						break;
					case "her":
						databaseConstellation = "Herculis";
						break;
					case "hor":
						databaseConstellation = "Horologii";
						break;
					case "hya":
						databaseConstellation = "Hydrae";
						break;
					case "hyi":
						databaseConstellation = "Hydri";
						break;
					case "ind":
						databaseConstellation = "Indi";
						break;
					case "leo":
						databaseConstellation = "Leonis";
						break;
					case "lep":
						databaseConstellation = "Leporis";
						break;
					case "lib":
						databaseConstellation = "Librae";
						break;
					case "lup":
						databaseConstellation = "Lupi";
						break;
					case "lyr":
						databaseConstellation = "Lyrae";
						break;
					case "men":
						databaseConstellation = "Mensae";
						break;
					case "mic":
						databaseConstellation = "Microscopii";
						break;
					case "mus":
						databaseConstellation = "Muscae";
						break;
					case "nor":
						databaseConstellation = "Normae";
						break;
					case "oct":
						databaseConstellation = "Octantis";
						break;
					case "oph":
						databaseConstellation = "Ophiuchi";
						break;
					case "ori":
						databaseConstellation = "Orionis";
						break;
					case "pav":
						databaseConstellation = "Pavonis";
						break;
					case "peg":
						databaseConstellation = "Pegasi";
						break;
					case "per":
						databaseConstellation = "Persei";
						break;
					case "phe":
						databaseConstellation = "Phoenicis";
						break;
					case "pic":
						databaseConstellation = "Pictoris";
						break;
					case "psc":
						databaseConstellation = "Piscium";
						break;
					case "psa":
						databaseConstellation = "Piscis Austrini";
						break;
					case "pup":
						databaseConstellation = "Puppis";
						break;
					case "pyx":
						databaseConstellation = "Pyxidis";
						break;
					case "ret":
						databaseConstellation = "Reticuli";
						break;
					case "sgr":
						databaseConstellation = "Sagittarii";
						break;
					case "sco":
						databaseConstellation = "Scorpii";
						break;
					case "scl":
						databaseConstellation = "Sculptoris";
						break;
					case "sct":
						databaseConstellation = "Scuti";
						break;
					case "ser":
						databaseConstellation = "Serpentis";
						break;
					case "tel":
						databaseConstellation = "Telescopii";
						break;
					case "tra":
						databaseConstellation = "Trianguli Australis";
						break;
					case "tuc":
						databaseConstellation = "Tucanae";
						break;
					case "uma":
						databaseConstellation = "Ursae Majoris";
						break;
					case "umi":
						databaseConstellation = "Ursae Minoris";
						break;
					case "vel":
						databaseConstellation = "Velorum";
						break;
					case "vir":
						databaseConstellation = "Virginis";
						break;
					case "vol":
						databaseConstellation = "Volantis";
						break;
					}

					String databaseStar = databaseIdentifier + " " + databaseConstellation;
					System.out.println("CURRENT STAR: " + databaseStar);

					try {

						descriptionURL = new URL(simbadURL);
						sourceConnection = (HttpURLConnection) descriptionURL.openConnection();
						connectionCode = sourceConnection.getResponseCode();

						if (connectionCode != 404) {

							source = new BufferedReader(new InputStreamReader(descriptionURL.openStream()));
							unrefinedHTML = new StringBuilder();

							while ((precedingData = source.readLine()) != null) {
								unrefinedHTML.append(precedingData + "\n");
							}

							page = Jsoup.parse(unrefinedHTML.toString());

							String identifierHD = page.select("a:contains(HD)").get(1).text().replace("HD ", "");
							String identifierHIP = page.select("a:contains(HIP)").get(1).text().replace("HIP ", "");
							String identifierSAO = page.select("a:contains(SAO)").get(1).text().replace("SAO ", "");

							System.out.println("HD IDENTIFIER: " + identifierHD);
							System.out.println("HIP IDENTIFIER: " + identifierHIP);
							System.out.println("SAO IDENTIFIER: " + identifierSAO);

							databaseConnection = DriverManager.getConnection(
									"jdbc:mysql://localhost:3306/" + stellarSchema + "?characterEncoding=utf8", "root",
									"HadarToliman@#$");
							databaseManipulation = databaseConnection.prepareStatement("UPDATE " + stellarTable
									+ " SET `identifierHD`= ?, `identifierHIP`= ?, `identifierSAO`= ? WHERE `designation`='"
									+ databaseStar + "';");
							databaseManipulation.setString(1, identifierHD);
							databaseManipulation.setString(2, identifierHIP);
							databaseManipulation.setString(3, identifierSAO);
							databaseManipulation.execute();

							source.close();
							Thread.sleep(5000);
						}
					} catch (IndexOutOfBoundsException | IOException | InterruptedException
							| SQLException parsingException) {
						parsingException.getMessage();
					}
				}
			}
			break;

		case "flamsteed":

			for (int i = 0; i < flamsteedConstellations.length; i++) {
				for (int j = 0; j < flamsteedNumbers.length; j++) {

					String flamsteedNumber = flamsteedNumbers[j];
					String constellation = flamsteedConstellations[i];
					String star = flamsteedNumber + "+" + constellation;

					String simbadURL = "http://simbad.u-strasbg.fr/simbad/sim-id?Ident=" + star
							+ "&NbIdent=1&Radius=2&Radius.unit=arcmin&submit=submit+id";
					String stellarSchema = "flamsteed";
					String stellarTable = "flamsteedstar";

					String databaseIdentifier = flamsteedNumber;
					String databaseConstellation = null;

					switch (constellation) {
					case "cam":
						databaseConstellation = "Camelopardalis";
						break;
					case "cvn":
						databaseConstellation = "Canum Venaticorum";
						break;
					case "cmi":
						databaseConstellation = "Canis Minoris";
						break;
					case "com":
						databaseConstellation = "Comae Berenices";
						break;
					case "del":
						databaseConstellation = "Delphini";
						break;
					case "equ":
						databaseConstellation = "Equulei";
						break;
					case "lac":
						databaseConstellation = "Lacertae";
						break;
					case "lmi":
						databaseConstellation = "Leonis Minoris";
						break;
					case "lyn":
						databaseConstellation = "Lyncis";
						break;
					case "mon":
						databaseConstellation = "Monocerotis";
						break;
					case "sge":
						databaseConstellation = "Sagittae";
						break;
					case "sex":
						databaseConstellation = "Sextantis";
						break;
					case "tau":
						databaseConstellation = "Tauri";
						break;
					case "tri":
						databaseConstellation = "Trianguli";
						break;
					case "vul":
						databaseConstellation = "Vulpeculae";
						break;
					}

					String databaseStar = databaseIdentifier + " " + databaseConstellation;
					System.out.println("CURRENT STAR: " + databaseStar);

					try {

						descriptionURL = new URL(simbadURL);
						sourceConnection = (HttpURLConnection) descriptionURL.openConnection();
						connectionCode = sourceConnection.getResponseCode();

						if (connectionCode != 404) {

							source = new BufferedReader(new InputStreamReader(descriptionURL.openStream()));
							unrefinedHTML = new StringBuilder();

							while ((precedingData = source.readLine()) != null) {
								unrefinedHTML.append(precedingData + "\n");
							}

							page = Jsoup.parse(unrefinedHTML.toString());

							String identifierHD = page.select("a:contains(HD)").get(1).text().replace("HD ", "");
							String identifierHIP = page.select("a:contains(HIP)").get(1).text().replace("HIP ", "");
							String identifierSAO = page.select("a:contains(SAO)").get(1).text().replace("SAO ", "");

							System.out.println("HD IDENTIFIER: " + identifierHD);
							System.out.println("HIP IDENTIFIER: " + identifierHIP);
							System.out.println("SAO IDENTIFIER: " + identifierSAO);

							databaseConnection = DriverManager.getConnection(
									"jdbc:mysql://localhost:3306/" + stellarSchema + "?characterEncoding=utf8", "root",
									"HadarToliman@#$");
							databaseManipulation = databaseConnection.prepareStatement("UPDATE " + stellarTable
									+ " SET `identifierHD`= ?, `identifierHIP`= ?, `identifierSAO`= ? WHERE `designation`='"
									+ databaseStar + "';");
							databaseManipulation.setString(1, identifierHD);
							databaseManipulation.setString(2, identifierHIP);
							databaseManipulation.setString(3, identifierSAO);
							databaseManipulation.execute();

							source.close();
							Thread.sleep(5000);
						}
					} catch (IndexOutOfBoundsException | IOException | InterruptedException
							| SQLException parsingException) {
						parsingException.getMessage();
					}
				}
			}
			break;
		}
	}
}
