package main.java.fr.farmeurimmo.sanctionset;

import main.java.fr.farmeurimmo.sanctionset.cmd.*;
import main.java.fr.farmeurimmo.sanctionset.events.ChatEvent;
import main.java.fr.farmeurimmo.sanctionset.events.JoinLeaveEvent;
import main.java.fr.farmeurimmo.sanctionset.gui.GuiManager;
import main.java.fr.farmeurimmo.sanctionset.sanctions.ApplySanction;
import main.java.fr.farmeurimmo.sanctionset.sanctions.BanRevoker;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class SanctionMain extends JavaPlugin implements Listener {

    public static SanctionMain instance;
    public static ArrayList<Player> vanished = new ArrayList<>();
    public static String aaa = "";
    public FileConfiguration data;
    public File dfile;
    public String Preffix = null;
    public HashMap<String, String> ipblocked = new HashMap<>();

    @Override
    public void onEnable() {
        saveDefaultConfig();
        instance = this;
        aaa = Bukkit.getServer().getBukkitVersion();
        System.out.println("-----------------------------------------------------------------------------------------------------");
        System.out.println("This server is using: " + aaa);
        System.out.println("[" + getConfig().getString("SanctionSet.Settings.Prefix.Inconsole") + "] " + getConfig().getString("SanctionSet.Settings.StartMessage"));
        System.out.println("-----------------------------------------------------------------------------------------------------");
        setup();
        getServer().getPluginManager().registerEvents(new GuiManager(), this);
        getServer().getPluginManager().registerEvents(new JoinLeaveEvent(), this);
        getServer().getPluginManager().registerEvents(new ChatEvent(), this);
        this.getCommand("vanish").setExecutor(new VanishCmd());
        this.getCommand("report").setExecutor(new ReportCmd());
        this.getCommand("ssadmin").setExecutor(new SsAdminCmd());
        this.getCommand("ss").setExecutor(new SsCmd());
        this.getCommand("ban").setExecutor(new BanCmd());
        this.getCommand("tempban").setExecutor(new TempBanCmd());
        this.getCommand("ban-ip").setExecutor(new BanIpCmd());
        this.getCommand("mute").setExecutor(new MuteCmd());
        this.getCommand("tempmute").setExecutor(new TempMuteCmd());
        this.getCommand("unmute").setExecutor(new UnMuteCmd());
        this.getCommand("unban").setExecutor(new UnBanCmd());
        new ApplySanction();
        Vanish();
        BanRevoker.CheckForUnban();
        Preffix = getConfig().getString("SanctionSet.Settings.Prefix.game").replace("&", "§");

        for (String a : getData().getConfigurationSection("").getKeys(false)) {
            if (getData().getBoolean(a + ".ban-ip.isipbanned") == true) {
                ipblocked.put(a, getData().getString(a + ".ban-ip.ip"));
            }
        }
    }

    @Override
    public void onDisable() {
        System.out.println("-----------------------------------------------------------------------------------------------------");
        System.out.println("[" + getConfig().getString("SanctionSet.Settings.Prefix.Inconsole") + "] " + getConfig().getString("SanctionSet.Settings.StopMessage"));
        System.out.println("-----------------------------------------------------------------------------------------------------");
    }

    public void setup() {
        dfile = new File(this.getDataFolder(), "Sanctions.yml");

        if (!dfile.exists()) {
            try {
                dfile.createNewFile();
            } catch (IOException e) {
                getLogger().info("§c§lError in creation of Sanctions.yml");
            }
        }

        data = YamlConfiguration.loadConfiguration(dfile);

    }

    public FileConfiguration getData() {
        return data;
    }

    public void reloadData() throws FileNotFoundException, IOException {
        try {
            data.load(dfile);
        } catch (InvalidConfigurationException e) {
            getLogger().info("§c§lError in reloading data for Sanctions.yml!");
            e.printStackTrace();
        }
    }

    public void saveData() {
        try {
            data.save(dfile);
        } catch (IOException e) {
            getLogger().info("§c§lError in save for Sanctions.yml!");
        }
    }


    @SuppressWarnings("deprecation")
    public void Vanish() {
        ArrayList<Player> vanish = vanished;
        for (Player players : Bukkit.getOnlinePlayers()) {
            for (Player pl : vanish) {
                players.hidePlayer(pl);
            }
        }
        Bukkit.getServer().getScheduler().scheduleAsyncDelayedTask(SanctionMain.instance, new Runnable() {
            public void run() {
                Vanish();
            }
        }, 20);
    }
}