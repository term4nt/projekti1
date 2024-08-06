package fi.tman.henkilotietoja.sisaisia.henkilo;

import fi.tman.henkilotietoja.sisaisia.henkilosuhde.Henkilosuhde;
import fi.tman.henkilotietoja.sisaisia.osoite.Osoite;
import fi.tman.henkilotietoja.sisaisia.yleiset.ListRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ListHenkiloRepository
        extends ListRepository<Henkilo>
        implements HenkiloRepository {

    public ListHenkiloRepository() {
        this(new ArrayList<>());
    }

    public ListHenkiloRepository(List<Henkilo> henkilot) {
        super(henkilot);
    }

    @Override
    public List<Henkilo> haeKaikkihenkilot() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Henkilo haeTunnisteella(Long tunniste) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Henkilo haeSosiaaliturvatunnuksella(String sosiaaliturvatunnus) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Henkilo> haeHenkilotTunnisteella(List<Long> tunnisteet) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Henkilo tallenna(Henkilo henkilo) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Osoite> haeOsoitteet(Long tunniste) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Henkilosuhde.Tyyppi> haeHenkilosuhdetyypit() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Henkilo> haeSuhteet(Long tunniste, Henkilosuhde.Tyyppi tyyppi) {
        throw new UnsupportedOperationException();
    }
}
