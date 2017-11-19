package no.hakgul.parking.api;

import no.hakgul.parking.model.PrisV1;
import no.hakgul.parking.service.BeregningService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ParkingController {

    private BeregningService beregningService = new BeregningService();

    /**
     * REST get metode som tar inn sone og antall minutter det skal parkeres, og kaller korrekt beregningsmetode
     * for gitt parkeringssone.
     *
     * @param sone String
     * @param minutter int
     * @return PrisV1 java objekt i JSON format
     */
    @RequestMapping(value = "/takst", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public PrisV1 beregnTakst(@RequestParam("sone") String sone,
                              @RequestParam("minutter") int minutter) {
        long start = System.currentTimeMillis();

        PrisV1 resultat = beregningService.beregnPris(sone, minutter);

        System.out.println("Tid brukt: " + (System.currentTimeMillis() - start));

        return resultat;
    }
}
