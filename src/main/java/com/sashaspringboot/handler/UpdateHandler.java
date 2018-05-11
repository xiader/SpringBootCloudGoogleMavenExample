package com.sashaspringboot.handler;


import org.telegram.telegrambots.api.objects.Update;

public interface UpdateHandler {

	void handleUpdate(Update update);
}
