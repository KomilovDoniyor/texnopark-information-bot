/**
 * Author: komiloff_doniyor2505@gmail.com
 * Date:10/7/2022
 * Time:2:31 PM
 * Project Name:texnopark-information-bot
 */
package config;

import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import util.BotMenu;
import util.BotSettings;

import java.sql.SQLException;

public class TexnoparkBot extends TelegramLongPollingBot {


    /**
     * TELEGRAM BOT CONFIGURATION METHOD
     *
     * @param update
     */
    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage()) {
            Message message = update.getMessage();
            SendMessage sendMessage = new SendMessage();


            if (message.hasText()) {
                String text = message.getText();
                switch (text) {
                    case BotMenu.START:
                        try {
                            sendMessage = BotService.start(update);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        break;
                    case BotMenu.MENU_CATEGORY:
                        try {
                            sendMessage = BotService.categoryMenu(message.getChatId());
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        break;
                }
            }
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }


        } else if (update.hasCallbackQuery()) {
            String data = update.getCallbackQuery().getData();
            Message message = update.getCallbackQuery().getMessage();
            EditMessageText editMessageText;

            if (data.contains("category")) {
                long categoryId = Long.parseLong(data.substring(9).trim());
                editMessageText = BotService.subjectMenuByCategory(message, categoryId);
                try {
                    execute(editMessageText);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else if (data.contains("subject")) {
                long subjectId = Long.parseLong(data.substring(8).trim());
                SendPhoto sendPhoto = BotService.showSubjectInfo(message, subjectId);
                try {
                    execute(sendPhoto);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * BOT TOKEN
     *
     * @return
     */
    @Override
    public String getBotToken() {
        return BotSettings.TOKEN;
    }

    /**
     * BOT USERNAME;
     *
     * @return
     */
    @Override
    public String getBotUsername() {
        return BotSettings.USER_NAME;
    }
}
