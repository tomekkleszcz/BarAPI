package pl.defabricated.barapi;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.WrappedDataWatcher;
import net.minecraft.server.v1_7_R4.EntityPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.spigotmc.ProtocolData;
import pl.defabricated.barapi.data.DataDragon;

import java.lang.reflect.InvocationTargetException;

public class BarUtils {

    static BarPlugin plugin;

    public BarUtils(BarPlugin barPlugin) {
        plugin = barPlugin;
    }

    public static void sendRemovePacket(Player player) {
        PacketContainer packet = plugin.protocolManager.createPacket(PacketType.Play.Server.ENTITY_DESTROY);
        int[] ids = new int[]{ -1 };
        packet.getIntegerArrays().write(0, ids);

        try {
            plugin.protocolManager.sendServerPacket(player, packet);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static void sendSpawnPacket(Player player, String message, float health) {
        Location loc = getDragonLocation(player);

        PacketContainer packet = plugin.protocolManager.createPacket(PacketType.Play.Server.SPAWN_ENTITY_LIVING);
        packet.getIntegers().write(0, -1).write(1, (int) EntityType.ENDER_DRAGON.getTypeId()).write(2, (int) Math.floor(loc.getBlockX() * 32.0D)).write(3, (int) Math.floor(loc.getBlockY() * 32.0D)).write(4, (int) Math.floor(loc.getBlockZ() * 32.0D)).write(5, 0).write(6, 0).write(7, 0);

        WrappedDataWatcher watcher = new WrappedDataWatcher();
        watcher.setEntity(player);
        watcher.setObject(0, (Byte) (byte) 0x20);
        watcher.setObject(6, (Float) (float) health * 2);
        if(useHack(player)) {
            watcher.setObject(2, (String) message);
            watcher.setObject(3, (Byte) (byte) 1);
        } else {
            watcher.setObject(10, (String) message);
            watcher.setObject(11, (Byte) (byte) 1);
        }

        packet.getDataWatcherModifier().write(0, watcher);

        try {
            plugin.protocolManager.sendServerPacket(player, packet);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static void sendTeleportPacket(Player player) {
        Location loc = getDragonLocation(player);

        PacketContainer packet = plugin.protocolManager.createPacket(PacketType.Play.Server.ENTITY_TELEPORT);
        packet.getIntegers().write(0, -1).write(1, (int) Math.floor(loc.getX() * 32.0D)).write(2, (int) Math.floor(loc.getY() * 32.0D)).write(3, (int) Math.floor(loc.getZ() * 32.0D));
        packet.getBytes().write(0, (byte) 0).write(1, (byte) 0);

        try {
            plugin.protocolManager.sendServerPacket(player, packet);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static void sendUpdatePacket(Player player) {
        PacketContainer packet = plugin.protocolManager.createPacket(PacketType.Play.Server.ENTITY_METADATA);
        packet.getIntegers().write(0, -1);

        DataDragon dragon = plugin.dataManager.getDragonByPlayer(player);

        WrappedDataWatcher watcher = new WrappedDataWatcher();
        watcher.setEntity(player);
        watcher.setObject(0, (Byte) (byte) 0x20);
        watcher.setObject(6, (Float) (float) (dragon != null ? dragon.getHealth() * 2 : 0F));
        if(useHack(player)) {
            watcher.setObject(2, (String) (dragon != null ? dragon.getMessage() : ""));
            watcher.setObject(3, (Byte) (byte) (dragon == null ? 0 : (dragon.getTime() <= System.currentTimeMillis() ? 0 : 1)));
        } else {
            watcher.setObject(10, (String) (dragon != null ? dragon.getMessage() : ""));
            watcher.setObject(11, (Byte) (byte) (dragon == null ? 0 : (dragon.getTime() <= System.currentTimeMillis() ? 0 : 1)));
        }

        packet.getWatchableCollectionModifier().write(0, watcher.getWatchableObjects());

        try {
            plugin.protocolManager.sendServerPacket(player, packet);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private static boolean useHack(Player player) {
        try {
            EntityPlayer entityPlayer = ((org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer)player).getHandle();
            return entityPlayer.playerConnection.networkManager.getVersion() >= 47;
        } catch (NoClassDefFoundError ex) { }
        return false;
    }

    private static Location getDragonLocation(Player player) {
        Location loc = player.getLocation().clone();

        if(!useHack(player)) {
            return loc.subtract(0, -200, 0);
        }

        loc = player.getLocation().add(player.getLocation().getDirection().normalize().multiply(64));

        float pitch = loc.getPitch();
        if (pitch >= 55) {
            loc.setY(player.getLocation().getY() - 32);
        } else if (pitch <= -55) {
            loc.add(0, 200, 0);
        } else {
            loc.add(0, 50, 0);
        }

        return loc;
    }

}
