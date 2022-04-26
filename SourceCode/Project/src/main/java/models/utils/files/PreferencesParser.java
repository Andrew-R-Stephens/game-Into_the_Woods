package models.utils.files;

import models.utils.config.Config;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * <p>The PreferencesParser accepts the local Preferences file and parses the data within it.</p>
 * <p>The obtained data is used within the Config to initialize the program with default preferences.</p>
 */
public class PreferencesParser {

    private File file;
    private final Config config;

    /**
     * <p>This initializes the PreferencesParser with a reference to the Config and a reference to the preferences.xml
     * file.</p>
     * @param config The config file used across the entire software.
     * @param file The preferences.xml file
     */
    public PreferencesParser(Config config, File file) {
        this.config = config;
        this.file = file;
    }

    /**
     * <p>Parses through the preferences.xml through the use of DocumentBuilderFactory.</p>
     * @return If the data registered properly.
     */
    public boolean parse() {

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
                    config.setWindowWidthDefault(dwidth);

                    int dheight = Integer.parseInt(eElement
                            .getElementsByTagName("resolutionHeightDefault")
                            .item(0)
                            .getTextContent());
                    config.setWindowHeightDefault(dheight);

                    int width = Integer.parseInt(eElement
                            .getElementsByTagName("resolutionWidth")
                            .item(0)
                            .getTextContent());
                    config.setWindowWidthSelected(width);

                    int height = Integer.parseInt(eElement
                            .getElementsByTagName("resolutionHeight")
                            .item(0)
                            .getTextContent());
                    config.setWindowHeightSelected(height);

                    int windowType = Integer.parseInt(eElement
                            .getElementsByTagName("windowType")
                            .item(0)
                            .getTextContent());
                    config.setWindowType(windowType);

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
                    config.setGameUpdateRate(gameUpdateRate);

                    short frameRateDefault = Short.parseShort(eElement
                            .getElementsByTagName("frameRateDefault")
                            .item(0)
                            .getTextContent());
                    config.setFrameRateDefault(frameRateDefault);

                    short frameRate = Short.parseShort(eElement
                            .getElementsByTagName("frameRate")
                            .item(0)
                            .getTextContent());
                    config.setFrameRate(frameRate);

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
