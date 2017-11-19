package no.hakgul.parking.service;

import no.hakgul.parking.model.PrisV1;
import no.hakgul.parking.model.soner.M1;
import no.hakgul.parking.model.soner.M2;
import no.hakgul.parking.model.soner.M3;
import org.springframework.stereotype.Service;

@Service
public class BeregningService {

    private M1 m1 = new M1();
    private M2 m2 = new M2();
    private M3 m3 = new M3();

    public BeregningService() {
    }

    public PrisV1 beregnPris(String sone, int antallMinutter) {
        switch(sone) {
            case "M1" :
                return m1.beregnPris(antallMinutter);

            case "M2" :
                return m2.beregnPris(antallMinutter);

            case "M3" :
                return m3.beregnPris(antallMinutter);

            default :
                return null;
        }
    }

}
