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
public class M2Test {

    private boolean produksjon = false;

    private M2 m2;
    private final String sone2 = "M2";
    private final LocalDateTime sunday = LocalDateTime.of(2017, 11, 19, 12, 0);
    private final LocalDateTime monday = LocalDateTime.of(2017, 11, 20, 12, 0);

    @Test
    public void getTakstFor_63Minutes_Monday() throws Exception {
        Clock mockClock = Clock.fixed(monday.atZone(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());

        m2 = new M2(produksjon);
        m2.setClock(mockClock);

        PrisV1 resultat = m2.beregnPris(63);
        assert(resultat.getPris() == 200);
        assert(resultat.getSone().equalsIgnoreCase(sone2));
    }

    @Test
    public void getTakstFor_447Minutes_Monday() throws Exception {
        Clock mockClock = Clock.fixed(monday.atZone(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());

        m2 = new M2(produksjon);
        m2.setClock(mockClock);

        PrisV1 resultat = m2.beregnPris(447);
        assert(resultat.getPris() == 800);
        assert(resultat.getSone().equalsIgnoreCase(sone2));
    }

    @Test
    public void getTakstFor_63Minutes_Sunday() throws Exception {
        Clock mockClock = Clock.fixed(sunday.atZone(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());

        m2 = new M2(produksjon);
        m2.setClock(mockClock);

        PrisV1 resultat = m2.beregnPris(63);
        assert(resultat.getPris() == 400);
        assert(resultat.getSone().equalsIgnoreCase(sone2));
    }

    @Test
    public void getTakstFor_447Minutes_Sunday() throws Exception {
        Clock mockClock = Clock.fixed(sunday.atZone(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());

        m2 = new M2(produksjon);
        m2.setClock(mockClock);

        PrisV1 resultat = m2.beregnPris(447);
        assert(resultat.getPris() == 1600);
        assert(resultat.getSone().equalsIgnoreCase(sone2));
    }
}
