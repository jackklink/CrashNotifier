
package com.jacklinkproductions.CrashNotifier;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class CrashListener implements Listener {
	
    private final Main plugin;
    public static boolean crashPerm = false;
    public static boolean quitPerm = false;
    public static boolean fakeCrashPerm = false;
    public static boolean fakeCrash = false;
    public static boolean filterCheckQuitting = false;
    public static boolean filterCheckKick = false;
    public static boolean filterCheckSpam = false;
    public static boolean filterCheckGeneric = false;
    public static boolean filterCheckStream = false;
    public static boolean filterCheckOverflow = false;
    public static boolean filterCheckTimeout = false;
    public static String joinmessage = "&e{player} joined the game.";
    public static String quitmessage = "&e{player} left the game.";
    public static String kickmessage = "&e{player} has been kicked.";
    public static String spammessage = "&e{player} has been kicked for spam.";
    public static String timeoutmessage = "&e{player} has lost connection. &cTimeout";
    public static String overflowmessage = "&e{player} has lost connection. &cOverflow";
    public static String streammessage = "&e{player} has lost connection. &cEnd of Stream";
    public static String genericmessage = "&e{player} has lost connection. &cGeneric Reason";


    public CrashListener(Main instance) {
        plugin = instance;
    }
    
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

    	fakeCrash = false;
	    filterCheckQuitting = false;
	    filterCheckKick = false;
	    filterCheckSpam = false;
	    filterCheckGeneric = false;
	    filterCheckStream = false;
	    filterCheckOverflow = false;
	    filterCheckTimeout = false;
    	
	    if (event.getPlayer().hasPermission("crashnotifier.join")) {

	        String message = null;
	    	int online = plugin.getServer().getOnlinePlayers().length;
	    	
	    	String m = joinmessage.replace("{player}", event.getPlayer().getName());
			message	= m.replace("{online}", ""+online);
			
			event.setJoinMessage(Main.parseColor(message));
	    }
    }
    
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
    	
    	if (fakeCrash) { return; } //DONT DO THIS IF THE CRASH WAS FAKE
    	
	    if (event.getPlayer().hasPermission("crashnotifier.crash"))
	    {
	        crashPerm = true;
	    }
	    if (event.getPlayer().hasPermission("crashnotifier.quit"))
	    {
	        quitPerm = true;
	    }

        String message = null;
    	int online = plugin.getServer().getOnlinePlayers().length - 1;
    	
		if (filterCheckGeneric && crashPerm) {
			String m = genericmessage.replace("{player}", event.getPlayer().getName());
			message	= m.replace("{online}", ""+online);
			plugin.getLogger().info(event.getPlayer().getName() + " crashed!");
		}
		else if (filterCheckStream && crashPerm) {
			String m = streammessage.replace("{player}", event.getPlayer().getName());
			message	= m.replace("{online}", ""+online);
			plugin.getLogger().info(event.getPlayer().getName() + " crashed!");
		}
		else if (filterCheckOverflow && crashPerm) {
			String m = overflowmessage.replace("{player}", event.getPlayer().getName());
			message	= m.replace("{online}", ""+online);
			plugin.getLogger().info(event.getPlayer().getName() + " crashed!");
		}
		else if (filterCheckTimeout && crashPerm) {
			String m = timeoutmessage.replace("{player}", event.getPlayer().getName());
			message	= m.replace("{online}", ""+online);
			plugin.getLogger().info(event.getPlayer().getName() + " crashed!");
		}
		else if (filterCheckSpam && crashPerm) {
			String m = spammessage.replace("{player}", event.getPlayer().getName());
			message	= m.replace("{online}", ""+online);
		}
		else if (filterCheckKick && crashPerm) {
			String m = kickmessage.replace("{player}", event.getPlayer().getName());
			message	= m.replace("{online}", ""+online);
		}
		else if (filterCheckQuitting && quitPerm) {
			String m = quitmessage.replace("{player}", event.getPlayer().getName());
			message	= m.replace("{online}", ""+online);
		}
		else
		{
			return;
		}

		if (quitPerm || crashPerm)
		{
			event.setQuitMessage(Main.parseColor(message));
		}
    }
    
    @EventHandler
    public void onPlayerKick(PlayerKickEvent event) {
    	
	    if (event.getPlayer().hasPermission("crashnotifier.fakecrash"))
	    {
	        fakeCrashPerm = true;
	    }

        String message = null;
    	int online = plugin.getServer().getOnlinePlayers().length - 1;
    	
		if (filterCheckGeneric) {
			String m = genericmessage.replace("{player}", event.getPlayer().getName());
			message	= m.replace("{online}", ""+online);
			plugin.getLogger().info(event.getPlayer().getName() + " fake crashed!");
		}
		else if (filterCheckStream) {
			String m = streammessage.replace("{player}", event.getPlayer().getName());
			message	= m.replace("{online}", ""+online);
			plugin.getLogger().info(event.getPlayer().getName() + " fake crashed!");
		}
		else if (filterCheckOverflow) {
			String m = overflowmessage.replace("{player}", event.getPlayer().getName());
			message	= m.replace("{online}", ""+online);
			plugin.getLogger().info(event.getPlayer().getName() + " fake crashed!");
		}
		else if (filterCheckTimeout) {
			String m = timeoutmessage.replace("{player}", event.getPlayer().getName());
			message	= m.replace("{online}", ""+online);
			plugin.getLogger().info(event.getPlayer().getName() + " fake crashed!");
		}
		else
		{
			return;
		}
		
		if (fakeCrashPerm)
		{
			event.setLeaveMessage(Main.parseColor(message));
		}
    }
}