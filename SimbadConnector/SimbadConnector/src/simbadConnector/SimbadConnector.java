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
		Connection bayerConnection = null;
		Connection flamsteedConnection = null;
		PreparedStatement databaseManipulation = null;

		try {
			bayerConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bayer?characterEncoding=utf8",
					"root", "HadarToliman@#$");
			flamsteedConnection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/flamsteed?characterEncoding=utf8", "root", "HadarToliman@#$");
		} catch (SQLException connectionError) {
			connectionError.getMessage();
		}

		String[] bayerLetters = { "alf", "bet", "gam", "del", "eps", "zet", "eta", "tet", "iot", "kap", "lam", "mu",
				"nu", "ksi", "omi", "pi", "rho", "sig", "tau", "ups", "phi", "chi", "psi", "ome" };

		String[] bayerNumbers = { "0", "1", "2", "3", "4", "5" };

		String[] bayerConstellations = { "and", "ant", "aps", "aqr", "aql", "ara", "ari", "aur", "boo", "cae", "cnc",
				"cma", "cap", "car", "cas", "cen", "cep", "cet", "cha", "cir", "col", "cra", "crb", "crv", "crt", "cru",
				"cyg", "dor", "dra", "eri", "for", "gem", "gru", "her", "hor", "hya", "hyi", "ind", "leo", "lep", "lib",
				"lup", "lyr", "men", "mic", "mus", "nor", "oct", "oph", "ori", "pav", "peg", "per", "phe", "pic", "psc",
				"psa", "pup", "pyx", "ret", "sgr", "sco", "scl", "sct", "ser", "tel", "tra", "tuc", "uma", "umi", "vel",
				"vir", "vol" };

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

		option = "bayer";

		switch (option) {

		case "bayer":

			String bayerLetter = null;

			for (int i = 0; i < bayerConstellations.length; i++) {
				for (int j = 0; j < bayerLetters.length; j++) {
					for (int k = 0; k < bayerNumbers.length; k++) {

						String constellation = bayerConstellations[i];

						if (bayerNumbers[k] == "0")
							bayerLetter = bayerLetters[j];
						else
							bayerLetter = bayerLetters[j] + bayerNumbers[k];
						String star = bayerLetter + "+" + constellation;

						System.out.println("SIMBAD STAR: " + star);

						String simbadURL = "http://simbad.u-strasbg.fr/simbad/sim-id?Ident=" + star
								+ "&NbIdent=1&Radius=2&Radius.unit=arcmin&submit=submit+id";
						String stellarTable = "bayerstar";

						String databaseIdentifier = null;
						String databaseConstellation = null;

						switch (bayerLetter) {
						case "alf":
							databaseIdentifier = "α";
							break;
						case "alf1":
							databaseIdentifier = "α1";
							break;
						case "alf2":
							databaseIdentifier = "α2";
							break;
						case "alf3":
							databaseIdentifier = "α3";
							break;
						case "alf4":
							databaseIdentifier = "α4";
							break;
						case "alf5":
							databaseIdentifier = "α5";
							break;
						case "bet":
							databaseIdentifier = "β";
							break;
						case "bet1":
							databaseIdentifier = "β1";
							break;
						case "bet2":
							databaseIdentifier = "β2";
							break;
						case "bet3":
							databaseIdentifier = "β3";
							break;
						case "bet4":
							databaseIdentifier = "β4";
							break;
						case "bet5":
							databaseIdentifier = "β5";
							break;
						case "gam":
							databaseIdentifier = "γ";
							break;
						case "gam1":
							databaseIdentifier = "γ1";
							break;
						case "gam2":
							databaseIdentifier = "γ2";
							break;
						case "gam3":
							databaseIdentifier = "γ3";
							break;
						case "gam4":
							databaseIdentifier = "γ4";
							break;
						case "gam5":
							databaseIdentifier = "γ5";
							break;
						case "del":
							databaseIdentifier = "δ";
							break;
						case "del1":
							databaseIdentifier = "δ1";
							break;
						case "del2":
							databaseIdentifier = "δ2";
							break;
						case "del3":
							databaseIdentifier = "δ3";
							break;
						case "del4":
							databaseIdentifier = "δ4";
							break;
						case "del5":
							databaseIdentifier = "δ5";
							break;
						case "eps":
							databaseIdentifier = "ε";
							break;
						case "eps1":
							databaseIdentifier = "ε1";
							break;
						case "eps2":
							databaseIdentifier = "ε2";
							break;
						case "eps3":
							databaseIdentifier = "ε3";
							break;
						case "eps4":
							databaseIdentifier = "ε4";
							break;
						case "eps5":
							databaseIdentifier = "ε5";
							break;
						case "zet":
							databaseIdentifier = "ζ";
							break;
						case "zet1":
							databaseIdentifier = "ζ1";
							break;
						case "zet2":
							databaseIdentifier = "ζ2";
							break;
						case "zet3":
							databaseIdentifier = "ζ3";
							break;
						case "zet4":
							databaseIdentifier = "ζ4";
							break;
						case "zet5":
							databaseIdentifier = "ζ5";
							break;
						case "eta":
							databaseIdentifier = "η";
							break;
						case "eta1":
							databaseIdentifier = "η1";
							break;
						case "eta2":
							databaseIdentifier = "η2";
							break;
						case "eta3":
							databaseIdentifier = "η3";
							break;
						case "eta4":
							databaseIdentifier = "η4";
							break;
						case "eta5":
							databaseIdentifier = "η5";
							break;
						case "tet":
							databaseIdentifier = "θ";
							break;
						case "tet1":
							databaseIdentifier = "θ1";
							break;
						case "tet2":
							databaseIdentifier = "θ2";
							break;
						case "tet3":
							databaseIdentifier = "θ3";
							break;
						case "tet4":
							databaseIdentifier = "θ4";
							break;
						case "tet5":
							databaseIdentifier = "θ5";
							break;
						case "iot":
							databaseIdentifier = "ι";
							break;
						case "iot1":
							databaseIdentifier = "ι1";
							break;
						case "iot2":
							databaseIdentifier = "ι2";
							break;
						case "iot3":
							databaseIdentifier = "ι3";
							break;
						case "iot4":
							databaseIdentifier = "ι4";
							break;
						case "iot5":
							databaseIdentifier = "ι5";
							break;
						case "kap":
							databaseIdentifier = "κ";
							break;
						case "kap1":
							databaseIdentifier = "κ1";
							break;
						case "kap2":
							databaseIdentifier = "κ2";
							break;
						case "kap3":
							databaseIdentifier = "κ3";
							break;
						case "kap4":
							databaseIdentifier = "κ4";
							break;
						case "kap5":
							databaseIdentifier = "κ5";
							break;
						case "lam":
							databaseIdentifier = "λ";
							break;
						case "lam1":
							databaseIdentifier = "λ1";
							break;
						case "lam2":
							databaseIdentifier = "λ2";
							break;
						case "lam3":
							databaseIdentifier = "λ3";
							break;
						case "lam4":
							databaseIdentifier = "λ4";
							break;
						case "lam5":
							databaseIdentifier = "λ5";
							break;
						case "mu":
							databaseIdentifier = "μ";
							break;
						case "mu1":
							databaseIdentifier = "μ1";
							break;
						case "mu2":
							databaseIdentifier = "μ2";
							break;
						case "mu3":
							databaseIdentifier = "μ3";
							break;
						case "mu4":
							databaseIdentifier = "μ4";
							break;
						case "mu5":
							databaseIdentifier = "μ5";
							break;
						case "nu":
							databaseIdentifier = "ν";
							break;
						case "nu1":
							databaseIdentifier = "ν1";
							break;
						case "nu2":
							databaseIdentifier = "ν2";
							break;
						case "nu3":
							databaseIdentifier = "ν3";
							break;
						case "nu4":
							databaseIdentifier = "ν4";
							break;
						case "nu5":
							databaseIdentifier = "ν5";
							break;
						case "ksi":
							databaseIdentifier = "ξ";
							break;
						case "ksi1":
							databaseIdentifier = "ξ1";
							break;
						case "ksi2":
							databaseIdentifier = "ξ2";
							break;
						case "ksi3":
							databaseIdentifier = "ξ3";
							break;
						case "ksi4":
							databaseIdentifier = "ξ4";
							break;
						case "ksi5":
							databaseIdentifier = "ξ5";
							break;
						case "omi":
							databaseIdentifier = "ο";
							break;
						case "omi1":
							databaseIdentifier = "ο1";
							break;
						case "omi2":
							databaseIdentifier = "ο2";
							break;
						case "omi3":
							databaseIdentifier = "ο3";
							break;
						case "omi4":
							databaseIdentifier = "ο4";
							break;
						case "omi5":
							databaseIdentifier = "ο5";
							break;
						case "pi":
							databaseIdentifier = "π";
							break;
						case "pi1":
							databaseIdentifier = "π1";
							break;
						case "pi2":
							databaseIdentifier = "π2";
							break;
						case "pi3":
							databaseIdentifier = "π3";
							break;
						case "pi4":
							databaseIdentifier = "π4";
							break;
						case "pi5":
							databaseIdentifier = "π5";
							break;
						case "rho":
							databaseIdentifier = "ρ";
							break;
						case "rho1":
							databaseIdentifier = "ρ1";
							break;
						case "rho2":
							databaseIdentifier = "ρ2";
							break;
						case "rho3":
							databaseIdentifier = "ρ3";
							break;
						case "rho4":
							databaseIdentifier = "ρ4";
							break;
						case "rho5":
							databaseIdentifier = "ρ5";
							break;
						case "sig":
							databaseIdentifier = "σ";
							break;
						case "sig1":
							databaseIdentifier = "σ1";
							break;
						case "sig2":
							databaseIdentifier = "σ2";
							break;
						case "sig3":
							databaseIdentifier = "σ3";
							break;
						case "sig4":
							databaseIdentifier = "σ4";
							break;
						case "sig5":
							databaseIdentifier = "σ5";
							break;
						case "tau":
							databaseIdentifier = "τ";
							break;
						case "tau1":
							databaseIdentifier = "τ1";
							break;
						case "tau2":
							databaseIdentifier = "τ2";
							break;
						case "tau3":
							databaseIdentifier = "τ3";
							break;
						case "tau4":
							databaseIdentifier = "τ4";
							break;
						case "tau5":
							databaseIdentifier = "τ5";
							break;
						case "ups":
							databaseIdentifier = "υ";
							break;
						case "ups1":
							databaseIdentifier = "υ1";
							break;
						case "ups2":
							databaseIdentifier = "υ2";
							break;
						case "ups3":
							databaseIdentifier = "υ3";
							break;
						case "ups4":
							databaseIdentifier = "υ4";
							break;
						case "ups5":
							databaseIdentifier = "υ5";
							break;
						case "phi":
							databaseIdentifier = "φ";
							break;
						case "phi1":
							databaseIdentifier = "φ1";
							break;
						case "phi2":
							databaseIdentifier = "φ2";
							break;
						case "phi3":
							databaseIdentifier = "φ3";
							break;
						case "phi4":
							databaseIdentifier = "φ4";
							break;
						case "phi5":
							databaseIdentifier = "φ5";
							break;
						case "chi":
							databaseIdentifier = "χ";
							break;
						case "chi1":
							databaseIdentifier = "χ1";
							break;
						case "chi2":
							databaseIdentifier = "χ2";
							break;
						case "chi3":
							databaseIdentifier = "χ3";
							break;
						case "chi4":
							databaseIdentifier = "χ4";
							break;
						case "chi5":
							databaseIdentifier = "χ5";
							break;
						case "psi":
							databaseIdentifier = "ψ";
							break;
						case "psi1":
							databaseIdentifier = "ψ1";
							break;
						case "psi2":
							databaseIdentifier = "ψ2";
							break;
						case "psi3":
							databaseIdentifier = "ψ3";
							break;
						case "psi4":
							databaseIdentifier = "ψ4";
							break;
						case "psi5":
							databaseIdentifier = "ψ5";
							break;
						case "ome":
							databaseIdentifier = "ω";
							break;
						case "ome1":
							databaseIdentifier = "ω1";
							break;
						case "ome2":
							databaseIdentifier = "ω2";
							break;
						case "ome3":
							databaseIdentifier = "ω3";
							break;
						case "ome4":
							databaseIdentifier = "ω4";
							break;
						case "ome5":
							databaseIdentifier = "ω5";
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
						System.out.println("DATABASE STAR: " + databaseStar);

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

								databaseManipulation = bayerConnection.prepareStatement("UPDATE " + stellarTable
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

							databaseManipulation = flamsteedConnection.prepareStatement("UPDATE " + stellarTable
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
