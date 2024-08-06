package fi.tman.henkilotietoja.sisaisia.henkilo;

import fi.tman.henkilotietoja.sisaisia.henkilosuhde.Henkilosuhde;
import fi.tman.henkilotietoja.sisaisia.osoite.Osoite;
import fi.tman.henkilotietoja.sisaisia.yleiset.Entiteetti;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Henkilo extends Entiteetti {
    public enum Sukupuoli { Mies, Nainen, Muu };

    // NOTE: voi vaihtua
    private String etunimet;
    // NOTE: voi vaihtua
    private String sukunimi;
    @NonNull
    private Sukupuoli sukupuoli;
    @NonNull
    private String sosiaaliturvatunnus;
    @NonNull
    private Date syntynyt;
    private Date kuollut;
    private String sahkopostiosoite;
    private String puhelinnumero;
    private List<Henkilosuhde> henkilosuhteet;
    private List<Osoite> osoitteet;

    public Henkilo() {
        super();
    }

    public Henkilo(@NonNull Sukupuoli sukupuoli,
                   @NonNull String sosiaaliturvatunnus,
                   @NonNull Date syntynyt) {
        super();
        this.sosiaaliturvatunnus = sosiaaliturvatunnus;
        this.sukupuoli = sukupuoli;
        this.syntynyt = syntynyt;
        this.henkilosuhteet = new ArrayList<Henkilosuhde>();
        this.osoitteet = new ArrayList<Osoite>();
    }

    public Henkilo(@NonNull Long tunniste,
                   @NonNull Sukupuoli sukupuoli,
                   @NonNull String sosiaaliturvatunnus,
                   @NonNull Date syntynyt) {
        this(sukupuoli, sosiaaliturvatunnus, syntynyt);
        setTunniste(tunniste);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Henkilo henkilo = (Henkilo) o;
        return Objects.equals(getTunniste(), henkilo.getTunniste()) && Objects.equals(getEtunimet(), henkilo.getEtunimet()) && Objects.equals(getSukunimi(), henkilo.getSukunimi()) && getSukupuoli() == henkilo.getSukupuoli() && Objects.equals(sosiaaliturvatunnus, henkilo.sosiaaliturvatunnus) && Objects.equals(syntynyt, henkilo.syntynyt) && Objects.equals(getKuollut(), henkilo.getKuollut()) && Objects.equals(getSahkopostiosoite(), henkilo.getSahkopostiosoite()) && Objects.equals(getPuhelinnumero(), henkilo.getPuhelinnumero());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getEtunimet(), getSukunimi(), getSukupuoli(), sosiaaliturvatunnus, syntynyt, getKuollut(), getSahkopostiosoite(), getPuhelinnumero());
    }

    public String getEtunimet() {
        return etunimet;
    }

    public void setEtunimet(String etunimet) {
        this.etunimet = etunimet;
    }

    public String getSukunimi() {
        return sukunimi;
    }

    public void setSukunimi(String sukunimi) {
        this.sukunimi = sukunimi;
    }

    public Sukupuoli getSukupuoli() {
        return sukupuoli;
    }

    @NonNull
    public Date getSyntynyt() {
        return syntynyt;
    }

    @NonNull
    public String getSosiaaliturvatunnus() {
        return sosiaaliturvatunnus;
    }

    public Date getKuollut() {
        return kuollut;
    }

    public void setKuollut(Date kuollut) {
        this.kuollut = kuollut;
    }

    public String getSahkopostiosoite() {
        return sahkopostiosoite;
    }

    public void setSahkopostiosoite(String sahkopostiosoite) {
        this.sahkopostiosoite = sahkopostiosoite;
    }

    public String getPuhelinnumero() {
        return puhelinnumero;
    }

    public void setPuhelinnumero(String puhelinnumero) {
        this.puhelinnumero = puhelinnumero;
    }

    public List<Henkilosuhde> getHenkilosuhteet() {
        return henkilosuhteet;
    }

    public void setHenkilosuhteet(List<Henkilosuhde> henkilosuhteet) {
        this.henkilosuhteet = henkilosuhteet;
    }

    public void addHenkilosuhde(Henkilosuhde henkilosuhde) {
        this.henkilosuhteet.add(henkilosuhde);
    }

    public List<Osoite> getOsoitteet() {
        return osoitteet;
    }

    public void setOsoitteet(List<Osoite> osoitteet) {
        this.osoitteet = osoitteet;
    }

    public void addOsoite(Osoite osoite) {
        this.osoitteet.add(osoite);
    }

    @Override
    public String toString() {
        return "Henkilo{" +
                "etunimet='" + etunimet + '\'' +
                ", sukunimi='" + sukunimi + '\'' +
                ", sukupuoli=" + sukupuoli +
                ", sosiaaliturvatunnus='" + sosiaaliturvatunnus + '\'' +
                ", syntynyt=" + syntynyt +
                ", kuollut=" + kuollut +
                ", sahkopostiosoite='" + sahkopostiosoite + '\'' +
                ", puhelinnumero='" + puhelinnumero + '\'' +
                ", henkilosuhteet=" + henkilosuhteet +
                ", osoitteet=" + osoitteet +
                '}';
    }
}
