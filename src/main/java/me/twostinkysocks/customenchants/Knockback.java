package me.twostinkysocks.customenchants;


import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class Knockback implements Listener {

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        if(e.getDamager() instanceof Player) {
            Player p = (Player) e.getDamager();
            if(p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInMainHand().getItemMeta().hasEnchants() && p.getInventory().getItemInMainHand().getItemMeta().hasEnchant(Enchantment.KNOCKBACK)) {
                Entity damaged = e.getEntity();
                damaged.setVelocity(p.getLocation().getDirection().setY(0).normalize().multiply(CustomEnchants.instance.getConfig().getDouble("knockback-effectiveness-multiplier")));
            }
        }
    }

}


