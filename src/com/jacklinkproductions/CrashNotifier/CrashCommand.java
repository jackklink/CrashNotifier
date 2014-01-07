
package com.jacklinkproductions.CrashNotifier;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;

public class CrashCommand implements CommandExecutor {
	
    private final Main plugin;

    CrashCommand(Main plugin) {
        this.plugin = plugin;
    }
	
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (cmd.getName().equalsIgnoreCase("crashnotifier"))
		{
	        if (args.length == 0 || args[0].equalsIgnoreCase("version") || args[0].equalsIgnoreCase("info"))
			{
		        PluginDescriptionFile pdfFile = Main.getPDF();
		        sender.sendMessage( ChatColor.GREEN + pdfFile.getName() + " " + pdfFile.getVersion() + "" );
		        
		        return true;
			}
			else if (args[0].equalsIgnoreCase("reload") && sender.hasPermission("crashnotifier.reload"))
			{
                plugin.reloadConfiguration();
                sender.sendMessage(ChatColor.GREEN + "Configuration reloaded.");
                
		        return true;
			}
		}
	    else if (cmd.getName().equalsIgnoreCase("crash"))
		{
	    	Player player = (Player) sender;
			CrashListener.fakeCrash = true;
	    	
	        if (args.length == 0 && sender.hasPermission("crashnotifier.fakecrash"))
			{
				player.kickPlayer("You have fake crashed! \"Network Error\"");
		        return true;
			}
			else if (args[0].equalsIgnoreCase("reload") && sender.hasPermission("crashnotifier.reload"))
			{
                plugin.reloadConfiguration();
                sender.sendMessage(ChatColor.GREEN + "Configuration reloaded.");
                
		        return true;
			}
			else if (args[0].equalsIgnoreCase("version") || args[0].equalsIgnoreCase("info"))
			{
		        PluginDescriptionFile pdfFile = Main.getPDF();
		        sender.sendMessage( ChatColor.GREEN + pdfFile.getName() + " " + pdfFile.getVersion() + "" );
		        
		        return true;
			}
		}
		
		return false;
    }
}
