package com.sashaspringboot.handler;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;

import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.*;

import org.telegram.telegrambots.api.methods.BotApiMethod;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;

@Component
public class UpdateHandlerImpl extends TelegramWebhookBot implements UpdateHandler {

	//private Logger LOG = LoggerFactory.getLogger(UpdateHandlerImpl.class);


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
      //  LOG.debug("$$$$$$$$$$$$$$$$ onWebhookUpdateReceived был выполнен {}", update);
        System.out.println(update);

        if (update.hasMessage() && update.getMessage().hasText()) {
			SendMessage sendMessage = new SendMessage();
			sendMessage.setChatId(update.getMessage().getChatId().toString());
			sendMessage.setText("Well, all information looks like noise until you break the code.");
			System.out.println(sendMessage.toString());
			try {
			//	sendMessage()
				//sendApiMethod(sendMessage);
				//HttpHeaders headers = new HttpHeaders();
				//headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
				HttpEntity<?> entity = new HttpEntity<>(sendMessage/*, headers*/);
				RestTemplate restTemplate = new RestTemplate();
				StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter(StandardCharsets.UTF_8);
				stringHttpMessageConverter.setWriteAcceptCharset(false);
				restTemplate.getMessageConverters().add(0, stringHttpMessageConverter);
				restTemplate.postForEntity("https://api.telegram.org/bot593968163:AAFtjb0uYLn44OozyBiBhtGd-rE9g2yBo9Q/sendmessage", entity, String.class);
				//execute(sendMessage);
               // LOG.debug("======================сообщение было послано telegram================ {}", sendMessage);
                System.out.println("сообщение было послано teleg" +sendMessage);
			} catch (Exception e) {
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
      //  LOG.debug("-------------GetBotPathExecuted--------------");
        System.out.println("---------GetBotPathExecuted----");
		return "sashahookexample";
	}
}




