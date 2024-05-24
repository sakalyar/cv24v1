package fr.univrouen.cv24.model;

import java.io.Serializable;

import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlAccessType;

@XmlRootElement(name = "cv")
@XmlAccessorType(XmlAccessType.FIELD)
public class TestCV implements Serializable {
    private static final long serialVersionUID = 2024L;
    private static int compteur = 1;

    @XmlAttribute
    private Integer id;

    @XmlElement
    private String genre;

    @XmlElement
    private String prenom;

    @XmlElement
    private String nom;

    @XmlElement
    private String objectif;

    @XmlElement
    private String diplome;

    @XmlElement
    private String date;

    @XmlElement
    private String mel;

    public TestCV() {
        this.id = compteur++;
    }

    public TestCV(String genre, String prenom, String nom, String objectif, String diplome, String date, String mel) {
        this.id = compteur++;
        this.genre = genre;
        this.prenom = prenom;
        this.nom = nom;
        this.objectif = objectif;
        this.diplome = diplome;
        this.date = date;
        this.mel = mel;
    }

    // Getters and setters...

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getObjectif() {
        return objectif;
    }

    public void setObjectif(String objectif) {
        this.objectif = objectif;
    }

    public String getDiplome() {
        return diplome;
    }

    public void setDiplome(String diplome) {
        this.diplome = diplome;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMel() {
        return mel;
    }

    public void setMel(String mel) {
        this.mel = mel;
    }

    @Override
    public String toString() {
        return "TestCV [id=" + id + ", genre=" + genre + ", prenom=" + prenom + ", nom=" + nom + ", objectif=" + objectif
                + ", diplome=" + diplome + ", date=" + date + ", mel=" + mel + "]";
    }
}
