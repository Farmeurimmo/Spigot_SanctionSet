package main.java.fr.farmeurimmo.sanctionset.utils;

import main.java.fr.farmeurimmo.sanctionset.SanctionMain;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeConverter {

    public static String getFormatTimeWithTZ(Date currentTime) {
        SimpleDateFormat timeZoneDate = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy", Locale.FRANCE);
        return timeZoneDate.format(currentTime);
    }

    public static String replaceArgs(String toReplace, String duration, String type, String player, String sender, String reason) {
        toReplace = toReplace.replaceAll("%duration%", duration + " " + type.replaceAll("sec",
                                SanctionMain.instance.getConfig().getString("SanctionSet.Settings.Seconds").replaceAll("&", "§"))
                        .replaceAll("min",
                                SanctionMain.instance.getConfig().getString("SanctionSet.Settings.Minutes").replaceAll("&", "§"))
                        .replaceAll("hour",
                                SanctionMain.instance.getConfig().getString("SanctionSet.Settings.Hours").replaceAll("&", "§"))
                        .replaceAll("day",
                                SanctionMain.instance.getConfig().getString("SanctionSet.Settings.Days").replaceAll("&", "§"))
                        .replaceAll("year",
                                SanctionMain.instance.getConfig().getString("SanctionSet.Settings.Years").replaceAll("&", "§")))
                .replaceAll("&", "§")
                .replaceAll("%player%", player).replaceAll("%banner%", sender.trim())
                .replaceAll("%reason%", reason.trim());
        return toReplace;
    }
}
