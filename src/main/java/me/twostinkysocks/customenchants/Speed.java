package me.twostinkysocks.customenchants;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerToggleSprintEvent;
import org.bukkit.event.player.PlayerVelocityEvent;

import java.util.HashMap;
import java.util.UUID;

public class Speed implements Listener {
    private static HashMap<UUID, Double> defaultMovementSpeeds = new HashMap<>();

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        defaultMovementSpeeds.put(e.getPlayer().getUniqueId(), e.getPlayer().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue());
        e.getPlayer().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(defaultMovementSpeeds.get(e.getPlayer().getUniqueId())*CustomEnchants.instance.getConfig().getDouble("movement-speed-multiplier"));
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        defaultMovementSpeeds.remove(e.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onToggleSprint(PlayerToggleSprintEvent e) {
        if(e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).getBlockData().getMaterial() == Material.SOUL_SAND) return; // handle soul sand seperately
        if(e.isSprinting()) { // start sprint
            e.getPlayer().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(defaultMovementSpeeds.get(e.getPlayer().getUniqueId())*CustomEnchants.instance.getConfig().getDouble("sprint-speed-multiplier"));
        } else { // end sprint
            e.getPlayer().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(defaultMovementSpeeds.get(e.getPlayer().getUniqueId())*CustomEnchants.instance.getConfig().getDouble("movement-speed-multiplier"));
        }
    }

    @EventHandler
    public void onVelocityChange(PlayerVelocityEvent e) {
        if(e.getPlayer().getInventory().getBoots() != null && e.getPlayer().getInventory().getBoots().hasItemMeta() && e.getPlayer().getInventory().getBoots().getItemMeta().hasEnchant(Enchantment.SOUL_SPEED)) {
            if(e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).getBlockData().getMaterial() == Material.SOUL_SAND) {
                e.getPlayer().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(defaultMovementSpeeds.get(e.getPlayer().getUniqueId())*CustomEnchants.instance.getConfig().getDouble("soul-speed-multiplier"));
            }
        } else if(e.getPlayer().getInventory().getBoots() != null && e.getPlayer().getInventory().getBoots().hasItemMeta() && e.getPlayer().getInventory().getBoots().getItemMeta().hasEnchant(Enchantment.DEPTH_STRIDER)) {
            if(e.getPlayer().isInWater()) {
                e.getPlayer().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(defaultMovementSpeeds.get(e.getPlayer().getUniqueId())*CustomEnchants.instance.getConfig().getDouble("depth-strider-multiplier"));
            }
        }
    }
}
