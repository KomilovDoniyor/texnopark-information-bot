/**
 * Author: komiloff_doniyor2505@gmail.com
 * Date:10/7/2022
 * Time:2:06 PM
 * Project Name:texnopark-information-bot
 */
package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectInfo {
    private Long id;
    private String name;
    private String imageUrl;
    private String description;
}
