package me.twostinkysocks.customenchants;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class Thorns implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if (e.getCause() == EntityDamageEvent.DamageCause.THORNS) {
            e.setDamage(e.getFinalDamage() * CustomEnchants.instance.getConfig().getDouble("thorns-effectiveness-multiplier"));
        }
    }

}