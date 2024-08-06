package fi.tman.henkilotietoja.sisaisia.henkilosuhde;

import fi.tman.henkilotietoja.sisaisia.yleiset.ListRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ListHenkilosuhdeRepository
        extends ListRepository<Henkilosuhde>
        implements HenkilosuhdeRepository {

    public ListHenkilosuhdeRepository() {
        this(new ArrayList<>());
    }

    public ListHenkilosuhdeRepository(List<Henkilosuhde> henkilosuhteet) {
        super(henkilosuhteet);
    }

    @Override
    public List<Henkilosuhde> haeHenkilosuhteet(Long tunniste) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Henkilosuhde tallenna(Henkilosuhde henkilosuhde) {
        throw new UnsupportedOperationException();
    }
}
