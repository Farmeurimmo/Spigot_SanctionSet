package main.java.fr.farmeurimmo.sanctionset.sanctions;

import main.java.fr.farmeurimmo.sanctionset.SanctionMain;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

public class MuteRevoker {

    public static void revokepermamute(String aa, CommandSender sender) {
        if (SanctionMain.instance.getData().getBoolean(aa + ".mute.ismuted") == true ||
                SanctionMain.instance.getData().getBoolean(aa + ".tempmute.istempmuted") == true) {
            SanctionMain.instance.getData().set(aa + ".mute.banner", "");
            SanctionMain.instance.getData().set(aa + ".mute.reason", "");
            SanctionMain.instance.getData().set(aa + ".mute.ismuted", false);

            SanctionMain.instance.getData().set(aa + ".tempmute.banner", "");
            SanctionMain.instance.getData().set(aa + ".tempmute.reason", "");
            SanctionMain.instance.getData().set(aa + ".tempmute.timemillis", "");
            SanctionMain.instance.getData().set(aa + ".tempmute.duration", "");
            SanctionMain.instance.getData().set(aa + ".tempmute.istempmuted", false);

            sender.sendMessage(SanctionMain.instance.Preffix +
                    SanctionMain.instance.getConfig().getString("SanctionSet.Settings.SuccefullyUnmuted").replace("&", "§")
                            .replace("%player%", aa));

            if (Bukkit.getPlayer(aa) != null) {
                Bukkit.getPlayer(aa).sendMessage(SanctionMain.instance.Preffix +
                        SanctionMain.instance.getConfig().getString("SanctionSet.Settings.MuteEnded").replace("&", "§"));
            }

            SanctionMain.instance.saveData();
        } else {
            sender.sendMessage(SanctionMain.instance.Preffix +
                    SanctionMain.instance.getConfig().getString("SanctionSet.Settings.NotMuted").replace("&", "§"));
        }
    }
}
