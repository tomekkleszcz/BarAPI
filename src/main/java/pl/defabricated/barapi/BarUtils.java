package pl.defabricated.barapi;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import org.bukkit.entity.Player;

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

}
