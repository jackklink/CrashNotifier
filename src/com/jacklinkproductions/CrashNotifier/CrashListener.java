
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
    public static boolean filterCheckHost = false;
    public static String joinmessage = "&e{player} joined the game.";
    public static String quitmessage = "&e{player} left the game.";
    public static String kickmessage = "&e{player} has been kicked.";
    public static String spammessage = "&e{player} has been kicked for spam.";
    public static String hostmessage = "&e{player} has lost connection. &cNetwork Error";


    public CrashListener(Main instance) {
        plugin = instance;
    }
    
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
	    if (event.getPlayer().hasPermission("crashnotifier.join")) {

	        String message = null;
	    	int online = plugin.getServer().getOnlinePlayers().length;
	    	
	    	String m = joinmessage.replace("{player}", event.getPlayer().getName());
			message	= m.replace("{online}", ""+online);
			
			event.setJoinMessage(Main.parseColor(message));
	    }
    }
    
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event)
    {
	    if (event.getPlayer().hasPermission("crashnotifier.crash")) { crashPerm = true; }
	    if (event.getPlayer().hasPermission("crashnotifier.quit")) { quitPerm = true; }
	    
        String message = null;
    	int online = plugin.getServer().getOnlinePlayers().length - 1;
    	
    	if (fakeCrash) {
			String m = hostmessage.replace("{player}", event.getPlayer().getName());
			message	= m.replace("{online}", ""+online);
			plugin.getLogger().info(event.getPlayer().getName() + " crashed!");
    	}
    	else if (filterCheckHost && crashPerm) {
			String m = hostmessage.replace("{player}", event.getPlayer().getName());
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
    	
		if (filterCheckHost) {
			String m = hostmessage.replace("{player}", event.getPlayer().getName());
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