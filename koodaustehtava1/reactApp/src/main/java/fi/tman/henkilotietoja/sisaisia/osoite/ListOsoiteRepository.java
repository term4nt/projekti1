package fi.tman.henkilotietoja.sisaisia.osoite;

import fi.tman.henkilotietoja.sisaisia.yleiset.ListRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ListOsoiteRepository
        extends ListRepository<Osoite>
        implements OsoiteRepository {

    public ListOsoiteRepository() {
        super();
    }

    public ListOsoiteRepository(List<Osoite> osoitteet) {
        super(osoitteet);
    }

    @Override
    public List<Osoite> haeOsoitteet() {
        return super.haeKaikki();
    }

    @Override
    public Osoite haeOsoite(Long tunniste) {
        return super.haeTunnisteella(tunniste);
    }

    @Override
    public Osoite haeOsoite(String katuosoite, String postinumero, String postitoimipaikka) {
        for(Osoite osoite: haeKaikki()) {
            if(osoite.getKatuosoite().equals(katuosoite)
            && osoite.getPostinumero().equals(postinumero)
            && osoite.getPostitoimipaikka().equals(postitoimipaikka)) {
                return osoite;
            }
        }
        return null;
    }

    @Override
    public List<Osoite> haeOsoitteet(List<Long> tunnisteet) {
        return super.haeTunnisteilla(tunnisteet);
    }

    @Override
    public Osoite tallenna(Osoite osoite) {
        return super.tallenna(osoite);
    }
}
