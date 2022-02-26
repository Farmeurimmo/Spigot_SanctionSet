package main.java.fr.farmeurimmo.sanctionset.cmd;

import main.java.fr.farmeurimmo.sanctionset.SanctionMain;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SsAdminCmd implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(SanctionMain.instance.Preffix +
                    SanctionMain.instance.getConfig().getString("SanctionSet.Settings.ErrorArgAdminCommands").replace("&", "§"));
            sender.sendMessage("Subs commands available: infos, reload, rl");
        }
        if (args.length >= 2) {
            sender.sendMessage(SanctionMain.instance.Preffix +
                    SanctionMain.instance.getConfig().getString("SanctionSet.Settings.ErrorArgAdminCommands").replace("&", "§"));
            sender.sendMessage("Subs commands available: infos, reload, rl");
        }
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("reload")) {
                SanctionMain.instance.reloadConfig();
                sender.sendMessage(SanctionMain.instance.Preffix +
                        SanctionMain.instance.getConfig().getString("SanctionSet.Settings.ReloadMessage").replace("&", "§"));
            }
            if (args[0].equalsIgnoreCase("rl")) {
                SanctionMain.instance.reloadConfig();
                sender.sendMessage(SanctionMain.instance.Preffix +
                        SanctionMain.instance.getConfig().getString("SanctionSet.Settings.ReloadMessage").replace("&", "§"));
            } else if (args[0].equalsIgnoreCase("infos")) {
                sender.sendMessage("Plugin developper: Farmeurimmo#0462");
                sender.sendMessage("Email: farmeurimmo@gmail.com");
                sender.sendMessage("Version: beta/1.0.0");
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        ArrayList<String> subcmd = new ArrayList<String>();
        if (cmd.getName().equalsIgnoreCase("ssadmin")) {
            if (args.length == 1) {
                subcmd.add("reload");
                subcmd.add("rl");
                subcmd.add("infos");
            } else if (args.length >= 2) {
                subcmd.add("");
            }
            Collections.sort(subcmd);
        }
        return subcmd;
    }
}
