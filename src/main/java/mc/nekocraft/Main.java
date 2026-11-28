package mc.nekocraft;

import mc.nekocraft.commands.OpenMenuCommand;
import mc.nekocraft.listeners.GuiListener;
import mc.nekocraft.particles.PlayerParticles;
import mc.nekocraft.utils.MessageUtil;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    private PlayerParticles playerParticles;
    private MessageUtil messageUtil;

    @Override
    public void onEnable() {
        getLogger().info("Particles plugin enabled!");
        messageUtil = new MessageUtil(this);
        playerParticles = new PlayerParticles(this);
        OpenMenuCommand openMenuCommand = new OpenMenuCommand(this, messageUtil);
        getCommand("particles").setExecutor(openMenuCommand);
        getServer().getPluginManager().registerEvents(new GuiListener(this, messageUtil), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("Particles plugin disabled!");
        if (playerParticles != null) {
            playerParticles.stopAll();
        }
    }

    public MessageUtil getMessageUtil() {
        return messageUtil;
    }
}