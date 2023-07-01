import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class PomDependencyAdder {

    public static void main(String[] args) {
        String pomFilePath = "pom.xml";
        String groupId = "com.example";
        String artifactId = "my-library";
        String version = "1.0.0";

        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(pomFilePath);

            // Find the <dependencies> element
            Node dependenciesNode = doc.getElementsByTagName("dependencies").item(0);

            // Create a new <dependency> element
            Element dependencyElement = doc.createElement("dependency");

            // Create and append <groupId> element
            Element groupIdElement = doc.createElement("groupId");
            groupIdElement.appendChild(doc.createTextNode(groupId));
            dependencyElement.appendChild(groupIdElement);

            // Create and append <artifactId> element
            Element artifactIdElement = doc.createElement("artifactId");
            artifactIdElement.appendChild(doc.createTextNode(artifactId));
            dependencyElement.appendChild(artifactIdElement);

            // Create and append <version> element
            Element versionElement = doc.createElement("version");
            versionElement.appendChild(doc.createTextNode(version));
            dependencyElement.appendChild(versionElement);

            // Append the new <dependency> element to <dependencies>
            dependenciesNode.appendChild(dependencyElement);

            // Write the updated pom.xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(pomFilePath));
            transformer.transform(source, result);

            System.out.println("Dependency added successfully!");

        } catch (ParserConfigurationException | IOException | org.xml.sax.SAXException | TransformerException e) {
            e.printStackTrace();
        }
    }
}
