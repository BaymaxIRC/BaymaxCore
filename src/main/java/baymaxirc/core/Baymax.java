package baymaxirc.core;


import baymaxirc.core.command.*;
import baymaxirc.core.module.ModuleManager;
import com.google.common.eventbus.EventBus;
import net.shadowfacts.shadowlib.log.Logger;
import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;

/**
 * The main class.
 * @author shadowfacts
 */
public class Baymax {

	public static EventBus eventBus = new EventBus();

	public static CommandManager commandManager = new CommandManager();

	public static ModuleManager moduleManager = new ModuleManager();


	public static Logger logger = new Logger("BaymaxCore", true);

	public static void main(String[] args) throws Exception {
		moduleManager.loadModules();

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
