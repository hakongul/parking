package no.hakgul.parking.model.soner;

import no.hakgul.parking.model.PrisV1;
import no.hakgul.parking.model.Sone;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class M3 implements Sone {

    private String sone = "M3";
    private int dagMinuttPris = 2;
    private int kveldMinuttPris = 3;

    @Override
    public PrisV1 beregnPris(int antallMinutter) {
        return new PrisV1(sum(antallMinutter), sone);
    }

    private int sum(int antallMinutter) {
        LocalDateTime date = LocalDateTime.now();

        if(date.getDayOfWeek() == DayOfWeek.SUNDAY) {
            return 0;
        } else if(date.getHour() > 8 && date.getHour() < 16) {
            if(antallMinutter < 60) {
                return 0;
            } else {
                return (antallMinutter - 60)*dagMinuttPris;
            }
        } else {
            return antallMinutter*kveldMinuttPris;
        }
    }
}
