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

}
