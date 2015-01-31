package pl.defabricated.barapi.data;

import org.bukkit.entity.Player;
import pl.defabricated.barapi.BarUtils;

public class DataDragon {

    public DataDragon(Player player, String message, float health, long time) {
        this.player = player;
        this.message = message;
        this.health = health;
        this.time = time;
    }

    private boolean cMessage, cHealth;

    private Player player;
    private String message;
    private float health;
    private long time;
    private boolean sent = false;

    public void setMessage(String message) { this.message = message; cMessage = true; }
    public void setHealth(float health) { this.health = health; cHealth = true; }
    public void setTime(long time) { this.time = time; }

    public Player getPlayer() { return player; }
    public String getMessage() { return message; }
    public float getHealth() { return health; }
    public long getTime() { return time; }
    public boolean isSent() { return sent; }

    public void sendUpdate() {
        if(time <= System.currentTimeMillis() && time > 0L) {
            sent = false;
            BarUtils.sendRemovePacket(player);
            return;
        }
        if(!sent) {
            sent = true;
            cHealth = false;
            cMessage = false;
            BarUtils.sendSpawnPacket(player, message, health);
            return;
        } else {
            if (cHealth || cMessage) {
                cHealth = false;
                cMessage = false;
                BarUtils.sendUpdatePacket(player);
            }
            BarUtils.sendTeleportPacket(player);
        }
    }

    public void removeDragon() {
        sent = false;
        BarUtils.sendRemovePacket(player);
    }

}
