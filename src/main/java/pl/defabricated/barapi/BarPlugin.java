package pl.defabricated.barapi;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import pl.defabricated.barapi.data.DataManager;
import pl.defabricated.barapi.listeners.PlayerKickListener;
import pl.defabricated.barapi.listeners.PlayerQuitListener;

public class BarPlugin extends JavaPlugin implements Listener {

    public DataManager dataManager;

    BarAPI api;
    BarUtils barUtils;

    ProtocolManager protocolManager;

    BukkitTask messageTask;

    PlayerKickListener playerKickListener;
    PlayerQuitListener playerQuitListener;

    @Override
    public void onEnable() {
        this.dataManager = new DataManager(this);

        this.api = new BarAPI(this);
        this.barUtils = new BarUtils(this);

        this.protocolManager = ProtocolLibrary.getProtocolManager();

        this.messageTask = new MessageTask(this).runTaskTimerAsynchronously(this, 20L, 20L);

        this.playerKickListener = new PlayerKickListener(this);
        this.playerQuitListener = new PlayerQuitListener(this);
    }

}
