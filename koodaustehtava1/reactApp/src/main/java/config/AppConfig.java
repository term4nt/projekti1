package config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class AppConfig {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    JdbcTemplate jdbcTemplate;

    @Autowired  // Optional, Spring will autowire DataSource anyway
    public AppConfig(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

//    @Bean
//    public HenkiloRepository henkiloRepository() {
//        return new JdbcHenkiloRepository(jdbcTemplate);
//    }

//    @Bean
//    public HenkiloOsoiteRepository henkiloOsoiteRepository() {
//        return new JdbcHenkiloOsoiteRepository(jdbcTemplate);
//    }

//   @Bean
//public HenkilosuhdeRepository HenkilosuhdeRepository() {
//    return new JdbcHenkilosuhdeRepository(jdbcTemplate);
//}

//@Bean
//public OsoiteRepository osoiteRepository() {
//    return new JdbcOsoiteRepository(jdbcTemplate);
//}
}
