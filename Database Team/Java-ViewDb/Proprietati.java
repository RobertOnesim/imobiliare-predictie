package com.catalina;

/**
 * Created by Catalina on 21.04.2016.
 */
public class Proprietati {

    private String idProprietate;
    private String titlu;
    private String tip; // tip: Apartament semidecomandat / Casa/ Vila
    private String pret;
    private String moneda;
    private String suprafata;
    private String numarCamere;
    private String anConstructie;
    private String descriere;
    private String evaluare;
    private String taguri;
    private String adresa;
    private int vandut;

    public Proprietati() {}

    public Proprietati(String idProprietate, String titlu, String tip, String pret, String moneda, String suprafata,
                       String numarCamere, String anConstructie, String descriere, String evaluare, String taguri, String adresa, int vandut) {
        this.idProprietate = idProprietate;
        this.titlu = titlu;
        this.tip = tip;
        this.pret = pret;
        this.moneda = moneda;
        this.suprafata = suprafata;
        this.numarCamere = numarCamere;
        this.anConstructie = anConstructie;
        this.descriere = descriere;
        this.evaluare = evaluare;
        this.taguri = taguri;
        this.adresa = adresa;
        this.vandut = vandut;
    }


    public String getIdProprietate() {
        return idProprietate;
    }

    public void setIdProprietate(String idProprietate) {
        this.idProprietate = idProprietate;
    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }


    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getPret() {
        return pret;
    }

    public void setPret(String pret) {
        this.pret = pret;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getSuprafata() {
        return suprafata;
    }

    public void setSuprafata(String suprafata) {
        this.suprafata = suprafata;
    }

    public String getNumarCamere() {
        return numarCamere;
    }

    public void setNumarCamere(String numarCamere) {
        this.numarCamere = numarCamere;
    }

    public String getAnConstructie() {
        return anConstructie;
    }

    public void setAnConstructie(String anConstructie) {
        this.anConstructie = anConstructie;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public String getEvaluare() {
        return evaluare;
    }

    public void setEvaluare(String evaluare) {
        this.evaluare = evaluare;
    }

    public String getTaguri() {
        return taguri;
    }

    public void setTaguri(String taguri) {
        this.taguri = taguri;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public int getVandut() {
        return vandut;
    }

    public void setVandut(int vandut) {
        this.vandut = vandut;
    }

    public String toString(){
        return getIdProprietate() + "\t" + getTitlu() +
                "\nVandut: " + ((getVandut()==1)?"da":"nu") +
                "\nDescriere: " + getDescriere() + "" +
                "\nTip: " + getTip() +
                " Pret: " + getPret() + " " + getMoneda() +
                "\nSuprafata: " + getSuprafata() + " NumarCamere: " + getNumarCamere() + " AnConstuctie: " + getAnConstructie() +
                "\nAdresa: " + getAdresa() +
                "\nTaguri: " + getTaguri();
    }
}
