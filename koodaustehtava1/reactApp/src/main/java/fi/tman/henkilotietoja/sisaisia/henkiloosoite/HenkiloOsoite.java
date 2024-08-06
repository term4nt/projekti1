package fi.tman.henkilotietoja.sisaisia.henkiloosoite;

import fi.tman.henkilotietoja.sisaisia.henkilo.Henkilo;
import fi.tman.henkilotietoja.sisaisia.osoite.Osoite;
import fi.tman.henkilotietoja.sisaisia.yleiset.Entiteetti;
import org.springframework.lang.NonNull;

import java.util.Date;
import java.util.Objects;

public class HenkiloOsoite extends Entiteetti {
    @NonNull
    private Henkilo henkilo;
    @NonNull
    private Osoite osoite;
    @NonNull
    private Date alkaen;
    private Date paattyen;

    public HenkiloOsoite() {
        super();
    }

    public HenkiloOsoite(@NonNull Henkilo henkilo,
                         @NonNull Osoite osoite,
                         @NonNull Date alkaen,
                         Date paattyen) {
        super();
        this.henkilo = henkilo;
        this.osoite = osoite;
        this.alkaen = alkaen;
        this.paattyen = paattyen;
    }

    public HenkiloOsoite(@NonNull Long tunniste,
                         @NonNull Henkilo henkilo,
                         @NonNull Osoite osoite,
                         @NonNull Date alkaen,
                         Date paattyen) {
        this(henkilo, osoite, alkaen, paattyen);
        setTunniste(tunniste);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        HenkiloOsoite that = (HenkiloOsoite) o;
        return Objects.equals(getTunniste(), that.getTunniste()) && Objects.equals(getHenkilo(), that.getHenkilo()) && Objects.equals(getOsoite(), that.getOsoite()) && Objects.equals(getAlkaen(), that.getAlkaen()) && Objects.equals(getPaattyen(), that.getPaattyen());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getHenkilo(), getOsoite(), getAlkaen(), getPaattyen());
    }

    @NonNull
    public Henkilo getHenkilo() {
        return henkilo;
    }

    @NonNull
    public Osoite getOsoite() {
        return osoite;
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
        return "HenkiloOsoite{" +
                "henkilo=" + henkilo +
                ", osoite=" + osoite +
                ", alkaen=" + alkaen +
                ", paattyen=" + paattyen +
                '}';
    }
}
