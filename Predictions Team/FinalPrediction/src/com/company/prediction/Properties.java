package com.company.prediction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Catalina on 21.04.2016.
 */
public class Properties {
    private class Mapper<E> {
        E value;
        final int pozitieCoeficient;

        public Mapper(int pozitieArff) {
            this.pozitieCoeficient = pozitieArff;
            listaAtribute.add(this);
        }

        public double pondere() {
            //System.out.println(coeficienti);
            if (pozitieCoeficient != -1) {
                if (value.getClass().toString().contains("java.lang.Float")) {
                    return coefficients.get(pozitieCoeficient) * (Float) value;
                }
                if (value.getClass().toString().contains("java.lang.Integer")) {
                    return coefficients.get(pozitieCoeficient) * (Integer) value;
                }
            }
            return 0;
        }
    }

    private List<Mapper> listaAtribute = new ArrayList<>();
    private List<Double> coefficients = new ArrayList<>();

    private Mapper<String> idProprietate = new Mapper<String>(-1);
    private Mapper<String> link = new Mapper<>(-1);
    private Mapper<String> titlu = new Mapper<>(-1);
    private Mapper<String> tip = new Mapper<>(-1); // tip: Apartament semidecomandat / Casa/ Vila
    private Mapper<Float> pret = new Mapper<>(-1);
    private Mapper<String> moneda = new Mapper<>(-1);
    private Mapper<Float> pretEstimat = new Mapper<>(-1);

    private Mapper<Integer> suprafata = null;
    private Mapper<Integer> suprafataTeren = null;
    private Mapper<Integer> suprafataConstruita = new Mapper<>(-1);;

    private Mapper<Integer> numarCamere = null;
    private Mapper<Integer> aerConditionat = null;
    private Mapper<Integer> centralaTermica = null;
    private Mapper<Integer> frontStradal = new Mapper<>(-1);

    private Mapper<Integer> geamBaie = null;
    private Mapper<Integer> geamTermopan = null;
    private Mapper<Integer> negociabil = null;

    private Mapper<String> anConstructie = new Mapper<>(-1);;
    private Mapper<String> descriere = new Mapper<>(-1);

    private Mapper<String> adresa = new Mapper<>(-1);
    private Mapper<Integer> evaluare = null;
    private Mapper<Double> coeficient = null;
    private Mapper<Integer> vandut = null;

    /*
     * Aceast constructor trebuie schimbat in functie de parametri pe care ii dam
     * pentru functia de regresie. Constructorul de la mapper va primi al catelea
     * coeficient al functiei de regresie este
     */
    public Properties(List<Double> coefficients) {
        this.coefficients = coefficients;

        //@ATTRIBUTE detalii-Nr-camere:  NUMERIC
        numarCamere = new Mapper<>(0);
        //@ATTRIBUTE detalii-Suprafata:  NUMERIC
        suprafata = new Mapper<>(1); // tb modificate in int
        //@ATTRIBUTE dotari-Centrala-termica:  NUMERIC
        centralaTermica = new Mapper<>(2);
        //@ATTRIBUTE dotari-Geam-termopan:  NUMERIC
        geamTermopan = new Mapper<>(3);
        //@ATTRIBUTE dotari-Utilitati:-Apa  NUMERIC 4
        //@ATTRIBUTE dotari-Utilitati:-Gaze  NUMERIC 5
        //@ATTRIBUTE dotari-Utilitati:-Canal  NUMERIC 6
        //@ATTRIBUTE dotari-Utilitati:-Electricitate  NUMERIC 7
        //@ATTRIBUTE vandut  NUMERIC 8
        vandut = new Mapper<>(-1);
        //@ATTRIBUTE dotari-Usa-metalica:  NUMERIC 9
        //@ATTRIBUTE dotari-Aer-conditionat:  NUMERIC 10
        aerConditionat = new Mapper<>(10);
        //@ATTRIBUTE dotari-Geam-la-baie:  NUMERIC 11
        geamBaie = new Mapper<>(11);
        //@ATTRIBUTE dotari-Negociabil:  NUMERIC 12
        negociabil = new Mapper<>(12);
        //@ATTRIBUTE detalii-Suprafata-teren:  NUMERIC 13
        suprafataTeren = new Mapper<>(13); // tb modificate in int
        //
        evaluare = new Mapper<>(14);
        //@ATTRIBUTE pret-valoare  NUMERIC 15
        coeficient = new Mapper<>(15);
        //@ATTRIBUTE pret-valoare  NUMERIC 14
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

    public Integer getSuprafata() {
        return suprafata.value;
    }

    private Integer suprafataNumerica(String suprafata) {
        if (suprafata != null) {
            int i;
            for (i = 0; i < suprafata.length() && suprafata.charAt(i) >= '0' && suprafata.charAt(i) <= '9'; ++i) {
            }
            return Integer.valueOf(suprafata.substring(0, i));
        }
        return 0;
    }

    public void setSuprafata(String suprafata) {
        this.suprafata.value = suprafataNumerica(suprafata);
    }

    public Integer getSuprafataTeren() {
        return suprafataTeren.value;
    }

    public void setSuprafataTeren(String suprafataTeren) {
        this.suprafataTeren.value = suprafataNumerica(suprafataTeren);
    }

    public Integer getSuprafataConstruita() {
        return suprafataConstruita.value;
    }

    public void setSuprafataConstruita(String suprafataConstruita) {
        this.suprafataConstruita.value = suprafataNumerica(suprafataConstruita);
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
       // System.out.println("geam baie " + geamBaie);
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


    public Double getCoefficient() {
        return coeficient.value;
    }

    public void setCofficient(Double cofficient2) {
        coeficient.value = cofficient2;
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
        //  int i = 0;
        //System.out.println("***********************");
        for (Mapper atribut : listaAtribute) {
            if (atribut != null) {
                pret += atribut.pondere();
            //    if(atribut.pozitieCoeficient != -1) {
          //          System.out.println(coefficients.get(atribut.pozitieCoeficient) + " " + atribut.value);
              //  }
            }
        //    ++i;
          //  System.out.println(i);
        }
        System.out.println(pret);
        return pret;
    }

}
