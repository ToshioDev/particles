package mc.nekocraft.listeners;

import mc.nekocraft.gui.GuiHolder;
import mc.nekocraft.particles.ParticleType;
import mc.nekocraft.particles.PlayerParticles;
import mc.nekocraft.utils.MessageUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.Plugin;

public class GuiListener implements Listener {
    private final PlayerParticles playerParticles;
    private final MessageUtil messageUtil;

    public GuiListener(Plugin plugin, MessageUtil messageUtil) {
        this.playerParticles = new PlayerParticles(plugin);
        this.messageUtil = messageUtil;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getInventory().getHolder() instanceof GuiHolder) {
            event.setCancelled(true);
            if (event.getWhoClicked() instanceof Player) {
                Player player = (Player) event.getWhoClicked();
                int slot = event.getSlot();
                int[] slots = {10, 11, 12, 13, 14, 15, 16, 19, 20, 21, 22, 23, 24, 25, 28, 29, 30, 31, 32, 33, 34};
                if (slot == 49) {
                    player.sendMessage(messageUtil.getMessage("messages.particles_reset"));
                    playerParticles.stopParticleEffect(player);
                } else {
                    for (int i = 0; i < slots.length; i++) {
                        if (slot == slots[i]) {
                            ParticleType selectedParticle = ParticleType.values()[i];
                            if (player.hasPermission("particles." + selectedParticle.name().toLowerCase()) || player.hasPermission("particles.all")) {
                                applyParticleEffect(player, selectedParticle);
                            } else {
                                player.sendMessage(messageUtil.getMessage("messages.no_permission"));
                            }
                            break;
                        }
                    }
                }
            }
        }
    }

    private void applyParticleEffect(Player player, ParticleType particle) {
        player.sendMessage(messageUtil.getMessage("messages.particle_selected").replace("{particle}", particle.getDisplayName()));
        playerParticles.startParticleEffect(player, particle);
    }
}