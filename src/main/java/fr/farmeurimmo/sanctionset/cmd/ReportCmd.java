package main.java.fr.farmeurimmo.sanctionset.cmd;

import main.java.fr.farmeurimmo.sanctionset.SanctionMain;
import main.java.fr.farmeurimmo.sanctionset.gui.ReportGui;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReportCmd implements CommandExecutor, TabCompleter {

    @SuppressWarnings("deprecation")
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 0) {
                player.sendMessage(SanctionMain.instance.Preffix +
                        SanctionMain.instance.getConfig().getString("SanctionSet.Settings.Report.ErrorArg").replace("&", "§"));
                return true;
            }
            if (args.length >= 2) {
                player.sendMessage(SanctionMain.instance.Preffix +
                        SanctionMain.instance.getConfig().getString("SanctionSet.Settings.Report.ErrorArg").replace("&", "§"));
                return true;
            }
                if (Bukkit.getOfflinePlayer(args[0]).isOnline()) {
                    if (SanctionMain.instance.getConfig().getBoolean("SanctionSet.Settings.Report.Enabled")) {
                        ReportGui.MakeReportGui(player, args[0]);
                        return true;
                    } else {
                        player.sendMessage(SanctionMain.instance.Preffix +
                                SanctionMain.instance.getConfig().getString("SanctionSet.Settings.Report.Disabled").replace("&", "§"));
                        return true;
                    }
                } else {
                    player.sendMessage(SanctionMain.instance.Preffix +
                            SanctionMain.instance.getConfig().getString("SanctionSet.Settings.Report.PlayerNotonline").replace("&", "§"));
                    return true;
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        ArrayList<String> subcmd = new ArrayList<String>();
        if (cmd.getName().equalsIgnoreCase("report")) {
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
