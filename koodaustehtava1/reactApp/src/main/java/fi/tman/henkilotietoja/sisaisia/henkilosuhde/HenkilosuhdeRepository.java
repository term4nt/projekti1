package fi.tman.henkilotietoja.sisaisia.henkilosuhde;

import java.util.List;

public interface HenkilosuhdeRepository {

    // TODO
    public List<Henkilosuhde> haeHenkilosuhteet(Long tunniste);

    // TODO
    public Henkilosuhde tallenna(Henkilosuhde henkilosuhde);
}
