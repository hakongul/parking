package no.hakgul.parking.model;

public class PrisV1 {
    private int pris;
    private String sone;

    public PrisV1(int pris, String sone) {
        this.pris = pris;
        this.sone = sone;
    }

    public int getPris() {
        return pris;
    }

    public void setPris(int pris) {
        this.pris = pris;
    }

    public String getSone() {
        return sone;
    }

    public void setSone(String sone) {
        this.sone = sone;
    }
}
