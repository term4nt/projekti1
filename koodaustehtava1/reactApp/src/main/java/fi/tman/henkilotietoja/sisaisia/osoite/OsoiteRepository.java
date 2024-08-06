package fi.tman.henkilotietoja.sisaisia.osoite;

import java.util.List;

public interface OsoiteRepository {

    // TODO
    public List<Osoite> haeOsoitteet();

    // TODO
    public Osoite haeOsoite(Long tunniste);

    // TODO
    public Osoite haeOsoite(String katu, String postinumero, String postitoimipaikka);

    // TODO
    public List<Osoite> haeOsoitteet(List<Long> tunnisteet);

    // TODO
    public Osoite tallenna(Osoite osoite);
}
