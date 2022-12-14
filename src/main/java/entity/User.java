/**
 * Author: komiloff_doniyor2505@gmail.com
 * Date:10/7/2022
 * Time:2:06 PM
 * Project Name:texnopark-information-bot
 */
package entity;

import enums.BotState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long id;
    private Long chatId;
    private String firstname;
    private String lastname;
    private String username;
    private String phoneNumber;
    private BotState botState;

    public User(Long chatId, String firstname, String lastname, String username, String phoneNumber, BotState botState) {
        this.chatId = chatId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.botState = botState;
    }
}
