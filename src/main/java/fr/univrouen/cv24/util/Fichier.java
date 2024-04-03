package fr.univrouen.cv24.util; 

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@RestController
public class Fichier {

	@GetMapping("/testfic")
    public String loadFileXML() {
        Resource resource = new DefaultResourceLoader().getResource("classpath:xml/smallCV.xml");
        StringBuilder contentBuilder = new StringBuilder();

        try {
            InputStream inputStream = resource.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                contentBuilder.append(line);
                contentBuilder.append("\n");
            }

            bufferedReader.close();
            inputStream.close();
        } catch (IOException e) {
            return "Error reading file: " + e.getMessage();
        }

        return contentBuilder.toString();
    }
}
