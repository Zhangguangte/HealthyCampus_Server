package com.muyou.pojo;

import java.io.Serializable;

public class TbDisease implements Serializable{
    private Integer id;

    private String diseaseName;

    private String diseaseSymbol;

    private String diseaseAlias;

    private String diseaseIntroduce;

    private String diseaseContagious;

    private String cureRate;

    private String cureDepart;

    private String cureCost;

    private String cureWay;

    private String cureRecommendDrug;

    private String diseasePart;

    private String medicalInsurance;

    private String cureTime;

    private String diseaseComplication;

    private String diseasePopulation;

    private String diseaseTypicalSymptoms;

    private String diseasePrevention;

    private String diseaseCase;

    private String diseaseCheck;

    private String diseaseUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName == null ? null : diseaseName.trim();
    }

    public String getDiseaseSymbol() {
        return diseaseSymbol;
    }

    public void setDiseaseSymbol(String diseaseSymbol) {
        this.diseaseSymbol = diseaseSymbol == null ? null : diseaseSymbol.trim();
    }

    public String getDiseaseAlias() {
        return diseaseAlias;
    }

    public void setDiseaseAlias(String diseaseAlias) {
        this.diseaseAlias = diseaseAlias == null ? null : diseaseAlias.trim();
    }

    public String getDiseaseIntroduce() {
        return diseaseIntroduce;
    }

    public void setDiseaseIntroduce(String diseaseIntroduce) {
        this.diseaseIntroduce = diseaseIntroduce == null ? null : diseaseIntroduce.trim();
    }

    public String getDiseaseContagious() {
        return diseaseContagious;
    }

    public void setDiseaseContagious(String diseaseContagious) {
        this.diseaseContagious = diseaseContagious == null ? null : diseaseContagious.trim();
    }

    public String getCureRate() {
        return cureRate;
    }

    public void setCureRate(String cureRate) {
        this.cureRate = cureRate == null ? null : cureRate.trim();
    }

    public String getCureDepart() {
        return cureDepart;
    }

    public void setCureDepart(String cureDepart) {
        this.cureDepart = cureDepart == null ? null : cureDepart.trim();
    }

    public String getCureCost() {
        return cureCost;
    }

    public void setCureCost(String cureCost) {
        this.cureCost = cureCost == null ? null : cureCost.trim();
    }

    public String getCureWay() {
        return cureWay;
    }

    public void setCureWay(String cureWay) {
        this.cureWay = cureWay == null ? null : cureWay.trim();
    }

    public String getCureRecommendDrug() {
        return cureRecommendDrug;
    }

    public void setCureRecommendDrug(String cureRecommendDrug) {
        this.cureRecommendDrug = cureRecommendDrug == null ? null : cureRecommendDrug.trim();
    }

    public String getDiseasePart() {
        return diseasePart;
    }

    public void setDiseasePart(String diseasePart) {
        this.diseasePart = diseasePart == null ? null : diseasePart.trim();
    }

    public String getMedicalInsurance() {
        return medicalInsurance;
    }

    public void setMedicalInsurance(String medicalInsurance) {
        this.medicalInsurance = medicalInsurance == null ? null : medicalInsurance.trim();
    }

    public String getCureTime() {
        return cureTime;
    }

    public void setCureTime(String cureTime) {
        this.cureTime = cureTime == null ? null : cureTime.trim();
    }

    public String getDiseaseComplication() {
        return diseaseComplication;
    }

    public void setDiseaseComplication(String diseaseComplication) {
        this.diseaseComplication = diseaseComplication == null ? null : diseaseComplication.trim();
    }

    public String getDiseasePopulation() {
        return diseasePopulation;
    }

    public void setDiseasePopulation(String diseasePopulation) {
        this.diseasePopulation = diseasePopulation == null ? null : diseasePopulation.trim();
    }

    public String getDiseaseTypicalSymptoms() {
        return diseaseTypicalSymptoms;
    }

    public void setDiseaseTypicalSymptoms(String diseaseTypicalSymptoms) {
        this.diseaseTypicalSymptoms = diseaseTypicalSymptoms == null ? null : diseaseTypicalSymptoms.trim();
    }

    public String getDiseasePrevention() {
        return diseasePrevention;
    }

    public void setDiseasePrevention(String diseasePrevention) {
        this.diseasePrevention = diseasePrevention == null ? null : diseasePrevention.trim();
    }

    public String getDiseaseCase() {
        return diseaseCase;
    }

    public void setDiseaseCase(String diseaseCase) {
        this.diseaseCase = diseaseCase == null ? null : diseaseCase.trim();
    }

    public String getDiseaseCheck() {
        return diseaseCheck;
    }

    public void setDiseaseCheck(String diseaseCheck) {
        this.diseaseCheck = diseaseCheck == null ? null : diseaseCheck.trim();
    }

    public String getDiseaseUrl() {
        return diseaseUrl;
    }

    public void setDiseaseUrl(String diseaseUrl) {
        this.diseaseUrl = diseaseUrl == null ? null : diseaseUrl.trim();
    }
}