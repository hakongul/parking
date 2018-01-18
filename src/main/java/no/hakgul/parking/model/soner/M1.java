package no.hakgul.parking.model.soner;

import no.hakgul.parking.model.PrisV1;
import no.hakgul.parking.model.Sone;

/**
 * Klasse for beregning av pris for parkering i sone M1
 * Parameter for beregning er:
 * - 60 kroner timen, beregnet for hvert påbegynte minutt.
 */

public class M1 implements Sone {

    private final String sone = "M1";

    @Override
    public PrisV1 beregnPris(int antallMinutter) {
        return new PrisV1(antallMinutter, sone);
    }
}
