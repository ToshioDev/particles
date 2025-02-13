package mc.nekocraft.gui;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;

public class GuiBuilder {
    private final Inventory inventory;
    private final Plugin plugin;

    public GuiBuilder(Plugin plugin, String title, int size) {
        this.plugin = plugin;
        this.inventory = Bukkit.createInventory(new GuiHolder(), size, title);
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void registerEvents(Listener listener) {
        Bukkit.getPluginManager().registerEvents(listener, plugin);
    }
}
