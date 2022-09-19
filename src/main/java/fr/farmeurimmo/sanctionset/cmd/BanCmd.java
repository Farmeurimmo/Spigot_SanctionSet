package main.java.fr.farmeurimmo.sanctionset.cmd;

import main.java.fr.farmeurimmo.sanctionset.SanctionMain;
import main.java.fr.farmeurimmo.sanctionset.sanctions.ApplySanction;
import main.java.fr.farmeurimmo.sanctionset.utils.TimeConverter;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.*;

public class BanCmd implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("ban")) {
            Calendar calendar = Calendar.getInstance();
            if (args.length == 0) {
                sender.sendMessage(SanctionMain.instance.Preffix +
                        SanctionMain.instance.getConfig().getString("SanctionSet.Settings.ErrorBanArg").replace("&", "§"));
                return true;
            } else if (args.length == 1) {
                Date Mydate = new Date(System.currentTimeMillis());
                Player p = Bukkit.getPlayer(args[0]);
                String reason = SanctionMain.instance.getConfig().getString("SanctionSet.Settings.UnkownReasonSpecified").replace("&", "§");
                assert p != null;
                if (p.isOnline()) {
                    p.kickPlayer(SanctionMain.instance.getConfig().getString("SanctionSet.Settings.Ban.lines").replace("&", "§")
                            .replace("%banner%", sender.getName())
                            .replace("%date%", TimeConverter.getFormatTimeWithTZ(calendar.getTime()))
                            .replace("%reason%", reason.trim()));
                }
                StringBuilder sb = new StringBuilder();
                for (String s : args) {
                    sb.append(s).append(' ');
                }
                ApplySanction.instance.ApplyPermaBan(p, reason, sender.getName(),
                        TimeConverter.getFormatTimeWithTZ(Mydate));
                return true;
            } else {
                if (Bukkit.getPlayer(args[0]) != null) {
                    Player p = Bukkit.getPlayer(args[0]);
                    StringBuilder sb = new StringBuilder();
                    for (String s : args) {
                        sb.append(s).append(' ');
                    }
                    String reason = sb.toString().replace(args[0] + " ", "").trim();
                    assert p != null;
                    if (p.isOnline()) {
                        p.kickPlayer(SanctionMain.instance.getConfig().getString("SanctionSet.Settings.Ban.lines").replace("&", "§")
                                .replace("%banner%", sender.getName())
                                .replace("%date%", TimeConverter.getFormatTimeWithTZ(calendar.getTime()))
                                .replace("%reason%", reason));
                    }
                    Date Mydate = new Date(System.currentTimeMillis());
                    ApplySanction.instance.ApplyPermaBan(p, reason, sender.getName(),
                            TimeConverter.getFormatTimeWithTZ(Mydate));
                } else {
                    sender.sendMessage(SanctionMain.instance.Preffix +
                            SanctionMain.instance.getConfig().getString("SanctionSet.Settings.InvalidPlayer").replace("&", "§"));
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        ArrayList<String> subcmd = new ArrayList<>();
        if (cmd.getName().equalsIgnoreCase("ban")) {
            if (args.length == 1) {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    subcmd.add(player.getName());
                }
            } else if (args.length >= 2) {
                subcmd.add("");
            }
            Collections.sort(subcmd);
        }
        return subcmd;
    }
}
