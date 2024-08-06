package fi.tman.henkilotietoja.sisaisia.henkilo;

import fi.tman.henkilotietoja.sisaisia.henkilosuhde.Henkilosuhde;
import fi.tman.henkilotietoja.sisaisia.osoite.Osoite;

import java.util.List;
import java.util.Optional;

public interface HenkiloRepository {
    // TODO
    public List<Henkilo> haeKaikkihenkilot();

    // TODO
    public Henkilo haeTunnisteella(Long tunniste);

    // TODO
    public Henkilo haeSosiaaliturvatunnuksella(String sosiaaliturvatunnus);

    // TODO
    public List<Henkilo> haeHenkilotTunnisteella(List<Long> tunnisteet);

    // TODO
    public Henkilo tallenna(Henkilo henkilo);

    // TODO
    public List<Osoite> haeOsoitteet(Long tunniste);

    // TODO
    public List<Henkilosuhde.Tyyppi> haeHenkilosuhdetyypit();

    // TODO
    public List<Henkilo> haeSuhteet(Long tunniste, Henkilosuhde.Tyyppi tyyppi);
}
