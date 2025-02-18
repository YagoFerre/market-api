package yago.ferreira.marketapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import yago.ferreira.marketapi.infra.configuration.storage.FileStorageProperties;

@SpringBootApplication
@EnableConfigurationProperties(FileStorageProperties.class)
@ComponentScan({
        "yago.ferreira.marketapi.domain.port",
        "yago.ferreira.marketapi.application.service",
        "yago.ferreira.marketapi.adapters",
        "yago.ferreira.marketapi.adapters.out.encoder"
})
public class MarketApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(MarketApiApplication.class, args);
    }
}
