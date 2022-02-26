package main.java.fr.farmeurimmo.sanctionset.events;

import main.java.fr.farmeurimmo.sanctionset.SanctionMain;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatEvent implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void AsyncChat(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();
        if (SanctionMain.instance.getData().getBoolean(e.getPlayer().getName() + ".mute.ismuted") == true) {
            e.setCancelled(true);
            player.sendMessage(SanctionMain.instance.Preffix +
                    SanctionMain.instance.getConfig().getString("SanctionSet.Settings.PermaMutedPlayerChat").replace("&", "§")
                            .replace("%player%", player.getName()).replace("%banner%",
                                    SanctionMain.instance.getData().getString(player.getName() + ".mute.banner").replace("&", "§")));
            return;
        }
        if (SanctionMain.instance.getData().getBoolean(e.getPlayer().getName() + ".tempmute.istempmuted") == true) {
            e.setCancelled(true);
            player.sendMessage(SanctionMain.instance.Preffix +
                    SanctionMain.instance.getConfig().getString("SanctionSet.Settings.TempMutedPlayerChat").replace("&", "§")
                            .replace("%player%", player.getName()).replace("%banner%",
                                    SanctionMain.instance.getData().getString(player.getName() + ".tempmute.banner").replace("&", "§")));
            return;
        }
    }

}
