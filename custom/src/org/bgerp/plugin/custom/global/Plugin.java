package org.bgerp.plugin.custom.global;

import ru.bgcrm.util.ParameterMap;


public class Plugin extends ru.bgcrm.plugin.Plugin {
    public static final String ID = "custom.global";

    public Plugin() {
        super(ID);
    }

    @Override
    public boolean isEnabled(ParameterMap config, String defaultValue) {
        return true;
    }

}
