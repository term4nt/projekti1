package fi.tman.henkilotietoja.sisaisia.henkiloosoite;

import fi.tman.henkilotietoja.sisaisia.yleiset.ListRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ListHenkiloOsoiteRepository
        extends ListRepository<HenkiloOsoite>
        implements HenkiloOsoiteRepository {

    public ListHenkiloOsoiteRepository() {
        this(new ArrayList<>());
    }

    public ListHenkiloOsoiteRepository(List<HenkiloOsoite> henkiloOsoitteet) {
        super(henkiloOsoitteet);
    }

    @Override
    public List<HenkiloOsoite> haeHenkiloOsoitteet(Long henkiloTunniste) {
        return List.of();
    }

    @Override
    public HenkiloOsoite tallenna(HenkiloOsoite henkiloosoite) {
        return null;
    }
}
