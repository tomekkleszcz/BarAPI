package pl.defabricated.barapi;

import org.bukkit.entity.Player;
import pl.defabricated.barapi.data.DataDragon;

public class BarAPI {

    static BarPlugin plugin;

    BarAPI(BarPlugin barPlugin) {
        plugin = barPlugin;
    }

    public static void setMessage(Player player, String message) {
        setMessage(player, message, 0F, -1);
    }

    public static void setMessage(Player player, String message, float health) {
        setMessage(player, message, health, -1);
    }

    public static void setMessage(Player player, String message, int seconds) {
        setMessage(player, message, 0F, seconds);
    }

    public static void setMessage(Player player, String message, float health, int seconds) {

    }

    public static void setHealth(Player player, float health) {

    }

    public static void setTime(Player player, int seconds) {

    }

    public static boolean hasMessage(Player player) {
        DataDragon dragon = plugin.dataManager.getDragonByPlayer(player);
        return dragon == null ? false : (dragon.getTime() < System.currentTimeMillis() ? false : true);
    }

}
