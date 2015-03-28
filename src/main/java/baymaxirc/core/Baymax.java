package baymaxirc.core;


import baymaxirc.core.command.CommandManager;
import baymaxirc.core.ref.Reference;
import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;

/**
 * The main class.
 * @author shadowfacts
 */
public class Baymax {

	public static void main(String[] args) throws Exception {


	public static void main(String[] args) throws Exception {
		CommandManager.init();

		Configuration config = new Configuration.Builder()
				.setName(Reference.name)
				.setRealName(Reference.realName)
				.setServerHostname("irc.esper.net")
				.addAutoJoinChannel("#shadowfacts")
				.addListener(new MainListener())
				.setCapEnabled(true)
				.buildConfiguration();

		PircBotX bot = new PircBotX(config);
		bot.startBot();

	}
}
