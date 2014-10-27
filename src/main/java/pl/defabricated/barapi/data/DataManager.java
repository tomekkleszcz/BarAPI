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

    public DataDragon createDragon(Player player, String message) {

    }

}
