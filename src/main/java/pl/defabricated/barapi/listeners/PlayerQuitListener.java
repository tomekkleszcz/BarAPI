package pl.defabricated.barapi.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import pl.defabricated.barapi.BarPlugin;
import pl.defabricated.barapi.data.DataDragon;

public class PlayerQuitListener implements Listener {

    BarPlugin plugin;

    public PlayerQuitListener(BarPlugin plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        DataDragon dragon = plugin.dataManager.removeDragon(player);
        if(dragon != null) {
            dragon.removeDragon();
        }
    }

}
