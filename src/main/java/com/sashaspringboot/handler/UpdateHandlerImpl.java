package com.sashaspringboot.handler;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import org.telegram.telegrambots.*;

import org.telegram.telegrambots.api.methods.BotApiMethod;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class UpdateHandlerImpl extends TelegramWebhookBot implements UpdateHandler {

	private Logger LOG = LoggerFactory.getLogger(UpdateHandlerImpl.class);


//	@Value("${bot.token}")
//	private String TOKEN;
//
//	@Value("${bot.username}")
//	private String USERNAME;
//
//	@Value("${bot.webhook_user}")
//	private String WEBHOOK_USER;

	@Override
	public BotApiMethod onWebhookUpdateReceived(Update update) {
		if (update.hasMessage() && update.getMessage().hasText()) {
			SendMessage sendMessage = new SendMessage();
			sendMessage.setChatId(update.getMessage().getChatId().toString());
			sendMessage.setText("Well, all information looks like noise until you break the code.");
			System.out.println(sendMessage.toString());
			return sendMessage;
		}
		return null;
	}

	@Override
	public String getBotUsername() {
		return "sashahookexample_bot";
	}


	@Override
	public void handleUpdate(Update update) {
    onWebhookUpdateReceived(update);
	}

	@Override
	public String getBotToken() {
		return "593968163:AAFtjb0uYLn44OozyBiBhtGd-rE9g2yBo9Q";
	}

	@Override
	public String getBotPath() {
		return "https://webhooktryouts.appspot.com/mybotwebhook";
	}
}




