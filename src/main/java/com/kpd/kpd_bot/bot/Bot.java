package com.kpd.kpd_bot.bot;

import com.kpd.kpd_bot.config.BotConfig;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


@Component
@AllArgsConstructor
public class Bot extends TelegramLongPollingBot {


	private BotConfig botConfig;
	private MessageHandler messageHandler;

	@Override
	public String getBotToken() {
		return botConfig.getToken();
	}

	@Override
	public void onUpdateReceived(Update update) {
		if (update.hasMessage() && update.getMessage().hasText()) {
			MessageAdapter message = messageHandler.handleMessage(update);
			try {
				execute(message.getSendMessage());
			} catch (TelegramApiException e) {
				throw new RuntimeException(e);
			}
		}
		else if (update.hasCallbackQuery()) {

		}
	}

	@Override
	public String getBotUsername() {
		return botConfig.getUsernameBot();
	}

}
