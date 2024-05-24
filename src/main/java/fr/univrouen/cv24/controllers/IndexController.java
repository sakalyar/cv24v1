package fr.univrouen.cv24.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

	@GetMapping("/")
    public String index() {
        return """
            <!DOCTYPE html>
            <html>
            <head>
                <title>Project Home</title>
            </head>
            <body>
                <h1>Bienvenue au Projet CV24</h1>
                <p>Version: 2.0</p>
                <p>Developpeur:</p>
                <ul>
                    <li>Yaroslav SAKAL</li>
                </ul>
                <img src="https://www.nae.fr/wp-content/uploads/2014/06/logo-univ-rouen-800x450.jpg" alt="Université de Rouen Logo"/>
            </body>
            </html>
            """;
    }

    @GetMapping("/help")
    public String help() {
        return """
            <!DOCTYPE html>
            <html>
            <head>
                <title>Help Page</title>
            </head>
            <body>
                <h1>Help Information</h1>
                <ul>
                    <li>
                        <strong>URL:</strong> / <br/>
                        <strong>Method:</strong> GET <br/>
                        <strong>Operation:</strong> Displays the home page <br/>
                        <strong>Expected Format:</strong> HTML <br/>
                        <strong>Return Format:</strong> Valid HTML or XHTML
                    </li>
                    <li>
                        <strong>URL:</strong> / <br/>
                        <strong>Method:</strong> POST <br/>
                        <strong>Operation:</strong> Accepts a string and returns it <br/>
                        <strong>Expected Format:</strong> String <br/>
                        <strong>Return Format:</strong> String
                    </li>
                </ul>
            </body>
            </html>
            """;
    }
	
	@PostMapping("/")
	public String indexPost(String x) {
		System.out.println(x);
		return x;
	}
}
