package pl.defabricated.barapi;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import pl.defabricated.barapi.data.DataManager;

public class BarPlugin extends JavaPlugin {

    public DataManager dataManager;

    BarAPI api;
    BarUtils barUtils;

    ProtocolManager protocolManager;

    BukkitTask messageTask;

    @Override
    public void onEnable() {
        this.dataManager = new DataManager(this);

        this.api = new BarAPI(this);
        this.barUtils = new BarUtils(this);

        this.protocolManager = ProtocolLibrary.getProtocolManager();

        this.messageTask = new MessageTask(this).runTaskTimerAsynchronously(this, 20L, 20L);
    }

}
