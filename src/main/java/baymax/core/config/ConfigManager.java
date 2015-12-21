package baymax.core.config;

import baymax.core.Baymax;
import baymax.core.module.Module;
import baymax.core.util.LogHelper;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.File;
import java.io.IOException;

/**
 * Used for managing module configs
 *
 * @author shadowfacts
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConfigManager {

	public static ConfigManager instance = new ConfigManager();

	private final LogHelper log = LogHelper.getLogger("Baymax|ConfigManager");

	/**
	 * Retrieves the correct config file for the given module.
	 * This {MODULE_NAME}.conf in the config directory.
	 * @param module The module
	 * @return The config object
	 */
	public Config getModuleConfig(Module module) {
		String name = module.getName();

		File configFile = new File(Baymax.instance.getConfigDir(), name + ".conf");
		if (!configFile.exists()) {
			try {
				configFile.createNewFile();
			} catch (IOException e) {
				log.error(e, "Could not load config file for %s", name);
			}
		}

		Config reference = ConfigFactory.load(name + "-reference.conf");
		return ConfigFactory.parseFile(configFile).withFallback(reference);
	}

}
