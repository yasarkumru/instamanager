package config;

import org.brunocvcunha.instagram4j.Instagram4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import com.polo.instamanager.telegram.TelegramBot;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author yasar
 */
@Configuration
@Slf4j
@RequiredArgsConstructor
public class Initializers {
    private final TelegramBot telegramBot;
    private final Instagram4j instagram4j;
    
    @Bean
    @Order(1)
    public CommandLineRunner initTelegramBot() {
        return args -> {
            TelegramBotsApi botsApi = new TelegramBotsApi();
            try {
                botsApi.registerBot(telegramBot);
                log.info("TelegramBot has been initialized");
            } catch (TelegramApiRequestException e) {
                log.error("Problem while initializing telegram bot",e);
            }
        };
    }
    
    @Bean
    @Order(2)
    public CommandLineRunner initInstagram4J() {
        return args -> {
            instagram4j.setup();
            instagram4j.login();
        };
    }
    
    
    @Bean
    public Instagram4j instagram4j() {
        return Instagram4j.builder().username("yasar.evliyaoglu").password("12788yasar").build();
    }
}
