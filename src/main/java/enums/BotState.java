package enums;

public enum BotState {
    START,
    SHOW_CATEGORY,
    SHOW_SUBJECT,
    SELECT_SUBJECT,
    BACK;

    public static BotState fromString(String name){
        for (BotState value : BotState.values()) {
            if(value.name().equals(name)){
                return value;
            }
        }
        return null;
    }
}
