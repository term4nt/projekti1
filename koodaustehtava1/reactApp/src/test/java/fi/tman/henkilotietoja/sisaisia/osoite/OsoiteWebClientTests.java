package fi.tman.henkilotietoja.sisaisia.osoite;

import fi.tman.henkilotietoja.HenkilotietojarjestelmaRestServiceApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = HenkilotietojarjestelmaRestServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OsoiteWebClientTests {

    private static final String BASE_URL = "http://localhost:";

    private Random random = new Random();
    private WebClient webClient;

    @BeforeEach
    void setUp() {
        webClient = WebClient.create(BASE_URL + 8080);
    }

    @Test
    public void testListaaOsoitteet_WebClient_retrieve_blocking() {

        Osoite[] osoitteet = webClient.get()
                .uri("/osoitteet")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Osoite[].class)
                .block();

        assertEquals(1, osoitteet.length);
        assertEquals("Katuosoite", osoitteet[0].getKatuosoite());
        assertEquals("Postinumero", osoitteet[0].getPostinumero());
        assertEquals("Postitoimipaikka", osoitteet[0].getPostitoimipaikka());
    }

    @Test
    public void testListaaOsoitteet_WebClient_exchange_blocking() {

        Osoite[] osoitteet = webClient.get()
                .uri("/osoitteet")
                .accept(MediaType.APPLICATION_JSON)
                .exchangeToMono(response -> response.bodyToMono(Osoite[].class))
                .block();

        assertEquals(1, osoitteet.length);
        assertEquals("Katuosoite", osoitteet[0].getKatuosoite());
        assertEquals("Postinumero", osoitteet[0].getPostinumero());
        assertEquals("Postitoimipaikka", osoitteet[0].getPostitoimipaikka());
    }

    @Test
    public void testHaeOsoite_WebClient_retrieve_blocking() {

        Osoite osoite = webClient.get()
                .uri("/osoitteet/{id}", 1)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Osoite.class)
                .block();

        assertNotNull(osoite);
        assertEquals("Katuosoite", osoite.getKatuosoite());
        assertEquals("Postinumero", osoite.getPostinumero());
        assertEquals("Postitoimipaikka", osoite.getPostitoimipaikka());
    }

    @Test
    public void testHaeOsoite_WebClient_exchange_blocking() {

        Osoite osoite = webClient.get()
                .uri("/osoitteet/{id}", 1)
                .accept(MediaType.APPLICATION_JSON)
                .exchangeToMono(response -> response.bodyToMono(Osoite.class))
                .block();

        assertNotNull(osoite);
        assertEquals("Katuosoite", osoite.getKatuosoite());
        assertEquals("Postinumero", osoite.getPostinumero());
        assertEquals("Postitoimipaikka", osoite.getPostitoimipaikka());
    }

    @Test
    public void testLisaaOsoite_WebClient_blocking() throws Exception {

        Osoite osoite = new Osoite("Katuosoite1", "Postinumero1", "Postitoimipaikka1");

        ClientResponse clientResponse = webClient.post()
                .uri("/osoitteet")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(osoite)
                .exchangeToMono(response -> Mono.just(response))
                .block();

        URI newAccountLocation = new URI(clientResponse.headers().header("Location").get(0));

        Osoite noudettuOsoite = webClient.get()
                .uri(newAccountLocation)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Osoite.class)
                .block();

        assertNotNull(noudettuOsoite.getTunniste());
    }


    @Test
    public void testLisaaSamaOsoiteKahdestiTuottaa409_WebClient_blocking() {
        Osoite osoite = new Osoite("Katuosoite1", "Postinumero1", "Postitoimipaikka1");

        ClientResponse clientResponse = webClient.post()
                .uri("/osoitteet")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(osoite)
                .exchangeToMono(response -> Mono.just(response))
                .block();

        assertEquals(HttpStatus.CREATED, clientResponse.statusCode());
    }
}
