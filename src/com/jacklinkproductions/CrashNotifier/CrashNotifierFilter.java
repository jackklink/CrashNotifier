
package com.jacklinkproductions.CrashNotifier;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.message.Message;
import org.bukkit.ChatColor;

public class CrashNotifierFilter implements Filter
{
	private final Main plugin;
	
    public static String loginattempts = "false";

	public CrashNotifierFilter(Main instance) {
		plugin = instance;
	}
	
	public Result filter(LogEvent event)
	{
		String message = event.getMessage().toString().toLowerCase();
		String m = event.getMessage().toString();

		/* CrashListener.filterCheckKick = false;
		CrashListener.filterCheckSpam = false;
		CrashListener.filterCheckHost = false;
		CrashListener.filterCheckSoftware = false;
		CrashListener.filterCheckReadTimeout = false;
		CrashListener.filterCheckQuitting = false; */
		
		if (message.contains("gameprofile{id=") & loginattempts == "true")
		{
			String reason = "Unknown Reason";
			String username = m.replaceAll(".*name=\\'|\\'.*", "");
			
			if (message.contains("an existing connection was forcibly closed by the remote host")) {
				reason = "Network Error";
			}
			if (message.contains("an established connection was aborted by the software in your host machine")) {
				reason = "Client Error";
			}
			if (message.contains("internal exception: net.minecraft.util.io.netty.handler.timeout.readtimeoutexception")) {
				reason = "Read Timeout";
			}
			if (message.contains("took too long to log in")) {
					reason = "Login Timeout";
			}
			if (message.contains("disconnected")) {
				reason = "Disconnected";
			}
			
			if ((username != null) && (username.length() < 20))
				plugin.getServer().broadcastMessage(ChatColor.YELLOW + username + " attempted to login, but failed. " + ChatColor.RED + reason);
		}
		else if (message.contains("lost connection"))
		{
			if (message.contains("kick")) {
				CrashListener.filterCheckKick = true;
				return null;
			}
			if (message.contains("spam")) {
				CrashListener.filterCheckSpam = true;
				return null;
			}
			if (message.contains("an existing connection was forcibly closed by the remote host")) {
				CrashListener.filterCheckHost = true;
				return null;
			}
			if (message.contains("an established connection was aborted by the software in your host machine")) {
				CrashListener.filterCheckSoftware = true;
				return null;
			}
			if (message.contains("internal exception: net.minecraft.util.io.netty.handler.timeout.readtimeoutexception")) {
				CrashListener.filterCheckReadTimeout = true;
				return null;
			}
			if (message.contains("disconnected")) {
				CrashListener.filterCheckQuitting = true;
				return null;
			}
		}
        return null;
    }

	@Override
	public Result filter(org.apache.logging.log4j.core.Logger arg0, Level arg1,
			Marker arg2, String arg3, Object... arg4) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result filter(org.apache.logging.log4j.core.Logger arg0, Level arg1,
			Marker arg2, Object arg3, Throwable arg4) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result filter(org.apache.logging.log4j.core.Logger arg0, Level arg1,
			Marker arg2, Message arg3, Throwable arg4) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result getOnMatch() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result getOnMismatch() {
		// TODO Auto-generated method stub
		return null;
	}
}