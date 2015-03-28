package baymaxirc.core;


import baymaxirc.core.command.Manager;
import baymaxirc.core.ref.Reference;
import com.github.blamevic.irc.IRCClient;

import java.io.IOException;

/**
 * The main class.
 * @author shadowfacts
 */
public class Baymax {

	private static IRCClient client;


	public static void main(String[] args) throws IOException {
		Manager.init();

//		Configuration config = new Configuration.Builder()
//				.setName(Reference.name)
//				.setRealName(Reference.realName)
//				.setServerHostname("irc.esper.net")
//				.addAutoJoinChannel("#shadowfacts")
//				.addListener(new MainListener())
//				.setCapEnabled(true)
//				.buildConfiguration();
//
//		PircBotX bot = new PircBotX(config);
//		bot.startBot();

//		true indicates YAIL should use debug mode.
		client = new IRCClient(Reference.server, Reference.port, Reference.username, Reference.realName, true);
		client.connect();

		client.joinChannel(Reference.channel);
//		client.sendMessage("Hello World!", Reference.channel);
		client.sender.msg("Hello World!", "#shadowfacts");
		client.writeLine("PRIVMSG #shadowfacts :Hello World!");
	}
}
