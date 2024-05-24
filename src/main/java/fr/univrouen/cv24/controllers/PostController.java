package fr.univrouen.cv24.controllers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

@RestController
public class PostController {
	@RequestMapping(value = "/testpost", method = RequestMethod.POST, consumes = "application/xml")
		public String postTest(@RequestBody String flux) {
			return ("<result><response>Message reçu : </response>" + flux + "</result>");
	}
	

	private final String directoryPath = "src/main/resources/xml";

    @PostMapping("/cv24/insert")
    public ResponseEntity<String> insertCV(@RequestBody String xmlData) {
        try {
            // Get the directory path for storing uploaded files
            File directory = new File(directoryPath);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Generate a new file name
            String fileName = "cv_" + System.currentTimeMillis() + ".xml";

            // Write the XML content to a new file in the directory
            File newFile = new File(directory, fileName);
            try (FileWriter fileWriter = new FileWriter(newFile)) {
                fileWriter.write(xmlData);
            }

            return ResponseEntity.ok("CV data saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving CV data.");
        }
    }

    private void validateXML(String xmlPayload) throws SAXException, IOException, ParserConfigurationException {
        // Load XSD schema
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = factory.newSchema(getClass().getResource("/xsd/cv24.xsd"));

        // Create XML document from payload
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        docBuilderFactory.setNamespaceAware(true);
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        Document document = docBuilder.parse(new InputSource(new StringReader(xmlPayload)));

        // Validate XML against XSD schema
        schema.newValidator().validate(new StreamSource(new StringReader(xmlPayload)));
    }

    // Dummy implementation to generate unique ID (replace with actual logic)
    private int generateUniqueId() {
        return (int) (Math.random() * 1000); // Generate a random number for demo purposes
    }
	
}
