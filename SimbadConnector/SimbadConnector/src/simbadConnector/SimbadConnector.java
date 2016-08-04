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
		String precedingData = null;

		Document page = null;
		Connection databaseConnection = null;
		PreparedStatement databaseManipulation = null;

		String prefix = "alf";
		String constellation = "dra";
		String star = prefix + "+" + constellation;

		String simbadURL = "http://simbad.u-strasbg.fr/simbad/sim-id?Ident=" + star
				+ "&NbIdent=1&Radius=2&Radius.unit=arcmin&submit=submit+id";
		String stellarSchema = "bayer";
		String stellarTable = "bayerstar";

		String databasePrefix = null;
		String databaseConstellation = null;

		switch (prefix) {
		case "alf":
			databasePrefix = "α";
			break;
		case "bet":
			databasePrefix = "β";
			break;
		case "gam":
			databasePrefix = "γ";
			break;
		case "del":
			databasePrefix = "δ";
			break;
		case "eps":
			databasePrefix = "ε";
			break;
		case "zet":
			databasePrefix = "ζ";
			break;
		case "eta":
			databasePrefix = "η";
			break;
		case "tet":
			databasePrefix = "θ";
			break;
		case "iot":
			databasePrefix = "ι";
			break;
		case "kap":
			databasePrefix = "κ";
			break;
		case "lam":
			databasePrefix = "λ";
			break;
		case "mu":
			databasePrefix = "μ";
			break;
		case "nu":
			databasePrefix = "ν";
			break;
		case "ksi":
			databasePrefix = "ξ";
			break;
		case "omi":
			databasePrefix = "ο";
			break;
		case "pi":
			databasePrefix = "π";
			break;
		case "rho":
			databasePrefix = "ρ";
			break;
		case "sig":
			databasePrefix = "σ";
			break;
		case "tau":
			databasePrefix = "τ";
			break;
		case "ups":
			databasePrefix = "υ";
			break;
		case "phi":
			databasePrefix = "φ";
			break;
		case "chi":
			databasePrefix = "χ";
			break;
		case "psi":
			databasePrefix = "ψ";
			break;
		case "ome":
			databasePrefix = "ω";
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
		case "cam":
			databaseConstellation = "Camelopardalis";
			break;
		case "cnc":
			databaseConstellation = "Cancri";
			break;
		case "cvn":
			databaseConstellation = "Canum Venaticorum";
			break;
		case "cma":
			databaseConstellation = "Canis Majoris";
			break;
		case "cmi":
			databaseConstellation = "Canis Minoris";
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
		case "com":
			databaseConstellation = "Comae Berenices";
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
		case "del":
			databaseConstellation = "Delphini";
			break;
		case "dor":
			databaseConstellation = "Doradus";
			break;
		case "dra":
			databaseConstellation = "Draconis";
			break;
		case "equ":
			databaseConstellation = "Equuelei";
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
		case "lac":
			databaseConstellation = "Lacertae";
			break;
		case "leo":
			databaseConstellation = "Leonis";
			break;
		case "lmi":
			databaseConstellation = "Leonis Minoris";
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
		case "lyn":
			databaseConstellation = "Lyncis";
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
		case "mon":
			databaseConstellation = "Monocerotis";
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
		case "sge":
			databaseConstellation = "Sagittae";
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
		case "sex":
			databaseConstellation = "Sextantis";
			break;
		case "tau":
			databaseConstellation = "Tauri";
			break;
		case "tel":
			databaseConstellation = "Telescopii";
			break;
		case "tri":
			databaseConstellation = "Trianguli";
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
		case "vul":
			databaseConstellation = "Vulpeculae";
			break;
		}

		String databaseStar = databasePrefix + " " + databaseConstellation;
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

				Integer designationHD = Integer
						.parseInt(page.select("a:contains(HD)").get(1).text().replace("HD ", ""));
				Integer designationHIP = Integer
						.parseInt(page.select("a:contains(HIP)").get(1).text().replace("HIP ", ""));
				Integer designationSAO = Integer
						.parseInt(page.select("a:contains(SAO)").get(1).text().replace("SAO ", ""));

				System.out.println("HD IDENTIFIER: " + designationHD);
				System.out.println("HIP IDENTIFIER: " + designationHIP);
				System.out.println("SAO IDENTIFIER: " + designationSAO);

				databaseConnection = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/" + stellarSchema + "?characterEncoding=utf8", "root",
						"HadarToliman@#$");
				databaseManipulation = databaseConnection.prepareStatement("UPDATE " + stellarTable
						+ " SET `identifierHD`= ?, `identifierHIP`= ?, `identifierSAO`= ? WHERE `designation`='"
						+ databaseStar + "';");
				databaseManipulation.setInt(1, designationHD);
				databaseManipulation.setInt(2, designationHIP);
				databaseManipulation.setInt(3, designationSAO);
				databaseManipulation.execute();

				source.close();
			}

		} catch (IndexOutOfBoundsException | IOException | SQLException parsingException) {
			parsingException.getMessage();
		}
	}
}
