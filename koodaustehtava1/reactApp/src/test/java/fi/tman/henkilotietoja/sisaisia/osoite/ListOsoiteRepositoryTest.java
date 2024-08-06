package fi.tman.henkilotietoja.sisaisia.osoite;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ListOsoiteRepositoryTest {

    private OsoiteRepository sut;

    @BeforeEach
    void setUp() {
        List<Osoite> osoitteita = new ArrayList<>();
        osoitteita.add(new Osoite(1L, "Katuosoite1", "Postinumero1", "Postitoimipaikka1"));
        osoitteita.add(new Osoite(2L, "Katuosoite2", "Postinumero2", "Postitoimipaikka2"));
        osoitteita.add(new Osoite(3L, "Katuosoite3", "Postinumero3", "Postitoimipaikka3"));

        this.sut = new ListOsoiteRepository(osoitteita);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testHaeKaikkiOsoitteet() {
        List<Osoite> osoitteet = this.sut.haeOsoitteet();

        assertEquals(3, osoitteet.size());
    }

    @Test
    void testHaeOsoiteTunnisteella() {
        Osoite osoite = this.sut.haeOsoite(1L);

        assertNotNull(osoite);
    }

    @Test
    void testHaeOsoiteTiedoilla() {
        Osoite osoite = this.sut.haeOsoite("Katuosoite1", "Postinumero1", "Postitoimipaikka1");

        assertNotNull(osoite);
    }

    @Test
    void testHaeTietytOsoitteet() {
        List<Osoite> osoitteet = this.sut.haeOsoitteet(List.of(1L, 2L));

        assertEquals(2, osoitteet.size());
    }

    @Test
    void testTallenna() {
        Osoite lisattavaOsoite = new Osoite("Katuosoite4", "Postinumero4", "Postitoimipaikka4");

        assertNull(lisattavaOsoite.getTunniste());
        Osoite lisattyOsoite = this.sut.tallenna(lisattavaOsoite);

        assertNotNull(lisattyOsoite);
        assertNotNull(lisattyOsoite.getTunniste());
        assertEquals(4L, lisattavaOsoite.getTunniste());
    }
}