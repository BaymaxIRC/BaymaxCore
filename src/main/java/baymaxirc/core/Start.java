package baymaxirc.core;


import baymaxirc.core.command.Manager;
import baymaxirc.core.ref.Reference;
import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;

/**
 * The main class.
 * @author shadowfacts
 */
public class Start {

	public static void main(String[] args) throws Exception {


		Manager.init();

		Configuration config = new Configuration.Builder()
				.setName(Reference.name)
				.setRealName(Reference.realName)
				.setServerHostname("irc.esper.net")
				.addAutoJoinChannel("#shadowfacts")
				.addListener(new MainListener())
				.buildConfiguration();

		PircBotX bot = new PircBotX(config);
		bot.startBot();

	}
}
