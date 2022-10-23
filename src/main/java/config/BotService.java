/**
 * Author: komiloff_doniyor2505@gmail.com
 * Date:10/7/2022
 * Time:2:34 PM
 * Project Name:texnopark-information-bot
 */
package config;

import entity.Category;
import entity.Subject;
import entity.User;
import enums.BotState;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendLocation;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Location;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import service.CategoryService;
import service.SubjectService;
import service.UserService;
import service.impl.SubjectServiceImpl;
import service.impl.UserServiceImpl;
import service.impl.CategoryServiceImpl;
import util.BotConstans;
import util.BotMenu;
import util.SendPhotoDescription;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BotService {

    public static UserService userService = new UserServiceImpl();
    public static CategoryService categoryService = new CategoryServiceImpl();
    public static SubjectService subjectService = new SubjectServiceImpl();

    public static SendMessage start(Update update) throws SQLException {
        registerUser(update);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setParseMode(ParseMode.MARKDOWN);
        sendMessage.setChatId(update.getMessage().getChatId().toString());
        sendMessage.setText(BotConstans.MENE_HEADER);
        sendMessage.setReplyMarkup(getMenuKeyboard());
        return sendMessage;
    }

    private static ReplyKeyboard getMenuKeyboard() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        List<KeyboardRow> rows = new ArrayList<>();

        KeyboardRow keyboardRow1 = new KeyboardRow();
        keyboardRow1.add(new KeyboardButton(BotMenu.MENU_CATEGORY));
        keyboardRow1.add(new KeyboardButton(BotMenu.MENU_SUBJECT_INFO));
        rows.add(keyboardRow1);

        KeyboardRow keyboardRow2 = new KeyboardRow();
        keyboardRow2.add(new KeyboardButton(BotMenu.SETTINGS));
        keyboardRow2.add(new KeyboardButton(BotMenu.LOCATION));
        rows.add(keyboardRow2);

        replyKeyboardMarkup.setKeyboard(rows);
        return replyKeyboardMarkup;
    }

    private static void registerUser(Update update) throws SQLException {
        org.telegram.telegrambots.meta.api.objects.User from = update.getMessage().getFrom();
        if (!userService.exitsByChatId(update.getMessage().getChatId())) {
            entity.User user = new entity.User(
                    update.getMessage().getChatId(),
                    from.getFirstName(),
                    from.getLastName(),
                    from.getUserName(),
                    update.getMessage().getContact() != null ? update.getMessage().getContact().getPhoneNumber() : "",
                    BotState.START
            );
            userService.save(user);
        }

    }

    public static SendMessage categoryMenu(Long chatId) throws SQLException {
        User user = userService.findByChatId(chatId);
        if (user != null) {
            user.setBotState(BotState.SHOW_CATEGORY);
            userService.update(user);
        }

        SendMessage sendMessage = new SendMessage();
        sendMessage.setParseMode(ParseMode.MARKDOWN);
        sendMessage.setChatId(chatId.toString());
        sendMessage.setText(BotConstans.MENE_HEADER);

        List<Category> categoryList = categoryService.findAll();

        sendMessage.setReplyMarkup(getInlineKeyboards(categoryList));
        return sendMessage;
    }

    private static ReplyKeyboard getInlineKeyboards(List<Category> categoryList) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> inlineKeyboards = new ArrayList<>();
        List<InlineKeyboardButton> buttons;


        Iterator<Category> iterator = categoryList.iterator();
        while (iterator.hasNext()) {
            Category next = iterator.next();
            buttons = new ArrayList<>();
            InlineKeyboardButton button1 = new InlineKeyboardButton(next.getPrefix() + " " + next.getName());
            button1.setCallbackData("category/" + next.getId().toString());
            buttons.add(button1);

            if (iterator.hasNext()) {
                next = iterator.next();
                InlineKeyboardButton button2 = new InlineKeyboardButton(next.getPrefix() + " " + next.getName());
                button2.setCallbackData("category/" + next.getId().toString());
                buttons.add(button2);
            }

            inlineKeyboards.add(buttons);
        }


        inlineKeyboardMarkup.setKeyboard(inlineKeyboards);
        return inlineKeyboardMarkup;
    }

    public static EditMessageText subjectMenuByCategory(Message message, long categoryId) throws SQLException {
        User user = userService.findByChatId(message.getChatId());
        if (user != null) {
            user.setBotState(BotState.SHOW_SUBJECT);
            userService.update(user);
        }


        List<Subject> subjectList = subjectService.findAllCategoryId(categoryId);

        EditMessageText editMessageText = new EditMessageText();
        editMessageText.setChatId(message.getChatId());
        editMessageText.setMessageId(message.getMessageId());
        editMessageText.setParseMode(ParseMode.MARKDOWN);
        editMessageText.setText(BotConstans.MENE_HEADER);
        editMessageText.setReplyMarkup(getInlineKeyboardsSubject(subjectList));
        return editMessageText;
    }

    private static InlineKeyboardMarkup getInlineKeyboardsSubject(List<Subject> subjectList) {

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> inlineKeyboards = new ArrayList<>();
        List<InlineKeyboardButton> buttons;


        Iterator<Subject> iterator = subjectList.iterator();
        while (iterator.hasNext()) {
            Subject next = iterator.next();
            buttons = new ArrayList<>();
            InlineKeyboardButton button1 = new InlineKeyboardButton(next.getName());
            button1.setCallbackData("subject/" + next.getId().toString());
            buttons.add(button1);

            if (iterator.hasNext()) {
                next = iterator.next();
                InlineKeyboardButton button2 = new InlineKeyboardButton(next.getName());
                button2.setCallbackData("subject/" + next.getId().toString());
                buttons.add(button2);
            }

            inlineKeyboards.add(buttons);
        }

        InlineKeyboardButton button3 = new InlineKeyboardButton("Ma'lumot");
        button3.setCallbackData("information");
        buttons = new ArrayList<>();
        buttons.add(button3);
        inlineKeyboards.add(buttons);

        inlineKeyboardMarkup.setKeyboard(inlineKeyboards);
        return inlineKeyboardMarkup;
    }

    public static SendPhoto showSubjectInfo(Message message, long subjectId) throws SQLException {
        User user = userService.findByChatId(message.getChatId());
        if (user != null) {
            user.setBotState(BotState.SELECT_SUBJECT);
            userService.update(user);
        }
        Subject subject = subjectService.findById(subjectId);
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(message.getChatId());
        sendPhoto.setPhoto(new InputFile(subject.getImageUrl()));
        switch (subject.getName()) {
            case "Java":
                sendPhoto.setCaption(subject.getName() + "-" + SendPhotoDescription.JAVA_CORE_DESC);
                break;
            case "Java Spring":
                sendPhoto.setCaption(subject.getName() + "-" + SendPhotoDescription.JAVA_SPRING);
                break;
            case "Java Database":
                sendPhoto.setCaption(subject.getName() + "-" + SendPhotoDescription.JAVA_DATABASE);
                break;
            case "Postgresql":
                sendPhoto.setCaption(subject.getName() + "-" + SendPhotoDescription.POSTGRESQL);
                break;
        }
        sendPhoto.setReplyMarkup(getInlineKeyboardsBack());
        return sendPhoto;
    }

    private static ReplyKeyboard getInlineKeyboardsBack() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> inlineKeyboards = new ArrayList<>();
        List<InlineKeyboardButton> buttons = new ArrayList<>();

        String text = "ORQAGA\uD83D\uDD19";

        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(text);
        button.setCallbackData("back");

        buttons.add(button);

        inlineKeyboards.add(buttons);
        inlineKeyboardMarkup.setKeyboard(inlineKeyboards);
        return inlineKeyboardMarkup;
    }

    public static SendLocation location(Update update) {
        SendLocation sendLocation = new SendLocation();
        sendLocation.setChatId(update.getMessage().getChatId());
        sendLocation.setLongitude(59.60428023307573);
        sendLocation.setLatitude(42.457828167141926);
        return sendLocation;
    }

    public static SendMessage defaultInformation(Update update) {
        Long chatId = update.getMessage().getChatId();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setParseMode(ParseMode.MARKDOWN);
        sendMessage.setText(BotConstans.DEFAULT_INFORMATION);
        return sendMessage;
    }

    public static EditMessageText informationSubject(Message message) {
        EditMessageText editMessageText = new EditMessageText();
        editMessageText.setChatId(message.getChatId());
        editMessageText.setMessageId(message.getMessageId());
        editMessageText.setParseMode(ParseMode.MARKDOWN);
        editMessageText.setText(BotConstans.INFORMATION_SUBJECT);
        editMessageText.setReplyMarkup(getInlineKeyboardsInformation());

        return editMessageText;
    }

    private static InlineKeyboardMarkup getInlineKeyboardsInformation() {

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> inlineKeyboards = new ArrayList<>();
        List<InlineKeyboardButton> buttons = new ArrayList<>();

        String text = "ORQAGA\uD83D\uDD19";

        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(text);
        button.setCallbackData("course");

        buttons.add(button);

        inlineKeyboards.add(buttons);
        inlineKeyboardMarkup.setKeyboard(inlineKeyboards);
        return inlineKeyboardMarkup;
    }
}
