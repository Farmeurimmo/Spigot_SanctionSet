package main.java.fr.farmeurimmo.sanctionset.cmd;

import main.java.fr.farmeurimmo.sanctionset.SanctionMain;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VanishCmd implements CommandExecutor, TabCompleter {


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] arg3) {
        if (sender instanceof Player) {
            if (SanctionMain.instance.getConfig().getBoolean("SanctionSet.Settings.Vanish.Enabled") == true) {
                Player p = (Player) sender;
                if (p.hasPermission("vanish")) {
                    if (SanctionMain.vanished.contains(p)) {
                        for (Player players : Bukkit.getOnlinePlayers()) {
                            players.showPlayer(p);
                            p.removePotionEffect(PotionEffectType.INVISIBILITY);
                            if (p.getGameMode() == GameMode.ADVENTURE) {
                                p.setAllowFlight(false);
                            }
                            if (p.getGameMode() == GameMode.SURVIVAL) {
                                p.setAllowFlight(false);
                            }
                            if (SanctionMain.instance.getConfig().getBoolean("SanctionSet.Settings.Vanish.ExitGamemode") == true) {
                                if (SanctionMain.instance.getConfig().getInt("SanctionSet.Settings.Vanish.ExGamemode") == 1) {
                                    p.setGameMode(GameMode.CREATIVE);
                                    p.setAllowFlight(true);
                                    p.setFlying(true);
                                }
                                if (SanctionMain.instance.getConfig().getInt("SanctionSet.Settings.Vanish.ExGamemode") == 2) {
                                    p.setGameMode(GameMode.ADVENTURE);
                                }
                                if (SanctionMain.instance.getConfig().getInt("SanctionSet.Settings.Vanish.ExGamemode") == 3) {
                                    p.setGameMode(GameMode.SPECTATOR);
                                    p.setAllowFlight(true);
                                    p.setFlying(true);

                                }
                                if (SanctionMain.instance.getConfig().getInt("SanctionSet.Settings.Vanish.ExGamemode") == 0) {
                                    p.setGameMode(GameMode.SURVIVAL);
                                }
                            }
                        }
                        SanctionMain.vanished.remove(p);
                        p.sendMessage(SanctionMain.instance.getConfig().getString("SanctionSet.Settings.Vanish.Isoff").replace("&", "§"));
                    } else {
                        for (Player players : Bukkit.getOnlinePlayers()) {
                            players.hidePlayer(p);
                            p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 1000000000, 1));
                        }
                        SanctionMain.vanished.add(p);
                        p.sendMessage(SanctionMain.instance.getConfig().getString("SanctionSet.Settings.Vanish.Ison").replace("&", "§"));
                        if (SanctionMain.instance.getConfig().getBoolean("SanctionSet.Settings.Vanish.ChangeGamemode") == true) {
                            if (SanctionMain.instance.getConfig().getInt("SanctionSet.Settings.Vanish.Gamemode") == 1) {
                                p.setGameMode(GameMode.CREATIVE);
                            }
                            if (SanctionMain.instance.getConfig().getInt("SanctionSet.Settings.Vanish.Gamemode") == 2) {
                                p.setGameMode(GameMode.ADVENTURE);
                            }
                            if (SanctionMain.instance.getConfig().getInt("SanctionSet.Settings.Vanish.Gamemode") == 3) {
                                p.setGameMode(GameMode.SPECTATOR);
                            }
                            if (SanctionMain.instance.getConfig().getInt("SanctionSet.Settings.Vanish.Gamemode") == 0) {
                                p.setGameMode(GameMode.SURVIVAL);
                            }
                        }
                        if (SanctionMain.instance.getConfig().getBoolean("SanctionSet.Settings.Vanish.Fly") == true) {
                            if (p.getAllowFlight() == true) {
                            } else {
                                p.setAllowFlight(true);
                                p.setFlying(true);
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        ArrayList<String> subcmd = new ArrayList<String>();
        if (cmd.getName().equalsIgnoreCase("vanish")) {
            if (args.length >= 0) {
                subcmd.add("");
                Collections.sort(subcmd);
            }
        }
        return subcmd;
    }
}
