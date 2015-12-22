package baymax.core;

import baymax.core.command.CommandHelp;
import baymax.core.command.CommandListCommands;
import baymax.core.command.CommandManager;
import baymax.core.command.CommandRegistrar;
import baymax.core.command.alias.CommandAddAlias;
import baymax.core.command.alias.CommandRemoveAlias;
import baymax.core.event.BaymaxEventHandler;
import baymax.core.factoid.CommandRemoveFactoid;
import baymax.core.factoid.CommandSetFactoid;
import baymax.core.module.ModuleManager;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.shadowfacts.shadowlib.util.FileUtils;
import org.kitteh.irc.client.library.Client;
import org.kitteh.irc.client.library.ClientBuilder;

import java.io.File;
import java.io.IOException;

/**
 * Baymax!
 *
 * @author shadowfacts
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Baymax {

	/**
	 * The single instance of Baymax
	 */
	public static Baymax instance;

	/**
	 * The configuration directory used for Baymax and all modules.
	 * Defaults to {RUN_DIR}/config/
	 */
	@Getter
	private File configDir;
	/**
	 * The modules directory used to search for modules
	 * Defaults to {RUN_DIR}/modules/
	 */
	@Getter
	private File modulesDir;

	/**
	 * The Baymax configuration object
	 */
	@Getter
	private Config config;

	/**
	 * The KICL IRC client object
	 */
	@Getter
	private Client client;

	private void launch(OptionSet options) {
		if (options.has("help")) {
			printHelp();
			return;
		}

		configDir = new File((String)options.valueOf("configDir"));
		modulesDir = new File((String)options.valueOf("modulesDir"));
		FileUtils.checkCreateDirs(configDir, modulesDir);

		loadBaymaxConfig();

		ModuleManager.instance.loadModules();
//		TODO: provide limited access to client builder

		registerCommands(CommandManager.instance);
		ModuleManager.instance.registerCommands(CommandManager.instance);

		ClientBuilder clientBuilder = Client.builder()
				.nick(config.getString("baymax.irc.nick"))
				.realName(config.getString("baymax.irc.realName"))
				.serverHost(config.getString("baymax.irc.server.host"))
				.serverPort(config.getInt("baymax.irc.server.port"))
				.secure(config.getBoolean("baymax.irc.server.ssl"))
				.listenException(Throwable::printStackTrace);


		if (config.hasPath("baymax.irc.server.password")) {
			clientBuilder.serverPassword(config.getString("baymax.irc.server.password"));
		}

		client = clientBuilder.build();

		client.getEventManager().registerEventListener(BaymaxEventHandler.instance);

		client.addChannel(config.getStringList("baymax.irc.defaultChannels").toArray(new String[0]));

//		client.sendMessage("#shadowfacts", "Hello, World!");
	}

	private void registerCommands(CommandRegistrar registrar) {
		registrar.registerCommand(CommandListCommands.instance);
		registrar.registerCommand(CommandHelp.instance);

		registrar.registerCommand(CommandSetFactoid.instance);
		registrar.registerCommand(CommandRemoveFactoid.instance);

		registrar.registerCommand(CommandAddAlias.instance);
		registrar.registerCommand(CommandRemoveAlias.instance);
	}

	private void loadBaymaxConfig() {
		Config reference = ConfigFactory.load("reference.conf");

		File configFile = new File(configDir, "Baymax.conf");
		try {
			if (!configFile.exists()) configFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		config = ConfigFactory.parseFile(configFile).withFallback(reference);
	}

	private void printHelp() {
//		TODO: something better than this
		System.out.println("Options:");
		System.out.println("\t--configDir\t\tThe directory to used to store configuration files\tDeafult: ./config/"); // TODO: Localization?
		System.out.println("\t--modulesDir\tThe directory used to search for modules\t\t\tDefault: ./modules/");
	}

	public static void main(String[] args) {
		OptionParser parser = new OptionParser();
		parser.accepts("help").withOptionalArg();
		parser.accepts("configDir").withOptionalArg().defaultsTo("config/");
		parser.accepts("modulesDir").withOptionalArg().defaultsTo("modules/");

		OptionSet options = parser.parse(args);

		instance = new Baymax();
		instance.launch(options);
	}
}
