/**
 * Author: komiloff_doniyor2505@gmail.com
 * Date:10/7/2022
 * Time:2:31 PM
 * Project Name:texnopark-information-bot
 */
package config;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import util.BotSettings;

public class TexnoparkBot extends TelegramLongPollingBot {


    /**
     * TELEGRAM BOT CONFIGURATION METHOD
     * @param update
     */
    @Override
    public void onUpdateReceived(Update update) {

    }

    /**
     * BOT TOKEN
     * @return
     */
    @Override
    public String getBotToken() {
        return BotSettings.TOKEN;
    }

    /**
     * BOT USERNAME;
     * @return
     */
    @Override
    public String getBotUsername() {
        return BotSettings.USER_NAME;
    }
}
