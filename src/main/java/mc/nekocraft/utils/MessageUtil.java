package mc.nekocraft.utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.List;

public class MessageUtil {
    private final Plugin plugin;
    private FileConfiguration messagesConfig;

    public MessageUtil(Plugin plugin) {
        this.plugin = plugin;
        loadMessages();
    }

    private void loadMessages() {
        File messagesFile = new File(plugin.getDataFolder(), "messages.yml");
        if (!messagesFile.exists()) {
            plugin.saveResource("messages.yml", false);
        }
        messagesConfig = YamlConfiguration.loadConfiguration(messagesFile);
    }

    public String getMessage(String path) {
        return messagesConfig.getString(path);
    }

    public String getMessage(String path, String defaultValue) {
        return messagesConfig.getString(path, defaultValue);
    }

    public List<String> getMessageList(String path) {
        return messagesConfig.getStringList(path);
    }
}
