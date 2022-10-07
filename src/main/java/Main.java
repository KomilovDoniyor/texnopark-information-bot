import config.TexnoparkBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import service.StoreDataToDbFromJson;

import java.sql.SQLException;

/**
 * Author: komiloff_doniyor2505@gmail.com
 * Date:10/7/2022
 * Time:2:29 PM
 * Project Name:texnopark-information-bot
 */

public class Main {
    public static void main(String[] args) throws SQLException {

        StoreDataToDbFromJson.store();
        try {
            TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
            api.registerBot(new TexnoparkBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
