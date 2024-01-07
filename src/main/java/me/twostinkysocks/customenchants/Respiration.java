package me.twostinkysocks.customenchants;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityAirChangeEvent;

public class Respiration implements Listener {

    private static int prevAmount = 10;

    @EventHandler
    public void onAirChange(EntityAirChangeEvent e) {
        if(e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            if(p.getInventory().getHelmet() != null && p.getInventory().getHelmet().hasItemMeta() && p.getInventory().getHelmet().getItemMeta().hasEnchants() && p.getInventory().getHelmet().getItemMeta().hasEnchant(Enchantment.OXYGEN)) {
                if(p.isInWater() && p.getLocation().getBlock().getRelative(BlockFace.UP).getBlockData().getMaterial() == Material.WATER) {
                    e.setAmount(e.getAmount() + ((int)((e.getAmount() - prevAmount) * CustomEnchants.instance.getConfig().getDouble("respiration-effectiveness-multiplier"))));
                }
            }
        }
        prevAmount = e.getAmount();
    }
}
