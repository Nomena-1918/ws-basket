package org.nba.wsbasket.api;

public class StatsIonic {
    String nom;
    String prenom;
    String numero;
    Long idjoueur;
    int M= 1;
    int MJ= 1;
    double PPM= 1;
    double RPM= 1;
    double PDPM= 1;
    double MPM= 1;
    double EFF= 1;
    double FG= 1;
    double troisP= 1;
    double LF= 1;
    
    public StatsIonic(String nom, String prenom, String numero, Long idjoueur, int m, int mJ, double pPM, double rPM, double pDPM,
            double mPM, double eFF, double fG, double troisP, double lF) {
        this.nom = nom;
        this.prenom = prenom;
        this.numero = numero;
        this.idjoueur = idjoueur;
        M = m;
        MJ = mJ;
        PPM = pPM;
        RPM = rPM;
        PDPM = pDPM;
        MPM = mPM;
        EFF = eFF;
        FG = fG;
        this.troisP = troisP;
        LF = lF;
    }

    public StatsIonic(String nom2, String prenom2, String numero2, Long idjoueur2, int matchJoueur,
            Double pointMarqueParMatch, Double rebondsParMatch, Double passeDeParMatch, Double minuteJoues,
            Double calculer_eff, Double pourcentageFieldGoal, Double pourcentageThreeThrows,
            Double pourcentageLancerFranc) {
    }

    public Long getIdjoueur() {
        return idjoueur;
    }

    public void setIdjoueur(Long idjoueur) {
        this.idjoueur = idjoueur;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }
    public int getM() {
        return M;
    }
    public void setM(int m) {
        M = m;
    }
    public int getMJ() {
        return MJ;
    }
    public void setMJ(int mJ) {
        MJ = mJ;
    }
    public double getPPM() {
        return PPM;
    }
    public void setPPM(double pPM) {
        PPM = pPM;
    }
    public double getRPM() {
        return RPM;
    }
    public void setRPM(double rPM) {
        RPM = rPM;
    }
    public double getPDPM() {
        return PDPM;
    }
    public void setPDPM(double pDPM) {
        PDPM = pDPM;
    }
    public double getMPM() {
        return MPM;
    }
    public void setMPM(double mPM) {
        MPM = mPM;
    }
    public double getEFF() {
        return EFF;
    }
    public void setEFF(double eFF) {
        EFF = eFF;
    }
    public double getFG() {
        return FG;
    }
    public void setFG(double fG) {
        FG = fG;
    }
    public double getTroisP() {
        return troisP;
    }
    public void setTroisP(double troisP) {
        this.troisP = troisP;
    }
    public double getLF() {
        return LF;
    }
    public void setLF(double lF) {
        LF = lF;
    }
}
