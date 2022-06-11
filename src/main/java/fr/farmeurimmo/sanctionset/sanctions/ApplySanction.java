package main.java.fr.farmeurimmo.sanctionset.sanctions;

import main.java.fr.farmeurimmo.sanctionset.SanctionMain;
import main.java.fr.farmeurimmo.sanctionset.utils.TimeConverter;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ApplySanction {

    public static ApplySanction instance;

    public ApplySanction() {
        instance = this;
    }

    public void ApplyPermaBan(Player player, String reason, String banner, String string) {
        SanctionMain.instance.getData().set(player.getName() + ".ban.banner", banner);
        SanctionMain.instance.getData().set(player.getName() + ".ban.reason", reason);
        SanctionMain.instance.getData().set(player.getName() + ".ban.date", string);
        SanctionMain.instance.getData().set(player.getName() + ".ban.isbanned", true);
        SanctionMain.instance.saveData();
        Bukkit.broadcastMessage(TimeConverter.replaceArgs(SanctionMain.instance.Preffix
                        + SanctionMain.instance.getConfig().getString("SanctionSet.Settings.PlayerGotPermaBan"),
                "null", "null", player.getName(), banner, reason));
    }

    public void ApplyPermaBanIp(Player player, String reason, String banner, String string) {
        SanctionMain.instance.getData().set(player.getName() + ".ban-ip.banner", banner);
        SanctionMain.instance.getData().set(player.getName() + ".ban-ip.reason", reason);
        SanctionMain.instance.getData().set(player.getName() + ".ban-ip.date", string);
        String ip = player.getAddress().getHostString();
        String partialIp = ip.substring(0, ip.lastIndexOf("."));
        SanctionMain.instance.ipblocked.put(partialIp, player.getName());
        SanctionMain.instance.getData().set(player.getName() + ".ban-ip.ip", partialIp);
        SanctionMain.instance.getData().set(player.getName() + ".ban-ip.isipbanned", true);
        SanctionMain.instance.saveData();
        Bukkit.broadcastMessage(TimeConverter.replaceArgs(SanctionMain.instance.Preffix
                        + SanctionMain.instance.getConfig().getString("SanctionSet.Settings.PlayerGotPermaBanIp"),
                "null", "null", player.getName(), banner, reason));
    }

    public void ApplyTempBan(String player, String reason, CommandSender sender, String string, String Expiration, String duration, String type) {
        if (!SanctionMain.instance.getData().getBoolean(player + ".mute.isbanned")) {
            SanctionMain.instance.getData().set(player + ".tempban.banner", sender.getName());
            SanctionMain.instance.getData().set(player + ".tempban.reason", reason);
            SanctionMain.instance.getData().set(player + ".tempban.date", string);
            SanctionMain.instance.getData().set(player + ".tempban.expiration", Expiration);
            SanctionMain.instance.getData().set(player + ".tempban.unit", type);
            long timemillis = System.currentTimeMillis();
            if (type.equalsIgnoreCase("sec")) {
                timemillis = timemillis + Integer.parseInt(duration) * 1000L;
            }
            if (type.equalsIgnoreCase("min")) {
                timemillis = timemillis + Integer.parseInt(duration) * 60000L;
            }
            if (type.equalsIgnoreCase("hour")) {
                timemillis = timemillis + Integer.parseInt(duration) * 360000L;
            }
            if (type.equalsIgnoreCase("day")) {
                timemillis = timemillis + Integer.parseInt(duration) * 86400000L;
            }
            if (type.equalsIgnoreCase("year")) {
                timemillis = timemillis + (long) Integer.parseInt(duration) * 31536000 * 100;
            }
            SanctionMain.instance.getData().set(player + ".tempban.duration", duration);
            SanctionMain.instance.getData().set(player + ".tempban.timemillis", timemillis);
            SanctionMain.instance.getData().set(player + ".tempban.istempbanned", true);
            SanctionMain.instance.saveData();
            Bukkit.broadcastMessage(TimeConverter.replaceArgs(SanctionMain.instance.Preffix
                            + SanctionMain.instance.getConfig().getString("SanctionSet.Settings.PlayerGotTempBan"),
                    duration, type, player, sender.getName(), reason));
        } else {
            sender.sendMessage(SanctionMain.instance.Preffix +
                    SanctionMain.instance.getConfig().getString("SanctionSet.Settings.AlreadyBanned").replace("&", "§"));
        }
    }

    public void ApplyTempMute(String player, String reason, CommandSender sender, String duration, String type) {
        if (!SanctionMain.instance.getData().getBoolean(player + ".mute.ismuted")) {
            SanctionMain.instance.getData().set(player + ".tempmute.banner", sender.getName());
            SanctionMain.instance.getData().set(player + ".tempmute.reason", reason);
            SanctionMain.instance.getData().set(player + ".tempmute.duration", duration);
            SanctionMain.instance.getData().set(player + ".tempmute.unit", type);
            long timemillis = System.currentTimeMillis();
            if (type.equalsIgnoreCase("sec")) {
                timemillis = timemillis + Integer.parseInt(duration) * 1000L;
            }
            if (type.equalsIgnoreCase("min")) {
                timemillis = timemillis + Integer.parseInt(duration) * 60000L;
            }
            if (type.equalsIgnoreCase("hour")) {
                timemillis = timemillis + Integer.parseInt(duration) * 360000L;
            }
            if (type.equalsIgnoreCase("day")) {
                timemillis = timemillis + Integer.parseInt(duration) * 86400000L;
            }
            if (type.equalsIgnoreCase("year")) {
                timemillis = timemillis + (long) Integer.parseInt(duration) * 31536000 * 100;
            }
            SanctionMain.instance.getData().set(player + ".tempmute.timemillis", timemillis);
            SanctionMain.instance.getData().set(player + ".tempmute.istempmuted", true);
            SanctionMain.instance.saveData();
            Bukkit.getPlayer(player).sendMessage(TimeConverter.replaceArgs(SanctionMain.instance.Preffix
                            + SanctionMain.instance.getConfig().getString("SanctionSet.Settings.MessageToPlayerGotTempMuted"),
                    duration, type, player, sender.getName(), reason));

            Bukkit.broadcastMessage(SanctionMain.instance.Preffix +
                    TimeConverter.replaceArgs(SanctionMain.instance.getConfig().getString("SanctionSet.Settings.PlayerGotTempMute"),
                            duration, type, player, sender.getName(), reason));
        } else {
            sender.sendMessage(SanctionMain.instance.Preffix +
                    SanctionMain.instance.getConfig().getString("SanctionSet.Settings.AlreadyMuted").replace("&", "§"));
        }
    }

    public void ApplyPermaMute(String player, String reason, String banner, CommandSender sender) {
        SanctionMain.instance.getData().set(player + ".mute.banner", banner);
        SanctionMain.instance.getData().set(player + ".mute.reason", reason);
        SanctionMain.instance.getData().set(player + ".mute.ismuted", true);
        SanctionMain.instance.saveData();
        Bukkit.getPlayer(player).sendMessage(SanctionMain.instance.Preffix + TimeConverter.replaceArgs(SanctionMain.instance.getConfig()
                        .getString("SanctionSet.Settings.MessageToPlayerGotPermaMuted"),
                "null", "null", player, sender.getName(), reason));

        Bukkit.broadcastMessage(SanctionMain.instance.Preffix + TimeConverter.replaceArgs(SanctionMain.instance.getConfig()
                        .getString("SanctionSet.Settings.PlayerGotPermaMute"),
                "null", "null", player, sender.getName(), reason));
    }
}
