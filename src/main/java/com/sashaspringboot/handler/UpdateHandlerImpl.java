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

import javax.annotation.PostConstruct;

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

	static {
		ApiContextInitializer.init();
	}


	@PostConstruct
	public void registerBot() {
		TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
		try {
			telegramBotsApi.registerBot(this);

		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}

	@Override
	public BotApiMethod onWebhookUpdateReceived(Update update) {
        LOG.info("$$$$$$$$$$$$$$$$ onWebhookUpdateReceived был выполнен {}", update);
		if (update.hasMessage() && update.getMessage().hasText()) {
			SendMessage sendMessage = new SendMessage();
			sendMessage.setChatId(update.getMessage().getChatId().toString());
			sendMessage.setText("Well, all information looks like noise until you break the code.");
			System.out.println(sendMessage.toString());
			try {
				execute(sendMessage);
                LOG.info("======================сообщение было послано telegram================ {}", sendMessage);
			} catch (TelegramApiException e) {
				e.printStackTrace();
			}
			return sendMessage;
		}
		return null;
	}

	@Override
	public String getBotUsername() {
		return "sashahookexample_bot";
	}


	@Override
	public BotApiMethod handleUpdate(Update update) {
   return onWebhookUpdateReceived(update);
	}

	@Override
	public String getBotToken() {
		return "593968163:AAFtjb0uYLn44OozyBiBhtGd-rE9g2yBo9Q";
	}

	@Override
	public String getBotPath() {
        LOG.info("-------------GetBotPathExecuted--------------");
		return "https://webhooktryouts.appspot.com/mybotwebhook";
	}
}




