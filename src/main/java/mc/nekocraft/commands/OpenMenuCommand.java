package mc.nekocraft.commands;

import mc.nekocraft.gui.ExampleMenu;
import mc.nekocraft.utils.MessageUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class OpenMenuCommand implements CommandExecutor {
    private final Plugin plugin;
    private final MessageUtil messageUtil;

    public OpenMenuCommand(Plugin plugin, MessageUtil messageUtil) {
        this.plugin = plugin;
        this.messageUtil = messageUtil;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            ExampleMenu menu = new ExampleMenu(plugin, messageUtil);
            player.openInventory(menu.getInventory());
            player.sendMessage(messageUtil.getMessage("messages.menu_opened"));
            return true;
        }
        sender.sendMessage(messageUtil.getMessage("messages.player_only_command"));
        return false;
    }
}
