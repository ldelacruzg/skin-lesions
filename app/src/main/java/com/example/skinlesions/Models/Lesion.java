package com.example.skinlesions.Models;

import java.util.List;
import java.util.Map;

public class Lesion {
    private int id;
    private String name;
    private String description;
    private List<Symptom> symptoms;
    private String diagnosis;
    private List<Treatment> treatments;
    private List<String> care;
    private List<FAQ> faq;

    public Lesion(int id, String name, String description, List<Symptom> symptoms, String diagnosis, List<Treatment> treatments, List<String> care, List<FAQ> faq) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.symptoms = symptoms;
        this.diagnosis = diagnosis;
        this.treatments = treatments;
        this.care = care;
        this.faq = faq;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Symptom> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(List<Symptom> symptoms) {
        this.symptoms = symptoms;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public List<Treatment> getTreatments() {
        return treatments;
    }

    public void setTreatments(List<Treatment> treatments) {
        this.treatments = treatments;
    }

    public List<String> getCare() {
        return care;
    }

    public void setCare(List<String> care) {
        this.care = care;
    }

    public List<FAQ> getFaq() {
        return faq;
    }

    public void setFaq(List<FAQ> faq) {
        this.faq = faq;
    }
}
