package com.jacklinkproductions.CrashNotifier;

import java.io.File;
import org.apache.logging.log4j.LogManager;

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
            getLogger().info( "Creating config.yml" );
            saveDefaultConfig();
        }

        // Load configuration
        reloadConfiguration();
        
        // Check for old config
        if ((getConfig().isSet("config-version") == false) || (getConfig().getInt("config-version") < 2))
        {
            File file = new File(this.getDataFolder(), "config.yml");
            file.delete();
            saveDefaultConfig();
            getLogger().info( "Created a new config.yml for this version." );
        }
        
        // Setup Updater system
        if (getConfig().getString("update-notification") == "true")
        {
        	new Updater(this, 54918, this.getFile(), Updater.UpdateType.DEFAULT, false);
        }
        
        // Register our events
        getServer().getPluginManager().registerEvents(new CrashListener(this), this);
        //getLogger().addFilter(new CrashNotifierFilter(this));

        // Register logger (1.7 HAX) :(
        ((org.apache.logging.log4j.core.Logger) LogManager.getRootLogger()).addFilter(new CrashNotifierFilter(this));

        // Register command executor.
        CrashCommand crashCommandExecutor = new CrashCommand(this);
        getCommand("crash").setExecutor(crashCommandExecutor);
        getCommand("crashnotifier").setExecutor(crashCommandExecutor);

        // Output info to console on load
        pdfFile = this.getDescription();
        getLogger().info( pdfFile.getName() + " v" + pdfFile.getVersion() + " is enabled!" );
    }
    
    public void reloadConfiguration() {
        reloadConfig();
        CrashListener.joinmessage = getConfig().getString("default-messages.join");
        CrashListener.quitmessage = getConfig().getString("default-messages.quit");
        CrashListener.kickmessage = getConfig().getString("crash-messages.kick");
        CrashListener.spammessage = getConfig().getString("crash-messages.spam");
        CrashListener.hostmessage = getConfig().getString("crash-messages.remoteHost");
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