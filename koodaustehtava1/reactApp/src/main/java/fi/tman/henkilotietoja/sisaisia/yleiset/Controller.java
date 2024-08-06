package fi.tman.henkilotietoja.sisaisia.yleiset;

import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public abstract class Controller {
    public ResponseEntity<Void> olioSijainnilla(Long tunniste) {
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{tunniste}")
                .buildAndExpand(tunniste)
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
