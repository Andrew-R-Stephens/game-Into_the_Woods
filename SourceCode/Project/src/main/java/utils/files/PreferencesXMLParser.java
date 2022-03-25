package utils.files;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import utils.config.PreferenceData;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * The type Preferences xml parser.
 */
public class PreferencesXMLParser extends AFileReader {

    private final PreferenceData preferences;

    /**
     * Instantiates a new Preferences xml parser.
     *
     * @param preferences the preferences
     * @param filePath    the file path
     * @param fileName    the file name
     * @param fileType    the file type
     */
    public PreferencesXMLParser(PreferenceData preferences, String filePath, String fileName, String fileType) {
        super(filePath, fileName, fileType);

        this.preferences = preferences;
    }

    @Override
    public boolean read() {

        // Parse through file
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            return false;
        }

        Document doc;
        try {
            doc = dBuilder.parse(file);

            doc.getDocumentElement().normalize();
            //System.out.println("Root element = " + doc.getDocumentElement().getNodeName());
            NodeList windowNodes = doc.getElementsByTagName("Window");
            //System.out.println("----------------------------");

            for (int temp = 0; temp < windowNodes.getLength(); temp++) {
                Node nNode = windowNodes.item(temp);
                //System.out.println("\nCurrent Element = " + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    int dwidth = Integer.parseInt(eElement
                            .getElementsByTagName("resolutionWidthDefault")
                            .item(0)
                            .getTextContent());
                    preferences.setWindowWidthDefault(dwidth);

                    int dheight = Integer.parseInt(eElement
                            .getElementsByTagName("resolutionHeightDefault")
                            .item(0)
                            .getTextContent());
                    preferences.setWindowHeightDefault(dheight);

                    int width = Integer.parseInt(eElement
                            .getElementsByTagName("resolutionWidth")
                            .item(0)
                            .getTextContent());
                    preferences.setWindowWidthSelected(width);

                    int height = Integer.parseInt(eElement
                            .getElementsByTagName("resolutionHeight")
                            .item(0)
                            .getTextContent());
                    preferences.setWindowHeightSelected(height);

                    int windowType = Integer.parseInt(eElement
                            .getElementsByTagName("windowType")
                            .item(0)
                            .getTextContent());
                    preferences.setWindowType(windowType);

                    /*
                    System.out.println("Resolution Width = "
                            + width);

                    System.out.println("Resolution Height = "
                            + height);

                    System.out.println("Window Type = "
                            + windowType);
                    */
                }
            }

            NodeList performanceNodes = doc.getElementsByTagName("Performance");
            //System.out.println("----------------------------");
            for (int temp = 0; temp < performanceNodes.getLength(); temp++) {
                Node nNode = performanceNodes.item(temp);
                //System.out.println("\nCurrent Element = " + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    short gameUpdateRate = Short.parseShort(eElement
                            .getElementsByTagName("gameUpdateRate")
                            .item(0)
                            .getTextContent());
                    preferences.setGameUpdateRate(gameUpdateRate);

                    short frameRateDefault = Short.parseShort(eElement
                            .getElementsByTagName("frameRateDefault")
                            .item(0)
                            .getTextContent());
                    preferences.setFrameRateDefault(frameRateDefault);

                    short frameRate = Short.parseShort(eElement
                            .getElementsByTagName("frameRate")
                            .item(0)
                            .getTextContent());
                    preferences.setFrameRate(frameRate);

                    /*
                    System.out.println("Game Update Rate = "
                            + gameUpdateRate);
                    System.out.println("Frame Rate Default= "
                            + frameRateDefault);
                    System.out.println("Frame Rate = "
                            + frameRate);
                    */
                }
            }
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }

        return true;

    }

}
