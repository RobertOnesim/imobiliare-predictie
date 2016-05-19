/**
 * Created by Catalina on 21.04.2016.
 */
public class Proprietati {

    private String idProprietate;
    private String link;
    private String titlu;
    private String tip; // tip: Apartament semidecomandat / Casa/ Vila
    private String pret;
    private String moneda;
    private String pretEstimat;

    private String suprafata;
    private String suprafataTeren;
    private String suprafataConstruita;

    private String numarCamere;
    private String aerConditionat;
    private String centralaTermica;
    private String frontStradal;

    private String geamBaie;
    private String geamTermopan;
    private String negociabil;

    private String anConstructie;
    private String descriere;

    private String evaluare;
    private String adresa;
    private int vandut;

    public Proprietati() {}


    public String getIdProprietate() {
        return idProprietate;
    }

    public void setIdProprietate(String idProprietate) {
        this.idProprietate = idProprietate;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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

    public String getPretEstimat() {
        return pretEstimat;
    }

    public void setPretEstimat(String pretEstimat) {
        this.pretEstimat = pretEstimat;
    }

    public String getSuprafata() {
        return suprafata;
    }

    public void setSuprafata(String suprafata) {
        this.suprafata = suprafata;
    }

    public String getSuprafataTeren() {
        return suprafataTeren;
    }

    public void setSuprafataTeren(String suprafataTeren) {
        this.suprafataTeren = suprafataTeren;
    }

    public String getSuprafataConstruita() {
        return suprafataConstruita;
    }

    public void setSuprafataConstruita(String suprafataConstruita) {
        this.suprafataConstruita = suprafataConstruita;
    }

    public String getNumarCamere() {
        return numarCamere;
    }

    public void setNumarCamere(String numarCamere) {
        this.numarCamere = numarCamere;
    }

    public String getAerConditionat() {
        return aerConditionat;
    }

    public void setAerConditionat(String aerConditionat) {
        this.aerConditionat = aerConditionat;
    }

    public String getCentralaTermica() {
        return centralaTermica;
    }

    public void setCentralaTermica(String centralaTermica) {
        this.centralaTermica = centralaTermica;
    }

    public String getFrontStradal() {
        return frontStradal;
    }

    public void setFrontStradal(String frontStradal) {
        this.frontStradal = frontStradal;
    }

    public String getGeamBaie() {
        return geamBaie;
    }

    public void setGeamBaie(String geamBaie) {
        this.geamBaie = geamBaie;
    }

    public String getGeamTermopan() {
        return geamTermopan;
    }

    public void setGeamTermopan(String geamTermopan) {
        this.geamTermopan = geamTermopan;
    }

    public String getNegociabil() {
        return negociabil;
    }

    public void setNegociabil(String negociabil) {
        this.negociabil = negociabil;
    }

    public String getAnConstructie() {return anConstructie;}

    public void setAnConstructie(String anConstructie) {this.anConstructie = anConstructie;}

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
                "\nAdresa: " + getAdresa();
    }


    public String insert (){
        return "Insert into Proprietati (id_proprietate, link, titlu, tip, pret, moneda, descriere, adresa, vandut) VALUES ( "
                + getIdProprietate() +  ", '" + getLink() + "', '" + getTitlu() + "' , '"+ getTip()+ "' , " + getPret()
                + " , '" + getMoneda() + "', '" + getDescriere() +"', '" + getAdresa() + "', " + getVandut() +" )";
    }


    public String updateSuprafata(){
        if(getSuprafata() == null)
            return null;
        return "Update Proprietati set suprafata = '" + getSuprafata() + "' where id_proprietate = " + getIdProprietate();
    }

    public String updateSuprafataTeren(){
        if(getSuprafataTeren() == null)
            return null;
        return "Update Proprietati set suprafata_teren = '" + getSuprafataTeren() + "' where id_proprietate = " + getIdProprietate();
    }

    public String updateSuprafataConstruita(){
        if(getSuprafataConstruita() == null)
            return null;
        return "Update Proprietati set suprafata_construita = '" + getSuprafataConstruita() + "' where id_proprietate = " + getIdProprietate();
    }

    public String updateNumarCamere(){
        if(getNumarCamere() == null)
            return null;
        return "Update Proprietati set numar_camere = " + getNumarCamere() + " where id_proprietate = " + getIdProprietate();
    }

    public String updateAnConstuctie(){
        if(getAnConstructie() == null)
            return null;
        return "Update Proprietati set an_constructie = '" + getAnConstructie() + "' where id_proprietate = " + getIdProprietate();
    }

    public String updateAerConditionat(){
        if(getAerConditionat() == null)
            return "Update Proprietati set aer_conditionat = 0 where id_proprietate = " + getIdProprietate();
        return "Update Proprietati set aer_conditionat = 1 where id_proprietate = " + getIdProprietate();
    }

    public String updateCentralaTermica(){
        if(getCentralaTermica() == null)
            return "Update Proprietati set centrala_termica = 0 where id_proprietate = " + getIdProprietate();
        return "Update Proprietati set centrala_termica = 1 where id_proprietate = " + getIdProprietate();
    }

    public String updateFrontStradal(){
        if(getFrontStradal() == null)
            return "Update Proprietati set front_stradal = 0 where id_proprietate = " + getIdProprietate();
        return "Update Proprietati set front_stradal = " + getFrontStradal() + " where id_proprietate = " + getIdProprietate();
    }

    public String updateGeamBaie(){
        if(getGeamBaie() == null)
            return "Update Proprietati set geam_baie = 0 where id_proprietate = " + getIdProprietate();
        return "Update Proprietati set geam_baie = 1 where id_proprietate = " + getIdProprietate();
    }

    public String updateGeamTermopan(){
        if(getGeamTermopan() == null)
            return  "Update Proprietati set geam_termopan = 0 where id_proprietate = " + getIdProprietate();
        return "Update Proprietati set geam_termopan = 1 where id_proprietate = " + getIdProprietate();
    }

    public String updateNegociabil(){
        if(getNegociabil() == null)
            return "Update Proprietati set negociabil = 0 where id_proprietate = " + getIdProprietate();
        return "Update Proprietati set negociabil = 1 where id_proprietate = " + getIdProprietate();
    }

}
