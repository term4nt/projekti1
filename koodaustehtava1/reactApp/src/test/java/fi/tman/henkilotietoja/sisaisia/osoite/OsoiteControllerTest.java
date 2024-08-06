package fi.tman.henkilotietoja.sisaisia.osoite;

import fi.tman.henkilotietoja.sisaisia.henkilo.Henkilo;
import fi.tman.henkilotietoja.sisaisia.henkilo.HenkiloController;
import fi.tman.henkilotietoja.sisaisia.henkilo.HenkiloRepository;
import fi.tman.henkilotietoja.sisaisia.henkiloosoite.HenkiloOsoiteRepository;
import fi.tman.henkilotietoja.sisaisia.henkilosuhde.HenkilosuhdeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpEntity;
import org.springframework.lang.NonNull;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

class OsoiteControllerTest {

    private OsoiteController sut;

    private OsoiteRepository osoiteRepository;

    private Map<Long, Osoite> osoitteet;

    @BeforeEach
    public void setUp() throws Exception {
        this.osoiteRepository = mock(OsoiteRepository.class);

        sut = new OsoiteController(this.osoiteRepository);

        osoitteet = new HashMap<>();
        osoitteet.put(2L, new Osoite(2L, "Katuosoite1", "Postinumero1", "Postitoimipaikka1"));
        osoitteet.put(3L, new Osoite(3L, "Katuosoite2", "Postinumero2", "Postitoimipaikka2"));
        osoitteet.put(4L, new Osoite(4L, "Katuosoite3", "Postinumero3", "Postitoimipaikka3"));
    }

    @Test
    void testHaeOsoitteet() {
        Mockito.when(this.osoiteRepository.haeOsoitteet()).thenReturn(this.osoitteet.values().stream().toList());

        List<Osoite> osoitteet = this.sut.haeOsoitteet();

        Mockito.verify(this.osoiteRepository, times(1))
                .haeOsoitteet();
        assertEquals(3, osoitteet.size());
    }

    @Test
    void testHaeOsoiteTunnisteella() {
        Long tunniste = 2L;

        Mockito.when(this.osoiteRepository.haeOsoite(tunniste)).thenReturn(this.osoitteet.get(tunniste));

        Osoite osoite = this.sut.haeOsoite(tunniste);

        Mockito.verify(this.osoiteRepository, times(1))
                .haeOsoite(tunniste);
        assertNotNull(osoite);
        assertEquals(tunniste, osoite.getTunniste());
    }

    @Test
    void testHaeOsoiteTiedoilla() {
        Long tunniste = 2L;
        String katuosoite1 = this.osoitteet.get(tunniste).getKatuosoite();
        String postinumero1 = this.osoitteet.get(tunniste).getPostinumero();
        String postitoimipaikka1 = this.osoitteet.get(tunniste).getPostitoimipaikka();

        Mockito.when(this.osoiteRepository.haeOsoite(katuosoite1, postinumero1, postitoimipaikka1)).thenReturn(this.osoitteet.get(tunniste));

        Osoite osoite = this.sut.haeOsoite(katuosoite1, postinumero1, postitoimipaikka1);

        Mockito.verify(this.osoiteRepository, times(1))
                .haeOsoite(katuosoite1, postinumero1, postitoimipaikka1);
        assertNotNull(osoite);
        assertEquals(katuosoite1, osoite.getKatuosoite());
        assertEquals(postinumero1, osoite.getPostinumero());
        assertEquals(postitoimipaikka1, osoite.getPostitoimipaikka());
    }

    @Test
    void testLisaaOsoite() {
        Long tunniste = 2L;
        Osoite uusiOsoite = new Osoite(
                this.osoitteet.get(tunniste).getKatuosoite(),
                this.osoitteet.get(tunniste).getPostinumero(),
                this.osoitteet.get(tunniste).getPostitoimipaikka());

        Mockito.when(this.osoiteRepository.tallenna(uusiOsoite)).thenReturn(this.osoitteet.get(tunniste));

        asetaValepyynto("http://localhost/osoitteet");

        HttpEntity<?> result = this.sut.lisaaOsoite(uusiOsoite);
        assertNotNull(result);

        assertEquals("http://localhost/osoitteet/2", result.getHeaders().getLocation().toString());
        Mockito.verify(this.osoiteRepository, times(1)).tallenna(uusiOsoite);
    }

    @Test
    void testPaivitaOsoite() {
        Long tunniste = 2L;
        Osoite uusiOsoite = new Osoite(
                tunniste,
                "Katuosoite1-2",
                "Postinumero1-2",
                "Postitoimipaikka1-2");

        this.sut.paivitaOsoite(uusiOsoite, uusiOsoite.getTunniste());

        Mockito.verify(this.osoiteRepository, times(1)).tallenna(uusiOsoite);
    }

    private void asetaValepyynto(String url) {
        String pyynnonURI = url.substring(16); // Drop "http://localhost"

        MockHttpServletRequest pyynto = new MockHttpServletRequest("POST", pyynnonURI);

        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(pyynto));
    }
}