package pl.defabricated.barapi;

import org.bukkit.plugin.java.JavaPlugin;
import pl.defabricated.barapi.data.DataManager;

public class BarPlugin extends JavaPlugin {

    public DataManager dataManager;

    BarAPI api;

    @Override
    public void onEnable() {
        this.dataManager = new DataManager(this);

        this.api = new BarAPI(this);
    }

}
