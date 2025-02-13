package mc.nekocraft.particles;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.Map;

public class PlayerParticles {
    private final Plugin plugin;
    private final Map<Player, ParticleLive> playerParticles;

    public PlayerParticles(Plugin plugin) {
        this.plugin = plugin;
        this.playerParticles = new HashMap<>();
    }

    public void startParticleEffect(Player player, ParticleType particleType) {
        stopParticleEffect(player); // Stop any existing particle effect for the player
        ParticleLive particleLive = new ParticleLive(plugin, player, particleType);
        particleLive.start();
        playerParticles.put(player, particleLive);
    }

    public void stopParticleEffect(Player player) {
        ParticleLive particleLive = playerParticles.remove(player);
        if (particleLive != null) {
            particleLive.stop();
        }
    }

    public void stopAll() {
        for (ParticleLive particleLive : playerParticles.values()) {
            particleLive.stop();
        }
        playerParticles.clear();
    }
}
