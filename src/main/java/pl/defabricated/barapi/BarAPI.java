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
        DataDragon dragon = plugin.dataManager.getDragonByPlayer(player);
        if(dragon != null) {
            dragon.setMessage(message);
            dragon.setHealth(health);
            if(seconds >= 0) {
                dragon.setTime(System.currentTimeMillis() + (seconds * 1000));
            } else {
                dragon.setTime(0L);
            }
        } else {
            dragon = plugin.dataManager.createDragon(player, message, health, (seconds >= 0 ? System.currentTimeMillis() + (seconds * 1000) : 0L));
        }
        dragon.sendUpdate();
    }

    public static void setHealth(Player player, float health) {
        DataDragon dragon = plugin.dataManager.getDragonByPlayer(player);
        if(dragon == null) {
            return;
        }
        dragon.setHealth(health);
        dragon.sendUpdate();
    }

    public static void setTime(Player player, int seconds) {
        DataDragon dragon = plugin.dataManager.getDragonByPlayer(player);
        if(dragon == null) {
            return;
        }
        dragon.setTime(System.currentTimeMillis() + (seconds * 1000));
        dragon.sendUpdate();
    }

    public static boolean isMessageVisible(Player player) {
        DataDragon dragon = plugin.dataManager.getDragonByPlayer(player);
        return dragon == null ? false : (dragon.getTime() <= System.currentTimeMillis() ? false : true);
    }

    public static String getMessage(Player player) {
        DataDragon dragon = plugin.dataManager.getDragonByPlayer(player);
        if(dragon == null) {
            return null;
        }
        return dragon.getMessage();
    }

    public static float getHealth(Player player) {
        DataDragon dragon = plugin.dataManager.getDragonByPlayer(player);
        if(dragon == null) {
            return -1F;
        }
        return dragon.getHealth();
    }

}
