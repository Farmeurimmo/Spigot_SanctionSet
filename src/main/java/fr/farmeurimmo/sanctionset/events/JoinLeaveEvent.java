package main.java.fr.farmeurimmo.sanctionset.events;

import main.java.fr.farmeurimmo.sanctionset.SanctionMain;
import main.java.fr.farmeurimmo.sanctionset.sanctions.BanRevoker;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerPreLoginEvent.Result;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffectType;

@SuppressWarnings("deprecation")
public class JoinLeaveEvent implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void AsyncPlayer(AsyncPlayerPreLoginEvent e) {
        String ip = e.getAddress().getHostAddress();
        String partialIp = ip.substring(0, ip.lastIndexOf("."));
        if (SanctionMain.instance.ipblocked.containsKey(partialIp)) {
            e.disallow(Result.KICK_BANNED, SanctionMain.instance.getConfig().getString("SanctionSet.Settings.BanIp.lines").replace("&", "§")
                    .replace("%banner%", SanctionMain.instance.getData().getString(
                            SanctionMain.instance.ipblocked.get(partialIp) + ".ban-ip.banner"))
                    .replace("%date%", SanctionMain.instance.getData().getString(
                            SanctionMain.instance.ipblocked.get(partialIp) + ".ban-ip.date").replace("T", " "))
                    .replace("%reason%", SanctionMain.instance.getData().getString(
                            SanctionMain.instance.ipblocked.get(partialIp) + ".ban-ip.reason")));
            return;
        }
        if (SanctionMain.instance.getData().get(e.getName() + ".ban-ip.isipbanned") == null) {
            SanctionMain.instance.getData().set(e.getName() + ".ban-ip.banner", "");
            SanctionMain.instance.getData().set(e.getName() + ".ban-ip.date", "");
            SanctionMain.instance.getData().set(e.getName() + ".ban-ip.reason", "");
            SanctionMain.instance.getData().set(e.getName() + ".ban-ip.ip", "");
            SanctionMain.instance.getData().set(e.getName() + ".ban-ip.isipbanned", false);

            SanctionMain.instance.getData().set(e.getName() + ".ban.banner", "");
            SanctionMain.instance.getData().set(e.getName() + ".ban.date", "");
            SanctionMain.instance.getData().set(e.getName() + ".ban.reason", "");
            SanctionMain.instance.getData().set(e.getName() + ".ban.isbanned", false);

            SanctionMain.instance.getData().set(e.getName() + ".tempban.banner", "");
            SanctionMain.instance.getData().set(e.getName() + ".tempban.date", "");
            SanctionMain.instance.getData().set(e.getName() + ".tempban.timemillis", "");
            SanctionMain.instance.getData().set(e.getName() + ".tempban.duration", "");
            SanctionMain.instance.getData().set(e.getName() + ".tempban.reason", "");
            SanctionMain.instance.getData().set(e.getName() + ".tempban.istempbanned", false);

            SanctionMain.instance.getData().set(e.getName() + ".mute.ismuted", false);
            SanctionMain.instance.getData().set(e.getName() + ".mute.banner", "");
            SanctionMain.instance.getData().set(e.getName() + ".mute.reason", "");

            SanctionMain.instance.getData().set(e.getName() + ".tempmute.istempmuted", false);
            SanctionMain.instance.getData().set(e.getName() + ".tempmute.banner", "");
            SanctionMain.instance.getData().set(e.getName() + ".tempmute.timemillis", "");
            SanctionMain.instance.getData().set(e.getName() + ".tempmute.reason", "");
            SanctionMain.instance.getData().set(e.getName() + ".tempmute.duration", "");


            SanctionMain.instance.saveData();
        }
        if (SanctionMain.instance.getData().getBoolean(e.getName() + ".ban-ip.isipbanned") == true) {
            e.disallow(Result.KICK_BANNED, SanctionMain.instance.getConfig().getString("SanctionSet.Settings.BanIp.lines").replace("&", "§")
                    .replace("%banner%", SanctionMain.instance.getData().getString(e.getName() + ".ban-ip.banner"))
                    .replace("%date%", SanctionMain.instance.getData().getString(e.getName() + ".ban-ip.date").replace("T", " "))
                    .replace("%reason%", SanctionMain.instance.getData().getString(e.getName() + ".ban-ip.reason")));
            return;
        }
        if (SanctionMain.instance.getData().getBoolean(e.getName() + ".ban.isbanned") == true) {
            e.disallow(Result.KICK_BANNED, SanctionMain.instance.getConfig().getString("SanctionSet.Settings.Ban.lines").replace("&", "§")
                    .replace("%banner%", SanctionMain.instance.getData().getString(e.getName() + ".ban.banner"))
                    .replace("%date%", SanctionMain.instance.getData().getString(e.getName() + ".ban.date").replace("T", " "))
                    .replace("%reason%", SanctionMain.instance.getData().getString(e.getName() + ".ban.reason")));
            return;
        }
        if (SanctionMain.instance.getData().getBoolean(e.getName() + ".tempban.istempbanned") == true) {
            String aaaa = SanctionMain.instance.getData().getString(e.getName() + ".tempban.expiration");
            if (SanctionMain.instance.getData().getLong(e.getName() + ".tempban.timemillis") <= System.currentTimeMillis()) {
                BanRevoker.UnTempBan(e.getName(), Bukkit.getConsoleSender());
                return;
            } else {
                e.disallow(Result.KICK_BANNED, SanctionMain.instance.getConfig().getString("SanctionSet.Settings.TempBan.lines").replace("&", "§")
                        .replace("%banner%", SanctionMain.instance.getData().getString(e.getName() + ".tempban.banner"))
                        .replace("%date%", SanctionMain.instance.getData().getString(e.getName() + ".tempban.date").replace("T", " "))
                        .replace("%reason%", SanctionMain.instance.getData().getString(e.getName() + ".tempban.reason"))
                        .replace("%expiration%", aaaa)
                        .replace("%duration%", SanctionMain.instance.getData().getString(e.getName() + ".tempban.duration") +
                                SanctionMain.instance.getData().getString(e.getName() + ".tempban.unit").replace("sec", " second(s)").replace("min", " minute(s)")
                                        .replace("day", " day(s)").replace("hour", " hour(s)").replace("year", " year(s)")));
                return;
            }
        }
    }

    @EventHandler
    public void OnLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (SanctionMain.vanished.contains(player)) {
            player.removePotionEffect(PotionEffectType.INVISIBILITY);
            SanctionMain.vanished.remove(player);
            player.sendMessage(SanctionMain.instance.getConfig().getString("SanctionSet.Settings.Vanish.Isoff").replace("&", "§"));
        }
    }
}
