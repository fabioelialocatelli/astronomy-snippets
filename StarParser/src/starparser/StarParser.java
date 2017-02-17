package starparser;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class StarParser {

	public static void main(String[] args) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			
			Document constellation = builder.parse("src/starparser/scorpio.xml");
			FileWriter fileWriter = new FileWriter("src/starparser/scorpio.txt");
			BufferedWriter contentWriter = new BufferedWriter(fileWriter);

			constellation.getDocumentElement().normalize();
			Element root = constellation.getDocumentElement();
			System.out.println("Document Root: " + root.getNodeName().toUpperCase());

			// LOOP 1; RETRIEVE RECORD SET
			NodeList stars = constellation.getChildNodes();
			for (int i = 0; i < stars.getLength(); i++) {
				Node star = stars.item(i);
				NodeList starSet = star.getChildNodes();

				// LOOP 2; RETRIEVE ROWS
				for (int j = 0; j < starSet.getLength(); j++) {
					Node attribute = starSet.item(j);
					NodeList attributeSet = attribute.getChildNodes();

					// BYPASS "#text" ELEMENT
					if (starSet.item(j).getNodeName() != "#text") {
						try {
							contentWriter.write("\n");
						} catch (IOException e) {
							e.printStackTrace();
						}
						System.out.println("\n");

						// LOOP 3; RETRIEVE ATTRIBUTES
						for (int k = 0; k < attributeSet.getLength(); k++) {

							// BYPASS "#text" ELEMENT
							if (attributeSet.item(k).getNodeName() != "#text") {

								// WRITE TO SPECIFIED FILE
								try {
									contentWriter.write(attributeSet.item(k).getNodeName().toUpperCase() + " "
											+ attributeSet.item(k).getTextContent() + "\n");
								} catch (IOException e) {
									e.printStackTrace();
								}

								// WRITE TO CONSOLE
								System.out.println(attributeSet.item(k).getNodeName().toUpperCase() + " "
										+ attributeSet.item(k).getTextContent());
							}
						}
					}
				}
			}
			try {
				contentWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
