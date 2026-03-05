package pl.kamil.dreamanddoapi;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.postgresql.PostgreSQLContainer;

public class TestcontainersInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    public static PostgreSQLContainer postgres = new PostgreSQLContainer("postgres:18.2-alpine")
            .withDatabaseName("integration-tests-db")
            .withUsername("postgres")
            .withPassword("postgres")
            .withInitScripts("initial.sql", "dreams.sql", "add_unique_on_dream_title.sql");

    @Override
    public void initialize(ConfigurableApplicationContext ctx) {
        TestPropertyValues.of(
                "spring.datasource.url=" + postgres.getJdbcUrl(),
                "spring.datasource.username=" + postgres.getUsername(),
                "spring.datasource.password=" + postgres.getPassword()
        ).applyTo(ctx.getEnvironment());
    }

}
