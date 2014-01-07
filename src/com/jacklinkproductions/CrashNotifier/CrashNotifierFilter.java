
package com.jacklinkproductions.CrashNotifier;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.message.Message;

public class CrashNotifierFilter implements Filter
{
	public CrashNotifierFilter(Main instance)
	{
		
	}
	
	public Result filter(LogEvent event)
	{
		String message = event.getMessage().toString().toLowerCase();
		
		if (message.contains("lost connection")) {

			CrashListener.filterCheckKick = false;
			CrashListener.filterCheckSpam = false;
			CrashListener.filterCheckHost = false;
			CrashListener.filterCheckQuitting = false;
	
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


/*
		if (arg0.getMessage().toLowerCase().contains("lost connection")) {

			CrashListener.filterCheckKick = false;
			CrashListener.filterCheckSpam = false;
			CrashListener.filterCheckGeneric = false;
			CrashListener.filterCheckStream = false;
			CrashListener.filterCheckOverflow = false;
			CrashListener.filterCheckTimeout = false;

			if (arg0.getMessage().toLowerCase().contains("kick")) {
				CrashListener.filterCheckKick = true;
				return true;
			}
			if (arg0.getMessage().toLowerCase().contains("spam")) {
				CrashListener.filterCheckSpam = true;
				return true;
			}
			if (arg0.getMessage().toLowerCase().contains("genericreason")) {
				CrashListener.filterCheckGeneric = true;
				return true;
			}
			if (arg0.getMessage().toLowerCase().contains("an existing connection was forcibly closed by the remote host")) {
				CrashListener.filterCheckStream = true;
				return true;
			}
			if (arg0.getMessage().toLowerCase().contains("overflow")) {
				CrashListener.filterCheckOverflow = true;
				return true;
			}
			if (arg0.getMessage().toLowerCase().contains("timeout")) {
				CrashListener.filterCheckTimeout = true;
				return true;
			}
			if (arg0.getMessage().toLowerCase().contains("quitting")) {
				CrashListener.filterCheckQuitting = true;
				return true;
			}
		}
		return true;
	}
} */