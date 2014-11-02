package pl.defabricated.barapi.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import pl.defabricated.barapi.BarPlugin;
import pl.defabricated.barapi.data.DataDragon;

public class PlayerKickListener implements Listener {

    BarPlugin plugin;

    public PlayerKickListener(BarPlugin plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerKick(PlayerKickEvent event) {
        Player player = event.getPlayer();
        DataDragon dragon = plugin.dataManager.removeDragon(player);
        dragon.removeDragon();
    }

}
