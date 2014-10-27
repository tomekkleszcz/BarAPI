package pl.defabricated.barapi;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import pl.defabricated.barapi.data.DataDragon;

public class MessageTask extends BukkitRunnable {

    BarPlugin plugin;

    public MessageTask(BarPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        for(Player player : Bukkit.getOnlinePlayers()) {
            DataDragon dragon = plugin.dataManager.getDragonByPlayer(player);
            if(dragon != null && dragon.isSent()) {
                dragon.sendUpdate();
            }
        }
    }

}
