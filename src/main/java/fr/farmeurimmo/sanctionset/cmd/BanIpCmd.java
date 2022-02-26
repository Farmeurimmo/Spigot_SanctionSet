package main.java.fr.farmeurimmo.sanctionset.cmd;

import main.java.fr.farmeurimmo.sanctionset.SanctionMain;
import main.java.fr.farmeurimmo.sanctionset.sanctions.ApplySanction;
import main.java.fr.farmeurimmo.sanctionset.utils.TimeConverter;
import org.bukkit.BanList.Type;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Calendar;
import java.util.Date;

public class BanIpCmd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("banip")) {
            Calendar calendar = Calendar.instance;
            if (args.length == 0) {
                sender.sendMessage(SanctionMain.instance.Preffix +
                        SanctionMain.instance.getConfig().getString("SanctionSet.Settings.ErrorBanIpArg").replace("&", "§"));
            } else if (args.length == 1) {
                Player p = Bukkit.getPlayer(args[0]);
                StringBuilder sb = new StringBuilder();
                for (String s : args) {
                    sb.append(s).append(' ');
                }
                String reason = SanctionMain.instance.getConfig().getString("SanctionSet.Settings.UnkownReasonSpecified").replace("&", "§");
                if (p != null & p.isOnline()) {
                    p.kickPlayer(SanctionMain.instance.getConfig().getString("SanctionSet.Settings.BanIp.lines").replace("&", "§")
                            .replace("%banner%", sender.getName())
                            .replace("%date%", TimeConverter.getFormatTimeWithTZ(calendar.getTime()))
                            .replace("%reason%", reason));
                }
                Date Mydate = new Date(System.currentTimeMillis());
                ApplySanction.instance.ApplyPermaBanIp(p, reason, sender.getName(),
                        TimeConverter.getFormatTimeWithTZ(Mydate));
                Bukkit.getBanList(Type.IP).addBan(p.getAddress().getHostName(), reason,
                        null, sender.getName());
            } else if (args.length >= 2) {
                Player p = Bukkit.getPlayer(args[0]);
                StringBuilder sb = new StringBuilder();
                for (String s : args) {
                    sb.append(s).append(' ');
                }
                String reason = sb.toString().replace(args[0] + " ", "").trim();
                if (p != null & p.isOnline()) {
                    p.kickPlayer(SanctionMain.instance.getConfig().getString("SanctionSet.Settings.BanIp.lines").replace("&", "§")
                            .replace("%banner%", sender.getName())
                            .replace("%date%", TimeConverter.getFormatTimeWithTZ(calendar.getTime()))
                            .replace("%reason%", reason));
                }
                Date Mydate = new Date(System.currentTimeMillis());
                ApplySanction.instance.ApplyPermaBanIp(p, reason, sender.getName(),
                        TimeConverter.getFormatTimeWithTZ(Mydate));
            }
        }
        return false;
    }

}
