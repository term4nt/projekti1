package fi.tman.henkilotietoja.sisaisia.osoite;

import fi.tman.henkilotietoja.sisaisia.yleiset.Entiteetti;
import org.springframework.lang.NonNull;

import java.util.Objects;

public class Osoite extends Entiteetti {
    @NonNull
    private String katuosoite;
    @NonNull
    private String postinumero;
    @NonNull
    private String postitoimipaikka;

    public Osoite() {
        super();
    }

    public Osoite(@NonNull String katuosoite, @NonNull String postinumero, @NonNull String postitoimipaikka) {
        super();
        this.katuosoite = katuosoite;
        this.postinumero = postinumero;
        this.postitoimipaikka = postitoimipaikka;
    }

    public Osoite(@NonNull Long tunniste, @NonNull String katuosoite, @NonNull String postinumero, @NonNull String postitoimipaikka) {
        this(katuosoite, postinumero, postitoimipaikka);
        super.setTunniste(tunniste);
    }

    public String getKatuosoite() {
        return katuosoite;
    }

    public void setKatuosoite(@NonNull String katuosoite) {
        this.katuosoite = katuosoite;
    }

    public String getPostinumero() {
        return postinumero;
    }

    public void setPostinumero(@NonNull String postinumero) {
        this.postinumero = postinumero;
    }

    public String getPostitoimipaikka() {
        return postitoimipaikka;
    }

    public void setPostitoimipaikka(@NonNull String postitoimipaikka) {
        this.postitoimipaikka = postitoimipaikka;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Osoite osoite = (Osoite) o;
        return Objects.equals(getTunniste(), osoite.getTunniste()) && Objects.equals(getKatuosoite(), osoite.getKatuosoite()) && Objects.equals(getPostinumero(), osoite.getPostinumero()) && Objects.equals(getPostitoimipaikka(), osoite.getPostitoimipaikka());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTunniste(), getKatuosoite(), getPostinumero(), getPostitoimipaikka());
    }

    @Override
    public String toString() {
        return "Osoite{" +
                "katuosoite='" + katuosoite + '\'' +
                ", postinumero='" + postinumero + '\'' +
                ", postitoimipaikka='" + postitoimipaikka + '\'' +
                '}';
    }
}
