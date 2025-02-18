package yago.ferreira.marketapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import yago.ferreira.marketapi.infra.configuration.storage.FileStorageProperties;

@SpringBootApplication
@EnableConfigurationProperties(FileStorageProperties.class)
public class MarketApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(MarketApiApplication.class, args);
    }
}
