package baymaxirc.core;


import baymaxirc.core.command.CommandManager;
import baymaxirc.core.event.MainEventBus;
import baymaxirc.core.module.ModuleManager;
import baymaxirc.core.ref.Reference;
import net.shadowfacts.shadowlib.log.Logger;
import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;

/**
 * The main class.
 * @author shadowfacts
 */
public class Baymax {

	public static MainEventBus eventBus = new MainEventBus();

	public static Logger logger = new Logger("BaymaxCore", true);

	public static void main(String[] args) throws Exception {
		CommandManager.init();

		ModuleManager.loadModules();

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
