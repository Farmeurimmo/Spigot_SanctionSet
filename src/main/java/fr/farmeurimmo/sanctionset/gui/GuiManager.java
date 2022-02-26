package main.java.fr.farmeurimmo.sanctionset.gui;

import main.java.fr.farmeurimmo.sanctionset.SanctionMain;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class GuiManager implements Listener {

    StringBuilder bc = new StringBuilder();
    String part;

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ItemStack current = event.getCurrentItem();
        Material currenttype = current.getType();

        if (current == null) {
            return;
        }
        if (current.getItemMeta() == null) {
            return;
        }

        int slot = event.getSlot();
        if (slot <= -1) {
            return;
        }

        ItemStack cibleitem = event.getInventory().getItem(13);
        String cible = cibleitem.getItemMeta().getDisplayName().replace("§", "").replace("6", "");

        String title = event.getView().getTitle();

        switch (title) {
            case "§4SanctionSet":

                event.setCancelled(true);

                switch (currenttype) {
                    case GRASS:
                        if (player.hasPermission("mod")) {
                            MuteGui.mutegui(player, cible);
                        } else {
                            SsGui.SsMainGui(player, cible);
                            player.sendMessage(SanctionMain.instance.Preffix + SanctionMain.instance.getConfig().getString("SanctionSet.Settings.NoPermission").replace("&", "§"));
                        }
                        break;
                    case DIAMOND_SWORD:
                        if (player.hasPermission("mod")) {
                            BanGui.bangui(player, cible);
                        } else {
                            SsGui.SsMainGui(player, cible);
                            player.sendMessage(SanctionMain.instance.Preffix + SanctionMain.instance.getConfig().getString("SanctionSet.Settings.NoPermission").replace("&", "§"));
                        }
                        break;
                    case ANVIL:
                        if (player.hasPermission("mod+")) {
                            BanIpGui.banipgui(player, cible);
                        } else {
                            SsGui.SsMainGui(player, cible);
                            player.sendMessage(SanctionMain.instance.Preffix + SanctionMain.instance.getConfig().getString("SanctionSet.Settings.NoPermission").replace("&", "§"));
                        }
                        break;
                    case PAPER:
                        if (player.hasPermission("mod+")) {
                            EndGui.endgui(player, cible);
                        } else {
                            SsGui.SsMainGui(player, cible);
                            player.sendMessage(SanctionMain.instance.Preffix + SanctionMain.instance.getConfig().getString("SanctionSet.Settings.NoPermission").replace("&", "§"));
                        }
                        break;
                    case BARRIER:
                        event.setCancelled(true);
                        player.sendMessage(SanctionMain.instance.Preffix + SanctionMain.instance.getConfig().getString("SanctionSet.Settings.NoPermission").replace("&", "§"));
                        break;
                }
                break;
            case "§4SanctionSet Mutes":
                event.setCancelled(true);

                if (player.hasPermission("mod")) {
                    switch (currenttype) {
                        case BOW:
                            player.chat("/tempmute " + cible + " " + SanctionMain.instance.getConfig().getString("SanctionSet.Settings.Menu.Mutes.Bow.Duration") + " " + SanctionMain.instance.getConfig().getString("SanctionSet.Settings.Menu.Mutes.Bow.Reason"));
                            player.sendMessage(SanctionMain.instance.Preffix + SanctionMain.instance.getConfig().getString("SanctionSet.Settings.SanctionWaitForApplication").replace("&", "§"));
                            player.closeInventory();
                            break;
                        case DIAMOND_SWORD:
                            player.chat("/tempmute " + cible + " " + SanctionMain.instance.getConfig().getString("SanctionSet.Settings.Menu.Mutes.DiamondSword.Duration") + " " + SanctionMain.instance.getConfig().getString("SanctionSet.Settings.Menu.Mutes.DiamondSword.Reason"));
                            player.sendMessage(SanctionMain.instance.Preffix + SanctionMain.instance.getConfig().getString("SanctionSet.Settings.SanctionWaitForApplication").replace("&", "§"));
                            player.closeInventory();
                            break;
                        case ANVIL:
                            player.chat("/tempmute " + cible + " " + SanctionMain.instance.getConfig().getString("SanctionSet.Settings.Menu.Mutes.Anvil.Duration") + " " + SanctionMain.instance.getConfig().getString("SanctionSet.Settings.Menu.Mutes.Anvil.Reason"));
                            player.sendMessage(SanctionMain.instance.Preffix + SanctionMain.instance.getConfig().getString("SanctionSet.Settings.SanctionWaitForApplication").replace("&", "§"));
                            player.closeInventory();
                            break;
                        case REDSTONE_BLOCK:
                            player.chat("/tempmute " + cible + " " + SanctionMain.instance.getConfig().getString("SanctionSet.Settings.Menu.Mutes.RedstoneBlock.Duration") + " " + SanctionMain.instance.getConfig().getString("SanctionSet.Settings.Menu.Mutes.RedstoneBlock.Reason"));
                            player.sendMessage(SanctionMain.instance.Preffix + SanctionMain.instance.getConfig().getString("SanctionSet.Settings.SanctionWaitForApplication").replace("&", "§"));
                            player.closeInventory();
                            break;
                        case ACTIVATOR_RAIL:
                            player.closeInventory();
                            player.chat("/tempmute " + cible + " " + SanctionMain.instance.getConfig().getString("SanctionSet.Settings.Menu.Mutes.ActivatorRail.Duration") + " " + SanctionMain.instance.getConfig().getString("SanctionSet.Settings.Menu.Mutes.ActivatorRail.Reason"));
                            player.sendMessage(SanctionMain.instance.Preffix + SanctionMain.instance.getConfig().getString("SanctionSet.Settings.SanctionWaitForApplication").replace("&", "§"));
                            break;
                        case ARMOR_STAND:
                            player.closeInventory();
                            player.chat("/tempmute " + cible + " " + SanctionMain.instance.getConfig().getString("SanctionSet.Settings.Menu.Mutes.ArmorStand.Duration") + " " + SanctionMain.instance.getConfig().getString("SanctionSet.Settings.Menu.Mutes.ArmorStand.Reason"));
                            player.sendMessage(SanctionMain.instance.Preffix + SanctionMain.instance.getConfig().getString("SanctionSet.Settings.SanctionWaitForApplication").replace("&", "§"));
                            break;
                        case COMPASS:
                            player.closeInventory();
                            player.chat("/tempmute " + cible + " " + SanctionMain.instance.getConfig().getString("SanctionSet.Settings.Menu.Mutes.Compass.Duration") + " " + SanctionMain.instance.getConfig().getString("SanctionSet.Settings.Menu.Mutes.Compass.Reason"));
                            player.sendMessage(SanctionMain.instance.Preffix + SanctionMain.instance.getConfig().getString("SanctionSet.Settings.SanctionWaitForApplication").replace("&", "§"));
                            break;
                        case FLINT_AND_STEEL:
                            player.closeInventory();
                            player.chat("/tempmute " + cible + " " + SanctionMain.instance.getConfig().getString("SanctionSet.Settings.Menu.Mutes.FlintAndSteel.Duration") + " " + SanctionMain.instance.getConfig().getString("SanctionSet.Settings.Menu.Mutes.FlintAndSteel.Reason"));
                            player.sendMessage(SanctionMain.instance.Preffix + SanctionMain.instance.getConfig().getString("SanctionSet.Settings.SanctionWaitForApplication").replace("&", "§"));
                            break;
                        default:
                            SsGui.SsMainGui(player, cible);
                            break;
                    }
                } else {
                    SsGui.SsMainGui(player, cible);
                    player.sendMessage(SanctionMain.instance.Preffix + SanctionMain.instance.getConfig().getString("SanctionSet.Settings.NoPermission").replace("&", "§"));
                }
                break;
            case "§4SanctionSet Bans":
                event.setCancelled(true);

                if (player.hasPermission("mod")) {

                    switch (currenttype) {
                        case DIAMOND_SWORD:
                            player.closeInventory();
                            player.chat("/tempban " + cible + " " + SanctionMain.instance.getConfig().getString("SanctionSet.Settings.Menu.Bans.DiamondSword.Duration") + " " + SanctionMain.instance.getConfig().getString("SanctionSet.Settings.Menu.Bans.DiamondSword.Reason"));
                            player.sendMessage(SanctionMain.instance.Preffix + SanctionMain.instance.getConfig().getString("SanctionSet.Settings.SanctionWaitForApplication").replace("&", "§"));
                            break;
                        case WOOD_SWORD:
                            player.closeInventory();
                            player.chat("/tempban " + cible + " " + SanctionMain.instance.getConfig().getString("SanctionSet.Settings.Menu.Bans.WoodSword.Duration") + " " + SanctionMain.instance.getConfig().getString("SanctionSet.Settings.Menu.Bans.WoodSword.Reason"));
                            player.sendMessage(SanctionMain.instance.Preffix + SanctionMain.instance.getConfig().getString("SanctionSet.Settings.SanctionWaitForApplication").replace("&", "§"));
                            break;
                        case FEATHER:
                            player.closeInventory();
                            player.chat("/tempban " + cible + " " + SanctionMain.instance.getConfig().getString("SanctionSet.Settings.Menu.Bans.Feather.Duration") + " " + SanctionMain.instance.getConfig().getString("SanctionSet.Settings.Menu.Bans.Feather.Reason"));
                            player.sendMessage(SanctionMain.instance.Preffix + SanctionMain.instance.getConfig().getString("SanctionSet.Settings.SanctionWaitForApplication").replace("&", "§"));
                            break;
                        case IRON_BOOTS:
                            player.closeInventory();
                            player.chat("/tempban " + cible + " " + SanctionMain.instance.getConfig().getString("SanctionSet.Settings.Menu.Bans.IronBoots.Duration") + " " + SanctionMain.instance.getConfig().getString("SanctionSet.Settings.Menu.Bans.IronBoots.Reason"));
                            player.sendMessage(SanctionMain.instance.Preffix + SanctionMain.instance.getConfig().getString("SanctionSet.Settings.SanctionWaitForApplication").replace("&", "§"));
                            break;
                        case GOLD_AXE:
                            player.closeInventory();
                            player.chat("/tempban " + cible + " " + SanctionMain.instance.getConfig().getString("SanctionSet.Settings.Menu.Bans.GoldAxe.Duration") + " " + SanctionMain.instance.getConfig().getString("SanctionSet.Settings.Menu.Bans.GoldAxe.Reason"));
                            player.sendMessage(SanctionMain.instance.Preffix + SanctionMain.instance.getConfig().getString("SanctionSet.Settings.SanctionWaitForApplication").replace("&", "§"));
                            break;
                        case ARMOR_STAND:
                            player.closeInventory();
                            player.chat("/tempban " + cible + " " + SanctionMain.instance.getConfig().getString("SanctionSet.Settings.Menu.Bans.ArmorStand.Duration") + " " + SanctionMain.instance.getConfig().getString("SanctionSet.Settings.Menu.Bans.ArmorStand.Reason"));
                            player.sendMessage(SanctionMain.instance.Preffix + SanctionMain.instance.getConfig().getString("SanctionSet.Settings.SanctionWaitForApplication").replace("&", "§"));
                            break;
                        case TNT:
                            player.closeInventory();
                            player.chat("/tempban " + cible + " " + SanctionMain.instance.getConfig().getString("SanctionSet.Settings.Menu.Bans.Tnt.Duration") + " " + SanctionMain.instance.getConfig().getString("SanctionSet.Settings.Menu.Bans.Tnt.Reason"));
                            player.sendMessage(SanctionMain.instance.Preffix + SanctionMain.instance.getConfig().getString("SanctionSet.Settings.SanctionWaitForApplication").replace("&", "§"));
                            break;
                        case LEATHER:
                            player.closeInventory();
                            player.chat("/tempban " + cible + " " + SanctionMain.instance.getConfig().getString("SanctionSet.Settings.Menu.Bans.Leather.Duration") + " " + SanctionMain.instance.getConfig().getString("SanctionSet.Settings.Menu.Bans.Leather.Reason"));
                            player.sendMessage(SanctionMain.instance.Preffix + SanctionMain.instance.getConfig().getString("SanctionSet.Settings.SanctionWaitForApplication").replace("&", "§"));
                            break;
                        case DIAMOND_CHESTPLATE:
                            player.closeInventory();
                            player.chat("/tempban " + cible + " " + SanctionMain.instance.getConfig().getString("SanctionSet.Settings.Menu.Bans.DiamondChestPlate.Duration") + " " + SanctionMain.instance.getConfig().getString("SanctionSet.Settings.Menu.Bans.DiamondChestPlate.Reason"));
                            player.sendMessage(SanctionMain.instance.Preffix + SanctionMain.instance.getConfig().getString("SanctionSet.Settings.SanctionWaitForApplication").replace("&", "§"));
                            break;
                        case DIRT:
                            player.closeInventory();
                            player.chat("/tempban " + cible + " " + SanctionMain.instance.getConfig().getString("SanctionSet.Settings.Menu.Bans.Dirt.Duration") + " " + SanctionMain.instance.getConfig().getString("SanctionSet.Settings.Menu.Bans.Dirt.Reason"));
                            player.sendMessage(SanctionMain.instance.Preffix + SanctionMain.instance.getConfig().getString("SanctionSet.Settings.SanctionWaitForApplication").replace("&", "§"));
                            break;
                        default:
                            SsGui.SsMainGui(player, cible);
                            break;
                    }
                } else {
                    SsGui.SsMainGui(player, cible);
                    player.sendMessage(SanctionMain.instance.Preffix + SanctionMain.instance.getConfig().getString("SanctionSet.Settings.NoPermission").replace("&", "§"));
                }
                break;
            case "§4SanctionSet Bans-ip":
                event.setCancelled(true);

                if (player.hasPermission("mod+")) {

                    switch (currenttype) {
                        case NAME_TAG:
                            player.closeInventory();
                            player.chat("/ban-ip " + cible + " " + SanctionMain.instance.getConfig().getString("SanctionSet.Settings.Menu.Banip.NameTag.Reason"));
                            player.sendMessage(SanctionMain.instance.Preffix + SanctionMain.instance.getConfig().getString("SanctionSet.Settings.SanctionWaitForApplication").replace("&", "§"));
                            break;
                        case CLAY_BALL:
                            player.closeInventory();
                            player.chat("/ban-ip " + cible + " " + SanctionMain.instance.getConfig().getString("SanctionSet.Settings.Menu.Banip.ClayBall.Reason"));
                            player.sendMessage(SanctionMain.instance.Preffix + SanctionMain.instance.getConfig().getString("SanctionSet.Settings.SanctionWaitForApplication").replace("&", "§"));
                            break;
                        default:
                            SsGui.SsMainGui(player, cible);
                            break;
                    }
                } else {
                    SsGui.SsMainGui(player, cible);
                    player.sendMessage(SanctionMain.instance.Preffix + SanctionMain.instance.getConfig().getString("SanctionSet.Settings.NoPermission").replace("&", "§"));
                }
                break;
            case "§4SanctionSet Unbans/Unmutes":
                event.setCancelled(true);

                if (player.hasPermission("mod+")) {

                    switch (currenttype) {
                        case BOW:
                            player.closeInventory();
                            player.chat("/unmute " + cible);
                            player.sendMessage(SanctionMain.instance.Preffix + SanctionMain.instance.getConfig().getString("SanctionSet.Settings.SanctionWaitEnd").replace("&", "§"));
                            break;
                        case DIAMOND_SWORD:
                            player.closeInventory();
                            player.chat("/unban " + cible);
                            player.chat("/unbanip " + cible);
                            player.sendMessage(SanctionMain.instance.Preffix + SanctionMain.instance.getConfig().getString("SanctionSet.Settings.SanctionWaitEnd").replace("&", "§"));
                            break;
                        case ANVIL:
                            player.closeInventory();
                            player.chat("/unmute " + cible);
                            player.chat("/unban " + cible);
                            player.chat("/unbanip " + cible);
                            player.sendMessage(SanctionMain.instance.Preffix + SanctionMain.instance.getConfig().getString("SanctionSet.Settings.SanctionWaitEnd").replace("&", "§"));
                            break;
                        default:
                            SsGui.SsMainGui(player, cible);
                            break;
                    }
                }
                break;
            default:
                if(!title.equalsIgnoreCase("§4Report "+cible)){
                    return;
                }
                event.setCancelled(true);
                String ReportReason = current.getItemMeta().getDisplayName();
                part = ReportReason;

                switch (currenttype) {
                    case GRASS:
                        player.sendMessage(SanctionMain.instance.Preffix + SanctionMain.instance.getConfig().getString("SanctionSet.Settings.Report.sended").replace("&", "§").replace("%player%", cible).replace("%reason%", ReportReason));
                        player.closeInventory();
                        for (Player all : Bukkit.getServer().getOnlinePlayers()) {
                            bc.append(part + " ");
                            if (all.hasPermission("reportview")) {
                                all.sendMessage(SanctionMain.instance.getConfig().getString("SanctionSet.Settings.Report.Obtain").replace("&", "§").replace("%player%", cible).replace("%reason%", ReportReason).replace("%sender%", player.getName()));
                                bc.delete(0, 100);
                            }
                        }
                        break;
                    case DIAMOND_SWORD:
                        player.sendMessage(SanctionMain.instance.Preffix + SanctionMain.instance.getConfig().getString("SanctionSet.Settings.Report.sended").replace("&", "§").replace("%player%", cible).replace("%reason%", ReportReason));
                        player.closeInventory();
                        for (Player all : Bukkit.getServer().getOnlinePlayers()) {
                            bc.append(part + " ");
                            if (all.hasPermission("reportview")) {
                                all.sendMessage(SanctionMain.instance.getConfig().getString("SanctionSet.Settings.Report.Obtain").replace("&", "§").replace("%player%", cible).replace("%reason%", ReportReason).replace("%sender%", player.getName()));
                                bc.delete(0, 100);
                            }
                        }
                        break;
                    case APPLE:
                        player.sendMessage(SanctionMain.instance.Preffix + SanctionMain.instance.getConfig().getString("SanctionSet.Settings.Report.sended").replace("&", "§").replace("%player%", cible).replace("%reason%", ReportReason));
                        player.closeInventory();
                        for (Player all : Bukkit.getServer().getOnlinePlayers()) {
                            bc.append(part + " ");
                            if (all.hasPermission("reportview")) {
                                all.sendMessage(SanctionMain.instance.getConfig().getString("SanctionSet.Settings.Report.Obtain").replace("&", "§").replace("%player%", cible).replace("%reason%", ReportReason).replace("%sender%", player.getName()));
                                bc.delete(0, 100);
                            }
                        }
                        break;
                    case BEACON:
                        player.sendMessage(SanctionMain.instance.Preffix + SanctionMain.instance.getConfig().getString("SanctionSet.Settings.Report.sended").replace("&", "§").replace("%player%", cible).replace("%reason%", ReportReason));
                        player.closeInventory();
                        for (Player all : Bukkit.getServer().getOnlinePlayers()) {
                            bc.append(part + " ");
                            if (all.hasPermission("reportview")) {
                                all.sendMessage(SanctionMain.instance.getConfig().getString("SanctionSet.Settings.Report.Obtain").replace("&", "§").replace("%player%", cible).replace("%reason%", ReportReason).replace("%sender%", player.getName()));
                                bc.delete(0, 100);
                            }
                        }
                        break;
                }
        }
    }

}

