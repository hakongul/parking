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

    private boolean produksjon = false;

    private M3 m3;
    private final String sone3 = "M3";
    private final LocalDateTime sunday = LocalDateTime.of(2017, 11, 19, 12, 0);
    private final LocalDateTime monday_noon = LocalDateTime.of(2017, 11, 20, 12, 0);
    private final LocalDateTime monday_evening = LocalDateTime.of(2017, 11, 20, 18, 32);

    @Test
    public void getTakstFor_45Minutes_Monday_Between_8_16() {
        Clock mockClock = Clock.fixed(monday_noon.atZone(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());

        m3 = new M3(produksjon);
        m3.setClock(mockClock);

        PrisV1 resultat = m3.beregnPris(45);
        assert(resultat.getPris() == 0);
        assert(resultat.getSone().equalsIgnoreCase(sone3));
    }

    @Test
    public void getTakstFor_60Minutes_Monday_Between_8_16() {
        Clock mockClock = Clock.fixed(monday_noon.atZone(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());

        m3 = new M3(produksjon);
        m3.setClock(mockClock);

        PrisV1 resultat = m3.beregnPris(60);
        assert(resultat.getPris() == 0);
        assert(resultat.getSone().equalsIgnoreCase(sone3));
    }

    @Test
    public void getTakstFor_61Minutes_Monday_Between_8_16() {
        Clock mockClock = Clock.fixed(monday_noon.atZone(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());

        m3 = new M3(produksjon);
        m3.setClock(mockClock);

        PrisV1 resultat = m3.beregnPris(61);
        assert(resultat.getPris() == 2);
        assert(resultat.getSone().equalsIgnoreCase(sone3));
    }

    @Test
    public void getTakstFor_162Minutes_Monday_Between_8_16() {
        Clock mockClock = Clock.fixed(monday_noon.atZone(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());

        m3 = new M3(produksjon);
        m3.setClock(mockClock);

        PrisV1 resultat = m3.beregnPris(162);
        assert(resultat.getPris() == 204);
        assert(resultat.getSone().equalsIgnoreCase(sone3));
    }

    @Test
    public void getTakstFor_45Minutes_Monday_Between_16_8() {
        Clock mockClock = Clock.fixed(monday_evening.atZone(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());

        m3 = new M3(produksjon);
        m3.setClock(mockClock);

        PrisV1 resultat = m3.beregnPris(45);
        System.out.println(resultat.getPris());
        assert(resultat.getPris() == 135);
        assert(resultat.getSone().equalsIgnoreCase(sone3));
    }

    @Test
    public void getTakstFor_162Minutes_Monday_Between_16_8() {
        Clock mockClock = Clock.fixed(monday_evening.atZone(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());

        m3 = new M3(produksjon);
        m3.setClock(mockClock);

        PrisV1 resultat = m3.beregnPris(162);
        assert(resultat.getPris() == 486);
        assert(resultat.getSone().equalsIgnoreCase(sone3));
    }

    @Test
    public void getTakstFor_45Minutes_Sunday() {
        Clock mockClock = Clock.fixed(sunday.atZone(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());

        m3 = new M3(produksjon);
        m3.setClock(mockClock);

        PrisV1 resultat = m3.beregnPris(45);
        assert(resultat.getPris() == 0);
        assert(resultat.getSone().equalsIgnoreCase(sone3));
    }

    @Test
    public void getTakstFor_162Minutes_Sunday() {
        Clock mockClock = Clock.fixed(sunday.atZone(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());

        m3 = new M3(produksjon);
        m3.setClock(mockClock);

        PrisV1 resultat = m3.beregnPris(162);
        assert(resultat.getPris() == 0);
        assert(resultat.getSone().equalsIgnoreCase(sone3));
    }
}
