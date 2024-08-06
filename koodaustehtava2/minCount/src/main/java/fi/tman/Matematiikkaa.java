package fi.tman;

// Palauttaa pienimmän lukumäärän mitä luonnollisessa järjestyksessä mahtuu taulukon eri numeroiden välille,
// jos sellainen löytyy, muuten arvon -1
public class Matematiikkaa {
    public static int minLukujenMaara(int[] input) {
        int result = Integer.MAX_VALUE;
        if(input.length > 1) {
            int countInBetween = 0;
            for(int i = 0; i < input.length - 1; i++) {
                countInBetween = countNumbersBetween(input[i + 1], input[i]);
                if(countInBetween > -1 && countInBetween < result) {
                    result = countInBetween;
                }
            }
        }
        if(result == Integer.MAX_VALUE) {
            result = -1;
        }
        return result;
    };

    private static int countNumbersBetween(int eka, int toka) {
        int erotus = Math.abs(eka - toka);
        return erotus - 1;
    }
}
