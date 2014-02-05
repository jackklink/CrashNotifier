
package com.jacklinkproductions.CrashNotifier;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.jacklinkproductions.CrashNotifier.Main;
import com.jacklinkproductions.CrashNotifier.Updater;
import com.jacklinkproductions.CrashNotifier.Updater.UpdateResult;

public class CrashCommand implements CommandExecutor {
	
    private final Main plugin;

    CrashCommand(Main plugin) {
        this.plugin = plugin;
    }
	
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (cmd.getName().equalsIgnoreCase("crashnotifier"))
		{
			sender.sendMessage(ChatColor.YELLOW + "-- " + Main.pdfFile.getName() + " v" + Main.pdfFile.getVersion() + " --");
			sender.sendMessage(ChatColor.RED + "/crash reload - Reload Config");
			sender.sendMessage(ChatColor.RED + "/crash update - Updates to latest version");
			sender.sendMessage(ChatColor.RED + "/crash - Fake Crash");
		    return true;
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
		        sender.sendMessage( ChatColor.GREEN + Main.pdfFile.getName() + " " + Main.pdfFile.getVersion() + "" );
		        
		        return true;
			}
            else if (args[0].equalsIgnoreCase("update"))
			{
				if (sender.hasPermission("crash.update") || sender.isOp()) 
				{
			        if (plugin.getConfig().getString("update-notification") == "false")
			        {
			            sender.sendMessage(ChatColor.RED + "This command is disabled in the config!");
		    		    return true;
			        }
			        
		            if(!plugin.updateAvailable) {
		                sender.sendMessage(ChatColor.YELLOW + "No updates are available!");
		    		    return true;
		            }
		            
		            Updater updater = new Updater(plugin, Main.updaterID, plugin.getFile(), Updater.UpdateType.DEFAULT, true);
		            if(updater.getResult() == UpdateResult.NO_UPDATE)
		                sender.sendMessage(ChatColor.YELLOW + "No updates are available!");
		            else
		            {
		                sender.sendMessage(ChatColor.YELLOW + "Updating... Check console for details.");
		            }
		            
	    		    return true;
				}
				else
				{
					sender.sendMessage(ChatColor.RED + "You do not have permissions to perform this command");
	    		    return true;
				}
			}
            else
            {
    			sender.sendMessage(ChatColor.YELLOW + "-- " + Main.pdfFile.getName() + " v" + Main.pdfFile.getVersion() + " --");
    			sender.sendMessage(ChatColor.RED + "/crash reload - Reload Config");
    			sender.sendMessage(ChatColor.RED + "/crash update - Updates to latest version");
    			sender.sendMessage(ChatColor.RED + "/crash - Fake Crash");
    		    return true;
            }
		}
		
		return false;
    }
}
