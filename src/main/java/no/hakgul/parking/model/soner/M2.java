package no.hakgul.parking.model.soner;

import no.hakgul.parking.model.PrisV1;
import no.hakgul.parking.model.Sone;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.LocalDateTime;

/**
 * Klasse som tar for seg beregning av pris for parkering i sone M2.
 * Parametere for beregning er:
 * - 100 kroner per påbegynte time på hverdager.
 * - I helgen er prisen det dobbelte, altså 200 kroner per påbegynte time.
 */

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
        return (antallMinutter+59) / 60;
    }

    void setClock(Clock clock) {
        this.clock = clock;
    }
}
