package fr.univrouen.cv24.model;

import java.util.List;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "cvs")
public class CVList {
    private List<TestCV> cvs;

    public CVList() {}

    public CVList(List<TestCV> cvs) {
        this.cvs = cvs;
    }

    @XmlElement(name = "cv")
    public List<TestCV> getCvs() {
        return cvs;
    }

    public void setCvs(List<TestCV> cvs) {
        this.cvs = cvs;
    }
}
