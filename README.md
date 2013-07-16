CrashNotifier
=============
Notify players when someone crashes! Set your own custom messages.<br />

Ever wonder why someone logged off right in the middle of your conversation with them??? Well this plugin will let your players know when these disconnections were actually crashes!
<br />
The plugin automatically detects the reason for each player's disconnection and then will change the "Player has left the game." message accordingly. Here are the types of disconnections the plugin currently supports:
<br /><br />

+ End Of Stream
+ Generic Reason
+ Overflow
+ Timeout
+ Spam
+ Kick (vanilla)
+ Fake Crashing

Fake out other players by pretending to crash. With /crash <type> you will be kicked, but the online players will think you crashed. /crash will default to End Of Stream.
Here are the crash commands:

+ /crash endofstream
+ /crash genericreason
+ /crash overflow
+ /crash timeout

Permissions
-----------

crashnotifier.*<br />
crashnotifier.join<br />
crashnotifier.quit<br />
crashnotifier.crash<br />
crashnotifier.fakecrash<br />
crashnotifier.reload<br />
