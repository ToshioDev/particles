package mc.nekocraft.particles;

import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.bukkit.plugin.Plugin;

public class ParticleLive {
    private final Plugin plugin;
    private final Player player;
    private final ParticleType particleType;
    private BukkitRunnable task;

    public ParticleLive(Plugin plugin, Player player, ParticleType particleType) {
        this.plugin = plugin;
        this.player = player;
        this.particleType = particleType;
    }

    public void start() {
        task = new BukkitRunnable() {
            int step = 0;

            @Override
            public void run() {
                if (player.isOnline()) {
                    if (step >= Integer.MAX_VALUE) {
                        step = 0;
                    }

                    double angle = step * 0.07853981633974483D; // 0.07853981633974483D = Math.PI / 40
                    double radius = 0.43D;
                    Vector offset = new Vector(Math.cos(angle) * radius, 0, Math.sin(angle) * radius);

                    switch (particleType) {
                        case RAINBOW_SPELL:
                            Particle[] rainbowParticles = {
                                Particle.ENTITY_EFFECT, Particle.WITCH, Particle.INSTANT_EFFECT
                            };
                            Particle rainbowParticle = rainbowParticles[step % rainbowParticles.length];
                            player.getWorld().spawnParticle(rainbowParticle, player.getLocation().add(offset).add(0, 2, 0), 1);
                            break;
                        case CRIT:
                        case CRIT_MAGIC:
                            player.getWorld().spawnParticle(Particle.valueOf(particleType.name()), player.getLocation().add(offset).add(0, 2, 0), 1);
                            break;
                        case REDSTONE:
                            Particle.DustOptions dustOptions = (Particle.DustOptions) particleType.getData();
                            player.getWorld().spawnParticle(Particle.DUST, player.getLocation().add(offset).add(0, 2, 0), 1, dustOptions);
                            break;
                        case SPELL_MOB:
                            if (particleType.getData() instanceof Color) {
                                Color color = (Color) particleType.getData();
                                player.getWorld().spawnParticle(Particle.ENTITY_EFFECT, player.getLocation().add(offset).add(0, 2, 0), 1, color.getRed() / 255.0, color.getGreen() / 255.0, color.getBlue() / 255.0, 1.0);
                            }
                            break;
                        default:
                            player.getWorld().spawnParticle(Particle.valueOf(particleType.name()), player.getLocation().add(offset).add(0, 2, 0), 1);
                            break;
                    }
                    step += 4;
                } else {
                    cancel();
                }
            }
        };
        task.runTaskTimerAsynchronously(plugin, 0L, 2L);
    }

    public void stop() {
        if (task != null) {
            task.cancel();
            task = null;
        }
    }
}
