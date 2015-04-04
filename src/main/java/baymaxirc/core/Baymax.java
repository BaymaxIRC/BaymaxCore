package baymaxirc.core;


import baymaxirc.core.command.CommandManager;
import baymaxirc.core.module.ModuleManager;
import com.google.common.eventbus.EventBus;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import net.shadowfacts.shadowlib.log.Logger;
import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;

import java.io.File;

/**
 * The main class.
 * @author shadowfacts
 */
public class Baymax {

	public static EventBus eventBus = new EventBus();

	public static CommandManager commandManager = new CommandManager();
	public static ModuleManager moduleManager = new ModuleManager();

	private static Config referenceConfig;
	public static Config config;

	public static Logger logger = new Logger("BaymaxCore", true);

	public static String commandStr;

	public static void main(String[] args) throws Exception {
		referenceConfig = ConfigFactory.load("reference.conf");
		config = ConfigFactory.parseFile(new File("config/core.conf")).withFallback(referenceConfig);

		String server = config.getString("baymax.irc.server");
		String nick = config.getString("baymax.irc.nickname");
		String realName = config.getString("baymax.irc.realName");
		String autoJoinChannel = config.getString("baymax.irc.autoJoinChannel");
		commandStr = config.getString("baymax.commands.commandChar");

		moduleManager.loadModules();

		Configuration config = new Configuration.Builder()
				.setName(nick)
				.setRealName(realName)
				.setServerHostname(server)
				.addAutoJoinChannel(autoJoinChannel)
				.addListener(new MainListener())
				.setCapEnabled(true)
				.buildConfiguration();

		PircBotX bot = new PircBotX(config);
		bot.startBot();

	}


}
