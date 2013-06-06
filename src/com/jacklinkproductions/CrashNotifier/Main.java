
package com.jacklinkproductions.CrashNotifier;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
    private static PluginDescriptionFile pdfFile;
	private static Server bukkitServer;
    
    @Override
    public void onDisable() {
        // Output info to console on disable
        getLogger().info("Thanks for using CrashNotifier!");
    }

    @Override
    public void onEnable() {
    	
        // Create default config if not exist yet.
        if (!new File(getDataFolder(), "config.yml").exists()) {
            saveDefaultConfig();
        }

        // Load configuration.
        reloadConfiguration();
        
        // Register our events
        getServer().getPluginManager().registerEvents(new CrashListener(this), this);
        getServer().getLogger().setFilter(new CrashNotifierFilter(this));

        // Register command executor.
        CrashCommand crashCommandExecutor = new CrashCommand(this);
        getCommand("crash").setExecutor(crashCommandExecutor);
        getCommand("crashnotifier").setExecutor(crashCommandExecutor);

        // Output info to console on load
        pdfFile = this.getDescription();
        getLogger().info( pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!" );
    }
    
    public void reloadConfiguration() {
        reloadConfig();
        CrashListener.joinmessage = getConfig().getString("default-messages.join");
        CrashListener.quitmessage = getConfig().getString("default-messages.quit");
        CrashListener.kickmessage = getConfig().getString("crash-messages.kick");
        CrashListener.spammessage = getConfig().getString("crash-messages.spam");
        CrashListener.genericmessage = getConfig().getString("crash-messages.genericReason");
        CrashListener.streammessage = getConfig().getString("crash-messages.endOfStream");
        CrashListener.overflowmessage = getConfig().getString("crash-messages.overflow");
        CrashListener.timeoutmessage = getConfig().getString("crash-messages.timeout");
    }
    
	public static PluginDescriptionFile getPDF()
	{
		return pdfFile;
	}

	public static Server getBukkitServer()
	{
		return bukkitServer;
	}

	public static String parseColor(String line)
	{
		return ChatColor.translateAlternateColorCodes('&', line);
	}
}