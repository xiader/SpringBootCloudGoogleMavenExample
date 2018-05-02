package com.sashaspringboot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import javax.annotation.PostConstruct;

@Component
public class Bot extends TelegramLongPollingBot {

    @Value("${bot.token}")
    private String TOKEN;

    @Value("${bot.username}")
    private String USERNAME;

    static {
        ApiContextInitializer.init();
    }


    @PostConstruct
    public void registerBot() {
        TelegramBotsApi botapi = new TelegramBotsApi();
        try {
            botapi.registerBot(this);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public Bot() {
    }

    public Bot(DefaultBotOptions options) {
        super(options);
    }

    @Override
    public String getBotToken() {
        return TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {
// We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
                    .setChatId(update.getMessage().getChatId())
                    .setText(update.getMessage().getText());
            try {
                execute(message); // Call method to send the message
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public String getBotUsername() {
        return USERNAME;
    }

    /*@Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            processCommand(update);
        } else if (update.hasCallbackQuery()) {
            processCallbackQuery(update);
        }
    }*/
}

