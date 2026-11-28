package mc.nekocraft.gui;

import mc.nekocraft.particles.ParticleType;
import mc.nekocraft.utils.MessageUtil;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.List;

public class ExampleMenu implements Listener {
    private final Inventory inventory;
    private final String title;
    private final MessageUtil messageUtil;

    public ExampleMenu(Plugin plugin, MessageUtil messageUtil) {
        this.title = messageUtil.getMessage("inventory.title");
        this.messageUtil = messageUtil;
        GuiBuilder builder = new GuiBuilder(plugin, title, 54);
        this.inventory = builder.getInventory();
        builder.registerEvents(this);
        initializeItems();
    }

    private void initializeItems() {
        int[] slots = {10, 11, 12, 13, 14, 15, 16, 19, 20, 21, 22, 23, 24, 25, 28, 29, 30, 31, 32, 33, 34};
        int index = 0;
        for (ParticleType particle : ParticleType.values()) {
            if (index >= slots.length) break;
            String name = messageUtil.getMessage("particles." + particle.name() + ".name");
            List<String> lore = messageUtil.getMessageList("particles." + particle.name() + ".lore");
            Material icon = Material.valueOf(messageUtil.getMessage("particles." + particle.name() + ".icon"));
            inventory.setItem(slots[index++], createGuiItem(icon, name, lore));
        }
        // Agregar botón de reinicio de partículas
        String resetName = messageUtil.getMessage("inventory.reset_button.name");
        List<String> resetLore = messageUtil.getMessageList("inventory.reset_button.lore");
        inventory.setItem(49, createGuiItem(Material.RED_STAINED_GLASS, resetName, resetLore));
    }

    private ItemStack createGuiItem(Material material, String name, List<String> lore) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public String getTitle() {
        return title;
    }
}
