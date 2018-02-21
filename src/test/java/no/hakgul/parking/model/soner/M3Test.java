package no.hakgul.parking.model.soner;

import no.hakgul.parking.model.PrisV1;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;

@RunWith(SpringRunner.class)
@SpringBootTest
public class M3Test {

    /**
     * For testing av Sone M3 har jeg lagt følgende til grunn:
     * - Dersom beregningen går fra søndag over til Mandag regnet jeg med at 1 gratis time parkering ikke gjaldt
     * - Dersom beregningen går lenge nok til at man har fått den gratis timen dagen før, får man det ikke igjen neste dag
     */

    private boolean produksjon = false;

    private M3 m3;
    private final String sone3 = "M3";
    private final LocalDateTime sunday = LocalDateTime.of(2017, 11, 19, 12, 0);
    private final LocalDateTime monday_noon = LocalDateTime.of(2017, 11, 20, 12, 0);
    private final LocalDateTime monday_evening = LocalDateTime.of(2017, 11, 20, 18, 32);
    private final LocalDateTime friday_noon = LocalDateTime.of(2018, 2, 16, 12, 0);

    private final Clock mockClockSundayNoon = Clock.fixed(sunday.atZone(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());
    private final Clock mockClockMondayNoon = Clock.fixed(monday_noon.atZone(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());
    private final Clock mockClockMondayEvening = Clock.fixed(monday_evening.atZone(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());
    private final Clock mockClockFridayNoon = Clock.fixed(friday_noon.atZone(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());

    @Test
    public void getTakstFor_Monday_45Minutes_FromNoon() {
        m3 = new M3(produksjon);
        m3.setClock(mockClockMondayNoon);

        PrisV1 resultat = m3.beregnPris(45);
        assert(resultat.getPris() == 0);
        assert(resultat.getSone().equalsIgnoreCase(sone3));
    }

    @Test
    public void getTakstFor_Monday_60Minutes_FromNoon() {
        m3 = new M3(produksjon);
        m3.setClock(mockClockMondayNoon);

        PrisV1 resultat = m3.beregnPris(60);
        assert(resultat.getPris() == 0);
        assert(resultat.getSone().equalsIgnoreCase(sone3));
    }

    @Test
    public void getTakstFor_Monday_61Minutes_FromNoon() {
        m3 = new M3(produksjon);
        m3.setClock(mockClockMondayNoon);

        PrisV1 resultat = m3.beregnPris(61);
        assert(resultat.getPris() == 2);
        assert(resultat.getSone().equalsIgnoreCase(sone3));
    }

    @Test
    public void getTakstFor_Monday_162Minutes_FromNoon() {
        m3 = new M3(produksjon);
        m3.setClock(mockClockMondayNoon);

        PrisV1 resultat = m3.beregnPris(162);
        assert(resultat.getPris() == 204);
        assert(resultat.getSone().equalsIgnoreCase(sone3));
    }

    @Test
    public void getTakstFor_Monday_1441Minutes_FromNoon() {
        m3 = new M3(produksjon);
        m3.setClock(mockClockMondayNoon);

        PrisV1 resultat = m3.beregnPris(1441);
        assert(resultat.getPris() == 3722);
        assert(resultat.getSone().equalsIgnoreCase(sone3));
    }

    @Test
    public void getTakstFor_Monday_45Minutes_FromEvening() {
        m3 = new M3(produksjon);
        m3.setClock(mockClockMondayEvening);

        PrisV1 resultat = m3.beregnPris(45);
        assert(resultat.getPris() == 135);
        assert(resultat.getSone().equalsIgnoreCase(sone3));
    }

    @Test
    public void getTakstFor_Monday_162Minutes_FromEvening() {
        m3 = new M3(produksjon);
        m3.setClock(mockClockMondayEvening);

        PrisV1 resultat = m3.beregnPris(162);
        assert(resultat.getPris() == 486);
        assert(resultat.getSone().equalsIgnoreCase(sone3));
    }

    @Test
    public void getTakstFor_Friday_960Minutes_FromNoon() {
        m3 = new M3(produksjon);
        m3.setClock(mockClockFridayNoon);

        PrisV1 resultat = m3.beregnPris(960);
        assert(resultat.getPris() == 2520);
        assert(resultat.getSone().equalsIgnoreCase(sone3));
    }

    @Test
    public void getTakstFor_Friday_2460Minutes_FromNoon() {
        m3 = new M3(produksjon);
        m3.setClock(mockClockFridayNoon);

        PrisV1 resultat = m3.beregnPris(2460);
        assert(resultat.getPris() == 5640);
        assert(resultat.getSone().equalsIgnoreCase(sone3));
    }

    @Test
    public void getTakstFor_Friday_5555Minutes_FromNoon() {
        m3 = new M3(produksjon);
        m3.setClock(mockClockFridayNoon);

        PrisV1 resultat = m3.beregnPris(5555);
        assert(resultat.getPris() == 10990);
        assert(resultat.getSone().equalsIgnoreCase(sone3));
    }

    @Test
    public void getTakstFor_Sunday_45Minutes_FromNoon() {
        m3 = new M3(produksjon);
        m3.setClock(mockClockSundayNoon);

        PrisV1 resultat = m3.beregnPris(45);
        assert(resultat.getPris() == 0);
        assert(resultat.getSone().equalsIgnoreCase(sone3));
    }

    @Test
    public void getTakstFor_Sunday_162Minutes_FromNoon() {
        m3 = new M3(produksjon);
        m3.setClock(mockClockSundayNoon);

        PrisV1 resultat = m3.beregnPris(162);
        assert(resultat.getPris() == 0);
        assert(resultat.getSone().equalsIgnoreCase(sone3));
    }

    @Test
    public void getTakstFor_Sunday_1200Minutes_FromNoon() {
        m3 = new M3(produksjon);
        m3.setClock(mockClockSundayNoon);

        PrisV1 resultat = m3.beregnPris(1200);
        assert(resultat.getPris() == 1440);
        assert(resultat.getSone().equalsIgnoreCase(sone3));
    }

    @Test
    public void getTakstFor_Sunday_10080Minutes_FromNoon() {
        m3 = new M3(produksjon);
        m3.setClock(mockClockSundayNoon);

        PrisV1 resultat = m3.beregnPris(10080);
        assert(resultat.getPris() == 23040);
        assert(resultat.getSone().equalsIgnoreCase(sone3));
    }

    @Test
    public void getTakstFor_Sunday_43200Minutes_FromNoon() {
        m3 = new M3(produksjon);
        m3.setClock(mockClockSundayNoon);

        PrisV1 resultat = m3.beregnPris(43200);
        assert(resultat.getPris() == 97920);
        assert(resultat.getSone().equalsIgnoreCase(sone3));
    }
}
