package org.bgerp.plugin.custom.global;

import ru.bgcrm.model.BGMessageException;
import ru.bgcrm.util.ParameterMap;

public class Config extends ru.bgcrm.util.Config {

    private final int paramId;
    private final String chatId;

    protected Config(ParameterMap setup, boolean validate) throws BGMessageException {
        super(setup, validate);

        setup = setup.sub(Plugin.ID + ":");
        paramId = setup.getInt("paramId", -1);
        chatId = setup.get("chatId", "");

    }

    public int getParamId() {
        return paramId;
    }

    public String getChatId() {
        return chatId;
    }

}
