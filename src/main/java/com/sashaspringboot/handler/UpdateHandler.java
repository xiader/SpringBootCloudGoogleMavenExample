package com.sashaspringboot.handler;


import org.telegram.telegrambots.api.methods.BotApiMethod;
import org.telegram.telegrambots.api.objects.Update;

public interface UpdateHandler {

	BotApiMethod handleUpdate(Update update);
}
