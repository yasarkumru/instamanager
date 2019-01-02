package com.polo.instamanager;

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
    
    @Bean
    @Order(1)
    public CommandLineRunner commandLineRunner() {
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
}
