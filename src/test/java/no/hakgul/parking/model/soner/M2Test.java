package no.hakgul.parking.model.soner;

import no.hakgul.parking.model.PrisV1;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class M2Test {

    private boolean produksjon = false;

    private M2 m2;
    private final String sone2 = "M2";
    private final LocalDateTime sunday = LocalDateTime.of(2017, 11, 19, 12, 0);
    private final LocalDateTime monday = LocalDateTime.of(2017, 11, 20, 12, 0);
    private Clock mockClockMonday = Clock.fixed(monday.atZone(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());
    private Clock mockClockSunday = Clock.fixed(sunday.atZone(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());

    @Test
    public void getTakstFor_Monday_60Minutes() {
        m2 = new M2(produksjon);
        m2.setClock(mockClockMonday);

        PrisV1 resultat = m2.beregnPris(60);
        assert(resultat.getPris() == 100);
        assert(resultat.getSone().equalsIgnoreCase(sone2));
    }

    @Test
    public void getTakstFor_Monday_63Minutes() {
        m2 = new M2(produksjon);
        m2.setClock(mockClockMonday);

        PrisV1 resultat = m2.beregnPris(63);
        assert(resultat.getPris() == 200);
        assert(resultat.getSone().equalsIgnoreCase(sone2));
    }

    @Test
    public void getTakstFor_Monday_447Minutes() {
        m2 = new M2(produksjon);
        m2.setClock(mockClockMonday);

        PrisV1 resultat = m2.beregnPris(447);
        assert(resultat.getPris() == 800);
        assert(resultat.getSone().equalsIgnoreCase(sone2));
    }

    @Test
    public void getTakstFor_Monday_5760Minutes() {
        m2 = new M2(produksjon);
        m2.setClock(mockClockMonday);

        PrisV1 resultat = m2.beregnPris(5760);
        assert(resultat.getPris() == 9600);
        assert(resultat.getSone().equalsIgnoreCase(sone2));
    }

    @Test
    public void getTakstFor_Monday_8888Minutes() {
        m2 = new M2(produksjon);
        m2.setClock(mockClockMonday);

        PrisV1 resultat = m2.beregnPris(8888);
        assert(resultat.getPris() == 19000);
        assert(resultat.getSone().equalsIgnoreCase(sone2));
    }

    @Test
    public void getTakstFor_Monday_10080Minutes() {
        m2 = new M2(produksjon);
        m2.setClock(mockClockMonday);

        //Testmetode som ogsaa printer tid brukt i nanosekunder
        long start = LocalTime.now().getNano();

        PrisV1 resultat = m2.beregnPris(10080);

        long slutt = LocalTime.now().getNano();

        System.out.println("Tid brukt i ns: " + (slutt - start));

        assert(resultat.getPris() == 21600);
        assert(resultat.getSone().equalsIgnoreCase(sone2));
    }

    @Test
    public void getTakstFor_Sunday_60Minutes() {
        m2 = new M2(produksjon);
        m2.setClock(mockClockSunday);

        PrisV1 resultat = m2.beregnPris(60);
        assert(resultat.getPris() == 200);
        assert(resultat.getSone().equalsIgnoreCase(sone2));
    }

    @Test
    public void getTakstFor_Sunday_63Minutes() {
        m2 = new M2(produksjon);
        m2.setClock(mockClockSunday);

        PrisV1 resultat = m2.beregnPris(63);
        assert(resultat.getPris() == 400);
        assert(resultat.getSone().equalsIgnoreCase(sone2));
    }

    @Test
    public void getTakstFor_Sunday_447Minutes() {
        m2 = new M2(produksjon);
        m2.setClock(mockClockSunday);

        PrisV1 resultat = m2.beregnPris(447);
        assert(resultat.getPris() == 1600);
        assert(resultat.getSone().equalsIgnoreCase(sone2));
    }

    @Test
    public void getTakstFor_Sunday_5760Minutes() {
        m2 = new M2(produksjon);
        m2.setClock(mockClockSunday);

        PrisV1 resultat = m2.beregnPris(5760);
        assert(resultat.getPris() == 10800);
        assert(resultat.getSone().equalsIgnoreCase(sone2));
    }

    @Test
    public void getTakstFor_Sunday_7564Minutes() {
        m2 = new M2(produksjon);
        m2.setClock(mockClockSunday);

        PrisV1 resultat = m2.beregnPris(7564);
        assert(resultat.getPris() == 13900);
        assert(resultat.getSone().equalsIgnoreCase(sone2));
    }

    @Test
    public void getTakstFor_Sunday_10080Minutes() {
        m2 = new M2(produksjon);
        m2.setClock(mockClockSunday);

        PrisV1 resultat = m2.beregnPris(10080);
        assert(resultat.getPris() == 21600);
        assert(resultat.getSone().equalsIgnoreCase(sone2));
    }
}
