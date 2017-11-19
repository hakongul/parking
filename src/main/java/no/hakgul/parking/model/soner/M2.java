package no.hakgul.parking.model.soner;

import no.hakgul.parking.model.PrisV1;
import no.hakgul.parking.model.Sone;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class M2 implements Sone {

    private final String sone = "M2";
    private final int timepris = 100;
    private final int timepris_helg = 200;

    @Override
    public PrisV1 beregnPris(int antallMinutter) {
        return new PrisV1(sum(antallMinutter), sone);
    }

    private int sum(int antallMinutter) {
        int begynteTimer = ((antallMinutter+60) / 60);

        LocalDateTime date = LocalDateTime.now();
        if(date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY){
            return begynteTimer*timepris_helg;
        } else {
            return begynteTimer*timepris;
        }
    }
}
