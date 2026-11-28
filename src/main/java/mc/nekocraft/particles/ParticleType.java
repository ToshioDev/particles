package mc.nekocraft.particles;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Particle;

public enum ParticleType {
    WATER_SPLASH(Material.WATER_BUCKET, "Water Splash"),
    DRIP_WATER(Material.WATER_BUCKET, "Drip Water"),
    DRIP_LAVA(Material.LAVA_BUCKET, "Drip Lava"),
    CRIT(Material.DIAMOND_SWORD, "Crit"),
    CRIT_MAGIC(Material.DIAMOND_SWORD, "Magic Crit"),
    SPELL(Material.POTION, "Spell"),
    SPELL_INSTANT(Material.POTION, "Instant Spell"),
    SPELL_MOB(Material.POTION, "Mob Spell"),
    SPELL_WITCH(Material.POTION, "Witch Spell"),
    VILLAGER_ANGRY(Material.ENDER_CHEST, "Angry Villager"),
    VILLAGER_HAPPY(Material.ENDER_CHEST, "Happy Villager"),
    TOWN_AURA(Material.NOTE_BLOCK, "Town Aura"),
    NOTE(Material.NOTE_BLOCK, "Note"),
    PORTAL(Material.ENDER_PEARL, "Portal"),
    ENCHANTMENT_TABLE(Material.ENCHANTING_TABLE, "Enchantment Table"),
    FLAME(Material.FIRE_CHARGE, "Flame"),
    REDSTONE(Material.REDSTONE, "Redstone", new Particle.DustOptions(Color.RED, 1)),
    HEART(Material.POPPY, "Heart"),
    FIREWORKS_SPARK(Material.FIREWORK_ROCKET, "Fireworks Spark"),
    SMOKE_NORMAL(Material.FIREWORK_STAR, "Smoke"),
    SLIME(Material.SLIME_BALL, "Slime"),
    RAINBOW_SPELL(Material.POTION, "Rainbow Spell");

    private final Material icon;
    private final String displayName;
    private final Object data;

    ParticleType(Material icon, String displayName) {
        this(icon, displayName, null);
    }

    ParticleType(Material icon, String displayName, Object data) {
        this.icon = icon;
        this.displayName = displayName;
        this.data = data;
    }

    public Material getIcon() {
        return icon;
    }

    public String getDisplayName() {
        return displayName;
    }

    public boolean requiresData() {
        return data != null;
    }

    public Object getData() {
        return data;
    }
}
