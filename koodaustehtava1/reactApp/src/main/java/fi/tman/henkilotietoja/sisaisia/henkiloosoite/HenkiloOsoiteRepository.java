package fi.tman.henkilotietoja.sisaisia.henkiloosoite;

import java.util.List;

public interface HenkiloOsoiteRepository {
    // TOOD
    public List<HenkiloOsoite> haeHenkiloOsoitteet(Long henkiloTunniste);

    // TODO
    public HenkiloOsoite tallenna(HenkiloOsoite henkiloosoite);
}
