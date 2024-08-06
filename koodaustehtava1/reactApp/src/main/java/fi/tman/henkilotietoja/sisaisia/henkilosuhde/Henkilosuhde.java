package fi.tman.henkilotietoja.sisaisia.henkilosuhde;

import fi.tman.henkilotietoja.sisaisia.henkilo.Henkilo;
import fi.tman.henkilotietoja.sisaisia.yleiset.Entiteetti;
import org.springframework.lang.NonNull;

import java.util.Date;
import java.util.Objects;

public class Henkilosuhde extends Entiteetti {
    @NonNull
    private Henkilo henkilo1;
    @NonNull
    private Henkilo henkilo2;
    @NonNull
    private Tyyppi tyyppi;
    @NonNull
    private Date alkaen;
    private Date paattyen;

    public enum Tyyppi { Vanhemmuus, Huoltajuus, Parisuhde, Edunvalvonta };

    public Henkilosuhde() {
        super();
    }

    public Henkilosuhde(@NonNull Tyyppi tyyppi,
                        @NonNull Henkilo henkilo1,
                        @NonNull Henkilo henkilo2,
                        @NonNull Date alkaen) {
        super();
        this.tyyppi = tyyppi;
        this.henkilo1 = henkilo1;
        this.henkilo2 = henkilo2;
        this.alkaen = alkaen;
    }

    public Henkilosuhde(@NonNull Long tunniste,
                        @NonNull Tyyppi tyyppi,
                        @NonNull Henkilo henkilo1,
                        @NonNull Henkilo henkilo2,
                        @NonNull Date alkaen) {
        this(tyyppi, henkilo1, henkilo2, alkaen);
        setTunniste(tunniste);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Henkilosuhde that = (Henkilosuhde) o;
        return Objects.equals(getTunniste(), that.getTunniste()) && Objects.equals(getTyyppi(), that.getTyyppi()) && Objects.equals(getHenkilo1(), that.getHenkilo1()) && Objects.equals(getHenkilo2(), that.getHenkilo2()) && Objects.equals(getAlkaen(), that.getAlkaen()) && Objects.equals(getPaattyen(), that.getPaattyen());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getTyyppi(), getHenkilo2(), getAlkaen(), getPaattyen());
    }

    @NonNull
    public Tyyppi getTyyppi() {
        return tyyppi;
    }

    @NonNull
    public Henkilo getHenkilo1() {
        return henkilo1;
    }

    @NonNull
    public Henkilo getHenkilo2() {
        return henkilo2;
    }

    @NonNull
    public Date getAlkaen() {
        return alkaen;
    }

    public Date getPaattyen() {
        return paattyen;
    }

    public void setPaattyen(Date paattyen) {
        this.paattyen = paattyen;
    }

    @Override
    public String toString() {
        return "Henkilosuhde{" +
                "henkilo1=" + henkilo1 +
                ", henkilo2=" + henkilo2 +
                ", tyyppi=" + tyyppi +
                ", alkaen=" + alkaen +
                ", paattyen=" + paattyen +
                '}';
    }
}
