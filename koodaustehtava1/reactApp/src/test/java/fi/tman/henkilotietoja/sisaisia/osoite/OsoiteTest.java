package fi.tman.henkilotietoja.sisaisia.osoite;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class OsoiteTest {

    private Osoite sut;

    final String katuosoite1 = "Katuosoite1";
    final String katuosoite2 = "Katuosoite2";
    final String postinumero1 = "Postinumero1";
    final String postinumero2 = "Postinumero2";
    final String postitoimipaikka1 = "Postitoimipaikka1";
    final String postitoimipaikka2 = "Postitoimipaikka2";

    @BeforeEach
    void setUp() {
        this.sut = new Osoite(
                1L,
                katuosoite1,
                postinumero1,
                postitoimipaikka1);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testGetKatuosoite() {
        assertNotNull(this.sut.getKatuosoite());
        assertEquals(katuosoite1, this.sut.getKatuosoite());
    }

    @Test
    void testSetKatuosoite() {
        this.sut.setKatuosoite(katuosoite2);
        assertEquals(katuosoite2, this.sut.getKatuosoite());
    }

    @Test
    void testGetPostinumero() {
        assertNotNull(this.sut.getPostinumero());
        assertEquals(postinumero1, this.sut.getPostinumero());
    }

    @Test
    void testSetPostinumero() {
        this.sut.setPostinumero(postinumero2);
        assertEquals(postinumero2, this.sut.getPostinumero());
    }

    @Test
    void testGetPostitoimipaikka() {
        assertNotNull(this.sut.getPostitoimipaikka());
        assertEquals(postitoimipaikka1, this.sut.getPostitoimipaikka());
    }

    @Test
    void testSetPostitoimipaikka() {
        this.sut.setPostitoimipaikka(postitoimipaikka2);
        assertEquals(postitoimipaikka2, this.sut.getPostitoimipaikka());
    }

    @Test
    void testEquals() {
        Osoite osoite1 = new Osoite(
                1L,
                katuosoite1,
                postinumero1,
                postitoimipaikka1);
        Osoite osoite2 = new Osoite(
                1L,
                katuosoite2,
                postinumero1,
                postitoimipaikka1);

        assertEquals(sut, osoite1);
        assertNotEquals(sut, osoite2);
    }

    @Test
    void testHashCode() {
        assertEquals(
                Objects.hash(this.sut.getTunniste(), this.sut.getKatuosoite(), this.sut.getPostinumero(), this.sut.getPostitoimipaikka()),
                this.sut.hashCode());
    }
}