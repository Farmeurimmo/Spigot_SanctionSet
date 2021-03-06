package main.java.fr.farmeurimmo.sanctionset.gui;

import main.java.fr.farmeurimmo.sanctionset.SanctionMain;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;

public class BanIpGui {

    public static void banipgui(Player player, String cible) {
        if (player.hasPermission("mod+")) {
            Inventory inv = Bukkit.createInventory(null, 27, "§4SanctionSet Bans-ip");

            ItemStack custom1 = new ItemStack(Material.NAME_TAG, 1);
            ItemMeta customS = custom1.getItemMeta();
            customS.setDisplayName("§6" + SanctionMain.instance.getConfig().getString("SanctionSet.Settings.Menu.Banip.NameTag.Reason").replace("&", "§"));
            custom1.setItemMeta(customS);
            inv.setItem(12, custom1);

            ItemStack custom2 = new ItemStack(Material.CLAY_BALL, 2);
            ItemMeta customa = custom2.getItemMeta();
            customa.setDisplayName("§6" + SanctionMain.instance.getConfig().getString("SanctionSet.Settings.Menu.Banip.ClayBall.Reason").replace("&", "§"));
            custom2.setItemMeta(customa);
            inv.setItem(14, custom2);

            if (player.hasPermission("mod+")) {
                if (SanctionMain.instance.getConfig().getBoolean("SanctionSet.Settings.IP.ShowIpForAdmin")) {
                    ItemStack stack = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
                    ItemMeta meta = stack.getItemMeta();
                    ((SkullMeta) meta).setOwner(cible);
                    meta.setDisplayName("§6" + cible);
                    meta.setLore(Arrays.asList(SanctionMain.instance.getConfig().getString("SanctionSet.Settings.Menu.SsMenu.SkullLore.line1").replace("&", "§").replace("%displayname%", player.getDisplayName()).replace("%ip%", player.getAddress().getHostName()),
                            SanctionMain.instance.getConfig().getString("SanctionSet.Settings.Menu.SsMenu.SkullLore.line2").replace("&", "§").replace("%displayname%", player.getDisplayName()).replace("%ip%", player.getAddress().getHostName())));
                    stack.setItemMeta(meta);
                    inv.setItem(13, stack);
                } else {
                    ItemStack stack = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
                    ItemMeta meta = stack.getItemMeta();
                    ((SkullMeta) meta).setOwner(cible);
                    meta.setDisplayName("§6" + cible);
                    meta.setLore(Arrays.asList(SanctionMain.instance.getConfig().getString("SanctionSet.Settings.Menu.SsMenu.SkullLore.line1").replace("&", "§").replace("%displayname%", player.getDisplayName()).replace("%ip%", "Disabled"),
                            SanctionMain.instance.getConfig().getString("SanctionSet.Settings.Menu.SsMenu.SkullLore.line2").replace("&", "§").replace("%displayname%", player.getDisplayName()).replace("%ip%", "Disabled")));
                    stack.setItemMeta(meta);
                    inv.setItem(13, stack);
                }
            } else {
                if (SanctionMain.instance.getConfig().getBoolean("SanctionSet.Settings.IP.ShowIpForMod") == true) {
                    ItemStack stack = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
                    ItemMeta meta = stack.getItemMeta();
                    ((SkullMeta) meta).setOwner(cible);
                    meta.setDisplayName("§6" + cible);
                    meta.setLore(Arrays.asList(SanctionMain.instance.getConfig().getString("SanctionSet.Settings.Menu.SsMenu.SkullLore.line1").replace("&", "§").replace("%displayname%", player.getDisplayName()).replace("%ip%", player.getAddress().getHostName()),
                            SanctionMain.instance.getConfig().getString("SanctionSet.Settings.Menu.SsMenu.SkullLore.line2").replace("&", "§").replace("%displayname%", player.getDisplayName()).replace("%ip%", player.getAddress().getHostName())));
                    stack.setItemMeta(meta);
                    inv.setItem(13, stack);
                } else {
                    ItemStack stack = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
                    ItemMeta meta = stack.getItemMeta();
                    ((SkullMeta) meta).setOwner(cible);
                    meta.setDisplayName("§6" + cible);
                    meta.setLore(Arrays.asList(SanctionMain.instance.getConfig().getString("SanctionSet.Settings.Menu.SsMenu.SkullLore.line1").replace("&", "§").replace("%displayname%", player.getDisplayName()).replace("%ip%", "Disabled"),
                            SanctionMain.instance.getConfig().getString("SanctionSet.Settings.Menu.SsMenu.SkullLore.line2").replace("&", "§").replace("%displayname%", player.getDisplayName()).replace("%ip%", "Disabled")));
                    stack.setItemMeta(meta);
                    inv.setItem(13, stack);
                }
            }

            ItemStack custom5 = new ItemStack(Material.IRON_DOOR, 1);
            ItemMeta customd = custom5.getItemMeta();
            customd.setDisplayName("§6" + SanctionMain.instance.getConfig().getString("SanctionSet.Settings.Menu.SsMenu.GoBackDoor").replace("&", "§"));
            custom5.setItemMeta(customd);
            inv.setItem(18, custom5);

            ItemStack custom6 = new ItemStack(Material.IRON_DOOR, 1);
            ItemMeta custome = custom6.getItemMeta();
            custome.setDisplayName("§6" + SanctionMain.instance.getConfig().getString("SanctionSet.Settings.Menu.SsMenu.GoBackDoor").replace("&", "§"));
            custom6.setItemMeta(custome);
            inv.setItem(26, custom6);

            if (SanctionMain.instance.getConfig().getBoolean("SanctionSet.Settings.FillInventoryWithGlassPane") == true) {
                ItemStack custom0 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 0);
                ItemMeta meta0 = custom0.getItemMeta();
                meta0.setDisplayName("§6");
                custom0.setItemMeta(meta0);

                for (int i = 0; i < inv.getSize(); i++) {
                    if (inv.getItem(i) == null || inv.getItem(i).getType().equals(Material.AIR)) {
                        inv.setItem(i, custom0);
                    }
                }
            }

            player.openInventory(inv);

        } else {
            player.sendMessage(SanctionMain.instance.Preffix +
                    SanctionMain.instance.getConfig().getString("SanctionSet.Settings.NoPermission").replace("&", "§"));
        }
    }

}
