package no.hakgul.parking.model.soner;

import no.hakgul.parking.model.PrisV1;
import no.hakgul.parking.model.Sone;

public class M1 implements Sone {

    private final String sone = "M1";

    @Override
    public PrisV1 beregnPris(int antallMinutter) {
        return new PrisV1(sum(antallMinutter), sone);
    }

    private int sum(int antallMinutter){
        return antallMinutter;
    }
}
