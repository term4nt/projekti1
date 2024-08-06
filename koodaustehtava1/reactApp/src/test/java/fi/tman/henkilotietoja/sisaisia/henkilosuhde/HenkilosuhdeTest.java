package fi.tman.henkilotietoja.sisaisia.henkilosuhde;

import fi.tman.henkilotietoja.sisaisia.henkilo.Henkilo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class HenkilosuhdeTest {

    private Henkilosuhde sut;

    @BeforeEach
    void setUp() {
        Henkilo henkilo1 = mock(Henkilo.class);
        Henkilo henkilo2 = mock(Henkilo.class);
        this.sut = new Henkilosuhde(1L, Henkilosuhde.Tyyppi.Parisuhde, henkilo1, henkilo2, Date.valueOf(LocalDate.now()));
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getHenkilo1() {
        assertNotNull(sut.getHenkilo1());
    }

    @Test
    void getHenkilo2() {
        assertNotNull(sut.getHenkilo2());
    }

    @Test
    void getAlkaen() {
        assertNotNull(sut.getAlkaen());
    }

    @Test
    void getPaattyen() {
        assertNull(sut.getPaattyen());
    }

    @Test
    void setPaattyen() {
        sut.setPaattyen(Date.valueOf(LocalDate.now()));
        assertNotNull(sut.getPaattyen());
    }
}