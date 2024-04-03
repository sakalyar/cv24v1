package fr.univrouen.cv24.model;

import java.io.Serializable;

import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlAccessType;

@XmlRootElement(name = "TestCV")
@XmlAccessorType(XmlAccessType.NONE)
public class TestCV implements Serializable {
    private static final long serialVersionUID = 2024L;
    private static int compteur = 1;

    @XmlAttribute
    private Integer id;

    @XmlElement
    private String nom;

    @XmlElement
    private String date;

    @XmlElement
    private String mel;

    public TestCV(String nom, String prenom, String date, String mel) {
        super();
        this.id = compteur++;
        this.nom = nom;
        this.date = date;
        this.mel = mel;
    }

    public TestCV() {
    }

    @Override
    public String toString() {
        return "CV (" + id + ") [" + nom + ", Date=" + date + ", mel=" + mel + "]";
    }
}
