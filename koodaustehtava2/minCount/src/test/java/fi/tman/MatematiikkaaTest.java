package fi.tman;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatematiikkaaTest {

    @Test
    void testTaulukkoOnTyhjä() {
        int[] input = {};
        assertEquals(-1, Matematiikkaa.minLukujenMaara(input));
    }
    @Test
    void testTaulukossaOnYksiLuku() {
        int[] input = {1};
        assertEquals(-1, Matematiikkaa.minLukujenMaara(input));
    }
    @Test
    void testTaulukossaOnKaksiSamaaLukua() {
        int[] input = {1, 1};
        assertEquals(-1, Matematiikkaa.minLukujenMaara(input));
    }
    @Test
    void testTaulukossaKaksiPerättäistäLukua() {
        int[] input = {1, 2};
        assertEquals(0, Matematiikkaa.minLukujenMaara(input));
    }
    @Test
    void testTaulukossaKolmeLukua() {
        int[] input = {1, 2, 4};
        assertEquals(0, Matematiikkaa.minLukujenMaara(input));
    }
    @Test
    void testEsimerkki1() {
        int[] input = {1, 4, 10};
        assertEquals(2, Matematiikkaa.minLukujenMaara(input));
    }
    @Test
    void testEsimerkki2() {
        int[] input = {1, 3, 5};
        assertEquals(1, Matematiikkaa.minLukujenMaara(input));
    }
}