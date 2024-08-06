package fi.tman.henkilotietoja.sisaisia.henkilo;

import fi.tman.henkilotietoja.sisaisia.henkiloosoite.HenkiloOsoite;
import fi.tman.henkilotietoja.sisaisia.henkiloosoite.HenkiloOsoiteRepository;
import fi.tman.henkilotietoja.sisaisia.henkilosuhde.Henkilosuhde;
import fi.tman.henkilotietoja.sisaisia.henkilosuhde.HenkilosuhdeRepository;
import fi.tman.henkilotietoja.sisaisia.yleiset.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class HenkiloController
        extends Controller {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final HenkiloRepository henkiloRepository;
    private final HenkiloOsoiteRepository henkiloOsoiteRepository;
    private final HenkilosuhdeRepository henkilosuhdeRepository;

    @Autowired
    public HenkiloController(HenkiloRepository henkiloRepository,
                             HenkiloOsoiteRepository henkiloOsoiteRepository,
                             HenkilosuhdeRepository henkilosuhdeRepository) {
        this.henkiloRepository = henkiloRepository;
        this.henkiloOsoiteRepository = henkiloOsoiteRepository;
        this.henkilosuhdeRepository = henkilosuhdeRepository;
    }

    // TODO
    @GetMapping(value = "/henkilot/{tunniste}")
    public Henkilo haeHenkilo(@PathVariable Long tunniste) {
        Henkilo henkilo = this.henkiloRepository.haeTunnisteella(tunniste);
        if (henkilo == null) {
            throw new IllegalArgumentException("Henkilöa ei löytynyt tunnisteella " + tunniste);
        }
        return henkilo;
    }

    // TODO
    @GetMapping(value = "/henkilot/{sosiaaliturvatunnus}")
    public Henkilo haeHenkilo(@PathVariable String sosiaaliturvatunnus) {
        Henkilo henkilo = this.henkiloRepository.haeSosiaaliturvatunnuksella(sosiaaliturvatunnus);
        if (henkilo == null) {
            throw new IllegalArgumentException("Henkilöa ei löytynyt sosiaaliturvatunnuksella " + sosiaaliturvatunnus);
        }
        return henkilo;
    }

    // TODO
    @PostMapping(value = "/henkilot")
    public ResponseEntity<Void> lisaaHenkilo(@RequestBody Henkilo uusiHenkilo) {
        Henkilo henkilo = henkiloRepository.tallenna(uusiHenkilo);
        return olioSijainnilla(henkilo.getTunniste());
    }

    // TODO
    @PutMapping(value = "/henkilot/{tunniste}")
    public Henkilo paivitaHenkilo(@RequestBody Henkilo uusiHenkilo,
                                  @PathVariable Long tunniste) {
            return Optional.ofNullable(henkiloRepository.haeTunnisteella(tunniste))
                .map(henkilo -> {
                    henkilo.setEtunimet(uusiHenkilo.getEtunimet());
                    henkilo.setSukunimi(uusiHenkilo.getSukunimi());
                    henkilo.setKuollut(uusiHenkilo.getKuollut());
                    henkilo.setSahkopostiosoite(uusiHenkilo.getSahkopostiosoite());
                    henkilo.setPuhelinnumero(uusiHenkilo.getPuhelinnumero());

                    return henkiloRepository.tallenna(henkilo);
            })
            .orElseGet(() -> {
                return henkiloRepository.tallenna(uusiHenkilo);
            });
    }

    // TODO
    @GetMapping(value = "/henkilot/{tunniste}/osoitteet")
    public List<HenkiloOsoite> haeHenkilonHenkiloOsoitteet(@PathVariable("tunniste") Long tunniste) {
        return this.henkiloOsoiteRepository.haeHenkiloOsoitteet(tunniste);
    }

    // TODO
    @GetMapping(value = "/henkilot/henkilosuhdetyypit")
    public List<Henkilosuhde.Tyyppi> haeHenkilosuhdetyypit() {
        return henkiloRepository.haeHenkilosuhdetyypit();
    }

    // TODO
    @GetMapping(value = "/henkilot/{tunniste}/suhteet")
    public List<Henkilosuhde> haeHenkilonSuhteet(@PathVariable("tunniste") Long tunniste) {
        return henkilosuhdeRepository.haeHenkilosuhteet(tunniste);
    }
}
