package com.polo.instamanager.telegram;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author yasar
 */
@Component
@Slf4j
public class TelegramBot extends TelegramLongPollingBot{
    
   
    
    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        DeleteMessage deleteMessage = new DeleteMessage(message.getChatId(), message.getMessageId());
        SendMessage sendMessage = new SendMessage(message.getChatId(), message.getText());
        
        try {
            execute(sendMessage);
            execute(deleteMessage);
        } catch (TelegramApiException e) {
            log.error("error",e);
        }
    
    }

    @Override
    public String getBotUsername() {
        return "instamanage";
    }

    @Override
    public String getBotToken() {
        return "669414873:AAE1n6ixOsPPab52sW8I6wySCs63y5L0p4c";
    }
    
    

}
