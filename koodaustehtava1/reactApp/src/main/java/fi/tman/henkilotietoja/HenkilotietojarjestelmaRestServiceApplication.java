package fi.tman.henkilotietoja;

import fi.tman.henkilotietoja.sisaisia.henkilo.HenkiloController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class HenkilotietojarjestelmaRestServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(HenkilotietojarjestelmaRestServiceApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/henkilot/**").allowedOrigins("http://localhost:3000");
                registry.addMapping("/henkiloosoitteet/**").allowedOrigins("http://localhost:3000");
                registry.addMapping("/henkilosuhteet/**").allowedOrigins("http://localhost:3000");
                registry.addMapping("/osoitteet/**").allowedOrigins("http://localhost:3000");
            }
        };
    }
}
