package pl.defabricated.barapi.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleExitEvent;
import pl.defabricated.barapi.BarPlugin;
import pl.defabricated.barapi.data.DataDragon;

public class VehicleExitListener implements Listener {

    BarPlugin plugin;

    public VehicleExitListener(BarPlugin plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onVehicleExit(VehicleExitEvent event) {
        if(event.getExited() instanceof Player) {
            Player player = (Player) event.getExited();
            DataDragon dragon = plugin.dataManager.getDragonByPlayer(player);
            if(dragon != null) {
                dragon.removeDragon();
            }
        }
    }

}
