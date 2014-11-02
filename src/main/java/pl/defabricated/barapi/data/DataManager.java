package pl.defabricated.barapi.data;

import org.bukkit.entity.Player;
import pl.defabricated.barapi.BarPlugin;

import java.util.HashMap;

public class DataManager {

    BarPlugin plugin;

    public DataManager(BarPlugin plugin) {
        this.plugin = plugin;
    }

    HashMap<String, DataDragon> dragons = new HashMap();

    public DataDragon getDragonByNick(String nick) {
        return dragons.get(nick);
    }

    public DataDragon getDragonByPlayer(Player player) {
        return getDragonByNick(player.getName());
    }

    public DataDragon createDragon(Player player, String message, float health, long time) {
        DataDragon dragon = new DataDragon(player, message, health, time);
        dragons.put(player.getName(), dragon);
        return dragon;
    }

    public DataDragon removeDragon(Player player) {
        return dragons.remove(player.getName().toLowerCase());
    }

}
