package com.company;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Catalina on 21.04.2016.
 */
public class Proprietati {
    private class Mapper<E> {
        E value;
        final int pozitieCoeficient;

        public Mapper(int pozitieArff) {
            this.pozitieCoeficient = pozitieArff;
            listaAtribute.add(this);
        }

        public double pondere() {
           // System.out.println(coeficienti);
            if (pozitieCoeficient != -1) {
                if (value.getClass().toString().contains("java.lang.Float")) {
                    return coeficienti[pozitieCoeficient] * (Float) value;
                }
                if (value.getClass().toString().contains("java.lang.Integer")) {
                    return coeficienti[pozitieCoeficient] * (Integer) value;
                }
            }
            return 0;
        }
    }

    private List<Mapper> listaAtribute = new ArrayList<>();
    private double [] coeficienti;

    private Mapper<String> idProprietate = new Mapper<String>(-1);
    private Mapper<String> link = new Mapper<>(-1);
    private Mapper<String> titlu = new Mapper<>(-1);
    private Mapper<String> tip = new Mapper<>(-1); // tip: Apartament semidecomandat / Casa/ Vila
    private Mapper<Float> pret = null;
    private Mapper<String> moneda = new Mapper<>(-1);
    private Mapper<Float> pretEstimat = new Mapper<>(-1);

    private Mapper<String> suprafata = null;
    private Mapper<String> suprafataTeren = null;
    private Mapper<String> suprafataConstruita = new Mapper<>(-1);;

    private Mapper<Integer> numarCamere = null;
    private Mapper<Integer> aerConditionat = null;
    private Mapper<Integer> centralaTermica = null;
    private Mapper<Integer> frontStradal = new Mapper<>(-1);

    private Mapper<Integer> geamBaie = null;
    private Mapper<Integer> geamTermopan = null;
    private Mapper<Integer> negociabil = null;

    private Mapper<String> anConstructie = new Mapper<>(-1);;
    private Mapper<String> descriere = new Mapper<>(-1);

    private Mapper<Integer> evaluare = new Mapper<>(-1); //daca se avauga ceva se va muta in constructor
    private Mapper<String> adresa = new Mapper<>(-1);
    private Mapper<Integer> vandut = null;

    /*
     * Aceast constructor trebuie schimbat in functie de parametri pe care ii dam
     * pentru functia de regresie. Constructorul de la mapper va primi al catelea
     * coeficient al functiei de regresie este
     */
    public Proprietati(double[] coeficienti) {
        this.coeficienti = coeficienti;
        numarCamere = new Mapper<>(0);
        suprafata = new Mapper<>(-1); // tb modificate in int
        centralaTermica = new Mapper<>(2);
        geamTermopan = new Mapper<>(3);
        //@ATTRIBUTE dotari-Utilitati:-Apa  NUMERIC 4
        //@ATTRIBUTE dotari-Utilitati:-Gaze  NUMERIC 5
        //@ATTRIBUTE dotari-Utilitati:-Canal  NUMERIC 6
        //@ATTRIBUTE dotari-Utilitati:-Electricitate  NUMERIC 7
        pret = new Mapper<>(8);
        vandut = new Mapper<>(9);
        //@ATTRIBUTE dotari-Usa-metalica:  NUMERIC 10
        aerConditionat = new Mapper<>(11);
        geamBaie = new Mapper<>(12);
        negociabil = new Mapper<>(13);
        suprafataTeren = new Mapper<>(-1); // tb modificate in int
    }

    public String getIdProprietate() {
        return idProprietate.value;
    }

    public void setIdProprietate(String idProprietate) {
        this.idProprietate.value = idProprietate;
    }

    public String getLink() {
        return link.value;
    }

    public void setLink(String link) {
        this.link.value = link;
    }

    public String getTitlu() {
        return titlu.value;
    }

    public void setTitlu(String titlu) {
        this.titlu.value = titlu;
    }

    public String getTip() {
        return tip.value;
    }

    public void setTip(String tip) {
        this.tip.value = tip;
    }

    public Float getPret() {
        return pret.value;
    }

    public void setPret(Float pret) {
        this.pret.value = pret;
    }

    public String getMoneda() {
        return moneda.value;
    }

    public void setMoneda(String moneda) {
        this.moneda.value = moneda;
    }

    public Float getPretEstimat() {
        return pretEstimat.value;
    }

    public void setPretEstimat(Float pretEstimat) {
        this.pretEstimat.value = pretEstimat;
    }

    public String getSuprafata() {
        return suprafata.value;
    }

    public void setSuprafata(String suprafata) {
        this.suprafata.value = suprafata;
    }

    public String getSuprafataTeren() {
        return suprafataTeren.value;
    }

    public void setSuprafataTeren(String suprafataTeren) {
        this.suprafataTeren.value = suprafataTeren;
    }

    public String getSuprafataConstruita() {
        return suprafataConstruita.value;
    }

    public void setSuprafataConstruita(String suprafataConstruita) {
        this.suprafataConstruita.value = suprafataConstruita;
    }

    public Integer getNumarCamere() {
        return numarCamere.value;
    }

    public void setNumarCamere(Integer numarCamere) {
        this.numarCamere.value = numarCamere;
    }

    public Integer getAerConditionat() {
        return aerConditionat.value;
    }

    public void setAerConditionat(Integer aerConditionat) {
        this.aerConditionat.value = aerConditionat;
    }

    public Integer getCentralaTermica() {
        return centralaTermica.value;
    }

    public void setCentralaTermica(Integer centralaTermica) {
        this.centralaTermica.value = centralaTermica;
    }

    public Integer getFrontStradal() {
        return frontStradal.value;
    }

    public void setFrontStradal(Integer frontStradal) {
        this.frontStradal.value = frontStradal;
    }

    public Integer getGeamBaie() {
        return geamBaie.value;
    }

    public void setGeamBaie(Integer geamBaie) {
        this.geamBaie.value = geamBaie;
    }

    public Integer getGeamTermopan() {
        return geamTermopan.value;
    }

    public void setGeamTermopan(Integer geamTermopan) {
        this.geamTermopan.value = geamTermopan;
    }

    public Integer getNegociabil() {
        return negociabil.value;
    }

    public void setNegociabil(Integer negociabil) {
        this.negociabil.value = negociabil;
    }

    public String getAnConstructie() {return anConstructie.value;}

    public void setAnConstructie(String anConstructie) {this.anConstructie.value = anConstructie;}

    public String getDescriere() {
        return descriere.value;
    }

    public void setDescriere(String descriere) {
        this.descriere.value = descriere;
    }

    public Integer getEvaluare() {
        return evaluare.value;
    }

    public void setEvaluare(Integer evaluare) {
        this.evaluare.value = evaluare;
    }

    public String getAdresa() {
        return adresa.value;
    }

    public void setAdresa(String adresa) {
        this.adresa.value = adresa;
    }

    public Integer getVandut() {
        return vandut.value;
    }

    public void setVandut(Integer vandut) {
        this.vandut.value = vandut;
    }

    public String toString(){
        return getIdProprietate() + "\t" + getTitlu() +
                "\nLink: " + getLink() +
                "\nVandut: " + ((vandut.value==1)?"da":"nu") +
                "\nDescriere: " + getDescriere() + "" +
                "\nTip: " + getTip() +
                " Pret: " + getPret() + " " + getMoneda() + "---->Pret estimat: " + getPretEstimat() +
                "\nSuprafata: " + getSuprafata() + "| Teren: " + getSuprafataTeren() + "| Construita: " + getSuprafataConstruita() +
                "\nNumarCamere: " + getNumarCamere() + " AnConstuctie: " + getAnConstructie() +
                "\nAdresa: " + getAdresa() +
                "\nFacilitati: " + "\n\tAerConditionat: " + ((aerConditionat.value==1)?"da":"nu") +
                                "\n\tCentralaTermica: " + ((centralaTermica.value==1)?"da":"nu") +
                                "\n\tFrontStradal: " + ((frontStradal.value==1)?"da":"nu") +
                                "\n\tGeamBaie: " + ((geamBaie.value==1)?"da":"nu") +
                                "\n\tGeamTermopan: " + ((geamTermopan.value==1)?"da":"nu") +
                                "\n\tNegociabil: " + ((negociabil.value==1)?"da":"nu");
    }

    public double calculeazaPret() {
        double pret = 0;
        //int i = 0;
        for (Mapper atribut : listaAtribute) {
            if (atribut != null) {
                pret += atribut.pondere();
            }
           // ++i;
            //System.out.println(i);
        }
        return pret;
    }

}
