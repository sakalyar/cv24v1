package fr.univrouen.cv24.controllers;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import fr.univrouen.cv24.model.CVList;
import fr.univrouen.cv24.model.TestCV;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import java.util.ArrayList;
import java.util.List;

@RestController
public class GetController {
	@GetMapping("/resume")
	public String getListCVinXML() {
		return "Envoi de la liste des CV";
	}
	@GetMapping("/cvid")
	public String getCVinXML(
	@RequestParam(value = "texte") String texte) {
		return ("Détail du CV n°" + texte);
	}
	
	@GetMapping("/test")
	public String testMethod(
	    @RequestParam(value = "id") int id,
	    @RequestParam(value = "titre") String titre) {
	    return "Test :\n" +
	           "id = " + id + "\n" +
	           "titre = " + titre;
	}
	
	@RequestMapping(value="/testxml", produces=MediaType.APPLICATION_XML_VALUE)
	public @ResponseBody TestCV getXML() {
		TestCV cv = new TestCV("Femme","Margaret","HAMILTON", "ingenieure", "mathematiques", "1969/07/21",
		"Appollo11@nasa.us");
		return cv;
	}
	
	@RequestMapping(value = "/cv24/resume/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public CVList getCVListXML() {
        List<TestCV> cvs = new ArrayList<>();
        try {
            File folder = new ClassPathResource("xml").getFile();
            File[] files = folder.listFiles((dir, name) -> name.endsWith(".xml"));
            if (files != null) {
                JAXBContext context = JAXBContext.newInstance(TestCV.class);
                Unmarshaller unmarshaller = context.createUnmarshaller();
                for (File file : files) {
                    TestCV cv = (TestCV) unmarshaller.unmarshal(file);
                    cvs.add(cv);
                }
            }
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        return new CVList(cvs);
    }
	
	@GetMapping(value = "/cv24/resume", produces = "text/html")
    @ResponseBody
    public ResponseEntity<String> getCVListHTML() {
        StringBuilder htmlBuilder = new StringBuilder();
        htmlBuilder.append("<html><body><h1>List of CVs</h1><ul>");

        try {
            File folder = new ClassPathResource("xml").getFile();
            File[] files = folder.listFiles((dir, name) -> name.endsWith(".xml"));
            if (files != null) {
                for (File file : files) {
                    String xmlContent = new String(Files.readAllBytes(file.toPath()));
                    htmlBuilder.append("<li>").append(parseCVFromXML(xmlContent)).append("</li>");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        htmlBuilder.append("</ul></body></html>");
        return ResponseEntity.ok(htmlBuilder.toString());
    }


    private String parseCVFromXML(String xmlContent) {
        String genre = getValueFromTag(xmlContent, "<genre>");
        String prenom = getValueFromTag(xmlContent, "<prenom>");
        String nom = getValueFromTag(xmlContent, "<nom>");
        String objectif = getValueFromTag(xmlContent, "<objectif>");
        String diplome = getValueFromTag(xmlContent, "<diplome>");
        String date = getValueFromTag(xmlContent, "<date>");
        String mel = getValueFromTag(xmlContent, "<mel>");

        return "<cvs><cv>" +
                "<genre>" + genre + "</genre>\n" +
                "<prenom>" + prenom + "</prenom>\n" +
                "<nom>" + nom + "</nom>\n" +
                "<objectif>" + objectif + "</objectif\n>" +
                "<diplome>" + diplome + "</diplome>\n" +
                "<date>" + date + "</date>\n" +
                "<mel>" + mel + "</mel>\n" +
                "</cv></cvs>\n";
    }

    private String getValueFromTag(String xmlContent, String tag) {
        int startIndex = xmlContent.indexOf(tag) + tag.length();
        int endIndex = xmlContent.indexOf("</", startIndex);
        return xmlContent.substring(startIndex, endIndex);
    }

}