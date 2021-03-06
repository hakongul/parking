package no.hakgul.parking.model.soner;

import no.hakgul.parking.model.PrisV1;
import no.hakgul.parking.model.Sone;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.LocalDateTime;

/**
 * Klasse som tar for seg beregningen av pris for parkering i sone M3.
 * Parametere for beregning er:
 * - Mandag til lørdag mellom 08:00 og 16:00 er første time gratis, deretter koster det 2 kroner per påbegynte minutt.
 * - Mandag til lørdag utenom disse tidspunktene koster det 3 kroner minuttet.
 * - Søndager er parkering gratis.
 */

public class M3 implements Sone {

    private final String sone = "M3";
    private final int dagMinuttPris = 2;
    private final int kveldMinuttPris = 3;
    private final boolean prod;

    private Clock clock;

    public M3(boolean prod) {
        this.prod = prod;
    }

    @Override
    public PrisV1 beregnPris(int antallMinutter) {
        return new PrisV1(sum(antallMinutter), sone);
    }

    private int sum(int antallMinutter) {
        LocalDateTime date;
        if(prod){
            date = LocalDateTime.now();
        } else {
            date = LocalDateTime.now(clock);
        }

        int pris = 0;
        LocalDateTime slutt = date.plusMinutes(antallMinutter);
        if(date.getDayOfWeek() == DayOfWeek.SUNDAY && slutt.getDayOfWeek() == DayOfWeek.SUNDAY && antallMinutter <= 1440)
            //Vi er innenfor en og samme Søndag. gratis
            return 0;
        else {
            for(int i = 0; i < antallMinutter; i++) {
                if(date.plusMinutes(i).getHour() >= 8 && date.plusMinutes(i).getHour() < 16) {
                    if (date.plusMinutes(i).getDayOfWeek() != DayOfWeek.SUNDAY && i >= 60) {
                        pris += dagMinuttPris;
                    }
                } else {
                    if (date.plusMinutes(i).getDayOfWeek() != DayOfWeek.SUNDAY) {
                        pris += kveldMinuttPris;
                    }
                }
            }
        }

        return pris;

    }

    void setClock(Clock clock) {
        this.clock = clock;
    }
}
