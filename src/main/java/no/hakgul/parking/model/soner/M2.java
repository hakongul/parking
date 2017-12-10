package no.hakgul.parking.model.soner;

import no.hakgul.parking.model.PrisV1;
import no.hakgul.parking.model.Sone;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class M2 implements Sone {

    private final boolean prod;
    private Clock clock;

    public M2(boolean prod) {
        this.prod = prod;
    }

    @Override
    public PrisV1 beregnPris(int antallMinutter) {
        String sone = "M2";
        return new PrisV1(sum(antallMinutter), sone);
    }

    private int sum(int antallMinutter) {
        LocalDateTime date;
        if(prod){
            date = LocalDateTime.now();
        } else {
            date = LocalDateTime.now(clock);
        }

        if(date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY){
            int timepris_helg = 200;
            return begynteTimer(antallMinutter) * timepris_helg;
        } else {
            int timepris = 100;
            return begynteTimer(antallMinutter) * timepris;
        }
    }

    private int begynteTimer(int antallMinutter) {
        return (antallMinutter+60) / 60;
    }

    void setClock(Clock clock) {
        this.clock = clock;
    }
}
