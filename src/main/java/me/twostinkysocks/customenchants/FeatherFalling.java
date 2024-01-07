package me.twostinkysocks.customenchants;

import com.google.common.base.Preconditions;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import java.lang.reflect.Field;
import java.util.Map;

public class FeatherFalling implements Listener {

    @EventHandler
    public void onFall(EntityDamageEvent e) {
        if(e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            if(e.getCause() == EntityDamageEvent.DamageCause.FALL) {
                if(p.getInventory().getBoots() != null && p.getInventory().getBoots().hasItemMeta() && p.getInventory().getBoots().getItemMeta().hasEnchants() && p.getInventory().getBoots().getItemMeta().hasEnchant(Enchantment.PROTECTION_FALL)) {
                    try {
                        double d = getFinalDamage(e); // xd
                        e.setDamage(0);
                        p.damage(d);
                    } catch (NoSuchFieldException | IllegalAccessException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }

    private double getFinalDamage(EntityDamageEvent e) throws NoSuchFieldException, IllegalAccessException {
        double damage = 0;
        for (EntityDamageEvent.DamageModifier modifier : EntityDamageEvent.DamageModifier.values()) {
            damage += getDamage(e, modifier);
        }
        return damage;
    }

    private double getDamage(EntityDamageEvent e, EntityDamageEvent.DamageModifier type) throws IllegalArgumentException, NoSuchFieldException, IllegalAccessException {
        Preconditions.checkArgument(type != null, "Cannot have null DamageModifier");
        Field f = EntityDamageEvent.class.getDeclaredField("modifiers");
        f.setAccessible(true);
        Map<EntityDamageEvent.DamageModifier, Double> modifiers = (Map<EntityDamageEvent.DamageModifier, Double>) f.get(e);
        modifiers.put(EntityDamageEvent.DamageModifier.MAGIC, modifiers.get(EntityDamageEvent.DamageModifier.MAGIC) * CustomEnchants.instance.getConfig().getDouble("feather-falling-effectiveness-multiplier"));
        final Double damage = modifiers.get(type);
        return damage == null ? 0 : damage;
    }
}
