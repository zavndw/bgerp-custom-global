package org.bgerp.plugin.custom.global;

import org.bgerp.plugin.telegram.bot.BgerpBot;

import ru.bgcrm.util.Setup;
import ru.bgerp.util.Log;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class TelegramGlobalNotifications implements Runnable {

    private static final Log log = Log.getLog();
    String description = "";
    Date currentDate = new Date();
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public void run() {

        Config config = Setup.getSetup().getConfig(Config.class);

        try {
            Connection con = Setup.getSetup().getDBConnectionFromPool();

            String queryProcess = "SELECT pr.description AS description, pdate.id AS id, pdate.value AS value FROM param_date AS pdate " +
                    "LEFT JOIN process AS pr on pr.id=pdate.id " +
                    "WHERE pdate.param_id=" + config.getParamId();
            log.debug("sql: " + queryProcess);

            PreparedStatement ps = con.prepareStatement(queryProcess);
            ResultSet rs = ps.executeQuery(queryProcess);


            while (rs.next()) {
                int processId = rs.getInt("id");
                description = rs.getString("description");
                Date last = rs.getDate("value");
                log.info("last:" + last.toString() + ", currentDate:" + currentDate.toString());
                if (df.format(currentDate).equalsIgnoreCase(last.toString())) {
                    BgerpBot bot = BgerpBot.getInstance();
                    bot.sendMessage(Long.valueOf(config.getChatId()), "Задача #" + processId + " - наступил назначенный срок" + System.lineSeparator() + "Описание: " + description);
                }
            }
            rs.close();
            ps.close();


        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

    }
}
