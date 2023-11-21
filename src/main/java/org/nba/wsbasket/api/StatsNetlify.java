package org.nba.wsbasket.api;


public class StatsNetlify {
    String nomJoueur;
    String prenomJoueur;
    String acronymeEquipe;

    int matchJouesEquipe;
    int matchJouesJoueur;
    Double ppm;
    Double rpm;
    Double pdpm;
    Double mpm;
    Double eff;
    Double pourcentageFG;
    Double pourcentage3P;
    Double pourcentageLF;


    public StatsNetlify(String nomJoueur, String prenomJoueur, String acronymeEquipe, int matchJouesEquipe, int matchJouesJoueur, Double ppm, Double rpm, Double pdpm, Double mpm, Double eff, Double pourcentageFG, Double pourcentage3P, Double pourcentageLF) {
        this.nomJoueur = nomJoueur;
        this.prenomJoueur = prenomJoueur;
        this.acronymeEquipe = acronymeEquipe;
        this.matchJouesEquipe = matchJouesEquipe;
        this.matchJouesJoueur = matchJouesJoueur;
        this.ppm = ppm;
        this.rpm = rpm;
        this.pdpm = pdpm;
        this.mpm = mpm;
        this.eff = eff;
        this.pourcentageFG = pourcentageFG;
        this.pourcentage3P = pourcentage3P;
        this.pourcentageLF = pourcentageLF;
    }

    public StatsNetlify(){}


    public int getMatchJouesEquipe() {
        return this.matchJouesEquipe;
    }

    public void setMatchJouesEquipe(int matchJouesEquipe) {
        this.matchJouesEquipe = matchJouesEquipe;
    }

    public int getMatchJouesJoueur() {
        return this.matchJouesJoueur;
    }

    public void setMatchJouesJoueur(int matchJouesJoueur) {
        this.matchJouesJoueur = matchJouesJoueur;
    }

    public Double getPpm() {
        return this.ppm;
    }

    public void setPpm(Double ppm) {
        this.ppm = ppm;
    }

    public Double getRpm() {
        return this.rpm;
    }

    public void setRpm(Double rpm) {
        this.rpm = rpm;
    }

    public Double getPdpm() {
        return this.pdpm;
    }

    public void setPdpm(Double pdpm) {
        this.pdpm = pdpm;
    }

    public Double getMpm() {
        return this.mpm;
    }

    public void setMpm(Double mpm) {
        this.mpm = mpm;
    }

    public Double getEff() {
        return this.eff;
    }

    public void setEff(Double eff) {
        this.eff = eff;
    }

    public Double getPourcentageFG() {
        return this.pourcentageFG;
    }

    public void setPourcentageFG(Double pourcentageFG) {
        this.pourcentageFG = pourcentageFG;
    }

    public Double getPourcentage3P() {
        return this.pourcentage3P;
    }

    public void setPourcentage3P(Double pourcentage3P) {
        this.pourcentage3P = pourcentage3P;
    }

    public Double getPourcentageLF() {
        return this.pourcentageLF;
    }

    public void setPourcentageLF(Double pourcentageLF) {
        this.pourcentageLF = pourcentageLF;
    }




    public String getNomJoueur() {
        return nomJoueur;
    }




    public void setNomJoueur(String nomJoueur) {
        this.nomJoueur = nomJoueur;
    }




    public String getPrenomJoueur() {
        return prenomJoueur;
    }




    public void setPrenomJoueur(String prenomJoueur) {
        this.prenomJoueur = prenomJoueur;
    }




    public String getAcronymeEquipe() {
        return acronymeEquipe;
    }




    public void setAcronymeEquipe(String acronymeEquipe) {
        this.acronymeEquipe = acronymeEquipe;
    }





}
