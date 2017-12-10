package no.hakgul.parking.model.soner;

import no.hakgul.parking.model.PrisV1;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class M1Test {

    private M1 m1 = new M1();
    private final String sone1 = "M1";

    @Test
    public void getTakstFor_35Minutes() throws Exception {
        PrisV1 resultat = m1.beregnPris(35);
        assert(resultat.getPris() == 35);
        assert(resultat.getSone().equalsIgnoreCase(sone1));
    }

    @Test
    public void getTakstFor_99Minutes() throws Exception {
        PrisV1 resultat = m1.beregnPris(99);
        assert(resultat.getPris() == 99);
        assert(resultat.getSone().equalsIgnoreCase(sone1));
    }
}
