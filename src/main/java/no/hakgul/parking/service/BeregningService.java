package no.hakgul.parking.service;

import no.hakgul.parking.model.PrisV1;
import no.hakgul.parking.model.soner.M1;
import no.hakgul.parking.model.soner.M2;
import no.hakgul.parking.model.soner.M3;
import org.springframework.stereotype.Service;

@Service
public class BeregningService {

    //Kan for eksempel settes til en miljøvariabel slik at man kan implementere oppførsel basert på prod/testmiljø
    private boolean prod = true;

    public BeregningService() {
    }

    private M1 m1 = new M1();
    private M2 m2 = new M2(prod);
    private M3 m3 = new M3(prod);

    /**
     * Service som fordeler beregningene til de respektive klassene som har ansvar for hver sin sone
     *
     * @param sone String
     * @param antallMinutter int
     * @return PrisV1 objekt med resultatet
     */
    public PrisV1 beregnPris(String sone, int antallMinutter) {
        switch(sone) {
            case "M1" :
                return m1.beregnPris(antallMinutter);

            case "M2" :
                return m2.beregnPris(antallMinutter);

            case "M3" :
                return m3.beregnPris(antallMinutter);

            default :
                throw new RuntimeException("Sonen som ble sendt inn finnes ikke");
        }
    }

}
