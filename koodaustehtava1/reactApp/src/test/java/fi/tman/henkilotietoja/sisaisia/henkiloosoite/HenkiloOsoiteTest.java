package fi.tman.henkilotietoja.sisaisia.henkiloosoite;

import fi.tman.henkilotietoja.sisaisia.henkilo.Henkilo;
import fi.tman.henkilotietoja.sisaisia.osoite.Osoite;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class HenkiloOsoiteTest {

    static private HenkiloOsoite sut;

    static private Henkilo henkilo;
    static private Osoite osoite;
    static private Date alkaen;

    @BeforeAll
    static void setUp() {
        henkilo = mock(Henkilo.class);
        osoite = mock(Osoite.class);
        alkaen = Date.valueOf(LocalDate.now());
        sut = new HenkiloOsoite(
                1L,
                henkilo,
                osoite,
                alkaen,
                null);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testGetTunnus() {
        assertNotNull(sut.getTunniste());
    }

    @Test
    void testGetHenkilo() {
        assertNotNull(sut.getHenkilo());
    }

    @Test
    void testGetOsoite() {
        assertNotNull(sut.getOsoite());
    }

    @Test
    void testGetAlkaen() {
        assertNotNull(sut.getAlkaen());
    }

    @Test
    void testGetPaattyen() {
        sut = new HenkiloOsoite(
                1L,
                henkilo,
                osoite,
                alkaen,
                null);
        assertNull(sut.getPaattyen());
    }

    @Test
    void testSetPaattyen() {
        sut.setPaattyen(Date.valueOf(LocalDate.now()));
        assertNotNull(sut.getPaattyen());
    }
}