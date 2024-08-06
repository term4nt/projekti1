package fi.tman.henkilotietoja.sisaisia.henkilo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class HenkiloTest {

    private Henkilo sut;

    @BeforeEach
    void setUp() {
        this.sut = new Henkilo(1L,
                Henkilo.Sukupuoli.Mies,
                "Sosiaaliturvatunnus1",
                Date.valueOf(LocalDate.now()));
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getEtunimet() {
        assertNull(this.sut.getEtunimet());
    }

    @Test
    void testSetEtunimet() {
        this.sut.setEtunimet("Etunimet");
        assertNotNull(this.sut.getEtunimet());
        assertEquals("Etunimet", this.sut.getEtunimet());
    }

    @Test
    void getSukunimi() {
        assertNull(this.sut.getSukunimi());
    }

    @Test
    void testSetSukunimi() {
        this.sut.setSukunimi("Sukunimi");
        assertNotNull(this.sut.getSukunimi());
        assertEquals("Sukunimi", this.sut.getSukunimi());
    }

    @Test
    void getSukupuoli() {
        assertNotNull(this.sut.getSukupuoli());
        assertEquals(Henkilo.Sukupuoli.Mies, this.sut.getSukupuoli());
    }

    @Test
    void getKuollut() {
        assertNull(this.sut.getKuollut());
    }

    @Test
    void testSetKuollut() {
        this.sut.setKuollut(Date.valueOf(LocalDate.now()));
        assertNotNull(this.sut.getKuollut());
    }

    @Test
    void getSahkopostiosoite() {
        assertNull(this.sut.getSahkopostiosoite());
    }

    @Test
    void testSetSahkopostiosoite() {
        this.sut.setSahkopostiosoite("Sähköpostiosoite");
        assertNotNull(this.sut.getSahkopostiosoite());
        assertEquals("Sähköpostiosoite", this.sut.getSahkopostiosoite());
    }

    @Test
    void getPuhelinnumero() {
        assertNull(this.sut.getPuhelinnumero());
    }

    @Test
    void testSetPuhelinnumero() {
        this.sut.setPuhelinnumero("Puhelinnumero");
        assertNotNull(this.sut.getPuhelinnumero());
        assertEquals("Puhelinnumero", this.sut.getPuhelinnumero());
    }


    @Test
    void testEquals() {
        Henkilo henkilo1 = new Henkilo(
                1L,
                Henkilo.Sukupuoli.Mies,
                "Sosiaaliturvatunnus1",
                Date.valueOf(LocalDate.now()));
        Henkilo henkilo2 = new Henkilo(
                1L,
                Henkilo.Sukupuoli.Mies,
                "Sosiaaliturvatunnus1",
                Date.valueOf(LocalDate.now()));
        Henkilo henkilo3 = new Henkilo(
                1L,
                Henkilo.Sukupuoli.Mies,
                "Sosiaaliturvatunnus2",
                Date.valueOf(LocalDate.now()));

        assertEquals(henkilo1, henkilo2);
        assertNotEquals(henkilo1, henkilo3);
    }

    @Test
    @Disabled
    void testHashCode() {
        Henkilo henkilo = new Henkilo(
                1L,
                Henkilo.Sukupuoli.Mies,
                "Sosiaaliturvatunnus",
                Date.valueOf(LocalDate.now()));
        assertEquals(Objects.hashCode("Sosiaaliturvatunnus"), henkilo.hashCode());
    }
}