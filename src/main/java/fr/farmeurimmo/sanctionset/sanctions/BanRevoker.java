package main.java.fr.farmeurimmo.sanctionset.sanctions;

import main.java.fr.farmeurimmo.sanctionset.SanctionMain;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

public class BanRevoker {

    @SuppressWarnings("deprecation")
    public static void CheckForUnban() {
        for (String aa : SanctionMain.instance.getData().getConfigurationSection("").getKeys(false)) {
            if (SanctionMain.instance.getData().getBoolean(aa + ".tempban.istempbanned") == true) {
                if (SanctionMain.instance.getData().getLong(aa + ".tempban.timemillis") <= System.currentTimeMillis()) {
                    UnTempBan(aa, Bukkit.getConsoleSender());
                }
            }
            if (SanctionMain.instance.getData().getBoolean(aa + ".tempmute.istempmuted") == true) {
                if (SanctionMain.instance.getData().getLong(aa + ".tempmute.timemillis") <= System.currentTimeMillis()) {
                    MuteRevoker.revokepermamute(aa, Bukkit.getConsoleSender());
                }
            }
        }
        Bukkit.getServer().getScheduler().scheduleAsyncDelayedTask(SanctionMain.instance, new Runnable() {
            public void run() {
                CheckForUnban();
            }
        }, 20);
    }

    public static void UnTempBan(String aa, CommandSender sender) {
        if (SanctionMain.instance.getData().getBoolean(aa + ".ban.isbanned") == true ||
                SanctionMain.instance.getData().getBoolean(aa + ".tempban.istempbanned") == true
                || SanctionMain.instance.getData().getBoolean(aa + ".ban.isipbanned") == true) {
            SanctionMain.instance.getData().set(aa + ".tempban.banner", "");
            SanctionMain.instance.getData().set(aa + ".tempban.reason", "");
            SanctionMain.instance.getData().set(aa + ".tempban.date", "");
            SanctionMain.instance.getData().set(aa + ".tempban.expiration", "");
            SanctionMain.instance.getData().set(aa + ".tempban.duration", "");
            SanctionMain.instance.getData().set(aa + ".tempban.timemillis", "");
            SanctionMain.instance.getData().set(aa + ".tempban.istempbanned", false);

            SanctionMain.instance.getData().set(aa + ".ban.banner", "");
            SanctionMain.instance.getData().set(aa + ".ban.reason", "");
            SanctionMain.instance.getData().set(aa + ".ban.date", "");
            SanctionMain.instance.getData().set(aa + ".ban.isbanned", false);

            SanctionMain.instance.saveData();

            sender.sendMessage(SanctionMain.instance.Preffix +
                    SanctionMain.instance.getConfig().getString("SanctionSet.Settings.SuccefullyUnbanned").replace("&", "§")
                            .replace("%player%", aa));
        } else {
            sender.sendMessage(SanctionMain.instance.Preffix +
                    SanctionMain.instance.getConfig().getString("SanctionSet.Settings.NotBanned").replace("&", "§"));
        }
    }
}
