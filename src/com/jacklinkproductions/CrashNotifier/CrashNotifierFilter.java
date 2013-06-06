
package com.jacklinkproductions.CrashNotifier;

import java.util.logging.Filter;
import java.util.logging.LogRecord;

public class CrashNotifierFilter
  implements Filter
{
	public CrashNotifierFilter(Main paramDCReason)
	{
	}
	
	public boolean isLoggable(LogRecord arg0)
	{
		if (arg0.getMessage().toLowerCase().contains("disconnect")) {

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
			if (arg0.getMessage().toLowerCase().contains("endofstream")) {
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
}