package fi.tman.henkilotietoja.sisaisia.osoite;

import fi.tman.henkilotietoja.sisaisia.henkilo.Henkilo;
import fi.tman.henkilotietoja.sisaisia.yleiset.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// TODO: PUT puuttuu

@RestController
public class OsoiteController
        extends Controller {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final OsoiteRepository osoiteRepository;

    @Autowired
    public OsoiteController(OsoiteRepository osoiteRepository) {
        this.osoiteRepository = osoiteRepository;

        this.osoiteRepository.tallenna(new Osoite("Katuosoite", "Postinumero", "Postitoimipaikka"));
    }

    @GetMapping(value = "/osoitteet")
    public List<Osoite> haeOsoitteet() {
        return this.osoiteRepository.haeOsoitteet();
    }

    // TODO
    @GetMapping(value = "/osoitteet/{tunniste}")
    public Osoite haeOsoite(@PathVariable Long tunniste) {
        return this.osoiteRepository.haeOsoite(tunniste);
    }

    // TODO
    @GetMapping(value = "/osoitteet/{katu}/{postinumero}/{postitoimipaikka}")
    public Osoite haeOsoite(@PathVariable String katu, @PathVariable String postinumero, @PathVariable String postitoimipaikka) {
        return this.osoiteRepository.haeOsoite(katu, postinumero, postitoimipaikka);
    }

    // TODO
    @PostMapping(value = "/osoitteet")
    public ResponseEntity<Void> lisaaOsoite(@RequestBody Osoite uusiOsoite) {
        Osoite osoite = this.osoiteRepository.tallenna(uusiOsoite);
        return olioSijainnilla(osoite.getTunniste());
    }

    // TODO
    @PutMapping(value = "/osoitteet/{tunniste}")
    public Osoite paivitaOsoite(@RequestBody Osoite uusiOsoite,
                                  @PathVariable Long tunniste) {
        return Optional.ofNullable(osoiteRepository.haeOsoite(tunniste))
            .map(osoite -> {
                osoite.setKatuosoite(uusiOsoite.getKatuosoite());
                osoite.setPostinumero(uusiOsoite.getPostinumero());
                osoite.setPostitoimipaikka(uusiOsoite.getPostitoimipaikka());

                return osoiteRepository.tallenna(osoite);
            })
            .orElseGet(() -> {
                return osoiteRepository.tallenna(uusiOsoite);
            });
    }
}
