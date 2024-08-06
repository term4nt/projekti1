package fi.tman.henkilotietoja.sisaisia.henkilo;

import fi.tman.henkilotietoja.sisaisia.henkiloosoite.HenkiloOsoiteRepository;
import fi.tman.henkilotietoja.sisaisia.henkilosuhde.Henkilosuhde;
import fi.tman.henkilotietoja.sisaisia.henkilosuhde.HenkilosuhdeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

public class HenkiloControllerTests {

    private HenkiloController sut;

    private HenkiloRepository henkiloRepository;
    private HenkiloOsoiteRepository henkiloOsoiteRepository;
    private HenkilosuhdeRepository henkilosuhdeRepository;

    @BeforeEach
    public void setUp() throws Exception {
        this.henkiloRepository = mock(HenkiloRepository.class);
        this.henkiloOsoiteRepository = mock(HenkiloOsoiteRepository.class);
        this.henkilosuhdeRepository = mock(HenkilosuhdeRepository.class);

        sut = new HenkiloController(this.henkiloRepository,
                this.henkiloOsoiteRepository,
                this.henkilosuhdeRepository);
    }

    @Test
    public void testKasitteleHaeHenkiloTunnisteella_eiLoydy() {
        Long tunniste = 1L;

        Mockito.when(this.henkiloRepository.haeTunnisteella(tunniste)).thenReturn(null);

        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> this.sut.haeHenkilo(tunniste));

        Mockito.verify(this.henkiloRepository, times(1))
                .haeTunnisteella(tunniste);
    }

    @Test
    public void testKasitteleHaeHenkiloTunnisteella_loytyy() {
        Henkilo henkilo = new Henkilo(
                1L,
                Henkilo.Sukupuoli.Mies,
                "Sosiaaliturvatunnus1",
                Date.valueOf(LocalDate.now()));

        Mockito.when(this.henkiloRepository.haeTunnisteella(henkilo.getTunniste())).thenReturn(henkilo);

        Henkilo haettuHenkilo = this.sut.haeHenkilo(henkilo.getTunniste());

        Mockito.verify(this.henkiloRepository, times(1))
                .haeTunnisteella(henkilo.getTunniste());
        assertNotNull(haettuHenkilo);
    }

    @Test
    public void testKasitteleHaeHenkiloSosiaaliturvatunnuksella_eiLoydy() {
        String sosiaaliturvatunnus = "Sosiaaliturvatunnus";

        Mockito.when(this.henkiloRepository.haeSosiaaliturvatunnuksella(sosiaaliturvatunnus))
                .thenReturn(null);

        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> this.sut.haeHenkilo(sosiaaliturvatunnus));

        Mockito.verify(this.henkiloRepository, times(1))
                .haeSosiaaliturvatunnuksella(sosiaaliturvatunnus);
    }

    @Test
    public void testKasitteleHaeHenkiloSosiaaliturvatunnuksella_loytyy() {
        Henkilo henkilo = new Henkilo(
                1L,
                Henkilo.Sukupuoli.Mies,
                "Sosiaaliturvatunnus1",
                Date.valueOf(LocalDate.now()));

        Mockito.when(this.henkiloRepository.haeSosiaaliturvatunnuksella(henkilo.getSosiaaliturvatunnus()))
                .thenReturn(henkilo);

        Henkilo haettuHenkilo = this.sut.haeHenkilo(henkilo.getSosiaaliturvatunnus());

        Mockito.verify(this.henkiloRepository, times(1))
                .haeSosiaaliturvatunnuksella(henkilo.getSosiaaliturvatunnus());
        assertNotNull(haettuHenkilo);
    }

    @Test
    public void testKasitteleLisaaHenkilo() {
        Henkilo uusiHenkilo = new Henkilo(
                1L,
                Henkilo.Sukupuoli.Mies,
                "Sosiaaliturvatunnus1",
                Date.valueOf(LocalDate.now()));

        Mockito.when(this.henkiloRepository.tallenna(uusiHenkilo)).thenReturn(uusiHenkilo);

        asetaValepyynto("http://localhost/henkilot");

        HttpEntity<?> result = this.sut.lisaaHenkilo(uusiHenkilo);
        assertNotNull(result);

        assertEquals("http://localhost/henkilot/1", result.getHeaders().getLocation().toString());
        Mockito.verify(this.henkiloRepository, times(1)).tallenna(uusiHenkilo);
    }

    @Test
    public void testKasittelePaivitaHenkilo() {
        Henkilo paivitettavatHenkilo = new Henkilo(
                1L,
                Henkilo.Sukupuoli.Mies,
                "Sosiaaliturvatunnus",
                Date.valueOf(LocalDate.now()));

        this.sut.paivitaHenkilo(paivitettavatHenkilo, paivitettavatHenkilo.getTunniste());

        Mockito.verify(this.henkiloRepository, times(1)).tallenna(paivitettavatHenkilo);
    }

    @Test
    public void testKasitteleHaeHenkilonOsoitteet() {
        Long tunniste = 1L;

        this.sut.haeHenkilonHenkiloOsoitteet(tunniste);

        Mockito.verify(this.henkiloOsoiteRepository, times(1)).haeHenkiloOsoitteet(tunniste);
    }

    @Test
    public void testKasitteleHaeHenkilosuhdetyypit() {
        Mockito.when(this.henkiloRepository.haeHenkilosuhdetyypit())
                .thenReturn(List.of(Henkilosuhde.Tyyppi.values()));

        List<Henkilosuhde.Tyyppi> tyypit = this.sut.haeHenkilosuhdetyypit();

        assertEquals(Henkilosuhde.Tyyppi.values().length, tyypit.size());
        for (Henkilosuhde.Tyyppi tyyppi: Henkilosuhde.Tyyppi.values()) {
            assertTrue(tyypit.contains(tyyppi));
        }
    }

    @Test
    public void testKasitteleHaeHenkilonSuhteet() {
        Long tunniste = 1L;

        this.sut.haeHenkilonSuhteet(tunniste);

        Mockito.verify(this.henkilosuhdeRepository, times(1))
                .haeHenkilosuhteet(tunniste);
    }

    private void asetaValepyynto(String url) {
        String pyynnonURI = url.substring(16); // Drop "http://localhost"

        MockHttpServletRequest pyynto = new MockHttpServletRequest("POST", pyynnonURI);

        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(pyynto));
    }
}
