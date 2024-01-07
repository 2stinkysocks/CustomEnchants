package me.twostinkysocks.customenchants;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemMendEvent;

public class Mending implements Listener {
    @EventHandler
    public void onDamageItem(PlayerItemMendEvent e) {
        e.setRepairAmount((int)(e.getRepairAmount()*CustomEnchants.instance.getConfig().getDouble("mending-effectiveness-multiplier")));
    }
}
